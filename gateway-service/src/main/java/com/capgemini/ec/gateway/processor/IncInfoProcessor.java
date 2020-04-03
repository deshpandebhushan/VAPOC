package com.capgemini.ec.gateway.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.json.simple.parser.JSONParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.capgemini.ec.gateway.model.IncidentInfo;
import com.capgemini.ec.gateway.model.ResultWrapper;

@Component
public class IncInfoProcessor  {
	
	@Produce
	private ProducerTemplate template;

	public String processIncInfo(IncidentInfo input) throws Exception {
		System.out.println("Welcome to INC INFO Processor");
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("sysparm_query", processSysParmQuery(input));
		headers.put("client", input.getClient().toUpperCase()+"_CLIENT_URL");
		headers.put("client_base_url", input.getClient().toUpperCase()+"_GET_INC_BASE_URL");
		
		
		
		          /*  "https://capgeminiukplcdemo10.service-now.com/api/now/v2/table/sc_req_item?sysparm_query=u_requested_for="
		            +ID
		            +"^closed_at%3E=javascript:gs.daysAgo(7)^ORclosed_atISEMPTY^ORDERBYDESCsys_updated_on&sysparm_fields=sys_id,number,state,description";
		
	*/
		byte[] result = template.requestBodyAndHeaders("direct:getIncInfo", input, headers, byte[].class);
		System.out.println("Out Put " + new String(result));
		return new String(result);
	}
	
	private String processSysParmQuery(IncidentInfo input) {
		String out = "?sysparm_query=";
		for (String iterable_element : input.getSysParmQuery().keySet()) {
			out = out+iterable_element+"="+input.getSysParmQuery().get(iterable_element)+"&";
		}
		out = out +processSysParmFields(input.getSysParmFields());
		return out;
	}
	
	private String processSysParmFields(Map<String,String> sysParmFields) {
		String out = "sysparm_fields=";
		for (String iterable_element : sysParmFields.keySet()) {
			out = out+iterable_element+",";
		}
		
		return out.substring(0, out.length()-1);
	}

}
