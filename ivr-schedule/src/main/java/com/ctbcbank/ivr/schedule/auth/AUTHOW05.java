package com.ctbcbank.ivr.schedule.auth;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.ctbcbank.ivr.schedule.properties.AuthProperties;

public class AUTHOW05 extends Auth {
	private AuthProperties authProperties;
	
	public AUTHOW05(NamedParameterJdbcTemplate namedParameterJdbcTemplate, Logger logger, AuthProperties authProperties) {
		super(namedParameterJdbcTemplate, logger);
		this.authProperties = authProperties;
	}
	
	@Override
	public String getFileName() {
		long time = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
		String fileName = "OW05" + sdf.format(new Date(time));
//		String fileName = "OW050621";
		return fileName;
	}
	
	@Override
	public void batchUpdate(BufferedReader bufferedReader) throws Exception {
		List<MapSqlParameterSource> batchArgsForUpdate = new ArrayList<MapSqlParameterSource>();
		List<MapSqlParameterSource> batchArgsForInsert = new ArrayList<MapSqlParameterSource>();
		Map<String, Object> params = new HashMap<String, Object>();
		String line;
		int insertCount = 0;
		int updateCount = 0;
		while((line = bufferedReader.readLine())!=null) {
			byte[] temp = line.getBytes("big5");
//			商店類別代碼
			String rec_type = new String(Arrays.copyOfRange(temp, 0, 1),"big5");
//			merch_org + merch_nbr = 商店代碼
			String merch_org = new String(Arrays.copyOfRange(temp, 1, 4),"big5");
			String merch_nbr = new String(Arrays.copyOfRange(temp, 4, 14),"big5");
//			商店名稱
			String business_name = new String(Arrays.copyOfRange(temp, 14, 54),"big5");
//			POS機型1
			String pos_type_1 = new String(Arrays.copyOfRange(temp, 54, 59),"big5");
//			POS機型2
			String pos_type_2 = new String(Arrays.copyOfRange(temp, 59, 64),"big5");
//			EDC機型1
			String edc_type_1 = new String(Arrays.copyOfRange(temp, 64, 69),"big5");
//			EDC機型2
			String edc_type_2 = new String(Arrays.copyOfRange(temp, 69, 74),"big5");
//			統編末四碼
			String corp_no_4 = new String(Arrays.copyOfRange(temp, 75, 78),"big5");
//			商店狀態(是否解約)
			String status = new String(Arrays.copyOfRange(temp, 78, 79),"big5");
//			聯絡電話-區碼
			String contact_phone_area = new String(Arrays.copyOfRange(temp, 79, 82),"big5");
//			聯絡電話
			String contact_phone_no = new String(Arrays.copyOfRange(temp, 82, 90),"big5");
//			聯絡電話-分機
			String contact_phone_ext = new String(Arrays.copyOfRange(temp, 90, 95),"big5");
//			trvl_flag
			String trvl_flag = new String(Arrays.copyOfRange(temp, 95, 96),"big5");
//			主機異動日期
			String maint_date = new String(Arrays.copyOfRange(temp, 96, 104),"big5");
//			String chechSql = authProperties.getAuthow05CheckSql().replace("@MerchantNo", merch_org + merch_nbr);
			params.put("MerchantNo", merch_org + merch_nbr);
			List<Map<String, Object>> list = namedParameterJdbcTemplate.queryForList(authProperties.getAuthow05CheckSql(), params);
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("StoreName", business_name);
			parameters.addValue("StoreNo", corp_no_4);
			parameters.addValue("Status", status);
			parameters.addValue("EDC1", edc_type_1);
			parameters.addValue("EDC2", edc_type_2);
			parameters.addValue("POS1", pos_type_1);
			parameters.addValue("POS2", pos_type_2);
			parameters.addValue("ContactPhoneArea", contact_phone_area);
			parameters.addValue("ContactPhone", contact_phone_no);
			parameters.addValue("ContactPhoneExt", contact_phone_ext);
			parameters.addValue("HostModifyDate", maint_date);
			parameters.addValue("TRVLFlag", trvl_flag);
			parameters.addValue("MerchantNo", merch_org + merch_nbr);
			if(!list.isEmpty()) {
				batchArgsForUpdate.add(parameters);
				while(batchArgsForUpdate.size()>=5000) {
					namedParameterJdbcTemplate.batchUpdate(authProperties.getAuthow05UpdateSql(), batchArgsForUpdate.toArray(new MapSqlParameterSource[0]));
					updateCount += batchArgsForUpdate.size();
					batchArgsForUpdate.clear();
				}
			}
			else {
				batchArgsForInsert.add(parameters);
				while(batchArgsForInsert.size()>=5000) {
					namedParameterJdbcTemplate.batchUpdate(authProperties.getAuthow05InsertSql(), batchArgsForInsert.toArray(new MapSqlParameterSource[0]));
					insertCount += batchArgsForInsert.size();
					batchArgsForInsert.clear();
				}
			}
		}
		if(!batchArgsForUpdate.isEmpty()) {
			namedParameterJdbcTemplate.batchUpdate(authProperties.getAuthow05UpdateSql(), batchArgsForUpdate.toArray(new MapSqlParameterSource[0]));
			updateCount += batchArgsForUpdate.size();
			batchArgsForUpdate.clear();
		}
		if(!batchArgsForInsert.isEmpty()) {
			namedParameterJdbcTemplate.batchUpdate(authProperties.getAuthow05InsertSql(), batchArgsForInsert.toArray(new MapSqlParameterSource[0]));
			insertCount += batchArgsForInsert.size();
			batchArgsForInsert.clear();
		}
		logger.info("Authow05 update count : {}", updateCount);
		logger.info("Authow05 insert count : {}", insertCount);
		params.clear();
		params.put("FilePath", getFileName());
		params.put("SuccessCount", updateCount + insertCount);
		params.put("FailCount", "0");
		namedParameterJdbcTemplate.update(authProperties.getAuthow05BatchSql(), params);
	}
}
