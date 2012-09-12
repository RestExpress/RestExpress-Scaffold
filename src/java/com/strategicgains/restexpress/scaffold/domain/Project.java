package com.strategicgains.restexpress.scaffold.domain;

public class Project
extends AbstractProcessable
{
	@Override
	protected String getKeyword()
	{
		return "project";
	}

	@Override
	public int process(String[] args, int beginWith)
	{
		System.out.println("Processing project command");
		return 2;
	}
}
