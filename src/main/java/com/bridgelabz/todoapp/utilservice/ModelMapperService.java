package com.bridgelabz.todoapp.utilservice;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.todoapp.utilservice.Exception.TodoException;

/**
 * @author LAKSHMI PRIYA
 * @since DATE:10-07-2018
 *        <p>
 *        <b>A POJO class with the user details.</b>
 *        </p>
 */
@Component
public class ModelMapperService {
	@Autowired
	ModelMapper modelMapper;
	
	public <D> D map(Object source,Class<D> destinationType) throws TodoException
	{
		RestPreCondition.checkNotNull(source,"Null Pointer Exception:source cannot be null");
		RestPreCondition.checkNotNull(source,"Null Pointer Exception:destination cannot be null");
		return modelMapper.map(source, destinationType);
	}

}
