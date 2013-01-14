package com.strategicgains.restexpress.scaffold.minimal.controller;

import com.strategicgains.restexpress.Request;
import com.strategicgains.restexpress.Response;

public class RouteController
{
	public RouteController()
	{
		super();
	}

	public Object create(Request request, Response response)
	{
		//TODO: Your 'create' logic here...
		return null;
	}

	public Object read(Request request, Response response)
	{
		//TODO: Your 'read' logic here...
		return null;
	}

	public void update(Request request, Response response)
	{
		//TODO: Your 'update' logic here...
		response.setResponseNoContent();
	}

	public void delete(Request request, Response response)
	{
		//TODO: Your 'delete' logic here...
		response.setResponseNoContent();
	}
}
