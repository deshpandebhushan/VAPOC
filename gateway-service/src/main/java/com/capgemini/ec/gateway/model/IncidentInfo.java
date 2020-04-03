package com.capgemini.ec.gateway.model;

public class IncidentInfo extends Request {
	private String client;
	private String system;

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	@Override
	public String toString() {
		return "IncidentInfo [client=" + client + ", system=" + system + ", sysParmQuery=" + sysParmQuery
				+ ", sysParmFields=" + sysParmFields + ", sysParmLimit=" + sysParmLimit + "]";
	}

}
