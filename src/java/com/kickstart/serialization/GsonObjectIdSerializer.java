/*
    Copyright 2011, Strategic Gains, Inc.

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

		http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/
package com.kickstart.serialization;

import java.lang.reflect.Type;

import org.bson.types.ObjectId;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.strategicgains.restexpress.serialization.json.GsonSerializer;

/**
 * @author toddf
 * @since Feb 16, 2011
 */
public class GsonObjectIdSerializer
implements GsonSerializer<ObjectId>
{
    @Override
    public ObjectId deserialize(JsonElement json, Type typeOf, JsonDeserializationContext context)
    throws JsonParseException
    {
	    return new ObjectId(json.getAsJsonPrimitive().getAsString());
    }

    @Override
    public JsonElement serialize(ObjectId id, Type typeOf, JsonSerializationContext context)
    {
    	return new JsonPrimitive(id.toString());
    }

    @Override
    public ObjectId createInstance(Type arg0)
    {
	    return new ObjectId();
    }
}
