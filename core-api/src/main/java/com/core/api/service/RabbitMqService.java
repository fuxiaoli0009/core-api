package com.core.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqService implements InitializingBean {
	
	private Logger logger = LoggerFactory.getLogger(RabbitMqService.class);
	
	private ApplicationContext context;
	
	public void sendMessage(String message) {
        try {
            AmqpTemplate rabbitMQTemplate = (AmqpTemplate) context.getBean("amqpTemplate");
            rabbitMQTemplate.convertAndSend("init.concerned.exchange", "init.concerned.key", message);
            logger.info("{},消息成功", message);
        } catch (Exception e) {
        	logger.error("{},消息发送异常", e);
        }
    }

	@Override
	public void afterPropertiesSet() throws Exception {
		context = new ClassPathXmlApplicationContext("spring-config.xml");
	}

}
