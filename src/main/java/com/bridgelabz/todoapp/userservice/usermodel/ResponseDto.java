package com.bridgelabz.todoapp.userservice.usermodel;

import java.io.Serializable;

/**
 * @author LAKSHMI PRIYA
 * @since DATE:10-07-2018
 *        <p>
 *        <b>Response dto to set status .</b>
 *        </p>
 */
@SuppressWarnings("serial")
public class ResponseDto implements Serializable {
private int status;
private String message;
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
@Override
public String toString() {
	return "ResponseDto [status=" + status + ", message=" + message + "]";
}
}