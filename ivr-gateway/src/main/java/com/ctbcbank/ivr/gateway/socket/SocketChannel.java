package com.ctbcbank.ivr.gateway.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ctbcbank.visual.ivr.encrypt.Log;
import com.ctbcbank.visual.ivr.esb.enumeraion.ProcessResultEnum;
import com.ctbcbank.visual.ivr.esb.model.ProcessResult;

@Component
public class SocketChannel {
	@Autowired
	private Log log;
	private static int changePort = 0;

	public static synchronized int getChangePort() {
		changePort++;
		if (changePort == 2)
			changePort = 0;
		return changePort;
	}

	public SocketOut sendAndReceive(SocketIn socketIn, String UUID, SocketProperties socketProperties, long ivrInTime) throws Exception {
		SocketOut socketOut = new SocketOut();
		Socket socket = null;
		BufferedOutputStream outputStream = null;
		BufferedInputStream inputStream = null;
		try {
			ProcessResult processResult = socketOut.getProcessResult();
			String result = StringUtils.EMPTY;
			String inputHexLen = Integer.toHexString(socketIn.getSocketData().length()).toUpperCase();
//		長度未滿4，前面補0
			while (inputHexLen.length() < 4)
				inputHexLen = "0" + inputHexLen;

//		此byte陣列為10進位的ascii編碼陣列
			byte[] iso8583MessageByte = buildMessage(socketIn.getSocketData().getBytes(StandardCharsets.US_ASCII),
					inputHexLen);
			int nowPort;
			switch (getChangePort()) {
			case 0:
				nowPort = socketProperties.getPort();
				break;
			case 1:
				nowPort = socketProperties.getPort2();
				break;
			default:
				nowPort = socketProperties.getPort();
				break;
			}
			System.out.println(nowPort);
			socket = connect(nowPort, socketProperties);
			socket.setSoTimeout(socketProperties.getSoTimeout());
			if (socket != null && socket.isConnected()) {
				outputStream = new BufferedOutputStream(socket.getOutputStream());
				inputStream = new BufferedInputStream(socket.getInputStream());
				if(System.currentTimeMillis()-ivrInTime>socketProperties.getSoTimeout())
					throw new Exception("API busy!");
				long socketInTime = System.currentTimeMillis();
				outputStream.write(iso8583MessageByte);
				outputStream.flush();
				byte[] dataByte = new byte[2048];
				int length = -1;
//			接收回傳值
				length = inputStream.read(dataByte);
				long socketOutTime = System.currentTimeMillis();
				log.writeTimeLog(socketIn.getConnID(), UUID, "IVRTANDEM", socketInTime, socketOutTime);
				if (length != -1) {
					processResult.setProcessResultEnum(ProcessResultEnum.QUERY_SUCCESS);
					String outputHexLen = StringUtils.EMPTY;
					for (int i = 0; i < 2; i++) {
						if (Integer.toHexString(dataByte[i] & 0xff).length() == 1)
							outputHexLen += "0" + Integer.toHexString(dataByte[i] & 0xff).toUpperCase();
						else
							outputHexLen += Integer.toHexString(dataByte[i] & 0xff).toUpperCase();
					}
					result = outputHexLen + new String(dataByte, 2, length, StandardCharsets.US_ASCII);
					ParseISO8583 parse = new ParseISO8583();
					String rspCode = parse.getRspCode(new String(dataByte, 14, length, StandardCharsets.US_ASCII),
							socketIn, log);
					if (rspCode.equals(StringUtils.EMPTY)) {
						processResult.setStatus(ProcessResultEnum.SYSTEM_ERROR.getStatus());
						processResult.setReturnMessage("ISOException error! see log to get detail error msg");
					}
					socketOut.setRspCode(rspCode);
					socketOut.setData(result.trim());
				} else {
					processResult.setProcessResultEnum(ProcessResultEnum.DATA_NOT_FOUND);
				}
				log.writeSocketInfo(socketIn, socket, inputHexLen + socketIn.getSocketData(), result.trim());
			}
		} finally {
			if(socket!=null) {
				try {
					socket.close();
				} catch (Exception e) {
					log.writeError(socketIn, e, Log.IVRSOCKETGATEWAY);
				}
			}
			if(inputStream!=null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					log.writeError(socketIn, e, Log.IVRSOCKETGATEWAY);
				}
			}
			if(outputStream!=null) {
				try {
					outputStream.close();
				} catch (Exception e) {
					log.writeError(socketIn, e, Log.IVRSOCKETGATEWAY);
				}
			}
		}
		return socketOut;
	}

	// 方法1:把isoMessage的長度和內容皆轉成為16進位字串，並串接起來，之後再一起轉成byte陣列
//    方法2:把isoMessage的長度轉成16進位字串，再轉成byte陣列(2 byte)，並和isoMessage的getBytes()組合成新陣列
	private byte[] buildMessage(byte[] isoMessage, String hexLen) {
		byte[] result = new byte[isoMessage.length + 2];
		System.arraycopy(hexToByte(hexLen), 0, result, 0, 2);
		System.arraycopy(isoMessage, 0, result, 2, isoMessage.length);
		return result;
	}

	private byte[] hexToByte(String isoMessage) {
		// 運算後的位元組長度:16進位數字字串長/2
		byte[] byteOut = new byte[isoMessage.length() / 2];
		for (int i = 0; i < isoMessage.length(); i += 2)
			// 每2位16進位數字轉換為一個10進位整數
			byteOut[i / 2] = (byte) Integer.parseInt(isoMessage.substring(i, i + 2), 16);
		return byteOut;
	}

	private Socket connect(int port ,SocketProperties socketProperties) throws Exception {
		Socket socket = new Socket();
		SocketAddress sokcetAddress = new InetSocketAddress(socketProperties.getIp(), port);
		boolean status = false;
		for (int i = 0; i < 3; i++) {
			try {
				socket.connect(sokcetAddress, socketProperties.getConnectTimeout());
				status = true;
				break;
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!status)
			throw new Exception("Connect fail");
		return socket;
	}
}
