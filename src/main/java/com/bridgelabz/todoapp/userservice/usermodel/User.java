package com.bridgelabz.todoapp.userservice.usermodel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author LAKSHMI PRIYA
 * @since DATE:10-07-2018
 *        <p>
 *        <b>A POJO class with the user details.</b>
 *        </p>
 */

@Document(collection = "User")
public class User {
	@Id
	private String id;
	private String email;
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	private String mobileNumber;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public boolean isActivate() {
		return isActivate;
	}

	public void setActivate(boolean isActivate) {
		this.isActivate = isActivate;
	}

	private boolean isActivate;

	@Override
	public String toString() {
		return "User [id=" + id + ", emailId=" + email + ", userName=" + userName + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", password=" + password + ", mobileNumber=" + mobileNumber
				+ ", isActivate=" + isActivate + "]";
	}
}