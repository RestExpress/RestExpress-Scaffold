package com.kickstart.domain;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Indexed;
import com.strategicgains.repoexpress.mongodb.AbstractMongodbEntity;
import com.strategicgains.syntaxe.annotation.StringValidation;

@Entity("blog_entries")
public class BlogEntry
extends AbstractMongodbEntity
{
	@Indexed
	@StringValidation(name="Blog ID", required=true)
	private String blogId;

	@StringValidation(name="Title", required=true)
	private String title;
	
	@StringValidation(name="Entry Content", required=true)
	private String content;
	
	@Indexed
	@StringValidation(name="Author", required=true)
	private String author;

	public String getBlogId()
    {
    	return blogId;
    }

	public void setBlogId(String blogId)
    {
    	this.blogId = blogId;
    }

	public String getTitle()
    {
    	return title;
    }

	public void setTitle(String title)
    {
    	this.title = title;
    }

	public String getContent()
    {
    	return content;
    }

	public void setContent(String content)
    {
    	this.content = content;
    }

	public String getAuthor()
    {
    	return author;
    }

	public void setAuthor(String author)
    {
    	this.author = author;
    }

//	public List<Comment> getComments()
//    {
//    	return (comments.isEmpty() ? null : Collections.unmodifiableList(comments));
//    }
//	
//	public void addComment(Comment comment)
//	{
//		comments.add(comment);
//	}
//	
//	public boolean removeComment(Comment comment)
//	{
//		return comments.remove(comment);
//	}
//	
//	public int getCommentsSize()
//	{
//		return comments.size();
//	}
}
