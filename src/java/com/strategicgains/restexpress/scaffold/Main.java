package com.strategicgains.restexpress.scaffold;

import com.strategicgains.restexpress.scaffold.domain.Domain;
import com.strategicgains.restexpress.scaffold.domain.Processable;
import com.strategicgains.restexpress.scaffold.domain.Project;
import com.strategicgains.restexpress.scaffold.domain.Support;

public class Main
{
	private static final Processable[] PROCESSABLES = new Processable[3];
	
	static
	{
		PROCESSABLES[0] = new Project();
		PROCESSABLES[1] = new Domain();
		PROCESSABLES[2] = new Support();
	};

	/**
	 * scaffold project com.pearson.BloggingService
	 * scaffold support mongodb support validation
	 * scaffold domain Enrollment support mongodb
	 *  
	 * @param args
	 * @throws CommandLineException 
	 */
	public static void main(String[] args)
	throws Exception
	{
		if (args.length < 2)
		{
			help(null);
			System.exit(1);
		}
		
		boolean isProcessed = false;
		int argv = 0;

		for (Processable p : PROCESSABLES)
		{
			if (p.canProcess(args[argv]))
			{
				isProcessed = true;
				argv = p.process(args, ++argv);
			}
		}

		if (!isProcessed)
		{
			help("Invalid command-line options");
		}
	}

	private static void help(String message)
    {
		if (message != null) System.out.println(message);

		System.out.println("\nUsage: scaffold [project <package/project name>]");
		System.out.println("where commands are:");
		System.out.println("\tproject <package/project name>");
		System.out.println("\tsupport mongodb | voldemort | redis | validation");
		System.out.println("\tdomain <model name> [persist <db support option>]");
		System.out.println("\nFor example:");
		System.out.println("\tscaffold project com.pearson.example.BloggingService support mongodb support validation");
		System.out.println("\tscaffold support mongodb support redis support validation");
		System.out.println("\tscaffold domain BlogEntry persist mongodb");
    }
}
