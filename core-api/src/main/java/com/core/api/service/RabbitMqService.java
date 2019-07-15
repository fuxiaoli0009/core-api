package com.core.api.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import com.core.api.po.ApiNotifyMsg;

@Component
public class RabbitMqService {
	
	private Logger logger = LoggerFactory.getLogger(RabbitMqService.class);
	
	public void sendMessage(String commandName, String applyId, String args) {
        try {
        	
        	ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
            AmqpTemplate rabbitMQTemplate = (AmqpTemplate) context.getBean("amqpTemplate");
        	
            ApiNotifyMsg notifyMsg = new ApiNotifyMsg(UUID.randomUUID().toString(), commandName, applyId, args);
            logger.info("ÂáÜÂ§áÂèëÈ?ÅMQ");
            rabbitMQTemplate.convertAndSend("rabbit.test.exchange", "rabbit.test.key", notifyMsg.toString());
            logger.info("ÂèëÈ?ÅÊàêÂä?");
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

}
