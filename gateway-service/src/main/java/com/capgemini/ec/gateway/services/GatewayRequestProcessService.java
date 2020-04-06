package com.capgemini.ec.gateway.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capgemini.ec.gateway.model.IncidentInfo;
import com.capgemini.ec.gateway.processor.IncInfoProcessor;

@Service
public class GatewayRequestProcessService {
	@Produce
	private ProducerTemplate template;
	
	@Autowired
	private IncInfoProcessor incInfoProcessor;

	public ResponseEntity<String> processUpdateInc(Map<String, String> input, String sys_id)
			throws Exception {
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("sys_id", sys_id);
		headers.put("customer", input.get("customer").toUpperCase());
		input.remove("customer");
		byte[] result = template.requestBodyAndHeaders("direct:forwardToUpdate", input, headers, byte[].class);
		System.out.println("Out Put " + new String(result));
		return new ResponseEntity<String>(new String(result), HttpStatus.OK);

	}
	
	public ResponseEntity<String> processCreateInc(Map<String, String> input)
			throws Exception {
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("customer", input.get("customer").toUpperCase());
		input.remove("customer");
		byte[] result = template.requestBodyAndHeaders("direct:forwardToCreate", input, headers, byte[].class);
		System.out.println("Out Put " + new String(result));
		return new ResponseEntity<String>(new String(result), HttpStatus.OK);

	}
	
	public ResponseEntity<String> processGetInc(String customer,String sysParmQuery,String sysParmFields)
			throws Exception {
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("sysParmQuery", sysParmQuery);
		headers.put("sysParmFields", sysParmFields);
		headers.put("customer", customer.toUpperCase());
		byte[] result = template.requestBodyAndHeaders("direct:forwardToGet", null, headers, byte[].class);
		System.out.println("Out Put " + new String(result));
		return new ResponseEntity<String>(new String(result), HttpStatus.OK);

	}
	
	
	public ResponseEntity<String> processGetIncidentInfo(IncidentInfo input)
			throws Exception {	
		return new ResponseEntity<String>(incInfoProcessor.processIncInfo(input), HttpStatus.OK);

	}

}
