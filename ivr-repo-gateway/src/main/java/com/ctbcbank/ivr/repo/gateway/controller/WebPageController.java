package com.ctbcbank.ivr.repo.gateway.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ctbcbank.ivr.repo.gateway.monitor.DynamicDataSource;
import com.ctbcbank.ivr.repo.gateway.webpage.bean.Product;
import com.ctbcbank.ivr.repo.gateway.webpage.bean.SMSParams;

@Controller
public class WebPageController {
	@Autowired
	private DynamicDataSource dynamicDataSource;
	
	@RequestMapping("/product")
	public String test(Model model) {
		Product prod1 = new Product();
		prod1.setName("test1");
		prod1.setPrice("123");
		Product prod2 = new Product();
		prod2.setName("test2");
		prod2.setPrice("543");
		Product prod3 = new Product();
		prod3.setName("test2");
		prod3.setPrice("543");
		ArrayList<Product> list = new ArrayList<Product>();
		list.add(prod1);
		list.add(prod2);
		list.add(prod3);
		model.addAttribute("allProducts", list);
		model.addAttribute("name", "John");
		return "product";
	}
    
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/mqTable")
    public String mqTable(Model model) {
        model.addAttribute("smsParams", new SMSParams());
        return "mqTable";
    }
	
	@PostMapping("/mqTable")
    public String mqTable(@ModelAttribute(value = "smsParams") SMSParams smsParams, Model model) {
		String sql = "select * from MQSetting where SMS_IdName=:SMS_IdName";
		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("SMS_Id", smsParams.getId());
		paramMap.put("SMS_IdName", smsParams.getIdName());
		List<Map<String, Object>> list = dynamicDataSource.getConfigNamedParameterJdbcTemplate().queryForList(sql, paramMap);
		model.addAttribute("list", list);
        return "mqTable";
    }
}