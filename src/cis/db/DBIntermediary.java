package cis.db;

import cis.buisness.Client;
import cis.db.DBStub;

/*------------------------------------------------------
 * CLASS:			DBIntermediary
 *
 * REMARKS:			This class will act as the interface to the DB
 * 					It will handle all the inserts and deletes, as well
 * 					as all the updates, and some other DB fuctions that
 *  					are important
 *
 ------------------------------------------------------*/
public class DBIntermediary
{
	private DBStub stubDB;
	private Integer dbSize;

	/*------------------------------------------------------
	 * Main/Only Constructor
	------------------------------------------------------*/
	public DBIntermediary()
	{
		init();
	}

	/*------------------------------------------------------
	 * PUBLIC METHODS
	------------------------------------------------------*/

	/*------------------------------------------------------
	 * METHOD:			insertClient
	 *
	 * PURPOSE:			This method will take a Client Object and that is it. It will
	 * 					attempt to insert it into the DB. If it is unsuccessful, it
	 * 					will return false. 	
	------------------------------------------------------*/
	public Boolean insertClient( Client client )
	{
		Boolean didInsert = false;

		// Check for the Client already. This will become redundant in the
		// actual DB (hopefully)
		if ( readClient( client.getName() ) == null )
		{
			didInsert = stubDB.insert( client );

			if ( didInsert )
			{
				dbSize++;
			}
		}

		return didInsert;
	}

	
	/*------------------------------------------------------
	 * METHOD:			deleteClient
	 *
	 * PURPOSE:			This class will attempt to delete the client
	 * 					This is the interface Client Delete for the DB	
	------------------------------------------------------*/
	public Boolean deleteClient( Client client )
	{
		Boolean didDelete = false;
		didDelete = stubDB.delete( client );

		if ( didDelete )
		{
			dbSize--;
		}

		return didDelete;
	}

	
	/*------------------------------------------------------
	 * METHOD:			updateClient
	 *
	 * PURPOSE:			This method will find a client object already in the system,
	 * 					and replace/update it with the new information
	 * 					This is the interface Client Update for the DB		
	 * 
	 * 					NOTE: This might need to change...
	------------------------------------------------------*/
	public Boolean updateClient( Client updatedClient )
	{
		Boolean didUpdate = false;
		int currSize = dbSize;
		int afterSize;

		didUpdate = stubDB.update( updatedClient );
		afterSize = stubDB.size();

		assert ( currSize == afterSize );

		return didUpdate;
	}

	
	/*------------------------------------------------------
	 * METHOD:			renameClient
	 *
	 * PURPOSE:			This method will change a clients name only. Since name is
	 * 					the Primary Key, we have to change it in a separate method.
	 * 					Will check to see if the name doesn't exist yet.
	------------------------------------------------------*/
	public Boolean renameClient( String oldName, String newName )
	{
		Boolean didRename = false;
		Client client = readClient( oldName );

		didRename = deleteClient( client );
		client.setName( newName );
		didRename = insertClient( client );

		return didRename;
	}

	
	/*------------------------------------------------------
	 * METHOD:			readClient
	 *
	 * PURPOSE:			This method will find a client object already in the system,
	 * 					(hopefully) and return it to us. Returns null if nothing found
	------------------------------------------------------*/
	public Client readClient( String name )
	{
		Client newClient = null;

		newClient = stubDB.find( name );

		return newClient;
	}

	
	/*------------------------------------------------------
	 * METHOD:			readClient
	 *
	 * PURPOSE:			This method will take a client object, and just call readClient
	 * 					based upon the name of that client
	------------------------------------------------------*/
	public Client readClient( Client client )
	{
		Client newClient = null;

		newClient = readClient( client.getName() );

		return newClient;
	}

	
	/*------------------------------------------------------
	 * METHOD:			DumpDB
	 *
	 * PURPOSE:			returns a String of the entire DB.
	 * 					This might change later on, be careful using it!
	------------------------------------------------------*/
	public String DumpDB()
	{
		return stubDB.dumpStub();
	}

	
	/*------------------------------------------------------
	 * METHOD:			getSize
	 *
	 * PURPOSE:			returns the size of the DB according to DB
	------------------------------------------------------*/
	public int getSize()
	{
		return stubDB.size();
	}

	
	/*------------------------------------------------------
	 * METHOD:			getINterSize
	 *
	 * PURPOSE:			returns the size of the DB according to 
	 *  					the IntermediaryDB.
	------------------------------------------------------*/
	public int getInterSize()
	{
		return dbSize;
	}

	
	/*------------------------------------------------------
	 * PRIVATE METHODS
	------------------------------------------------------*/

	/*------------------------------------------------------
	 * METHOD:			init
	 *
	 * PURPOSE:			Setup the DB, load any data that we already have in the DB
	 * 					up, and general DB setup.
	------------------------------------------------------*/
	private void init()
	{
		// This will eventually be replaced with the DB
		stubDB = new DBStub();
		dbSize = 0;
	}
}
