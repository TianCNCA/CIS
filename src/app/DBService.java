package app;

import cis.persistance.DataBaseAccess;

/*------------------------------------------------------
 * CLASS:			DBService
 *
 * REMARKS:			DBService is what we need to do to setup the initial
 * 					db. 
 *
 ------------------------------------------------------*/
public class DBService
{
	private static 	DataBaseAccess 	database;
	private 		Boolean 		valid;
	private static 	Boolean 		testing;


	public DBService()
	{
		System.out.println("Creating DB Service");
		valid	 = false;
		testing  = false;
		database = null;
	}


	public void initializeDB()
	{
		System.out.println("Initializing DB Service");
		database = new DataBaseAccess();
		valid 	 = database.init();
	}


	// THIS SHOULD ALWAYS BE CALLED!
	public void shutDownDB()
	{
		System.out.println("Shutting down DB Service");
		database.shutdownDB();
		testing = false;
		valid   = false;
	}


	public static DataBaseAccess getDB()
	{
		return database;
	}
	
	
	public static Boolean isTesting()
	{
		return testing;
	}


	public Boolean getValid()
	{
		return valid;
	}
	
	
	public void setTesting()
	{
		testing = true;
	}


	public void genClients()
	{
		database.genClients();
	}
}
