package com.capgemini.ec.gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PutCall {
	  @JsonProperty("username")
	  private String username = null;

	  @JsonProperty("password")
	  private String password = null;

	  @JsonProperty("CallData")
	  private UpdateDetails callData = null;
}
