package com.core.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.core.api.service.RabbitMqService;

@RestController
@RequestMapping(value="rabbit")
public class RabbitMqController {
	
	private static final Logger logger = LoggerFactory.getLogger(RabbitMqController.class);
	
	@Autowired
	private RabbitMqService rabbitMqService;
	
	@PostMapping(value="getAndSendMessage")
	public void getAndSendMessage(String message) {
		String applyId = "4617931";
		rabbitMqService.sendMessage(message);
	}
}
