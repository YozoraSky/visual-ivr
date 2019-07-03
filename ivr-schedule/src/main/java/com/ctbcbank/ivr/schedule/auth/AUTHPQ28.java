package com.ctbcbank.ivr.schedule.auth;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.ctbcbank.ivr.schedule.properties.AuthProperties;

public class AUTHPQ28 extends Auth {
	private AuthProperties authProperties;

	public AUTHPQ28(NamedParameterJdbcTemplate namedParameterJdbcTemplate, Logger logger,
			AuthProperties authProperties) {
		super(namedParameterJdbcTemplate, logger);
		this.authProperties = authProperties;
	}

	@Override
	public String getFileName() {
		long time = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
		String fileName = "PQ28" + sdf.format(new Date(time));
//		String fileName = "PQ280621";
		return fileName;
	}

	@Override
	public void batchUpdate(BufferedReader bufferedReader) throws Exception {
		List<MapSqlParameterSource> batchArgsForInsert = new ArrayList<MapSqlParameterSource>();
		String line;
		int insertTimes = 0;
		while ((line = bufferedReader.readLine()) != null) {
			byte[] temp = line.getBytes("big5");
//			帳單日
			String stmt_date = new String(Arrays.copyOfRange(temp, 0, 7), "big5");
//			特店代號
			String merch_nbr = new String(Arrays.copyOfRange(temp, 7, 16), "big5");
//			機器編號
			String edc_nbr = new String(Arrays.copyOfRange(temp, 16, 24), "big5");
//			批號
			String batch_nbr = new String(Arrays.copyOfRange(temp, 24, 36), "big5");
//			期數
			String inst_time = new String(Arrays.copyOfRange(temp, 36, 38), "big5");
//			原因碼
			String reason_code = new String(Arrays.copyOfRange(temp, 38, 41), "big5");
//			請款金額
			String amt = new String(Arrays.copyOfRange(temp, 41, 50), "big5");
//			正負號(請款金額)
			String amt_s = new String(Arrays.copyOfRange(temp, 50, 51), "big5");
//			請款手續費
			String dis = new String(Arrays.copyOfRange(temp, 51, 58), "big5");
//			正負號(請款手續費)
			String diss = new String(Arrays.copyOfRange(temp, 58, 59), "big5");
//			調整金額
			String adj_amt = new String(Arrays.copyOfRange(temp, 59, 67), "big5");
//			正負號(調整金額)
			String adj_amt_s = new String(Arrays.copyOfRange(temp, 67, 68), "big5");
//			調整手續費
			String adj_dis = new String(Arrays.copyOfRange(temp, 68, 75), "big5");
//			正負號(調整手續費)
			String adj_dis_s = new String(Arrays.copyOfRange(temp, 75, 76), "big5");
//			請款上傳日期(YYYMMDD)
			String settle_date = new String(Arrays.copyOfRange(temp, 76, 83), "big5");
//			請款筆數
			String settle_cnt = new String(Arrays.copyOfRange(temp, 83, 86), "big5");
//			子店代號
			String ori_merch = new String(Arrays.copyOfRange(temp, 86, 95), "big5");
//			銀行別
			String bank_nbr = new String(Arrays.copyOfRange(temp, 95, 98), "big5");
//			交易類別
			String tran_type = new String(Arrays.copyOfRange(temp, 98, 99), "big5");
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("STMTDate", stmt_date);
			parameters.addValue("MerchNBR", merch_nbr);
			parameters.addValue("EDCNBR", edc_nbr);
			parameters.addValue("BatchNBR", batch_nbr);
			parameters.addValue("INSTTime", inst_time);
			parameters.addValue("ReasonCode", reason_code);
			parameters.addValue("AMT", amt);
			parameters.addValue("AMTS", amt_s);
			parameters.addValue("DIS", dis);
			parameters.addValue("DISS", diss);
			parameters.addValue("ADJAMT", adj_amt);
			parameters.addValue("ADJAMTS", adj_amt_s);
			parameters.addValue("ADJDIS", adj_dis);
			parameters.addValue("ADJDISS", adj_dis_s);
			parameters.addValue("SettleDate", settle_date);
			parameters.addValue("SettleCNT", settle_cnt);
			parameters.addValue("ORIMerch", ori_merch);
			parameters.addValue("BankNBR", bank_nbr);
			parameters.addValue("TranType", tran_type);
			parameters.addValue("BatchFile", getFileName());
			batchArgsForInsert.add(parameters);
			while (batchArgsForInsert.size() >= 5000) {
				namedParameterJdbcTemplate.batchUpdate(authProperties.getAuthpq28InsertSql(),batchArgsForInsert.toArray(new MapSqlParameterSource[0]));
				insertTimes += batchArgsForInsert.size();
				batchArgsForInsert.clear();
			}
		}
		if(!batchArgsForInsert.isEmpty()) {
			namedParameterJdbcTemplate.batchUpdate(authProperties.getAuthpq28InsertSql(), batchArgsForInsert.toArray(new MapSqlParameterSource[0]));
			insertTimes += batchArgsForInsert.size();
			batchArgsForInsert.clear();
		}
		logger.info("Authpq28 insert times : {}", insertTimes);
	}
}
