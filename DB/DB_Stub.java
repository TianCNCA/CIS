import java.util.ArrayList;

import buisness.Client;

/*------------------------------------------------------
* CLASS:			DB_STub
*
* REMARKS:			This class acts as a pretend DB, where we
* 					insert, update, and delete into
*
------------------------------------------------------*/
class DB_Stub 
{
	ArrayList<Client> clientDB;
	
	//Constructor
	public DB_Stub()
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
	
	public Boolean update( Client updateClient )
	{
		int i;
		
		for ( i = 0; i < clientDB.size(); i++ ) 
		{
			if ( ( clientDB.get(i) ).equals( updateClient ) )
			{
				break;
			}
		}
		
		clientDB.remove( i );
		clientDB.add( updateClient );
	}
	
	public void dumpStub()
	{
		int i;
		System.out.print("[");
		
		for ( i = 0; i < clientDB.size() - 1; i++) 
		{
			System.out.print( clientDB.get( i ) + ", " );
		}
		i++;
		System.out.println( clientDB.get( i ) + "]" );
	}
}
