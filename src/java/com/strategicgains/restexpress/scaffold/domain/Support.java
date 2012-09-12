package com.strategicgains.restexpress.scaffold.domain;

public class Support
extends AbstractProcessable
{
	@Override
	protected String getKeyword()
	{
		return "support";
	}

	@Override
	public int process(String[] args, int beginWith)
	{
		System.out.println("Processing support command");
		return 2;
	}
}
