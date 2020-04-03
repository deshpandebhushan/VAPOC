package com.capgemini.ec.gateway.model;

import java.util.List;

public class CreateResponse {
	public static Response createResponse(String message, List<String> errors) {
		Response response = new Response();
		response.setMessage(message);
		ResponseErrors responseError;
		for (String error : errors) {
			responseError = new ResponseErrors();
			responseError.setError(error);
			response.addErrorsItem(responseError);
		}
		return response;
	}
}
