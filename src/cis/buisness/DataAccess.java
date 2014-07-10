package cis.buisness;

import java.util.ArrayList;
import java.util.UUID;

import app.DBService;
import cis.persistance.DataBaseAccess;

public class DataAccess
{
	private DataBaseAccess database;

	public DataAccess()
	{
		database = DBService.getDB();
	}


	public ArrayList<Client> getAllClients()
	{
		return database.getAllClients();
	}


	public Client readClient( String name )
	{
		return database.readClient( name );
	}


	public Client readClient( Client name )
	{
		return database.readClient( name );
	}


	public Boolean insertClient( Client client )
	{
		return database.insertClient( client );
	}


	public Boolean deleteClient( Client client )
	{
		return database.deleteClient( client );
	}


	public Boolean updateClient( Client updatedClient )
	{
		return database.updateClient( updatedClient );
	}


	public Boolean renameClient( String oldName, String newName )
	{
		return database.renameClient( oldName, newName );
	}


	public SoapBox readSoaps( String clientName )
	{
		return database.readSoaps( clientName );
	}
	
	
	public Soap readSoap( UUID id )
	{
		return database.readSoap( id );
	}


	public Boolean insertSoapBox( SoapBox soap )
	{
		return database.insertSoapBox( soap );
	}
	
	
	public Boolean insertSoap( Soap soap, String clientName )
	{
		return database.insertSoap( soap, clientName );
	}


	public Boolean deleteSoap( Soap soap )
	{
		return database.deleteSoap( soap );
	}


	public Boolean updateSoap( Soap soap )
	{
		return database.updateSoap( soap );
	}
	
	
	public Boolean insertHistory( ClientHistory history )
	{
		return database.insertHistory( history );
	}
	
	public ClientHistory readHistory( String clientName )
	{
		return database.readHistory( clientName );
	}
	
	public Boolean updateHistory( ClientHistory history )
	{
		return database.updateHistory( history );
	}
	
	public int getSize()
	{
		return database.getSize();
	}
	
	
	public void dbResetForTesting()
	{
		if ( DBService.isTesting() )
		{
			System.out.println("WARNING! RESETING DATABASE!!!");
			database.resetID();
		}
	}
	
	
	public int getClientCount()
	{
		return database.getClientCount();
	}
	
	
	public int getSoapCount()
	{
		return database.getSoapCount();
	}
	
	public int getHistCount()
	{
		return database.getHistCount();
	}
}
