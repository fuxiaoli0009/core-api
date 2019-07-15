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
	
	private Logger logger = LoggerFactory.getLogger(RabbitMqController.class);
	
	@Autowired
	private RabbitMqService rabbitMqService;
	
	@PostMapping(value="getAndSendMessage")
	public void getAndSendMessage(String args) {
		String commandName = "WACAI_OREDER_STATE_NOTIFY";
		String applyId = "4617931";
		//String args = {"applyId":4617931,"methodType":"STATE_14000","project":"xloan-app-api"}
		rabbitMqService.sendMessage(commandName, applyId, args);
	}
}
