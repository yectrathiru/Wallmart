package com.yectra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yectra.model.Item;
import com.yectra.service.ProductService;

@RestController
@RequestMapping("/item")
public class Controller {

	@Autowired
	ProductService productService;

	@GetMapping("/getitembypage")
	public Page<Item> getAllItem(@RequestParam int pageNumber, @RequestParam int pageSize) {
		return productService.getAllItems(pageNumber, pageSize);
	}

	@GetMapping("/getitembyfilter")
	public Page<Item> filter(@RequestParam(name = "brandName", required = false) String brandName,
			@RequestParam(name = "stock", required = false) String stock,
			@RequestParam(name = "availableOnline", required = false) String availableOnline,
			@RequestParam(name = "category", required = false) String category, Pageable pageable) {
		return productService.filter(brandName, stock, availableOnline, category, pageable);
	}

}
