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

public class AUTHPQ27 extends Auth {
	private AuthProperties authProperties;

	public AUTHPQ27(NamedParameterJdbcTemplate namedParameterJdbcTemplate, Logger logger,
			AuthProperties authProperties) {
		super(namedParameterJdbcTemplate, logger);
		this.authProperties = authProperties;
	}

	@Override
	public String getFileName() {
		long time = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
		String fileName = "PQ27" + sdf.format(new Date(time));
//		String fileName = "PQ270621";
		return fileName;
	}

	@Override
	public void batchUpdate(BufferedReader bufferedReader) throws Exception {
		List<MapSqlParameterSource> batchArgsForInsert = new ArrayList<MapSqlParameterSource>();
		String line;
		int insertTimes = 0;
		while ((line = bufferedReader.readLine()) != null) {
			byte[] temp = line.getBytes("big5");
//			撥款日
			String grant_date = new String(Arrays.copyOfRange(temp, 0, 7), "big5");
//			帳單日/請款日期
			String stmt_date = new String(Arrays.copyOfRange(temp, 7, 14), "big5");
//			特店代號
			String merch_nbr = new String(Arrays.copyOfRange(temp, 14, 23), "big5");
//			撥款帳號
			String acct_nbr = new String(Arrays.copyOfRange(temp, 23, 53), "big5");
//			請款淨額
			String sale = new String(Arrays.copyOfRange(temp, 53, 66), "big5");
//			請款交易手續費
			String dis = new String(Arrays.copyOfRange(temp, 66, 77), "big5");
//			其他費用
			String fee = new String(Arrays.copyOfRange(temp, 77, 88), "big5");
//			調整淨額
			String adj_sale = new String(Arrays.copyOfRange(temp, 88, 97), "big5");
//			調整手續費
			String adj_dis = new String(Arrays.copyOfRange(temp, 97, 104), "big5");
//			撥付淨額
			String net_sale = new String(Arrays.copyOfRange(temp, 104, 115), "big5");
//			出帳單FLAG
			String hold_stmt = new String(Arrays.copyOfRange(temp, 115, 116), "big5");
//			商店類別
			String merch_type = new String(Arrays.copyOfRange(temp, 116, 118), "big5");
//			加盟店之母店代號
			String chain_store = new String(Arrays.copyOfRange(temp, 118, 127), "big5");
//			連鎖店之母店代號
			String chain_merch_nbr = new String(Arrays.copyOfRange(temp, 127, 136), "big5");
//			連鎖店LEVEL
			String chain_merch_lvl = new String(Arrays.copyOfRange(temp, 136, 137), "big5");
//			合併於母店撥款FLAG
			String chain_merch_flag = new String(Arrays.copyOfRange(temp, 137, 138), "big5");
//			撥款天數
			String mmer_risk2 = new String(Arrays.copyOfRange(temp, 138, 140), "big5");
//			發票金額
			String invoice_amt = new String(Arrays.copyOfRange(temp, 140, 150), "big5");
//			收據金額
			String receipt_amt = new String(Arrays.copyOfRange(temp, 150, 160), "big5");
//			有簽約承作他行卡
			String offus = new String(Arrays.copyOfRange(temp, 160, 161), "big5");
//			銀行別
			String bank_nbr = new String(Arrays.copyOfRange(temp, 161, 164), "big5");
//			銀行名稱
			String bank_name = new String(Arrays.copyOfRange(temp, 164, 194), "big5");
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("GrantDate", grant_date);
			parameters.addValue("STMTDate", stmt_date);
			parameters.addValue("MerchNBR", merch_nbr);
			parameters.addValue("ACCTNBR", acct_nbr);
			parameters.addValue("Sale", sale);
			parameters.addValue("DIS", dis);
			parameters.addValue("FEE", fee);
			parameters.addValue("ADJSale", adj_sale);
			parameters.addValue("ADJDIS", adj_dis);
			parameters.addValue("NETSale", net_sale);
			parameters.addValue("HoldSTMT", hold_stmt);
			parameters.addValue("MerchType", merch_type);
			parameters.addValue("ChainStore", chain_store);
			parameters.addValue("ChainMerchNBR", chain_merch_nbr);
			parameters.addValue("ChainMerchLVL", chain_merch_lvl);
			parameters.addValue("ChainMerchFlag", chain_merch_flag);
			parameters.addValue("MMERRisk2", mmer_risk2);
			parameters.addValue("InvoiceAMT", invoice_amt);
			parameters.addValue("ReceiptAMT", receipt_amt);
			parameters.addValue("OFFUS", offus);
			parameters.addValue("BankNBR", bank_nbr);
			parameters.addValue("BankName", bank_name);
			parameters.addValue("BatchFile", getFileName());
			batchArgsForInsert.add(parameters);
			while(batchArgsForInsert.size()>=5000) {
				namedParameterJdbcTemplate.batchUpdate(authProperties.getAuthpq27InsertSql(), batchArgsForInsert.toArray(new MapSqlParameterSource[0]));
				insertTimes += batchArgsForInsert.size();
				batchArgsForInsert.clear();
			}
		}
		if(!batchArgsForInsert.isEmpty()) {
			namedParameterJdbcTemplate.batchUpdate(authProperties.getAuthpq27InsertSql(), batchArgsForInsert.toArray(new MapSqlParameterSource[0]));
			insertTimes += batchArgsForInsert.size();
			batchArgsForInsert.clear();
		}
		logger.info("Authpq27 insert times : {}", insertTimes);
	}
}
