package com.capgemini.ec.gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Response
 */
public class Response {
	@JsonProperty("message")
	private String message = null;

	@JsonProperty("errors")
	private List<ResponseErrors> errors = null;

	/**
	 * Get message
	 * 
	 * @return message
	 **/
	@JsonProperty("message")
	@ApiModelProperty(required = true, value = "")
	@NotNull
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message.toString();
	}

	public Response errors(List<ResponseErrors> errors) {
		this.errors = errors;
		return this;
	}

	public Response addErrorsItem(ResponseErrors errorsItem) {
		if (this.errors == null) {
			this.errors = new ArrayList<ResponseErrors>();
		}
		this.errors.add(errorsItem);
		return this;
	}

	/**
	 * Get errors
	 * 
	 * @return errors
	 **/
	@JsonProperty("errors")
	@ApiModelProperty(value = "")
	public List<ResponseErrors> getErrors() {
		return errors;
	}

	public void setErrors(List<ResponseErrors> errors) {
		this.errors = errors;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Response response = (Response) o;
		return Objects.equals(this.message, response.message) && Objects.equals(this.errors, response.errors);
	}

	@Override
	public int hashCode() {
		return Objects.hash(message, errors);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Response {\n");

		sb.append("    message: ").append(toIndentedString(message)).append("\n");
		sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
