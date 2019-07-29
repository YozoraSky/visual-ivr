package com.ctbcbank.mq.gateway.mqheadler;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ctbcbank.mq.gateway.object.MqIn;

public class MqCombinedMessage {
	private int header;
	private int data;
	public MqCombinedMessage(int header, int data) {
		this.header = header;
		this.data = data;
	}
	public String CombinedMessage(MqIn mqin) throws Exception {
		//Header
		StringBuffer msg = new StringBuffer();
		SimpleDateFormat sdFormatdata = new SimpleDateFormat("yyyymmdd");
		SimpleDateFormat sdFormattime = new SimpleDateFormat("hhmmss");
		Date date = new Date();
		msg.append(FormatSpace(mqin.getTx_date(),8));//TX-DATE 8
		msg.append(mqin.getType());//TYPE 3
		//TX-NO：系統串接的Key,方便查問題, 不能超過14字元，例：YYYYMMDDHHMMSS
		msg.append(FormatSpace(mqin.getTx_date()+mqin.getTx_time(),14));//TX-NO 14
		msg.append(FormatSpace(mqin.getTx_time(),6));//TX-TIME 6 
		//當下交易日期時間		
		msg.append(sdFormatdata.format(date));//SND-DATE 8
		msg.append(sdFormattime.format(date));//SND-TIME 6
		msg.append(FormatSpace("111111",6));//TYPE2 6
		msg.append(FormatSpace("",header-51));//FILLER 9
		if(msg.length()==header) {//檢查header length
			//Content
			//ID 10
			if(mqin.getId().length()==9)
				msg.append(FormatSpace(mqin.getId(),10));
			else if(mqin.getId().length()==10)
					msg.append(mqin.getId());
				 else if(mqin.getId().length()==11)
						 if(mqin.getId().indexOf("9")==0)
						 	msg.append(mqin.getId().substring(1,mqin.getId().length()));
						 else msg.append(mqin.getId().substring(0,mqin.getId().length()-1));
					  else msg.append(FormatSpace(mqin.getId(),10));
//					      else return "ID no 9,10,11 length error:"+mqin.getId().length();
			//0936 957702    +886912 345678
			if(mqin.getMobilno().matches("([\\)]?)[+]?(886)?([\\)]?)([-_－—\\s]?)"
					+ "([\\(]?)[0]?[1-9]{1}"
					+ "([-_－—\\s\\)]?)[0-9]{2}"
					+ "[-_－—]?[0-9]{3}[-_－—]?[0-9]{3}$"))
				msg.append(FormatSpace(mqin.getMobilno(),14));//MOBILNO 14
			else throw new Exception("Mobilno no matches (error)");
//			if(!mqin.getName().isEmpty())
			msg.append(FormatSpace(mqin.getName(),12));//NAME 12
//			else return "Name no exist error";
			msg.append(FormatSpace("",data-36));//FILLER 104
			if(msg.length()==header+data)//檢查data length
				return msg.toString();
			else 
				throw new Exception("data length error:"+(msg.length()-header));
		}
		else throw new Exception ("header length error :"+(msg.length()-data));
	}
	public String FormatSpace(String data, int number) {
//		if(data==null||data==StringUtils.EMPTY)
//			data="";
		if(data.length()==number)
			return data;
		else {
			number = number - data.length();
			for(int index=1;index<=number;index++)
				data=data + " ";
		}
		return data;
	}
}
