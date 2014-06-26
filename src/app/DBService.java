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
	private static DataBaseAccess database;
	private Boolean valid;


	public DBService()
	{
		valid = false;
		database = null;
	}


	public void initializeDB()
	{
		database = new DataBaseAccess();
		valid = database.init();
	}


	// THIS SHOULD ALWAYS BE CALLED!
	public void shutDownDB()
	{
		database.shutdownDB();
	}


	public static DataBaseAccess getDB()
	{
		return database;
	}


	public Boolean getValid()
	{
		return valid;
	}


	public void genClients()
	{
		database.genClients();
	}
}
