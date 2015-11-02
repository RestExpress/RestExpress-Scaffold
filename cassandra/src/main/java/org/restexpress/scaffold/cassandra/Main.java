package org.restexpress.scaffold.cassandra;

import org.restexpress.util.Environment;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		Configuration config = Environment.load(args, Configuration.class);
		Server server = new Server(config);
		server.start().awaitShutdown();
	}
}
