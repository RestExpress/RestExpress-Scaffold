package com.kickstart.controller;

import java.util.List;

import org.jboss.netty.handler.codec.http.HttpMethod;

import com.kickstart.Constants;
import com.kickstart.domain.Order;
import com.strategicgains.hyperexpress.RelTypes;
import com.strategicgains.hyperexpress.domain.Link;
import com.strategicgains.hyperexpress.domain.LinkableCollection;
import com.strategicgains.hyperexpress.util.LinkUtils;
import com.strategicgains.repoexpress.mongodb.MongodbEntityRepository;
import com.strategicgains.restexpress.Request;
import com.strategicgains.restexpress.Response;
import com.strategicgains.restexpress.exception.BadRequestException;
import com.strategicgains.restexpress.query.QueryFilter;
import com.strategicgains.restexpress.query.QueryOrder;
import com.strategicgains.restexpress.query.QueryRange;
import com.strategicgains.syntaxe.ValidationEngine;

public class OrderController
{
	private MongodbEntityRepository<Order> orders;
	
	public OrderController(MongodbEntityRepository<Order> orderRepository)
	{
		super();
		this.orders = orderRepository;
	}

	public String create(Request request, Response response)
	{
		Order order = request.getBodyAs(Order.class, "Order details not provided");
		ValidationEngine.validateAndThrow(order);
		Order saved = orders.create(order);

		// Construct the response for create...
		response.setResponseCreated();

		// Include the Location header...
		String locationPattern = request.getNamedUrl(HttpMethod.GET, Constants.KICKSTART_ORDER_ROUTE);
		response.addLocationHeader(LinkUtils.formatUrl(locationPattern, Constants.ORDER_ID_PARAMETER, saved.getId()));

		// Return the newly-created ID...
		return saved.getId();
	}

	public Order read(Request request, Response response)
	{
		String id = request.getUrlDecodedHeader(Constants.ORDER_ID_PARAMETER, "No Order ID supplied");
		Order result = orders.read(id);
		
		// Add 'self' link
		String selfPattern = request.getNamedUrl(HttpMethod.GET, Constants.KICKSTART_ORDER_ROUTE);
		String selfUrl = LinkUtils.formatUrl(selfPattern, Constants.ORDER_ID_PARAMETER, result.getId());
		result.addLink(new Link(RelTypes.SELF, selfUrl));

		return result;
	}

	public LinkableCollection<Order> readAll(Request request, Response response)
	{
		QueryFilter filter = QueryFilter.parseFrom(request);
		QueryOrder order = QueryOrder.parseFrom(request);
		QueryRange range = QueryRange.parseFrom(request, 20);
		List<Order> results = orders.readAll(filter, range, order);
		long count = orders.count(filter);
		response.setCollectionResponse(range, results.size(), count);
		
		// Add 'self' links
		String orderSelfPattern = request.getNamedUrl(HttpMethod.GET, Constants.KICKSTART_ORDER_ROUTE);

		for (Order result : results)
		{
			String selfUrl = LinkUtils.formatUrl(orderSelfPattern, Constants.ORDER_ID_PARAMETER, result.getId());
			result.addLink(new Link(RelTypes.SELF, selfUrl));
		}

		String selfUrl = request.getNamedUrl(HttpMethod.GET, Constants.KICKSTART_ORDER_COLLECTION_ROUTE);
//		String selfUrl = XLinkUtils.asLocationUrl(selfPattern, Constants.ORDER_ID_PARAMETER, orderId);
		LinkableCollection<Order> wrapper = new LinkableCollection<Order>(results);
		wrapper.addLink(new Link(RelTypes.SELF, selfUrl));
		return wrapper;
	}

	public void update(Request request, Response response)
	{
		String id = request.getUrlDecodedHeader(Constants.ORDER_ID_PARAMETER);
		Order order = request.getBodyAs(Order.class, "Order details not provided");
		
		if (!id.equals(order.getId()))
		{
			throw new BadRequestException("ID in URL and ID in Order must match");
		}

		ValidationEngine.validateAndThrow(order);
		orders.update(order);
		response.setResponseNoContent();
	}

	public void delete(Request request, Response response)
	{
		String id = request.getUrlDecodedHeader(Constants.ORDER_ID_PARAMETER, "No Order ID supplied");
		orders.delete(id);
		response.setResponseNoContent();
	}
}
