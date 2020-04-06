package com.capgemini.ec.gateway.api;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.capgemini.ec.gateway.model.IncidentInfo;
import com.capgemini.ec.gateway.model.ResultWrapper;
import com.capgemini.ec.gateway.services.GatewayRequestProcessService;

@RestController
@RequestMapping("/api/now/table/incident")
@CrossOrigin
public class ServiceCallController {

	@Autowired
	private GatewayRequestProcessService processService;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Will return incident Number that was updated.
	 * 
	 * @param request
	 * @return response for update request
	 * @throws Exception
	 */
	@PutMapping(path = "/{sys_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateIncident(HttpServletRequest request, @PathVariable String sys_id,
			@RequestBody Map<String, String> updateINC) throws Exception {
		return processService.processUpdateInc(updateINC, sys_id);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> CreateIncident(HttpServletRequest request, @RequestBody Map<String, String> createINC)
			throws Exception {
		return processService.processCreateInc(createINC);
	}

	@GetMapping(path = "/customer={customer}/sysParmQuery={sysParmQuery}/sysParmFields={sysParmFields}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getIncident(HttpServletRequest request, @PathVariable String customer,
			@PathVariable String sysParmQuery, @PathVariable String sysParmFields) throws Exception {
		return processService.processGetInc(customer, sysParmQuery, sysParmFields);
	}

}
