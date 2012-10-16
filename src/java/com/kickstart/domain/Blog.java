package com.kickstart.domain;

import com.google.code.morphia.annotations.Entity;
import com.strategicgains.repoexpress.mongodb.AbstractMongodbEntity;
import com.strategicgains.syntaxe.annotation.StringValidation;

@Entity("blogs")
public class Blog
extends AbstractMongodbEntity
{
	@StringValidation(name = "Blog Title", required = true)
	private String title;
	private String description;

	public Blog()
	{
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
}
