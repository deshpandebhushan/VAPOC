package com.capgemini.ec.gateway.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capgemini.ec.gateway.model.CreateResponse;
import com.capgemini.ec.gateway.model.Response;
import com.netflix.hystrix.exception.HystrixTimeoutException;

@RestControllerAdvice
public class GatewayServiceExceptionHandler {
	
	/**
	 * Will throw Hystrix Timeout Exception.
	 * @param ex
	 * @return error response
	 */
	@ExceptionHandler(value = { HystrixTimeoutException.class })
	@ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    public ResponseEntity<Response> timeOut(Exception ex)
    {
		List<String> error = new ArrayList<String>();
		error.add(ex.getMessage());
		return new ResponseEntity<Response>(CreateResponse.createResponse("Something went wrong in process", error),
				HttpStatus.REQUEST_TIMEOUT);
    }
    
	/**
	 * Will throw generic Exception.
	 * @param ex
	 * @return error response
	 */
    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Response> unKnownException(Exception ex)
    {
    	List<String> error = new ArrayList<String>();
		error.add(ex.getMessage());
		return new ResponseEntity<Response>(CreateResponse.createResponse("Something went wrong in process", error),
				HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
