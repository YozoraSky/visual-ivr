package com.ctbcbank.ivr.schedule.batch;

import java.io.FileWriter;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ctbcbank.ivr.schedule.properties.EthanProperties;

//@Component
@EnableScheduling
public class Ethan {
	private Logger logger = LoggerFactory.getLogger("rate_ivr");
	@Autowired
	@Qualifier("ivrConfigJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private EthanProperties ethanProperties;
	
	@Scheduled(fixedRate=10000)
	public void run() {
		List<Map<String,Object>> IVRCustomerSegement = jdbcTemplate.queryForList(ethanProperties.getIVRCustomerSegementSql());
		writeTxt(IVRCustomerSegement,ethanProperties.getIVRCustomerSegementPath());
		List<Map<String,Object>> CTISystemSetting = jdbcTemplate.queryForList(ethanProperties.getCTISystemSettingSql());
		writeTxt(CTISystemSetting,ethanProperties.getCTISystemSettingPath());
		List<Map<String,Object>> IVRTransferPoint2 = jdbcTemplate.queryForList(ethanProperties.getIVRTransferPoint2Sql());
		writeTxt(IVRTransferPoint2,ethanProperties.getIVRTransferPoint2Path());
		List<Map<String,Object>> IVRTransferService2 = jdbcTemplate.queryForList(ethanProperties.getIVRTransferService2Sql());
		writeTxt(IVRTransferService2,ethanProperties.getIVRTransferService2Path());
	}
	
	private void writeTxt(List<Map<String,Object>> list,String path) {
		try {
			FileWriter fw = new FileWriter(path);
			StringBuilder s = new StringBuilder();
			list.forEach(map->{
				map.values().forEach(value -> {
						s.append(String.valueOf(value));
						s.append("\t");
				});
//				刪除多餘的"\t"字元
				s.deleteCharAt(s.length()-1);
//				添加換行字元 。 windows為"\r\n" linux為"\n"
				s.append("\n");
			});
//			刪掉多餘的"\n"
			s.deleteCharAt(s.length()-1);
			fw.write(s.toString());
			fw.close();
		} catch (Exception e) {
			logger.info(e.toString());
		}
	}
}
