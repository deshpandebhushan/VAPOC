package com.capgemini.ec.gateway.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capgemini.ec.gateway.model.IncidentInfo;
import com.capgemini.ec.gateway.model.ResultWrapper;
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
		byte[] result = template.requestBodyAndHeaders("direct:forwardToUpdate", input, headers, byte[].class);
		System.out.println("Out Put " + new String(result));
		return new ResponseEntity<String>(new String(result), HttpStatus.OK);

	}
	
	public ResponseEntity<String> processCreateInc(Map<String, String> input)
			throws Exception {
		byte[] result = template.requestBodyAndHeaders("direct:forwardToCreate", input, null, byte[].class);
		System.out.println("Out Put " + new String(result));
		return new ResponseEntity<String>(new String(result), HttpStatus.OK);

	}
	
	public ResponseEntity<String> processGetInc(String incNumber)
			throws Exception {
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("incNumber", incNumber);
		byte[] result = template.requestBodyAndHeaders("direct:forwardToGet", null, headers, byte[].class);
		System.out.println("Out Put " + new String(result));
		return new ResponseEntity<String>(new String(result), HttpStatus.OK);

	}
	
	
	public ResponseEntity<String> processGetIncidentInfo(IncidentInfo input)
			throws Exception {	
		return new ResponseEntity<String>(incInfoProcessor.processIncInfo(input), HttpStatus.OK);

	}

}
