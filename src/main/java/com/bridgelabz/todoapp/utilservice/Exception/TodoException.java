package com.bridgelabz.todoapp.utilservice.Exception;

/**
 * @author LAKSHMI PRIYA
 * @since DATE:10-07-2018
 *        <p>
 *        <b>TodoException extending exception.</b>
 *        </p>
 */
@SuppressWarnings("serial")
public class TodoException extends Exception 
{
	public TodoException(String message) {
		super(message);
	}
}
