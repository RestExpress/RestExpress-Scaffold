package com.kickstart.serialization;

import com.kickstart.domain.Order;
import com.strategicgains.restexpress.domain.LinkableCollection;
import com.strategicgains.restexpress.serialization.xml.DefaultXmlProcessor;

public class XmlSerializationProcessor
extends DefaultXmlProcessor
{
	public XmlSerializationProcessor()
    {
	    super();
	    alias("order", Order.class);
	    alias("collection", LinkableCollection.class);
//		alias("element_name", Element.class);
//		alias("element_name", Element.class);
//		alias("element_name", Element.class);
//		alias("element_name", Element.class);
		registerConverter(new XstreamObjectIdConverter());
    }
}
