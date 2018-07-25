package com.bridgelabz.todoapp.utilservice.EmailSecurity;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.bridgelabz.todoapp.userservice.usermodel.MailDto;



@Component
public class UserEmailSecurityImpl implements UserEmailSecurity {
	@Autowired
	private JavaMailSender emailSender;
	
	@Override
	public void sendEmail(MailDto mailDTO) throws MessagingException {
		MimeMessage mimeMessage = emailSender.createMimeMessage();
		MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
		
		message.setTo(mailDTO.getToMailAddress());
		message.setSubject(mailDTO.getSubject()+"\n"+"\n"+mailDTO.getSubject());
		message.setText(mailDTO.getBody() + "\n" + "\n" + mailDTO.getBody());
		emailSender.send(mimeMessage);
	

	}

}
