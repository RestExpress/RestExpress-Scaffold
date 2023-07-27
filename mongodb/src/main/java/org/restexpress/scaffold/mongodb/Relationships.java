package org.restexpress.scaffold.mongodb;

import java.util.Map;

import org.restexpress.RestExpress;
import org.restexpress.common.exception.ConfigurationException;
import org.restexpress.scaffold.mongodb.uuid.SampleUuidEntity;

import com.strategicgains.hyperexpress.HyperExpress;
import com.strategicgains.hyperexpress.RelTypes;

public abstract class Relationships
{
	private static final String OFFSET_QUERY = "offset={offset}";
	private static final String LIMIT_QUERY = "limit={limit}";
	private static Map<String, String> routes;

	public static void define(RestExpress server)
	{
		routes = server.getRouteUrlsByName();

		HyperExpress.relationships()
		.forCollectionOf(SampleUuidEntity.class)
			.rel(RelTypes.SELF, href(Constants.Routes.SAMPLE_UUID_COLLECTION))
				.withQuery(LIMIT_QUERY)
				.withQuery(OFFSET_QUERY)
			.rel(RelTypes.NEXT, href(Constants.Routes.SAMPLE_UUID_COLLECTION) + "?offset={nextOffset}")
				.withQuery(LIMIT_QUERY)
				.optional()
			.rel(RelTypes.PREV, href(Constants.Routes.SAMPLE_UUID_COLLECTION) + "?offset={prevOffset}")
				.withQuery(LIMIT_QUERY)
				.optional()

		.forClass(SampleUuidEntity.class)
			.rel(RelTypes.SELF, href(Constants.Routes.SINGLE_UUID_SAMPLE))
			.rel(RelTypes.UP, href(Constants.Routes.SAMPLE_UUID_COLLECTION))
		;
	}

	private static String href(String name)
	{
		String href = routes.get(name);
		if (href == null) throw new ConfigurationException("Route name not found: " + name);
		return href;
	}

	private Relationships()
	{
		// prevents instantiation.
	}
}
