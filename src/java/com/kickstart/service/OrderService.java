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

	public int count(QueryFilter filter)
	{
		return orders.count(filter);

	}
}
