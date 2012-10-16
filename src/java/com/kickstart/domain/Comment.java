package com.kickstart.domain;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Indexed;
import com.strategicgains.repoexpress.mongodb.AbstractMongodbEntity;
import com.strategicgains.syntaxe.annotation.StringValidation;

@Entity("comments")
public class Comment
extends AbstractMongodbEntity
{
	@Indexed
	@StringValidation(name="Blog Entry ID", required=true)
	private String blogEntryId;
	
	@StringValidation(name="Author", required=true)
	private String author;
	
	@StringValidation(name="Comment Content", required=true)
	private String content;

	public String getBlogEntryId()
    {
    	return blogEntryId;
    }

	public void setBlogEntryId(String blogEntryId)
    {
    	this.blogEntryId = blogEntryId;
    }

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}
}
