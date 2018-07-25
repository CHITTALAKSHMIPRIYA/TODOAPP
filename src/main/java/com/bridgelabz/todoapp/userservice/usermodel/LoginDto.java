package com.bridgelabz.todoapp.userservice.usermodel;

import java.io.Serializable;

/**
 * @author LAKSHMI PRIYA
 * @since DATE:10-07-2018
 *        <p>
 *        <b>Login DTO class to login the user.</b>
 *        </p>
 */
@SuppressWarnings("serial")
public class LoginDto implements Serializable {
	private String emailId;
	private String password;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginDto [emailId=" + emailId + ", password=" + password + "]";
	}
}