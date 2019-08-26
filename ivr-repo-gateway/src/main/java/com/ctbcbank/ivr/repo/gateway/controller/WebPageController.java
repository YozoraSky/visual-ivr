package com.ctbcbank.ivr.repo.gateway.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ctbcbank.ivr.repo.gateway.properties.Product;

@Controller
public class WebPageController {
	@RequestMapping("/greeting")
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
//		Product[] allProd = new Product[2];
//		allProd[0] = prod1;
//		allProd[1] = prod2;
		ArrayList<Product> list = new ArrayList<Product>();
		list.add(prod1);
		list.add(prod2);
		list.add(prod3);
		model.addAttribute("allProducts", list);
		model.addAttribute("name", "John");
		return "greeting";
	}
}