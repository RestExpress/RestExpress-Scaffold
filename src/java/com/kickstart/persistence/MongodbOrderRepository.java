/*
    Copyright 2012, Strategic Gains, Inc.

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
package com.kickstart.persistence;

import java.util.List;

import com.kickstart.domain.Order;
import com.mongodb.Mongo;
import com.strategicgains.repoexpress.mongodb.MongodbEntityRepository;
import com.strategicgains.restexpress.query.QueryFilter;
import com.strategicgains.restexpress.query.QueryOrder;
import com.strategicgains.restexpress.query.QueryRange;

/**
 * @author toddf
 * @since Oct 10, 2012
 */
public class MongodbOrderRepository
extends MongodbEntityRepository<Order>
implements OrderRepository
{
	@SuppressWarnings("unchecked")
	public MongodbOrderRepository(Mongo mongo, String databaseName)
	{
		super(mongo, databaseName, Order.class);
	}

	@Override
	public List<Order> readAll(QueryFilter filter, QueryRange range, QueryOrder order)
	{
		return null;
	}

	@Override
	public int count(QueryFilter filter)
	{
		return 0;
	}
}
