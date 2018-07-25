
package com.bridgelabz.todoapp.utilservice.MessagingService;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



/**
 * @author LAKSHMI PRIYA
 * @since DATE:10-07-2018
 *        <p>
 *        <b>Producer uses amqp template to send messages.</b>
 *        </p>
 */
@Component
public class Producer {
	@Autowired
	private AmqpTemplate amqpTemplate;

	@Value("${lakshmi.rabbitmq.exchange}")
	private String exchange;

	@Value("${lakshmi.rabbitmq.routingkey}")
	private String routingKey;

	public void produceMessage(com.bridgelabz.todoapp.userservice.usermodel.MailDto mailDTO) {
		amqpTemplate.convertAndSend(exchange, routingKey, mailDTO);
		System.out.println("Send messasge = " + mailDTO);
	}
}
