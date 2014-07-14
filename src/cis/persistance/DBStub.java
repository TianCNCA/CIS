package cis.persistance;

import java.util.ArrayList;

import cis.objects.Client;

/*------------------------------------------------------
* CLASS:			DB_STub
*
* REMARKS:			This class acts as a pretend DB, where we
* 					insert, update, and delete into
*
------------------------------------------------------*/
class DBStub 
{
	private ArrayList<Client> clientDB;
	
	//Constructor
	public DBStub()
	{
		clientDB = new ArrayList<Client>();
	}
	
	public Boolean insert( Client newClient )
	{
		return ( clientDB.add( newClient ) );
	}
	
	public Boolean delete( Client clientToRem )
	{
		return ( clientDB.remove( clientToRem ) );
	}
	
	public ArrayList<Client> getDB()
	{
		return clientDB;
	}
	
	
	
	public Client find( String name )
	{
		Client  foundClient = null;
		Boolean found 	 	= false;
		
		for (int i = 0; i < clientDB.size() && !found; i++) 
		{
			foundClient = clientDB.get( i );
			
			if ( foundClient.getName().equals( name ) )
			{
				found = true;
			}
		}
		
		if ( !found )
		{
			foundClient = null;
		}
		
		return foundClient;
	}
	
	

	public Boolean update( Client updatedClient )
	{
		Client  oldClient = null;
		Boolean didUpdate = false;
		String clientToUpdate = updatedClient.getName();
		
		if ( clientToUpdate == updatedClient.getName() )
		{
			oldClient = find( clientToUpdate );
			
			if ( oldClient != null )
			{
				didUpdate = delete( oldClient );
				
				if ( didUpdate )
				{
					didUpdate = insert( updatedClient );
				}
			}
		}
		
		return didUpdate;
	}
	
	public int size()
	{
		return clientDB.size();
	}
	
	public String dumpStub()
	{
		int i;
		String dbDump = "[";
		
		for ( i = 0; i < clientDB.size() - 1; i++) 
		{
			dbDump += clientDB.get( i ).getName() + ", ";
		}
		
		dbDump += clientDB.get( i ).getName() + "]" ;
		
		return dbDump;
	}
}
