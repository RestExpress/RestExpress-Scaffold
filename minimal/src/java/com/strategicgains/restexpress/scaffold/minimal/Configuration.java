package com.strategicgains.restexpress.scaffold.minimal;

import java.util.Properties;

import com.strategicgains.restexpress.Format;
import com.strategicgains.restexpress.RestExpress;
import com.strategicgains.restexpress.scaffold.minimal.controller.RouteController;
import com.strategicgains.restexpress.util.Environment;

public class Configuration
extends Environment
{
	private static final String PORT_PROPERTY = "port";
	private static final String DEFAULT_FORMAT_PROPERTY = "default.Format";
	private static final String BASE_URL_PROPERTY = "base.url";

	private int port;
	private String defaultFormat;
	private String baseUrl;

	private RouteController routeController;

	@Override
	protected void fillValues(Properties p)
	{
		this.port = Integer.parseInt(p.getProperty(PORT_PROPERTY, String.valueOf(RestExpress.DEFAULT_PORT)));
		this.defaultFormat = p.getProperty(DEFAULT_FORMAT_PROPERTY, Format.JSON);
		this.baseUrl = p.getProperty(BASE_URL_PROPERTY, "http://localhost:" + String.valueOf(port));
		initialize();
	}

	private void initialize()
	{
		routeController = new RouteController();
	}

	public String getDefaultFormat()
	{
		return defaultFormat;
	}

	public int getPort()
	{
		return port;
	}

	public RouteController getRouteController()
	{
		return routeController;
	}
	
	public String getBaseUrl()
	{
		return baseUrl;
	}
}
