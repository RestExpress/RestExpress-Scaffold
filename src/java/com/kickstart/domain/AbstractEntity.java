package com.kickstart.domain;

import java.util.Date;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import com.strategicgains.repoexpress.domain.TimestampedIdentifiable;
import com.strategicgains.syntaxe.AbstractValidatable;

public abstract class AbstractEntity
extends AbstractValidatable
implements TimestampedIdentifiable
{
	@Id
	private ObjectId id;
	
	@Indexed
	private Date createdAt;
	private Date updatedAt;

	@Override
	public String getId()
	{
		return (id == null ? null : id.toString());
	}

	@Override
	public void setId(String id)
	{
		this.id = (id == null ? null : new ObjectId(id));
	}

	@Override
	public Date getCreatedAt()
	{
		return createdAt;
	}

	@Override
	public void setCreatedAt(Date createdAt)
	{
		this.createdAt = createdAt;
	}

	@Override
	public Date getUpdatedAt()
	{
		return updatedAt;
	}

	@Override
	public void setUpdatedAt(Date updatedAt)
	{
		this.updatedAt = updatedAt;
	}
}
