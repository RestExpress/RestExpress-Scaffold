package com.strategicgains.restexpress.scaffold.minimal;

public class Constants
{
	/**
	 * These define the URL parmaeters used in the route definition strings (e.g. '{userId}').
	 */
	public class UrlId
	{
		//TODO: Your URL parameter names here...
		public static final String ITEM = "userId";
	}

	/**
	 * These define the route names used in naming each route definitions.  These names are used
	 * to retrieve URL patterns within the controllers by name to create links in responses.
	 */
	public class Routes
	{
		//TODO: Your Route names here...
		public static final String SINGLE_ITEM = "item.single.route";
		public static final String ITEM_COLLECTION = "item.collection.route";
	}
}
