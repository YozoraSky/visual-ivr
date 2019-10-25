package com.ctbcbank.datasource.monitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ctbcbank.datasource.monitor.object.DataSourceStatus;
import com.ctbcbank.datasource.monitor.object.Radio;
import com.ctbcbank.datasource.monitor.schedule.SwitchDataSource;


@Controller
public class WebPageController {
	@Autowired
	private SwitchDataSource switchDataSource;
	
//	查看DataSourceStatus(取得當前的連線資訊)，展示給前端
	@GetMapping("/switchConnection")
    public String switchConnection(Model model) {
		model.addAttribute("connection", DataSourceStatus.getConnection().equals("main")?"Taipei":"Taichung");
		model.addAttribute("status", DataSourceStatus.getAutoOrNot()?"自動":"手動");
		model.addAttribute("radio", new Radio());
        return "switchConnection";
    }
	
//	根據使用者在前端的輸入，改變連線資訊，並展示給前端
	@PostMapping("/switchConnection")
    public String switchConnection(@ModelAttribute(value = "radio") Radio radio, Model model) {
		if(radio.getAutoOrNot().equals("auto")) {
			DataSourceStatus.setAutoOrNot(true);
			model.addAttribute("status", "自動");
		}
		else {
			switchDataSource.manual(radio.getConnection());
			DataSourceStatus.setAutoOrNot(false);
			model.addAttribute("status", "手動");
		}
		model.addAttribute("connection", DataSourceStatus.getConnection().equals("main")?"Taipei":"Taichung");
		return "switchConnection";
	}
}
