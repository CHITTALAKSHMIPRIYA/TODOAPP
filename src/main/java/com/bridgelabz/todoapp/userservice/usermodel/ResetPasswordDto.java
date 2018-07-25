package com.bridgelabz.todoapp.userservice.usermodel;

import java.io.Serializable;

/**
 * @author LAKSHMI PRIYA
 * @since DATE:10-07-2018
 *        <p>
 *        <b>ResetPasswordDto to change password.</b>
 *        </p>
 */
@SuppressWarnings("serial")
public class ResetPasswordDto implements Serializable {
	private String newPassword;
	private String confirmPassword;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
