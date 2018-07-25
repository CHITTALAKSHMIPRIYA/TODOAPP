
package com.bridgelabz.todoapp.utilservice.MessagingService;

import javax.mail.MessagingException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.todoapp.userservice.usermodel.MailDto;
import com.bridgelabz.todoapp.utilservice.EmailSecurity.UserEmailSecurity;




/**
 * @author LAKSHMI PRIYA
 * @since DATE:10-07-2018
 *        <p>
 *        <b>Consumer uses @RabbitListener to receive messages.</b>
 *        </p>
 */
@Component
public class Consumer {
	@Autowired
	private UserEmailSecurity userEmailSecurity;

	@RabbitListener(queues = "${lakshmi.rabbitmq.queue}", containerFactory = "containerFactory")
	public void recievedMessage(MailDto mailDTO) throws MessagingException {
		System.out.println(mailDTO);
		userEmailSecurity.sendEmail(mailDTO);
	}
}