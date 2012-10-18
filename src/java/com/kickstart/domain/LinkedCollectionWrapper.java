package com.kickstart.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.strategicgains.restexpress.domain.XLink;

/**
 * A wrapper for a Collection that enables addition of Hypermedia links.
 * Methods addLink() and addLinks() support method chaining.
 * 
 * @author toddf
 * @since Oct 18, 2012
 */
public class LinkedCollectionWrapper<E>
{
	private List<XLink> links = new ArrayList<XLink>();
	private Collection<E> items;

    public LinkedCollectionWrapper(Collection<E> items)
    {
    	setItems(items);
    }

	public LinkedCollectionWrapper<E> addLink(XLink link)
	{
		links.add(link);
		return this;
	}

	public LinkedCollectionWrapper<E> addLinks(List<XLink> links)
	{
		links.addAll(links);
		return this;
	}

	public List<XLink> getLinks()
	{
		return Collections.unmodifiableList(links);
	}

	public Collection<E> getItems()
	{
		return Collections.unmodifiableCollection(items);
	}

	private void setItems(Collection<E> items)
	{
		this.items = items;
	}
}
