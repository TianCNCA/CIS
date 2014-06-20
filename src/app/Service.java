package app;

import cis.persistance.DataBaseAccess;

public class Service
{
	private static DataBaseAccess database;
	
	public Service()
	{
		database = new DataBaseAccess();
		database.init();
		database.genClients();
	}
	
	public void shutDownDB()
	{
		database.shutdownDB();
	}
	
	public static DataBaseAccess getDB()
	{
		return database;
	}
}
