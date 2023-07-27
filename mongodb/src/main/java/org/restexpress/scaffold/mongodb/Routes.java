package org.restexpress.scaffold.mongodb;

import io.netty.handler.codec.http.HttpMethod;

import org.restexpress.RestExpress;

public abstract class Routes
{
	public static void define(Configuration config, RestExpress server)
	{
		// TODO: Your routes here...
		server.uri("/samples/uuid/{uuid}", config.getSampleUuidEntityController())
		    .method(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE)
		    .name(Constants.Routes.SINGLE_UUID_SAMPLE);

		server.uri("/samples/uuid", config.getSampleUuidEntityController())
		    .action("readAll", HttpMethod.GET)
		    .method(HttpMethod.POST)
		    .name(Constants.Routes.SAMPLE_UUID_COLLECTION);
	}

	private Routes()
	{
		// prevents instantiation.
	}
}
