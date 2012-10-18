package com.kickstart.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.strategicgains.repoexpress.mongodb.AbstractMongodbEntity;
import com.strategicgains.restexpress.domain.XLink;

/**
 * @author toddf
 * @since Oct 18, 2012
 */
public abstract class AbstractLinkedEntity
extends AbstractMongodbEntity
{
	private List<XLink> links;

	public List<XLink> getLinks()
	{
		return Collections.unmodifiableList(links);
	}

	public void setLinks(List<XLink> links)
	{
		this.links = new ArrayList<XLink>(links);
	}
	
	public void addLink(XLink link)
	{
		if (links == null)
		{
			links = new ArrayList<XLink>();
		}
		
		links.add(link);
	}
}
