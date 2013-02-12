package com.strategicgains.restexpress.scaffold.mongodb.config;

import java.util.Properties;

import com.strategicgains.repoexpress.mongodb.MongodbEntityRepository;
import com.strategicgains.restexpress.Format;
import com.strategicgains.restexpress.RestExpress;
import com.strategicgains.restexpress.scaffold.mongodb.controller.SampleController;
import com.strategicgains.restexpress.scaffold.mongodb.domain.Sample;
import com.strategicgains.restexpress.util.Environment;

public class Configuration
extends Environment
{
	private static final String DEFAULT_EXECUTOR_THREAD_POOL_SIZE = "20";

	private static final String PORT_PROPERTY = "port";
	private static final String DEFAULT_FORMAT_PROPERTY = "default.format";
	private static final String BASE_URL_PROPERTY = "base.url";
	private static final String EXECUTOR_THREAD_POOL_SIZE = "executor.threadPool.size";

	private int port;
	private String defaultFormat;
	private String baseUrl;
	private int executorThreadPoolSize;
	private MetricsConfig metricsSettings;

	private SampleController sampleController;

	@Override
	protected void fillValues(Properties p)
	{
		this.port = Integer.parseInt(p.getProperty(PORT_PROPERTY, String.valueOf(RestExpress.DEFAULT_PORT)));
		this.defaultFormat = p.getProperty(DEFAULT_FORMAT_PROPERTY, Format.JSON);
		this.baseUrl = p.getProperty(BASE_URL_PROPERTY, "http://localhost:" + String.valueOf(port));
		this.executorThreadPoolSize = Integer.parseInt(p.getProperty(EXECUTOR_THREAD_POOL_SIZE, DEFAULT_EXECUTOR_THREAD_POOL_SIZE));
		this.metricsSettings = new MetricsConfig(p);
		MongoConfig mongo = new MongoConfig(p);
		initialize(mongo);
	}

	private void initialize(MongoConfig mongo)
	{
		@SuppressWarnings("unchecked")
        MongodbEntityRepository<Sample> orderRepository = new MongodbEntityRepository<Sample>(mongo.getClient(), mongo.getDbName(), Sample.class);
		sampleController = new SampleController(orderRepository);
	}

	public String getDefaultFormat()
	{
		return defaultFormat;
	}

	public int getPort()
	{
		return port;
	}
	
	public String getBaseUrl()
	{
		return baseUrl;
	}
	
	public int getExecutorThreadPoolSize()
	{
		return executorThreadPoolSize;
	}

	public MetricsConfig getMetricsConfig()
    {
	    return metricsSettings;
    }

	public SampleController getSampleController()
	{
		return sampleController;
	}
}
