package com.kickstart;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.jboss.netty.handler.codec.http.HttpMethod;

import com.kickstart.postprocessor.LastModifiedHeaderPostprocessor;
import com.kickstart.serialization.ResponseProcessors;
import com.strategicgains.restexpress.Format;
import com.strategicgains.restexpress.Parameters;
import com.strategicgains.restexpress.RestExpress;
import com.strategicgains.restexpress.pipeline.SimpleConsoleLogMessageObserver;
import com.strategicgains.restexpress.plugin.CacheControlPlugin;
import com.strategicgains.restexpress.plugin.RoutesMetadataPlugin;
import com.strategicgains.restexpress.util.Environment;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		Configuration config = loadEnvironment(args);
		RestExpress server = new RestExpress()
		    .setName(config.getName())
		    .setDefaultFormat(config.getDefaultFormat())
		    .putResponseProcessor(Format.JSON, ResponseProcessors.json())
		    .putResponseProcessor(Format.XML, ResponseProcessors.xml())
		    .putResponseProcessor(Format.WRAPPED_JSON, ResponseProcessors.wrappedJson())
		    .putResponseProcessor(Format.WRAPPED_XML, ResponseProcessors.wrappedXml())
		    .addPostprocessor(new LastModifiedHeaderPostprocessor())
		    .addMessageObserver(new SimpleConsoleLogMessageObserver());

		defineRoutes(config, server);

		new RoutesMetadataPlugin()							// Support discoverability.
			.register(server)
			.parameter(Parameters.Cache.MAX_AGE, 86400);	// Cache for 1 day (24 hours).

		new CacheControlPlugin()							// Support caching headers.
			.register(server);

		mapExceptions(server);
		server.bind(config.getPort());
		server.awaitShutdown();
	}

	private static void defineRoutes(Configuration config, RestExpress server)
	{
		// Maps /kickstart uri with optional format ('json' or 'xml'), accepting
		// POST HTTP method only.  Calls KickStartService.create(Request, Reply).
		server.uri("/kickstart.{format}", config.getKickStartController())
			.method(HttpMethod.POST);

		// Maps /kickstart uri with required orderId and optional format identifier
		// to the KickStartService.  Accepts only GET, PUT, DELETE HTTP methods.
		// Names this route to allow returning links from read resources in
		// KickStartService methods via call to LinkUtils.asLinks().
		server.uri("/kickstart/{orderId}.{format}", config.getKickStartController())
			.method(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE)
			.name(Constants.KICKSTART_ORDER_URI)
			.parameter(Parameters.Cache.MAX_AGE, 3600);		// Cache for 3600 seconds (1 hour).
//			.flag(Flags.Cache.DONT_CACHE);					// Expressly deny cache-ability.
	}

	/**
     * @param server
     */
    private static void mapExceptions(RestExpress server)
    {
//    	server
//    	.mapException(ItemNotFoundException.class, NotFoundException.class)
//    	.mapException(DuplicateItemException.class, ConflictException.class)
//    	.mapException(ValidationException.class, BadRequestException.class);
    }

	private static Configuration loadEnvironment(String[] args)
    throws FileNotFoundException, IOException
    {
	    if (args.length > 0)
		{
			return Environment.from(args[0], Configuration.class);
		}

	    return Environment.fromDefault(Configuration.class);
    }
}
