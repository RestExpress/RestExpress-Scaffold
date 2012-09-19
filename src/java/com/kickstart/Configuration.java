package com.kickstart;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kickstart.controller.KickStartController;
import com.mongodb.Mongo;
import com.mongodb.ServerAddress;
import com.strategicgains.restexpress.Format;
import com.strategicgains.restexpress.RestExpress;
import com.strategicgains.restexpress.exception.ConfigurationException;
import com.strategicgains.restexpress.util.Environment;

public class Configuration
extends Environment
{
	private static final String NAME_PROPERTY = "name";
	private static final String PORT_PROPERTY = "port";
	private static final String DEFAULT_FORMAT_PROPERTY = "default.Format";
	private static final String MONGODB_BOOTSTRAPS_PROPERTY = "mongodb.bootstraps";
	private static final int DEFAULT_MONGODB_PORT = 27017;

	private int port;
	private String name;
	private String defaultFormat;
	
	private KickStartController kickStartController = new KickStartController();

	@Override
	protected void fillValues(Properties p)
	{
		this.name = p.getProperty(NAME_PROPERTY, RestExpress.DEFAULT_NAME);
		this.port = Integer.parseInt(p.getProperty(PORT_PROPERTY, String.valueOf(RestExpress.DEFAULT_PORT)));
		this.defaultFormat = p.getProperty(DEFAULT_FORMAT_PROPERTY, Format.JSON);
		String bootstrapString = p.getProperty(MONGODB_BOOTSTRAPS_PROPERTY);
		Mongo mongo = null;
		
		try
		{
			if (bootstrapString != null)
			{
		    	mongo = new Mongo(bootstrapString);
			}
			else
			{
				mongo = new Mongo();
			}
		}
		catch(UnknownHostException e)
		{
			throw new ConfigurationException(e);
		}

		initialize(mongo);
	}

	/**
	 * @param mongo
	 */
    private void initialize(Mongo mongo)
    {
	    // TODO Auto-generated method stub
    }

	public String getDefaultFormat()
	{
		return defaultFormat;
	}

	public int getPort()
	{
		return port;
	}

	public String getName()
	{
		return name;
	}
	
	public KickStartController getKickStartController()
	{
		return kickStartController;
	}
}
