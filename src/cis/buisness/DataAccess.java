package cis.buisness;

import java.util.ArrayList;

import app.Service;
import cis.persistance.DataBaseAccess;

public class DataAccess
{
	private DataBaseAccess database;
	
	public DataAccess()
	{
		database = Service.getDB();
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
	
	public Soap readSoap( String soapName )
	{
		return database.readSoap( soapName );
	}
	
	public Boolean insertSoap( Soap soap )
	{
		return database.insertSoap( soap );
	}

	public Boolean deleteSoap( Soap soap )
	{
		return database.deleteSoap( soap );
	}

	public Boolean updateSoap( Soap soap )
	{
		return database.updateSoap( soap );
	}
}
