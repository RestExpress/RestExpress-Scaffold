package com.strategicgains.restexpress.scaffold.minimal;

import com.strategicgains.restexpress.RestExpress;

public abstract class Routes
{
	public static void define(Configuration config, RestExpress server)
    {
		//TODO: Your routes here...
		server.uri("/your/route/here", config.getRouteController())
			.name(Constants.Routes.SINGLE_ITEM);
// or...
//		server.regex("/some.regex", config.getRouteController());
    }
}
