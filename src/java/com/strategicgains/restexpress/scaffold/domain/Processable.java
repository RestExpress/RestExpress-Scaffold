package com.strategicgains.restexpress.scaffold.domain;

public interface Processable
{
	public boolean canProcess(String keyword);	
	public int process(String[] args, int beginWith);
}
