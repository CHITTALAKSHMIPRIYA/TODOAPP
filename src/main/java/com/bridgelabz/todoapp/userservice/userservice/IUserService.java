package com.bridgelabz.todoapp.userservice.userservice;

import javax.mail.MessagingException;
import org.springframework.stereotype.Service;
import com.bridgelabz.todoapp.userservice.usermodel.LoginDto;
import com.bridgelabz.todoapp.userservice.usermodel.RegisterDto;
import com.bridgelabz.todoapp.userservice.usermodel.ResetPasswordDto;
import com.bridgelabz.todoapp.utilservice.Exception.TodoException;

/**
 * @author LAKSHMI PRIYA
 * @since DATE:10-07-2018
 *        <p>
 *        <b>This interface contains methods for registration,login,forgot
 *        password,Reset password.</b>
 *        </p>
 */

@Service
public interface IUserService {
	/**
	 * @param registerDTO
	 * @param uri
	 * @return
	 * @throws MessagingException
	 * @throws TodoException
	 *             This method is used to check if the User is present or not.if the
	 *             user is present it throws an exception else it will register the
	 *             user
	 */
	void registerUser(RegisterDto registerDTO, String uri) throws MessagingException, TodoException;

	/**
	 * @param token
	 * @throws TodoException
	 *             <p>
	 *             <b>This method is used to set the activation status if the user
	 *             is present
	 */
	public void setActivationStatus(String token) throws TodoException;

	/**
	 * @param resetPasswordDTO
	 * @param token
	 * @throws TodoException
	 *             <p>
	 *             <b>This method is used to change the password if the user forget
	 *             the password</b>
	 *             </p>
	 */
	public void resetPassword(ResetPasswordDto resetPasswordDTO, String token) throws TodoException;

	/**
	 * @param emailId
	 * @param uri
	 * @throws MessagingException
	 *             <p>
	 *             <b>Token will be generated to a mail to change password</b>
	 *             </p>
	 * @throws TodoException
	 */
	public void forgotPassword(String emailId, String uri) throws MessagingException, TodoException;

	/**
	 * @param loginDTO
	 * @param uri
	 * @param resp
	 * @return
	 * @throws TodoException
	 * @throws MessagingException
	 */
	String loginUser(LoginDto loginDTO, String uri) throws TodoException, MessagingException;
}
