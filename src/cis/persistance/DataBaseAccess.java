package cis.persistance;

import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.SQLRecoverableException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLWarning;

import cis.buisness.Client;
import cis.buisness.Soap;

/*------------------------------------------------------
 * CLASS:			DataBaseAccess
 *
 * REMARKS:			This class will act as the interface to the DB
 * 					It will handle all the inserts and deletes, as well
 * 					as all the updates, and some other DB functions that
 *  				are important
 *
 ------------------------------------------------------*/
@SuppressWarnings( "unused" )
public class DataBaseAccess
{
	private String 				dbName;
	private String 				dbDriver;
	private Integer 			dbSize;
	//private ArrayList<Client> 	allClients;
	private ArrayList<Soap> 	allSoaps;
	
	// DB Specifics
	private Statement 			sqlStatement;
	private Connection 			dbConnection;
	private ResultSet 			dbResult;
	private String 				sqlCommand;

	/*------------------------------------------------------
	 * Main/Only Constructor
	------------------------------------------------------*/
	public DataBaseAccess()
	{
		dbName 	 = "ClientSystem";
		dbDriver = "org.hsqldb.jdbcDriver";
		dbSize 	 = 0;
	}

	
	/*------------------------------------------------------
	 * METHOD:			initializeDB
	 *
	 * PURPOSE:			this will initialize the database, set up the tables,
	 * 					and get everything ready.
	------------------------------------------------------*/
	private void initializeDB()
	{
		// The location for the DB
		String dbLocation = "jdbc:hsqldb:database/" + dbName;
		
		// Attempt to connect or create the DB. If the DB does not already exist,
		// this will auto create it for us.
		try
		{
			Class.forName( dbDriver ).newInstance();
			dbConnection = DriverManager.getConnection( dbLocation, "SA", "" );
			sqlStatement = dbConnection.createStatement();
		}
		catch ( Exception ex )
		{
			System.out.println( ex );
		}
	}


	/*------------------------------------------------------
	 * METHOD:			init
	 *
	 * PURPOSE:			Setup the DB, load any data that we already have in the DB
	 * 					up, and general DB setup.
	------------------------------------------------------*/
	public Boolean init()
	{
	Boolean initiated = false;

	// Build or load the DB
	dbSize = 0;
	initiated = initializeDB();

	return initiated;
	}


