package com.strategicgains.restexpress.scaffold.domain;

public abstract class AbstractProcessable
implements Processable
{
	@Override
	public boolean canProcess(String keyword)
	{
		if (keyword == null || keyword.trim().isEmpty()) return false;
		
		return getKeyword().equalsIgnoreCase(keyword);
	}
	
	protected abstract String getKeyword();
}
