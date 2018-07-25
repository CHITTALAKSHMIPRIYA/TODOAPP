package com.bridgelabz.todoapp.userservice.userservice;

import java.util.Optional;

import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.todoapp.userservice.usermodel.LoginDto;
import com.bridgelabz.todoapp.userservice.usermodel.MailDto;
import com.bridgelabz.todoapp.userservice.usermodel.RegisterDto;
import com.bridgelabz.todoapp.userservice.usermodel.ResetPasswordDto;
import com.bridgelabz.todoapp.userservice.usermodel.User;
import com.bridgelabz.todoapp.userservice.userrepository.IUserDAO;
import com.bridgelabz.todoapp.utilservice.ModelMapperService;
import com.bridgelabz.todoapp.utilservice.RestPreCondition;

import com.bridgelabz.todoapp.utilservice.Utility;
import com.bridgelabz.todoapp.utilservice.EmailSecurity.UserEmailSecurity;
import com.bridgelabz.todoapp.utilservice.Exception.TodoException;
import com.bridgelabz.todoapp.utilservice.MessagingService.Producer;

import io.jsonwebtoken.Claims;

/**
 * @author LAKSHMI PRIYA
 * @since DATE:10-07-2018
 *        <p>
 *        <b>Service implementation class implements UserService interface .</b>
 *        </p>
 */

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDAO userDao;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserEmailSecurity userEmailSecurity;

	@Autowired
	private Producer produce;
	@Autowired
	private Utility utility;
	@Autowired
	ModelMapperService model;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bridgelabz.todoapp.userservice.userservice.IUserService#registerUser(com.
	 * bridgelabz.todoapp.userservice.userdto.RegisterDto, java.lang.String)
	 */
	@Override
	public void registerUser(RegisterDto registerDTO, String uri) throws MessagingException, TodoException {
		Optional<User> optionalUser = userDao.findByEmail(registerDTO.getEmail());
		RestPreCondition.checkArgument(userDao.findByEmail(registerDTO.getEmail()),
				"User already registered with this email id ");
		Utility.isValidateAllFields(registerDTO);
		//String jwtToken = utility.createToken(registerDTO.getEmail());
		User user = model.map(registerDTO, User.class);
        user.setPassword(encoder.encode(registerDTO.getPassword()));
        userDao.save(user);
		Optional<User> optionalUser1 = userDao.findByEmail(registerDTO.getEmail());
		sendEmailMessage(uri, optionalUser1);
		//return jwtToken;

	}

	@Override
	public String loginUser(LoginDto loginDTO, String uri) throws TodoException, MessagingException {
		Optional<User> optionalUser = userDao.findByEmail(loginDTO.getEmailId());
		if (optionalUser.get().isActivate()) {
			if (!optionalUser.isPresent()) {
				throw new TodoException("Please register!!User not found");
			}
			/*
			 * RestPreCondition.checkArgument(userDao.findByEmailId(loginDTO.getEmailId()),
			 * "Please register!!User not found");
			 */

			if (!encoder.matches(loginDTO.getPassword(), optionalUser.get().getPassword())) {
				throw new TodoException("Incorrect password exception");
			}
			String jwtToken = utility.createToken(loginDTO.getEmailId());
			// resp.addHeader("token",jwtToken);
			sendEmailMessage(uri, optionalUser);
			return jwtToken;
		}
		throw new TodoException("Please activate the user ");

	}

	@Override
	public void forgotPassword(String emailId, String uri) throws MessagingException, TodoException {
		System.out.println(emailId);
		Optional<User> optionalUser = userDao.findByEmail(emailId);
		System.out.println(optionalUser.get().getEmail());
		String token = Utility.createToken(optionalUser.get().getId());
		sendEmailMessage(uri, optionalUser);

	}

	@Override
	public void setActivationStatus(String token) throws TodoException {
		Claims claim = utility.parseJwt(token);

		Optional<User> optionalUser = userDao.findById(claim.getId());

		if (!optionalUser.isPresent()) {
			throw new TodoException("User not found Exception");
		}

		User user = optionalUser.get();
		user.setActivate(true);
		userDao.save(user);
	}

	@Override
	public void resetPassword(ResetPasswordDto resetPasswordDTO, String token) throws TodoException {
		Claims claim = utility.parseJwt(token);
		if (!Utility.validatePassword(resetPasswordDTO.getNewPassword())) {
			throw new TodoException("password not valid Exception");
		}

		if (!Utility.isPasswordMatch(resetPasswordDTO.getNewPassword(), resetPasswordDTO.getConfirmPassword())) {
			throw new TodoException("confirm password mismatch with new password Exception");
		}
		Optional<User> optionalUser = userDao.findById(claim.getId());
		if (!optionalUser.isPresent()) {
			throw new TodoException("User not registered, please register");
		}

		User user = optionalUser.get();
		user.setPassword(encoder.encode(resetPasswordDTO.getNewPassword()));
		userDao.save(user);
	}

	/**
	 * @param uri
	 * @param optionalUser
	 * @throws MessagingException
	 */
	public void sendEmailMessage(String uri, Optional<User> optionalUser) throws MessagingException {
		String token = Utility.createToken(optionalUser.get().getId());
		MailDto mailDTO = new MailDto();
		mailDTO.setId(optionalUser.get().getId());
		mailDTO.setToMailAddress(optionalUser.get().getEmail());
		mailDTO.setSubject(" Verification mail");
		mailDTO.setSalutation("Hi " + optionalUser.get().getFirstName());
		mailDTO.setBody(
				"Activate your account by clicking on this link: http://localhost:8080" + uri + "?token=" + token);
		mailDTO.setMailSign("\nThank you \n Lakshmi G \n Bridge Labz \n Mumbai");
		userEmailSecurity.sendEmail(mailDTO);
		produce.produceMessage(mailDTO);
	}

}