	/*------------------------------------------------------
	 * METHOD:			close
	 *
	 * PURPOSE:			Close will write everything to the DB, 
	 * 					then begin shutting it down
	------------------------------------------------------*/
	public void shutdownDB()
	{
		try
        {
			sqlCommand = "shutdown compact";
	        dbResult = sqlStatement.executeQuery( sqlCommand );
	        dbConnection.close();
        }
        catch ( SQLException ex )
        {
	        System.out.println( ex );
        }
		
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
		String  insertString;
		
		try
		{
			insertString = 
					  "'" + client.getName() + "'" + ", " 
				    + "'" + client.getDOB()  + "'" + ", "
					+ "'" + client.getHomePhone() + "'" + ", "
				    + "'" + client.getWorkPhone() + "'" + ", "
					+ "'" + client.getAddress() + "'"  + ", " 
					+ "'" + client.getCity() + "'"  + ", " 
					+ "'" + client.getProvince() + "'"  + ", " 
					+ "'" + client.getPostCode() + "'"  +	", " 
						  + client.getPhysician() + ", " 
						  + client.getPhysioTherapist() + ", " 
						  + client.getChiropractor() + ", " 
						  + client.getExperience() + ", " 
					+ "'" + client.getReason() + "'"  + ", " 
						  + client.getDiet() + ", " 
						  + client.getMedication() + ", " 
						  + client.getInsulin() + ", " 
						  + client.getUncontrolled() + ", " 
					+ "'" + client.getOccupation() + "'"  + ", " 
					+ "'" + client.getSports() + "'"  + ", " 
					+ "'" + client.getSleepPattern()  + "'" + ", " 
					+ "0" + ", " 
					+ "0" +	", " 
					+ "0"  + ", " 
					+ "0";
			
			sqlCommand = "Insert into Clients " + "Values(" + insertString + ")";
			System.out.println( sqlCommand );
			didInsert = sqlStatement.execute( sqlCommand );
			System.out.println( didInsert );
			System.out.println( "HERE" );
		}
		catch ( SQLException ex )
		{
			System.out.println( ex );
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
		int 	result;
		String  updateString, where;
		
		try
        {
			updateString = 
					  	  "'" + updatedClient.getName() + "'" + ", " 
					    + "'" + updatedClient.getDOB()  + "'" + ", "
						+ "'" + updatedClient.getHomePhone() + "'" + ", "
					    + "'" + updatedClient.getWorkPhone() + "'" + ", "
						+ "'" + updatedClient.getAddress() + "'"  + ", " 
						+ "'" + updatedClient.getCity() + "'"  + ", " 
						+ "'" + updatedClient.getProvince() + "'"  + ", " 
						+ "'" + updatedClient.getPostCode() + "'"  +	", " 
							  + updatedClient.getPhysician() + ", " 
							  + updatedClient.getPhysioTherapist() + ", " 
							  + updatedClient.getChiropractor() + ", " 
							  + updatedClient.getExperience() + ", " 
						+ "'" + updatedClient.getReason() + "'"  + ", " 
							  + updatedClient.getDiet() + ", " 
							  + updatedClient.getMedication() + ", " 
							  + updatedClient.getInsulin() + ", " 
							  + updatedClient.getUncontrolled() + ", " 
						+ "'" + updatedClient.getOccupation() + "'"  + ", " 
						+ "'" + updatedClient.getSports() + "'"  + ", " 
						+ "'" + updatedClient.getSleepPattern()  + "'" + ", " 
						+ "0" + ", " 
						+ "0" +	", " 
						+ "0"  + ", " 
						+ "0";
			
			where = "WHERE Name = " + updatedClient.getName();
			sqlCommand = "UPDATE CLIENTS SET " + updateString + " " + where;
			result = sqlStatement.executeUpdate( sqlCommand );
			
			if ( result == 1 )
			{
				didUpdate = true;
			}
			
        }
        catch ( SQLException e )
        {
	        System.out.println( e );
        }
		
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
		String address, city, province, postalCode, 
			   reason, occupation, sports, sleep,
			   DOB, homePhone, workPhone;
		
		try
        {
			sqlCommand = "SELECT * FROM CLIENTS WHERE Name = '" + name + "'";
	        dbResult = sqlStatement.executeQuery( sqlCommand );
        }
        catch ( SQLException e )
        {
	        System.out.println( e );
        }
		
		try
        {
	        while( dbResult.next() )
	        {
	        	name 		= dbResult.getString( "Name" );
	        	address 	= dbResult.getString( "Address" );
	        	city 		= dbResult.getString( "City" );
	        	province 	= dbResult.getString( "Province" );
	        	postalCode	= dbResult.getString( "PostalCode" );
	        	reason 		= dbResult.getString( "Reason" );
	        	occupation 	= dbResult.getString( "Occupation" );
	        	sports 		= dbResult.getString( "Sports" );
	        	sleep 		= dbResult.getString( "Sleep" );
	        	DOB 		= dbResult.getString( "DOB" );
	        	homePhone 	= dbResult.getString( "Homephone" );
	        	workPhone 	= dbResult.getString( "Workphone" );
	        	sleep 		= dbResult.getString( "Sleep" );
	        	
	        	newClient 	= new Client( name );
	        	
	        	newClient.setAddress( address );
	        	newClient.setDOB( DOB );
	        	newClient.setCity( city );
	        	newClient.setProvince( province );
	        	newClient.setPostCode( postalCode );
	        	newClient.setReason( reason );
	        	newClient.setOccupation( occupation );
	        	newClient.setSports( sports );
	        	newClient.setSleepPattern( sleep );
	        }
        }
        catch ( SQLException e )
        {
        	System.out.println( e );
        }

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

		return newClient;
	}


	/*------------------------------------------------------
	 * METHOD:			getAllClients
	 *
	 * PURPOSE:			This method will simply return a list of clients. There is not much more info
	 * 					returned at the moment
	------------------------------------------------------*/
	public ArrayList<Client> getAllClients()
	{
		// Reset all Clients
		Client client;
		String name, address, city;
		ArrayList<Client> allClients = new ArrayList<Client>();
		
		try
        {
			sqlCommand = "SELECT * FROM CLIENTS";
	        dbResult = sqlStatement.executeQuery( sqlCommand );
        }
        catch ( SQLException e )
        {
	        System.out.println( e );
        }
		
		try
        {
	        while( dbResult.next() )
	        {
	        	name 	= dbResult.getString( "Name" );
	        	address = dbResult.getString( "Address" );
	        	city 	= dbResult.getString( "City" );
	        	client 	= new Client( name );
	        	client.setAddress( address );
	        	client.setCity( city );
	        	allClients.add( client );
	        }
        }
        catch ( SQLException e )
        {
        	System.out.println( e );
        }

		return allClients;
	}


	/*------------------------------------------------------
	 * METHOD:			getAllSoaps
	 *
	 * PURPOSE:			This method will return the entire list of
	 * 					soaps. To be used in displaying them 
	 * 					and what not.
	------------------------------------------------------*/
	public ArrayList<Soap> getAllSoaps()
	{
		// Reset all Clients
		allSoaps = null;

		return allSoaps;
	}
	
	
	/*------------------------------------------------------
	 * METHOD:			readSoap
	 *
	 * PURPOSE:			This method will take a client name String, and search
	 * 					for the appropriate soap.
	------------------------------------------------------*/
	public Soap readSoap( String clientName )
	{
		Soap soap = null;

		return soap;
	}
	
	
	/*------------------------------------------------------
	 * METHOD:			insertSoap
	 *
	 * PURPOSE:				
	------------------------------------------------------*/
	public Boolean insertSoap( Soap client )
	{
		Boolean didInsert = false;

		return didInsert;
	}


	/*------------------------------------------------------
	 * METHOD:			deleteSoap
	 *
	 * PURPOSE:			This class will attempt to delete the soap
	------------------------------------------------------*/
	public Boolean deleteSoap( Soap client )
	{
		Boolean didDelete = false;

		return didDelete;
	}


	/*------------------------------------------------------
	 * METHOD:			updateSoap
	 *
	 * PURPOSE:			This method will find a soap object already in the system,
	 * 					and replace/update it with the new information
	------------------------------------------------------*/
	public Boolean updateSoap( Soap updatedClient )
	{
		Boolean didUpdate = false;

		return didUpdate;
	}


	/*------------------------------------------------------
	 * METHOD:			DumpDB
	 *
	 * PURPOSE:			returns a String of the entire DB.
	 * 					This might change later on, be careful using it!
	------------------------------------------------------*/
	public String DumpDB()
	{
		assert( false );
		return null;
	}


	/*------------------------------------------------------
	 * METHOD:			getSize
	 *
	 * PURPOSE:			returns the size of the DB according to DB
	------------------------------------------------------*/
	public int getSize()
	{
		return dbSize;
	}


	/*------------------------------------------------------
	 * METHOD:			getINterSize
	 *
	 * PURPOSE:			returns the size of the DB according to 
	 *  					the IntermediaryDB.
	------------------------------------------------------*/
	public int getInterSize()
	{
		assert ( false );
		return dbSize;
	}


	public void genClients()
	{
		Client one = new Client( "Pat Ricky" );
		Client two = new Client( "George Curious" );
		Client three = new Client( "Fred Freddy" );
		Client four = new Client( "Patty Rick" );
		Client five = new Client( "Travis Almighty" );
		insertClient( one );
		insertClient( two );
		insertClient( three );
		insertClient( four );
		insertClient( five );
	}
}
