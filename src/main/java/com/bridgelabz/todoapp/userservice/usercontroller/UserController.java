package com.bridgelabz.todoapp.userservice.usercontroller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.todoapp.userservice.usermodel.LoginDto;
import com.bridgelabz.todoapp.userservice.usermodel.RegisterDto;
import com.bridgelabz.todoapp.userservice.usermodel.ResetPasswordDto;
import com.bridgelabz.todoapp.userservice.usermodel.ResponseDto;
import com.bridgelabz.todoapp.userservice.userservice.IUserService;
import com.bridgelabz.todoapp.utilservice.Utility;
import com.bridgelabz.todoapp.utilservice.Exception.TodoException;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	public static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	@Value(value = "${spring.mail.properties.mail.transport.protocol}")
	private String host;
	@Autowired
	private IUserService userService;
	@Autowired
	private Utility utility;
	final String REQ_ID = "IN_USER";
	final String REs_ID = "OUT_USER";
	ResponseDto response = new ResponseDto();

	/**
	 * <p>
	 * <b>This method is used to register the user details</b>
	 * </p>
	 * 
	 * @param registerDTO
	 * @param request
	 * @return
	 * @throws TodoException
	 * @throws MessagingException
	 */
	@PostMapping("/register")
	public ResponseEntity<ResponseDto> registerUser(@RequestBody RegisterDto registerDTO, HttpServletRequest request) {
		LOG.info(host);
		LOG.info("Registring User ");
		LOG.info(REQ_ID + " " + registerDTO.getEmail());

		try {
			userService.registerUser(registerDTO, request.getRequestURI());
		} catch (MessagingException | TodoException e) {
			LOG.error("Registration unsuccessful");
			response.setMessage(e.getMessage());
			response.setStatus(404);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setMessage("Registered successfully: ");
		response.setStatus(200);
		LOG.info(REs_ID + " " + registerDTO.getEmail());
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	/**
	 * <p>
	 * <b>This method is used to login if the user is registered</b>
	 * </p>
	 * 
	 * @param loginDTO
	 * @param request
	 * @return
	 * @throws TodoException
	 * @throws MessagingException
	 */
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDTO, HttpServletRequest request) {
		LOG.info("login  User");
		LOG.info(REQ_ID + " " + loginDTO.getEmailId());
		String token = null;
		try {
			token = userService.loginUser(loginDTO, request.getRequestURI());

		} catch (MessagingException | TodoException e) {
			LOG.error("invalid user");
			response.setMessage(e.getMessage());
			response.setStatus(404);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

		}

		response.setMessage("User logged in successfully:" + token);
		response.setStatus(200);
		LOG.info(REs_ID + " " + loginDTO.getEmailId());
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	/**
	 * <p>
	 * <b>This method is used to activate the account once the user is
	 * registered</b>
	 * </p>
	 * 
	 * @param token
	 * @return
	 * @throws TodoException
	 */
	@GetMapping("/activation")
	public ResponseEntity<ResponseDto> activationUser(@RequestParam("token") String token) {
		LOG.info("check the user activation");
		try {
			userService.setActivationStatus(token);
		} catch (TodoException e) {
			response.setMessage("activation Unsuccessful");
			response.setStatus(404);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		response.setMessage("User activated successfully");
		response.setStatus(200);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	/**
	 * <p>
	 * <b> This method is used to send link if user forget the password</b>
	 * </p>
	 * 
	 * @param emailId
	 * @param request
	 * @return
	 * @throws TodoException
	 * @throws MessagingException
	 */
	@PostMapping("/forgotpassword")
	public ResponseEntity<ResponseDto> forgotPassword(@RequestBody String emailId, HttpServletRequest request) {
		LOG.info("Reset the password");
		LOG.info(REQ_ID + " " + emailId);
		try {
			userService.forgotPassword(emailId, request.getRequestURI());
		} catch (MessagingException | TodoException e) {
			response.setMessage("Not Found");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		response.setMessage("send the user mailid to reset password");
		response.setStatus(200);
		LOG.info(REs_ID + " " + emailId);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	/**
	 * <p>
	 * <b> This method is used to change password</b>
	 * </p>
	 * 
	 * @param resetPasswordDTO
	 * @param token
	 * @return
	 * @throws TodoException
	 */
	@PostMapping("/resetpassword")
	public ResponseEntity<ResponseDto> resetPassword(@RequestBody ResetPasswordDto resetPasswordDTO,
			@RequestParam("token") String token) {
		try {
			userService.resetPassword(resetPasswordDTO, token);
		} catch (TodoException e) {
			response.setMessage("Passord didn't reset");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		response.setMessage(" password changed successfully");
		LOG.info("Reset password done successfully");
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
