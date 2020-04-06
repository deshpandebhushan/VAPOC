package com.capgemini.ec.gateway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * This will process monitoring message.
 * 
 * @author bdeshpan
 *
 */
@Component
public class GatewayRequestProcessor extends RouteBuilder {

	/**
	 * This method will push monitoring message to publisher. Basic
	 * d2ViLkFQSTp3ZWIuQVBJ
	 * 
	 */
	@Override
	public void configure() {

		from("direct:forwardToCreate").routeId("forwardToCreate").marshal().json(JsonLibrary.Jackson)
				.setHeader(Exchange.HTTP_METHOD, simple(HttpMethod.POST.name()))
				.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
				.setHeader("Authorization", simple("Basic d2ViLkFQSTp3ZWIuQVBJ"))
				.choice()
				.when(header("customer").isEqualTo("CAMPARI"))
				.toD("{{CAMPARI_CLIENT_URL}}{{INC_BASE_URL}}")
				.stop()
				.end();

		from("direct:forwardToUpdate").routeId("forwardToUpdate").marshal().json(JsonLibrary.Jackson)
				.setHeader(Exchange.HTTP_METHOD, simple(HttpMethod.PUT.name()))
				.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
				.setHeader("Authorization", simple("Basic d2ViLkFQSTp3ZWIuQVBJ"))
				.choice()
				.when(header("customer").isEqualTo("CAMPARI"))
				.toD("{{CAMPARI_CLIENT_URL}}{{INC_BASE_URL}}/${header.sys_id}")
				.stop()
				.end();

		from("direct:forwardToGet").routeId("forwardToGet")
				.setHeader(Exchange.HTTP_METHOD, simple(HttpMethod.GET.name()))
				.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
				.setHeader("Authorization", simple("Basic d2ViLkFQSTp3ZWIuQVBJ"))
				.choice()
				.when(header("customer").isEqualTo("CAMPARI"))
					.toD("{{CAMPARI_CLIENT_URL}}{{INC_BASE_URL}}?sysparm_query=${header.sysParmQuery}"
						+ "&sysparm_fields=${header.sysParmFields}")
				.stop()
				.end();	
	}

}
