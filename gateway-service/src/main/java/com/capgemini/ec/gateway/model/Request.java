package com.capgemini.ec.gateway.model;

import java.util.Map;

public class Request {
	public Map<String, String> sysParmQuery;
	public Map<String, String> sysParmFields;
	public Map<String, String> sysParmLimit;

	public Map<String, String> getSysParmQuery() {
		return sysParmQuery;
	}

	public void setSysParmQuery(Map<String, String> sysParmQuery) {
		this.sysParmQuery = sysParmQuery;
	}

	public Map<String, String> getSysParmFields() {
		return sysParmFields;
	}

	public void setSysParmFields(Map<String, String> sysParmFields) {
		this.sysParmFields = sysParmFields;
	}

	public Map<String, String> getSysParmLimit() {
		return sysParmLimit;
	}

	public void setSysParmLimit(Map<String, String> sysParmLimit) {
		this.sysParmLimit = sysParmLimit;
	}

	@Override
	public String toString() {
		return "Request [sysParmQuery=" + sysParmQuery + ", sysParmFields=" + sysParmFields + ", sysParmLimit="
				+ sysParmLimit + "]";
	}

}
