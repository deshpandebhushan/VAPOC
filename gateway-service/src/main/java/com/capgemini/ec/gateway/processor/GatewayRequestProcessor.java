package com.capgemini.ec.gateway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.exception.HystrixTimeoutException;
/**
 * This will process monitoring message.
 * @author bdeshpan
 *
 */
@Component
public class GatewayRequestProcessor extends RouteBuilder {

	/**
	 * This method will push monitoring message to publisher.
	 * Basic d2ViLkFQSTp3ZWIuQVBJ
	 * 
	 */
	@Override
	public void configure() {
			
		
		from("direct:forwardToCreate")
		.routeId("forwardToCreate")
		.marshal()
		.json(JsonLibrary.Jackson)
		.setHeader(Exchange.HTTP_METHOD, simple(HttpMethod.POST.name()))
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		.setHeader("Authorization", simple("Basic d2ViLkFQSTp3ZWIuQVBJ"))	
		.toD("https://capgeminiukplcdemo10.service-now.com/api/now/table/incident")
		.end();
		
		
		from("direct:forwardToUpdate")
		.routeId("forwardToUpdate")
		.marshal()
		.json(JsonLibrary.Jackson)
		.setHeader(Exchange.HTTP_METHOD, simple(HttpMethod.PUT.name()))
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		.setHeader("Authorization", simple("Basic d2ViLkFQSTp3ZWIuQVBJ"))	
		.toD("https://capgeminiukplcdemo10.service-now.com/api/now/table/incident/${header.sys_id}")
		.end();
		
		
		from("direct:forwardToGet")
		.routeId("forwardToGet")
		.marshal()
		.json(JsonLibrary.Jackson)
		.setHeader(Exchange.HTTP_METHOD, simple(HttpMethod.GET.name()))
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		.setHeader("Authorization", simple("Basic d2ViLkFQSTp3ZWIuQVBJ"))	
		.toD("https://capgeminiukplcdemo10.service-now.com/api/now/table/incident?sysparm_query=number=${header.incNumber}"
				+ "&sysparm_fields=short_description,caller_id,number,priority,u_requested_for,u_tenant_category,u_tenant_subcategory")
		.end();
		
		
		from("direct:getIncInfo")
		.routeId("getIncInfo")
		.process(exchange ->{ System.out.println(exchange.getIn().getHeader("sysparm_query"));})
		.marshal()
		.json(JsonLibrary.Jackson)
		.setHeader(Exchange.HTTP_METHOD, simple(HttpMethod.GET.name()))
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		.setHeader("Authorization", simple("Basic d2ViLkFQSTp3ZWIuQVBJ"))
		.toD("{{CAMPARI_CLIENT_URL}}{{CAMPARI_GET_INC_BASE_URL}}${header.sysparm_query}")
		//https://capgeminiukplcdemo10.service-now.com/api/now/table/incident${header.sysparm_query}
		.end();
			
	}

	
}
