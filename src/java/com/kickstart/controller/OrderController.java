package com.kickstart.controller;

import java.util.List;

import org.jboss.netty.handler.codec.http.HttpMethod;

import com.kickstart.Constants;
import com.kickstart.domain.Order;
import com.kickstart.service.OrderService;
import com.strategicgains.restexpress.Request;
import com.strategicgains.restexpress.Response;
import com.strategicgains.restexpress.exception.BadRequestException;
import com.strategicgains.restexpress.query.QueryFilter;
import com.strategicgains.restexpress.query.QueryOrder;
import com.strategicgains.restexpress.query.QueryRange;
import com.strategicgains.restexpress.util.XLinkUtils;

public class OrderController
{
	private OrderService orderService;
	
	public OrderController(OrderService orderService)
	{
		super();
		this.orderService = orderService;
	}

	public String create(Request request, Response response)
	{
		Order order = request.getBodyAs(Order.class, "Order details not provided");
		Order saved = orderService.create(order);

		// Construct the response for create...
		response.setResponseCreated();

		// Include the Location header...
		String locationUrl = request.getNamedUrl(HttpMethod.GET, Constants.KICKSTART_ORDER_URI);
		response.addLocationHeader(XLinkUtils.asLocationUrl(saved.getId(), Constants.ORDER_ID_PARAMETER, locationUrl));

		// Return the newly-created ID...
		return saved.getId();
	}

	public Order read(Request request, Response response)
	{
		String id = request.getUrlDecodedHeader(Constants.ORDER_ID_PARAMETER, "No Order ID supplied");
		return orderService.read(id);
	}

	public List<Order> readAll(Request request, Response response)
	{
		QueryFilter filter = QueryFilter.parseFrom(request);
		QueryOrder order = QueryOrder.parseFrom(request);
		QueryRange range = QueryRange.parseFrom(request, 20);
		List<Order> results = orderService.readAll(filter, range, order);
		int count = orderService.count(filter);
		response.addRangeHeader(range, count);
		return results;
	}

	public void update(Request request, Response response)
	{
		String id = request.getUrlDecodedHeader(Constants.ORDER_ID_PARAMETER);
		Order order = request.getBodyAs(Order.class, "Order details not provided");
		
		if (!id.equals(order.getId()))
		{
			throw new BadRequestException("ID in URL and ID in Order must match");
		}
		
		orderService.update(order);
		response.setResponseNoContent();
	}

	public void delete(Request request, Response response)
	{
		String id = request.getUrlDecodedHeader(Constants.ORDER_ID_PARAMETER, "No Order ID supplied");
		orderService.delete(id);
		response.setResponseNoContent();
	}
}
