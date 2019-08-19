package com.ctbcbank.ivr.repo.gateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ctbcbank.ivr.repo.gateway.detailLog.DetailLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/schedule")
@Api(tags = "當DetailLog批次自動執行失敗時，可透過此API手動重新執行")
public class DetailLogFailController {
	private Logger logger = LoggerFactory.getLogger("batch_Dlog");
	@Autowired
	private DetailLog detailLog;
	
	@ApiOperation(value = "DetailLog重新寫入DB", notes = "會先把輸入的日期的DetailLog從DB中刪除，再重新寫入DetailLog")
	@PostMapping("/re_execute_detailLog")
	public String detailLog(@ApiParam(name = "date", value = "須符合 yyyy-MM-dd格式", required = true) @RequestParam String date) {
		String dateFromat = "((^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._])(10|12|0?[13578])([-\\/\\._])"
				+ "(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._])"
				+ "(11|0?[469])([-\\/\\._])(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))"
				+ "([-\\/\\._])(0?2)([-\\/\\._])(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\\/\\._])"
				+ "(0?2)([-\\/\\._])(29)$)|(^([3579][26]00)([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([1][89][0][48])"
				+ "([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([2-9][0-9][0][48])([-\\/\\._])(0?2)([-\\/\\._])(29)$)"
				+ "|(^([1][89][2468][048])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([2-9][0-9][2468][048])([-\\/\\._])"
				+ "(0?2)([-\\/\\._])(29)$)|(^([1][89][13579][26])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|"
				+ "(^([2-9][0-9][13579][26])([-\\/\\._])(0?2)([-\\/\\._])(29)$))";
		if(date.matches(dateFromat)) {
			date = date.replaceAll("[-\\/\\._]", "-");
			Boolean deleteStatus = detailLog.delete(date);
			Boolean insertStatus = detailLog.insert(date);
			logger.info("#$$%%%%$$#");
			if(deleteStatus && insertStatus)
				return "DetailLog delete and insert success";
			else if(deleteStatus && !insertStatus)
				return "DetailLog insert fail... Please try it again!";
			else if(!deleteStatus && insertStatus)
				return "DetailLog delete fail... Please try it again!";
			else
				return "DetailLog delete and insert fail... Please try it again!";
		}
		else
			return "Date format is invaild!";
	}
}
