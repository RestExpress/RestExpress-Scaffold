package org.restexpress.scaffold.cassandra.controller;

import org.jboss.netty.handler.codec.http.HttpMethod;
import org.restexpress.Request;
import org.restexpress.Response;
import org.restexpress.exception.BadRequestException;
import org.restexpress.scaffold.cassandra.Constants;
import org.restexpress.scaffold.cassandra.domain.SampleUuidEntity;
import org.restexpress.scaffold.cassandra.service.SampleUuidEntityService;

import com.strategicgains.hyperexpress.UrlBuilder;
import com.strategicgains.repoexpress.adapter.Identifiers;
import com.strategicgains.repoexpress.util.UuidConverter;

/**
 * This is the 'controller' layer, where HTTP details are converted to domain concepts and passed to the service layer.
 * Then service layer response information is enhanced with HTTP details, if applicable, for the response.
 * <p/>
 * This controller demonstrates how to process a Cassandra entity that is identified by a single, primary row key such
 * as a UUID.
 */
public class SampleUuidEntityController
{
	private SampleUuidEntityService service;
	
	public SampleUuidEntityController(SampleUuidEntityService sampleService)
	{
		super();
		this.service = sampleService;
	}

	public SampleUuidEntity create(Request request, Response response)
	{
		SampleUuidEntity entity = request.getBodyAs(SampleUuidEntity.class, "Resource details not provided");
		SampleUuidEntity saved = service.create(entity);

		// Construct the response for create...
		response.setResponseCreated();

		// Include the Location header...
		String locationPattern = request.getNamedUrl(HttpMethod.GET, Constants.Routes.SINGLE_UUID_SAMPLE);
		response.addLocationHeader(new UrlBuilder(locationPattern)
			.param(Constants.Url.UUID, saved.getId().toString())
			.build());

		// enrich the resource with links, etc. here...

		// Return the newly-created resource...
		return saved;
	}

	public SampleUuidEntity read(Request request, Response response)
	{
		String id = request.getHeader(Constants.Url.UUID, "No resource ID supplied");
		SampleUuidEntity entity = service.read(Identifiers.UUID.parse(id));

		// enrich the entity with links, etc. here...

		return entity;
	}

	public void update(Request request, Response response)
	{
		String id = request.getHeader(Constants.Url.UUID, "No resource ID supplied");
		SampleUuidEntity entity = request.getBodyAs(SampleUuidEntity.class, "Resource details not provided");
		
		if (!UuidConverter.parse(id).equals(entity.getId()))
		{
			throw new BadRequestException("ID in URL and ID in resource body must match");
		}

		service.update(entity);
		response.setResponseNoContent();
	}

	public void delete(Request request, Response response)
	{
		String id = request.getHeader(Constants.Url.UUID, "No resource ID supplied");
		service.delete(Identifiers.UUID.parse(id));
		response.setResponseNoContent();
	}
}
