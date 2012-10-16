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
		return query(Order.class, range, filter, order);
	}

	@Override
	public long count(QueryFilter filter)
	{
		return count(Order.class, filter);
	}
}
