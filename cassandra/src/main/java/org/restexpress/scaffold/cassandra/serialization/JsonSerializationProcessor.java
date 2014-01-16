package org.restexpress.scaffold.cassandra.serialization;

import java.util.UUID;

import org.restexpress.serialization.json.JacksonJsonProcessor;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class JsonSerializationProcessor
extends JacksonJsonProcessor
{
	@Override
    protected void initializeModule(SimpleModule module)
    {
	    super.initializeModule(module);
	    module.addDeserializer(UUID.class, new UuidDeserializer());
	    module.addSerializer(UUID.class, new UuidSerializer());
    }
}
