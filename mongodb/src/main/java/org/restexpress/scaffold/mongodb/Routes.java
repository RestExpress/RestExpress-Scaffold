package org.restexpress.scaffold.mongodb;

import org.jboss.netty.handler.codec.http.HttpMethod;
import org.restexpress.RestExpress;
import org.restexpress.scaffold.mongodb.config.Configuration;

public abstract class Routes
{
	public static void define(Configuration config, RestExpress server)
	{
		// TODO: Your routes here...
		server.uri("/your/route/here/{sampleId}.{format}", config.getSampleController())
		    .method(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE)
		    .name(Constants.Routes.SINGLE_SAMPLE);

		server
		    .uri("/your/collection/route/here.{format}", config.getSampleController())
		    .action("readAll", HttpMethod.GET)
		    .method(HttpMethod.POST)
		    .name(Constants.Routes.SAMPLE_COLLECTION);

		// or REGEX matching routes...
		// server.regex("/some.regex", config.getRouteController());
	}
}
