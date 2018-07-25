package com.bridgelabz.todoapp.userservice.usermodel;

import java.io.Serializable;

/**
 * @author LAKSHMI PRIYA
 * @since DATE:10-07-2018
 *        <p>
 *        <b>MailDto class .</b>
 *        </p>
 */
@SuppressWarnings("serial")
public class MailDto implements Serializable{
	private String id;
	private String toMailAddress;
	private String subject;
	private String salutation;
	private String body;
	private String mailSign;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToMailAddress() {
		return toMailAddress;
	}

	public void setToMailAddress(String toMailAddress) {
		this.toMailAddress = toMailAddress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getMailSign() {
		return mailSign;
	}

	public void setMailSign(String mailSign) {
		this.mailSign = mailSign;
	}
}
