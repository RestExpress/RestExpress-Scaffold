package com.kickstart;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kickstart.controller.OrderController;
import com.kickstart.domain.Order;
import com.mongodb.Mongo;
import com.mongodb.ServerAddress;
import com.strategicgains.repoexpress.mongodb.MongodbEntityRepository;
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
	private static final String MONGODB_DATABASE_PROPERTY = "mongodb.database";
	private static final String MONGODB_USERNAME_PROPERTY = "mongodb.user";
	private static final String MONGODB_PASSWORD_PROPERTY = "mongodb.password";
	private static final String BASE_URL_PROPERTY = "base.url";

	private int port;
	private String name;
	private String defaultFormat;
	private String baseUrl;

	private OrderController orderController;

	@Override
	protected void fillValues(Properties p)
	{
		this.name = p.getProperty(NAME_PROPERTY, RestExpress.DEFAULT_NAME);
		this.port = Integer.parseInt(p.getProperty(PORT_PROPERTY, String.valueOf(RestExpress.DEFAULT_PORT)));
		this.defaultFormat = p.getProperty(DEFAULT_FORMAT_PROPERTY, Format.JSON);
		this.baseUrl = p.getProperty(BASE_URL_PROPERTY, "http://localhost:" + String.valueOf(port));
		String dbName = p.getProperty(MONGODB_DATABASE_PROPERTY);

		if (dbName == null)
		{
			throw new ConfigurationException("Please define a database name for property: " + MONGODB_DATABASE_PROPERTY);
		}

		String dbUser = p.getProperty(MONGODB_USERNAME_PROPERTY);
		String dbPassword = p.getProperty(MONGODB_PASSWORD_PROPERTY);
		List<ServerAddress> bootstraps = null;

		try
		{
			String bootstrapString = p.getProperty(MONGODB_BOOTSTRAPS_PROPERTY);
			bootstraps = parseBootstrapString(bootstrapString);
		}
		catch (Exception e)
		{
			throw new ConfigurationException(e);
		}

		Mongo mongo = new Mongo(bootstraps);
		initialize(mongo, dbName, dbUser, dbPassword);
	}

	private void initialize(Mongo mongo, String dbName, String dbUser, String dbPassword)
	{
		if (dbUser != null && dbPassword != null && dbPassword.length() > 0)
		{
			if (!mongo.getDB(dbName).authenticate(dbUser, dbPassword.toCharArray()))
			{
				throw new ConfigurationException("User not authenticated against database: " + dbName);
			}
		}

		MongodbEntityRepository<Order> orderRepository = new MongodbEntityRepository<Order>(mongo, dbName, Order.class);
		orderController = new OrderController(orderRepository);
	}

	/**
	 * @param bootstrapString
	 * @return
	 * @throws UnknownHostException
	 * @throws NumberFormatException
	 */
	private List<ServerAddress> parseBootstrapString(String bootstrapString)
	throws NumberFormatException,
	    UnknownHostException
	{
		if (bootstrapString == null || bootstrapString.trim().isEmpty())
		{
			throw new ConfigurationException("Please set MongoDB bootstrap servers in property: " + MONGODB_BOOTSTRAPS_PROPERTY);
		}

		String[] serverAndPorts = bootstrapString.split(",");
		List<ServerAddress> results = new ArrayList<ServerAddress>(serverAndPorts.length);

		for (String sp : serverAndPorts)
		{
			String[] server = sp.split(":");

			if (server.length == 2)
			{
				results.add(new ServerAddress(server[0], Integer.parseInt(server[1])));
			}
			else if (server.length == 1)
			{
				results.add(new ServerAddress(server[0]));
			}
		}

		return results;
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

	public OrderController getOrderController()
	{
		return orderController;
	}
	
	public String getBaseUrl()
	{
		return baseUrl;
	}
}
