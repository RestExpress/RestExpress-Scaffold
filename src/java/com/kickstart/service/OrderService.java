package com.kickstart.service;

import java.util.List;

import com.kickstart.domain.Order;
import com.kickstart.persistence.OrderRepository;
import com.strategicgains.restexpress.query.QueryFilter;
import com.strategicgains.restexpress.query.QueryOrder;
import com.strategicgains.restexpress.query.QueryRange;
import com.strategicgains.syntaxe.ValidationEngine;

/**
 * @author toddf
 * @since Oct 9, 2012
 */
public class OrderService
{
	private OrderRepository orders;

	public OrderService(OrderRepository orderRepository)
	{
		super();
		this.orders = orderRepository;
	}

	public Order create(Order order)
	{
		ValidationEngine.validateAndThrow(order);
		return orders.create(order);
	}

	public Order read(String id)
	{
		return orders.read(id);
	}

	public void update(Order order)
	{
		ValidationEngine.validateAndThrow(order);
		orders.update(order);
	}

	public void delete(String id)
	{
		orders.delete(id);
	}

	/**
	 * @param filter
	 * @param range
	 * @param order
	 * @return
	 */
	public List<Order> readAll(QueryFilter filter, QueryRange range, QueryOrder order)
	{
		List<Order> results = orders.readAll(filter, range, order);
		return results;
	}

	public long count(QueryFilter filter)
	{
		return orders.count(filter);

	}
}
