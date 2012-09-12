package com.strategicgains.restexpress.scaffold.domain;

public class Domain
extends AbstractProcessable
{
	@Override
	protected String getKeyword()
	{
		return "domain";
	}

	@Override
	public int process(String[] args, int beginWith)
	{
		System.out.println("Processing domain command");
		return 2;
	}
}
