package org.restexpress.scaffold.mongodb.serialization;

import org.restexpress.scaffold.mongodb.domain.SampleUuidEntity;
import org.serialization.xml.XstreamXmlProcessor;

import com.strategicgains.hyperexpress.domain.Link;
import com.strategicgains.hyperexpress.domain.LinkableCollection;

public class XmlSerializationProcessor
extends XstreamXmlProcessor
{
	public XmlSerializationProcessor()
    {
	    super();
	    alias("sample", SampleUuidEntity.class);
		alias("link", Link.class);
		alias("collection", LinkableCollection.class);
//		alias("element_name", Element.class);
//		alias("element_name", Element.class);
//		alias("element_name", Element.class);
//		alias("element_name", Element.class);
		registerConverter(new XstreamUuidConverter());
    }
}
