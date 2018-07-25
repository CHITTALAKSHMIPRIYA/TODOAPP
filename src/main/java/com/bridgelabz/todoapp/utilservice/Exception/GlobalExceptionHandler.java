
package com.bridgelabz.todoapp.utilservice.Exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.todoapp.userservice.usermodel.ResponseDto;



/**
 * @author LAKSHMI PRIYA
 * @since DATE:10-07-2018
 *        <p>
 *        <b>Global Exception Handler class.</b>
 *        </p>
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * @param exception
	 * @return ResponseEntity
	 *         <p>
	 * 		<b>If exceptions occur it display message and status</b>
	 *         </p>
	 * 
	 */
	@ExceptionHandler(TodoException.class)
	public ResponseEntity<ResponseDto> todoException(TodoException e) {
		logger.info("Error exists");
		ResponseDto response = new ResponseDto();
		response.setMessage(e.getMessage());
		response.setStatus(400);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	/**
	 * @param exception
	 * @param request
	 * @return ResponseEntity
	 * 
	 * 
	 * 
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseDto> exception(Exception exception, HttpServletRequest request) {
		logger.error("Error exists");
		ResponseDto response = new ResponseDto();
		response.setMessage(exception.getMessage());
		response.setStatus(400);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
