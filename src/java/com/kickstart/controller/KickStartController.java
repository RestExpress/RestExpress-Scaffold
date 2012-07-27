package com.kickstart.controller;

import java.util.HashMap;
import java.util.Map;

import org.jboss.netty.handler.codec.http.HttpMethod;

import com.kickstart.Constants;
import com.strategicgains.restexpress.Request;
import com.strategicgains.restexpress.Response;
import com.strategicgains.restexpress.util.XLinkUtils;

public class KickStartController
{
	/**
	 * Creates a new object, returning the ID and Location header on the response.
	 * 
	 * @param request
	 * @param response
	 * @return Either the ID of the newly created object, or could be 'void' and only return the Location header.
	 */
	public String create(Request request, Response response)
	{
		String newId = "42"; // Assume a new object created.
		response.setResponseCreated();
		// Include the Location header...
		String locationUrl = request.getNamedUrl(HttpMethod.GET, Constants.KICKSTART_ORDER_URI);
		response.addLocationHeader(XLinkUtils.asLocationUrl(newId, Constants.ORDER_ID_PARAMETER, locationUrl));
		// Return the newly-created ID...
		return newId;
	}

	/**
	 * Reads a requested object from persistent storage.
	 * 
	 * @param request
	 * @param response
	 * @return Best practice is to return the actual DTO or domain object here.  The Map returned from
	 *         this particular implementation is for demo purposes only.
	 */
	public Map<String, String> read(Request request, Response response)
	{
		String id = request.getUrlDecodedHeader(Constants.ORDER_ID_PARAMETER, "No Order ID supplied");
		
		// Normally one would return an actual DTO or domain object instead of a Map.  While returning a 
		// Map works, this particular implementation is for demonstration purposes... in leau of a domain
		// model.
		Map<String, String> result = new HashMap<String, String>();
		result.put("id", id);
		result.put("value", "something here");
		return result;
	}

	public void update(Request request, Response response)
	{
		// Use this ONLY if not using wrapped responses.  Wrapped responses WILL contain content.
//		response.setResponseNoContent();
	}

	public void delete(Request request, Response response)
	{
		// Use this ONLY if not using wrapped responses.  Wrapped responses WILL contain content.
//		response.setResponseNoContent();
	}
}
