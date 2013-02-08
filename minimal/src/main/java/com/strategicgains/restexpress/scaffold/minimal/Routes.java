package com.strategicgains.restexpress.scaffold.minimal;

import org.jboss.netty.handler.codec.http.HttpMethod;

import com.strategicgains.restexpress.RestExpress;
import com.strategicgains.restexpress.scaffold.minimal.config.Configuration;

public abstract class Routes
{
	public static void define(Configuration config, RestExpress server)
    {
		//TODO: Your routes here...
		server.uri("/your/route/here/{sampleId}", config.getSampleController())
			.method(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE)
			.name(Constants.Routes.SINGLE_SAMPLE);

//		server.uri("/your/collection/route/here", config.getSampleController())
//			.action("readAll", HttpMethod.GET)
//			.method(HttpMethod.POST)
//			.name(Constants.Routes.SAMPLE_COLLECTION);
// or...
//		server.regex("/some.regex", config.getRouteController());
    }
}
