package cis.buisness;

import java.util.ArrayList;

import app.DBService;
import cis.persistance.DataBaseAccess;

public class DataAccess
{
	private DataBaseAccess database;
	private static DataAccess fullList = new DataAccess();
	private static ArrayList<Client> allClients = fullList.getAllClients();


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


	public SoapBox getAllSoaps( String clientName )
	{
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
		return database.insertSoap( soap, clientName );
	}


	public Boolean deleteSoap( Soap soap )
	{
		return database.deleteSoap( soap );
	}


	public Boolean updateSoap( SoapBox soap, String oldMessage )
	{
		return database.updateSoap( soap, oldMessage );
	}
	
	
	public int getSize()
	{
		return database.getSize();
	}
	
	public ArrayList<Client> searchClients( String target )
	{
		ArrayList<Client> searchSet = new ArrayList<Client>();
		
		for (Client c : allClients)
		{
			if (!(c.getName().toLowerCase().contains(target.toLowerCase()))) // TODO: code smell? more like code REEKS! refactor this ASAP
				{
					searchSet.add(c);
				}
		}
		return searchSet;
		
	}
}
