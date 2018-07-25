package com.bridgelabz.todoapp.utilservice.EmailSecurity;

import javax.mail.MessagingException;

import com.bridgelabz.todoapp.userservice.usermodel.MailDto;



public interface UserEmailSecurity {
	public void sendEmail(MailDto mailDTO) throws MessagingException;
}
