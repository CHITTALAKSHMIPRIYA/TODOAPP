package com.bridgelabz.todoapp.utilservice;

import java.util.Date;
import org.springframework.stereotype.Component;



import com.bridgelabz.todoapp.userservice.usermodel.LoginDto;
import com.bridgelabz.todoapp.userservice.usermodel.RegisterDto;
import com.bridgelabz.todoapp.utilservice.Exception.TodoException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author LAKSHMI PRIYA
 * @since DATE:10-07-2018
 *        <p>
 *        <b>Utility class to validate user details and to create token.</b>
 *        </p>
 */
@Component
public class Utility {
	private Utility() {

	}

	public static void isValidateAllFields(RegisterDto registerDTO) throws TodoException {
		if (!validateEmailAddress(registerDTO.getEmail())) {
			throw new TodoException("emailid not valid  Exception");
		} else if (!isValidUserName(registerDTO.getUserName())) {
			throw new TodoException("UserName should contain 8 digits long and atleast 1 lowercase letter and 1 digit");
		} else if (!validatePassword(registerDTO.getPassword())) {
			throw new TodoException(
					"password must have atleast 8 characters long and it should contain atleast 1 uppercase,lowercase letters,1 digit, i special character");
		} else if (!isValidMobileNumber(registerDTO.getMobileNumber())) {
			throw new TodoException("mobilenumber should contain 10 digits");
		} else if (!isPasswordMatch(registerDTO.getPassword(), registerDTO.getConfirmPassword())) {
			throw new TodoException("password and confirm password should match");
		}
	}

	/**
	 * @param emailId
	 * @return
	 */
	public static boolean validateEmailAddress(String emailId) {
		String pattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}";
		return emailId.matches(pattern);

	}

	/**
	 * @param password
	 * @return
	 */
	public static boolean validatePassword(String password) {
		String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
		return password.matches(pattern);
	}

	/**
	 * @param userName
	 * @return
	 */
	public static boolean isValidUserName(String userName) {
		String pattern = ("^[a-z0-9_-]{6,14}$");
		return userName.matches(pattern);

	}

	/**
	 * @param mobileNumber
	 * @return
	 */
	public static boolean isValidMobileNumber(String mobileNumber) {
		String pattern = ("\\d{10}");
		return mobileNumber.matches(pattern);
	}

	public static boolean isPasswordMatch(String password, String confirmPassword) {
		return password.equals(confirmPassword);
	}

	/**
	 * @param loginDTO
	 * @return
	 */
	public String createToken(LoginDto loginDTO) {
		final String key = "lakshmi";
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		String subject = loginDTO.getEmailId();
		Date date = new Date();

		JwtBuilder builder = Jwts.builder().setSubject(subject).setIssuedAt(date).signWith(signatureAlgorithm, key);
		return builder.compact();
	}

	/**
	 * @param id
	 * @return
	 */
	public static String createToken(String id) {
		final String KEY = "lakshmi";
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		Date startTime = new Date();
		Date expireTime = new Date(startTime.getTime() + (24 * 60 * 60 * 1000));

		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(startTime).setExpiration(expireTime)
				.signWith(signatureAlgorithm, KEY);
		return builder.compact();
	}

	/**
	 * @param jwt
	 * @return
	 */
	public Claims parseJwt(String jwt) {
		final String KEY = "lakshmi";
		return Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwt).getBody();
	}

	
}
