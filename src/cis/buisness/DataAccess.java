package cis.buisness;

import java.util.ArrayList;

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
		name = name.trim();
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
		oldName = oldName.trim();
		newName = newName.trim();
		return database.renameClient( oldName, newName );
	}


	public SoapBox getAllSoaps( String clientName )
	{
		clientName = clientName.trim();
		return database.getAllSoaps( clientName );
	}


	public SoapBox readSoaps( String clientName )
	{
		return database.readSoaps( clientName );
	}


	public Boolean insertSoapBox( SoapBox soap )
	{
		return database.insertSoapBox( soap );
	}
	
	
	public Boolean insertSoap( Soap soap, String clientName )
	{
		clientName = clientName.trim();
		return database.insertSoap( soap, clientName );
	}


	public Boolean deleteSoap( Soap soap )
	{
		return database.deleteSoap( soap );
	}


	public Boolean updateSoap( SoapBox soap, String oldMessage )
	{
		oldMessage = oldMessage.trim();
		return database.updateSoap( soap, oldMessage );
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
}
