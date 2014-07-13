package cis.persistance;

import java.util.ArrayList;

import cis.objects.Client;
import cis.persistance.DBStub;

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
	 * METHOD:			initialize
	 *
	 * PURPOSE:			this will initialize the database, set up the tables,
	 * 					and get everything ready.
	------------------------------------------------------*/
	public void initialize( String dbName )
	{
		init(); // NOT THIS OH WOW DON'T DO THIS
		// TODO: init database
	}
	
	
	/*------------------------------------------------------
	 * METHOD:			close
	 *
	 * PURPOSE:			Close will write everything to the DB, 
	 * 					then begin shutting it down
	------------------------------------------------------*/
	public void close()
	{
		// TODO: write this method
	}

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
	 * METHOD:			getAllClients
	 *
	 * PURPOSE:			This method will return the entire list of
	 * 					clients. To be used in displaying them 
	 * 					and what not.
	------------------------------------------------------*/
	public ArrayList<Client> getAllClients()
	{
		ArrayList<Client> allClients = null;
		
		allClients = stubDB.getDB();

		return allClients;
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
	
	
	public void genClients()
	{
		Client one = new Client( "Pat Ricky" );
		Client two = new Client( "George Curious" );
		Client three = new Client( "Fred Freddy" );
		Client four = new Client( "Patty Rick" );
		Client five = new Client( "Travis Almighty" );
		stubDB.insert( one );
		stubDB.insert( two );
		stubDB.insert( three );
		stubDB.insert( four );
		stubDB.insert( five );
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
