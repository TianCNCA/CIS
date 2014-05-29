/*------------------------------------------------------
* CLASS:			DBIntermediary
*
* REMARKS:			This class will act as the interface to the DB
* 					It will handle all the inserts and deletes, as well
* 					as all the updates
*
------------------------------------------------------*/
public class DBIntermediary 
{
	private DB_Stub stubDB;
	
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
	* insertClient:
	* This class will take a Client Obj and that is it. It will
	* attept to insert it into the DB. If it is unsucessful, it
	* will return false. 
	* This is the interface Client Insert for the DB		
	------------------------------------------------------*/
	public Boolean insertClient( Client client )
	{
		Boolean didInsert = false;
		
		didInsert = stubDB.insert( client );
		
		return didInsert;
	}
	
	/*------------------------------------------------------
	* deleteClient:
	* This class will attempt to delete the client
	* This is the interface Client Delete for the DB		
	------------------------------------------------------*/
	public Boolean deleteClient( Client client )
	{
		Boolean didDelete = false;
		
		didDelete = stubDB.delete( client );
		
		return didDelete;
	}
	
	/*------------------------------------------------------
	* deleteClient:
	* This class will find a client object already in the system,
	* and replace/update it with the new information
	* This is the interface Client Update for the DB		
	------------------------------------------------------*/
	public Boolean updateClient( Client client )
	{
		Boolean didUpdate = false;
		
		didUpdate = stubDB.update( client );
		
		return didUpdate;
	}
	
	/*------------------------------------------------------
	* readClient:
	* This class will find a client object already in the system,
	* (hopefully) and return it to us.	
	------------------------------------------------------*/
	public Boolean readClient( int id )
	{
		Client newClient = new Client();
		
		return newClient;
	}
	
	/*------------------------------------------------------
	* readClient:
	* This class will find a client object already in the system,
	* (hopefully) and return it to us.	
	------------------------------------------------------*/
	public Boolean readClient( String firstName, String lastName )
	{
		Client newClient = null;
		
		return newClient;
	}
	
	public void DumpDB()
	{
		stubDB.dumpStub();
	}
	
	
	/*------------------------------------------------------
	* PRIVATE METHODS
	------------------------------------------------------*/
	
	/*------------------------------------------------------
	* init:
	* Setup the DB, load anydata that we already have in the DB
	* up, and general DB setup.
	------------------------------------------------------*/
	private void init()
	{
		//This will eventually be replaced with the DB
		stubDB = new DB_Stub();
	}
}
