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
}
