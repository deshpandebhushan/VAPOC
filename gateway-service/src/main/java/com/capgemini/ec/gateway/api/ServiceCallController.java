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
public class ServiceCallController  {
	
	@Autowired
	private GatewayRequestProcessService processService;
	
	
	@Autowired
	private RestTemplate restTemplate;
	/**
	 * Will return incident Number that was updated.
	 * @param request
	 * @return response for update request
	 * @throws Exception
	 */
	@PutMapping(path = "/{sys_id}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateIncident(HttpServletRequest request,@PathVariable String sys_id,@RequestBody Map<String,String> updateINC) throws Exception {
		return processService.processUpdateInc(updateINC,sys_id);
	}
	
	@PostMapping( produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> CreateIncident(HttpServletRequest request,@RequestBody Map<String,String> createINC) throws Exception {
		return processService.processCreateInc(createINC);
	}
	
		
	@GetMapping(path = "/{incNumber}/sysQuery= {}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getIncident(HttpServletRequest request,@PathVariable String incNumber) throws Exception {	
		return processService.processGetInc(incNumber);
	}
	
	/*@PostMapping(path = "/incidentInfo/",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getIncidentInfo(HttpServletRequest request,@RequestBody IncidentInfo input) throws Exception {
		System.out.println("Incoming Request => "+input.toString());
		return processService.processGetIncidentInfo(input);
	}*/
	
	
	
	
	
	
	
	
	/*  private HttpHeaders createHeaders(String username, String password){
		    return new HttpHeaders() {{
		      String auth = username + ":" + password;
		      byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")) );
		      String authHeader = "Basic " + new String( encodedAuth );
		      set( "Authorization", authHeader );
		    }};
		  }
		  @GetMapping(path = "/{ID}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
		          produces = MediaType.APPLICATION_JSON_VALUE)
		  @ResponseBody
		  public ResponseEntity<String> getRITMByUserId1(@PathVariable String ID,
		                                                 HttpServletRequest request) throws Exception{
		    String host =
		            "https://capgeminiukplcdemo10.service-now.com/api/now/v2/table/sc_req_item?sysparm_query=u_requested_for="
		            +ID
		            +"^closed_at%3E=javascript:gs.daysAgo(7)^ORclosed_atISEMPTY^ORDERBYDESCsys_updated_on&sysparm_fields=sys_id,number,state,description";
		    HttpHeaders h = createHeaders("web.API", "web.API");
		    h.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		    HttpEntity<String> entity = new HttpEntity<String>("parameters", h);
		    ResponseEntity<String> responseEntity =
		            restTemplate.exchange(host, HttpMethod.GET, entity, String.class);
		    System.out.println("Sending Responce from ServiceNow to VA/Postman");
		    return responseEntity;
		  }*/

}
