
package com.bridgelabz.todoapp.utilservice;

import java.util.Optional;

import org.springframework.lang.Nullable;


import com.bridgelabz.todoapp.userservice.usermodel.User;
import com.bridgelabz.todoapp.utilservice.Exception.TodoException;

/**
 * @author LAKSHMI PRIYA
 * @since DATE:10-07-2018
 *        <p>
 *        <b>RestPreConditions to check for null.</b>
 *        </p>
 */

public class RestPreCondition {
	 private RestPreCondition() {
	    }
	 public static <T> Optional<T> checkArgument(Optional<T> checkArgument,String errorMessage) throws TodoException {
	        if (!checkArgument.isPresent()) {
	            throw new TodoException(String.valueOf(errorMessage));
	        }
	        return checkArgument;
	    }
	 /**
	 * @param reference
	 * @param errorMessage
	 * @return
	 * @throws TodoException
	 */
	public static <T> T checkNotNull(T reference, @Nullable Object errorMessage) throws TodoException {
	        if (reference == null) {
	            throw new TodoException(String.valueOf(errorMessage));
	        }
	        return reference;
	    }
	/**
	 * @param checkArgument
	 * @param errorMessage
	 * @return
	 * @throws TodoException
	 */
	public static <T> boolean checkArgument(boolean checkArgument, @Nullable Object errorMessage) throws TodoException {
        if (!checkArgument) {
            throw new TodoException(String.valueOf(errorMessage));
        }
        return checkArgument;
    }
}
