package cis.persistance;

import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;
import java.sql.SQLRecoverableException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLWarning;

import app.DBService;
import cis.buisness.Client;
import cis.buisness.Soap;
import cis.buisness.SoapBox;

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
	private Integer 			key;
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
		key 	 = -1;
	}

	
	/*------------------------------------------------------
	 * METHOD:			initializeDB
	 *
	 * PURPOSE:			this will initialize the database, set up the tables,
	 * 					and get everything ready.
	------------------------------------------------------*/
	private Boolean initializeDB()
	{
		Boolean initiated = false;
		// The location for the DB : URL
		String dbLocation = "jdbc:hsqldb:database/" + dbName;
		
		// Attempt to connect or create the DB. If the DB does not already exist,
		// this will auto create it for us.
		try
		{
			Class.forName( dbDriver ).newInstance();
			dbConnection = DriverManager.getConnection( dbLocation, "SA", "" );
			sqlStatement = dbConnection.createStatement();
			
			try
	        {
				sqlCommand = "SELECT * FROM ID;";
		        dbResult = sqlStatement.executeQuery( sqlCommand );
	        }
	        catch ( SQLException e )
	        {
	        	initiated = false;
		        System.out.println( e );
	        }
			
			try
	        {
		        while( dbResult.next() )
		        {
		        	key	= dbResult.getInt( "ID" );
		        	
		        	// we made it this far! We need ID to be set.
		        	if ( key > -1 )
		        	{
		        		initiated = true;
		        	}
		        }
	        }
	        catch ( SQLException e )
	        {
	        	initiated = false;
	        	System.out.println( e );
	        }
		}
		catch ( Exception ex )
		{
			initiated = false;
			System.out.println( ex );
		}
		
		return initiated;
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
			try
			{
				// Save the key to the DB
				sqlCommand 	= "UPDATE ID SET ID = " + key + " WHERE KEY = 0;" ;
				sqlStatement.executeUpdate( sqlCommand );
				System.out.println( sqlCommand );
				sqlStatement.execute( sqlCommand );
				
				dbSize++;
			}
			catch ( SQLException ex )
			{
				System.out.println( ex );
			}
			
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
		Boolean didInsert 		= false;
		String  insertString;
		
		if ( client.getName().equals( "" ) || client.getName() == null )
		{
			System.out.println("Invalid Client Insert");
			return false;
		}
		
		try
		{
			insertString = buildClientString( client );			
			sqlCommand = "Insert into Clients " + "Values(" + insertString + ");";
			System.out.println( sqlCommand );
			didInsert = sqlStatement.execute( sqlCommand );
			
			key++;			
			dbSize++;
		}
		catch ( SQLException ex )
		{
			System.out.println( ex );
		}
		
		// Now insert the soaps from the client, regardless weather the client inserted or not.
		insertSoapBox( client.getSoaps() );

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
		
		assert( false );

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
		String  updateString, where;
		int 	result;
		
		if ( updatedClient.getName().equals( "" ) || updatedClient.getName() == null )
		{
			System.out.println("Invalid Client Update");
			return false;
		}
		
		try
        {
			updateString = buildClientUpdateString( updatedClient );
			where 		 = "WHERE Name = " + updatedClient.getName();
			sqlCommand 	 = "UPDATE CLIENTS " + updateString + " " + where + ";";
			System.out.println(sqlCommand);
			result 		 = sqlStatement.executeUpdate( sqlCommand );
			
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
	public Client readClient( String clientName )
	{
		Client 	newClient = null;
		String 	name, address, city, province, postalCode, 
			   	reason, occupation, sports, sleep, DOB,
			   	homePhone, workPhone;
		int 	smoking, alcohol, stress, appetite, key;
		
		if ( clientName.equals( "" ) || clientName == null )
		{
			return null;
		}
		
		try
        {
			sqlCommand 	= "SELECT * FROM CLIENTS WHERE Name = '" + clientName + "';";
	        dbResult 	= sqlStatement.executeQuery( sqlCommand );
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
	        	smoking 	= dbResult.getInt( "Smoking" );
	        	alcohol 	= dbResult.getInt( "Alcohol" );
	        	stress 		= dbResult.getInt( "Stress" );
	        	appetite 	= dbResult.getInt( "Appetite" );
	        	key 		= dbResult.getInt( "ID" );
	        	
	        	newClient 	= new Client( name );
	        	
	        	newClient.setAddress( address );
	        	newClient.setCity( city );
	        	newClient.setProvince( province );
	        	newClient.setPostCode( postalCode );
	        	newClient.setReason( reason );
	        	newClient.setOccupation( occupation );
	        	newClient.setSports( sports );
	        	newClient.setSleepPattern( sleep );
	        	newClient.setDOB( DOB );
	        	newClient.setHomePhone( homePhone );
	        	newClient.setWorkPhone( workPhone );
	        	newClient.setSmoking( smoking );
	        	newClient.setAlcohol( alcohol );
	        	newClient.setStress( stress );
	        	newClient.setAppetite( appetite );
	        	newClient.setKey( key );
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
		return readClient( client.getName() );
	}


	/*------------------------------------------------------
	 * METHOD:			getAllClients
	 *
	 * PURPOSE:			This method will simply return a list of clients. There is not much more info
	 * 					returned at the moment
	------------------------------------------------------*/
	public ArrayList<Client> getAllClients()
	{
		Client 				client;
		String 				name, address, city;
		ArrayList<Client> 	allClients = new ArrayList<Client>();
		
		try
        {
			sqlCommand = "SELECT * FROM CLIENTS;";
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
	@SuppressWarnings( "deprecation" )
    public SoapBox getAllSoaps( String clientName )
	{
		SoapBox allSoaps = new SoapBox( clientName );
		
		Client newClient = null;
		String date, disc;
		
		try
        {
			sqlCommand 	= "SELECT * FROM SOAPS WHERE Name = '" + clientName + "';";
	        dbResult 	= sqlStatement.executeQuery( sqlCommand );
        }
        catch ( SQLException e )
        {
	        System.out.println( e );
        }
		
		try
        {
	        while( dbResult.next() )
	        {
	        	Soap tempSoap = new Soap();
	        	date = dbResult.getString( "Date" );
	        	disc = dbResult.getString( "Disc" );
	        	tempSoap.setDate( new Date( date ) );
	        	tempSoap.setInfo( disc );
	        	
	        	allSoaps.add( tempSoap );
	        }
        }
        catch ( SQLException e )
        {
        	System.out.println( e );
        }

		return allSoaps;
	}
	
	
	/*------------------------------------------------------
	 * METHOD:			readSoap
	 *
	 * PURPOSE:			This method will take a client name String, and search
	 * 					for the appropriate soap
	------------------------------------------------------*/
	public SoapBox readSoaps( String clientName )
	{
		SoapBox soap = null;

		return soap;
	}
	
	
	public Boolean insertSoap( Soap soap, String clientName )
    {
		Boolean didInsert = false;
		String  insertString;
		
		if ( clientName.equals( "" ) || clientName == null )
		{
			return false;
		}
		
		insertString = buildSoapString( clientName, soap );
//		insertString 	= "Insert into Soaps Values (" + key + " ,'" + clientName + "', " 
//							+ "'" + soap.getDate().toString() + "' , "
//							+ "'" + soap.getInfo() + "'";				 
		sqlCommand 		= "Insert into Soaps " + "Values(" + insertString + ");";
		System.out.println( sqlCommand );
		
		try
        {
            didInsert = sqlStatement.execute( sqlCommand );
            key++;
        }
        catch ( SQLException e )
        {
            System.out.println( e );
            e.printStackTrace();
        }

		return didInsert;
    }
	
	
	/*------------------------------------------------------
	 * METHOD:			insertSoap
	 *
	 * PURPOSE:			This inserts a whole bunch of messages, all from a client
	------------------------------------------------------*/
	public Boolean insertSoapBox( SoapBox soapBox )
	{
		Boolean didInsert = false;
		String  insertString;
		// We use soapBox because the name is going to be the same everytime!
		String 	clientName = soapBox.getClientName();
		
		if ( clientName.equals( "" ) || clientName == null )
		{
			return false;
		}
		
		for ( int i = 0; i < soapBox.numSoaps(); i++ )
		{
			Soap soap 		= soapBox.getSoapByIndex( i );
			insertString 	= buildSoapString( clientName, soap );					 
			sqlCommand 		= "Insert into Soaps " + "Values(" + insertString + ")";
			System.out.println( sqlCommand );
			
			try
            {
	            didInsert = sqlStatement.execute( sqlCommand );
	            key++;
            }
            catch ( SQLException e )
            {
	            System.out.println( e );
	            e.printStackTrace();
            }
		}

		return didInsert;
	}


	/*------------------------------------------------------
	 * METHOD:			deleteSoap
	 *
	 * PURPOSE:			This class will attempt to delete the soap
	------------------------------------------------------*/
	public Boolean deleteSoap( Soap soap )
	{
		Boolean didDelete = false;
		
		assert( false );

		return didDelete;
	}


	/*------------------------------------------------------
	 * METHOD:			updateSoap
	 *
	 * PURPOSE:			This method will find a soap object already in the system,
	 * 					and replace/update it with the new information
	------------------------------------------------------*/
	public Boolean updateSoap( SoapBox updatedSoap, String oldMessage )
	{
		Boolean didUpdate = false;
		String  updateString, where, clientName;
		int 	result;
		
		if ( updatedSoap.getClientName().equals( "" ) || updatedSoap.getClientName() == null )
		{
			System.out.println("Invalid Client Update");
			return false;
		}
		
		clientName = updatedSoap.getClientName();
		where 		= "WHERE Name = " + clientName;
		
		for ( int i = 0; i < updatedSoap.numSoaps(); i++ )
		{
			Soap soap 		= updatedSoap.getSoapByIndex( i );
			updateString 	= buildSoapString( clientName, soap );					 
			sqlCommand 		= "Insert into Soaps " + "Values(" + updateString + ")";
			sqlCommand 		= "UPDATE SOAPS " + updateString + " " + where;
			
			System.out.println( sqlCommand );
			
			try
            {
				didUpdate = sqlStatement.execute( sqlCommand );
	            key++;
            }
            catch ( SQLException e )
            {
	            System.out.println( e );
	            e.printStackTrace();
            }
		}
		
		return didUpdate;
	}
	
	
	/*------------------------------------------------------
	 * METHOD:			getCurrentKey
	 *
	 * PURPOSE:			Used for assigning the key, gets the key and then
	 * 					returns it.
	------------------------------------------------------*/
	public int getCurrentKey()
	{
		key++;
		return key;
	}


	/*------------------------------------------------------
	 * METHOD:			DumpDB
	 *
	 * PURPOSE:			returns a String of the entire DB.
	 * 					This might change later on, be careful using it!
	------------------------------------------------------*/
	public String DumpDB()
	{
		ArrayList<Client> temp = getAllClients();
		
		return temp.toString();
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
		// Yeah don't use this anymore
		assert ( false );
		return dbSize;
	}
	
	
	private String buildClientString( Client client )
	{
		String insertString = 
				 	  	  client.getKey()				+ "," +
				  "'" 	+ client.getName() 				+ "'" + "," 
			    + "'" 	+ client.getDOB()  				+ "'" + ","
				+ "'" 	+ client.getHomePhone() 		+ "'" + ","
			    + "'" 	+ client.getWorkPhone() 		+ "'" + ","
				+ "'" 	+ client.getAddress() 			+ "'" + "," 
				+ "'" 	+ client.getCity() 				+ "'" + "," 
				+ "'" 	+ client.getProvince() 			+ "'" + "," 
				+ "'" 	+ client.getPostCode() 			+ "'" +	"," 
					  	+ client.getPhysician() 		+ ", " 
					  	+ client.getPhysioTherapist() 	+ ", " 
					  	+ client.getChiropractor() 		+ ", " 
					  	+ client.getExperience() 		+ ", " 
				+ "'"	+ client.getReason() 			+ "'" + "," 
					  	+ client.getDiet() 				+ ", " 
					  	+ client.getMedication() 		+ ", " 
					  	+ client.getInsulin() 			+ ", " 
					  	+ client.getUncontrolled() 		+ ", " 
				+ "'" 	+ client.getOccupation() 		+ "'" + "," 
				+ "'" 	+ client.getSports() 			+ "'" + "," 
				+ "'" 	+ client.getSleepPattern()  	+ "'" + "," 
						+ client.getSmoking() 			+ "," 
						+ client.getAlcohol() 			+ "," 
						+ client.getStress()  			+ "," 
						+ client.getAppetite();
		
		return insertString;
	}
	
	
	private String buildClientUpdateString( Client updatedClient )
    {
		String insertString = 
		      "Set DOB = '" 		+ updatedClient.getDOB()  				+ "'" + ", "
			+ "Set HomePhone = '" 	+ updatedClient.getHomePhone() 			+ "'" + ", "
		    + "Set WorkPhone = '" 	+ updatedClient.getWorkPhone() 			+ "'" + ", "
			+ "Set Address = '" 	+ updatedClient.getAddress() 			+ "'" + ", " 
			+ "Set City = '" 		+ updatedClient.getCity() 				+ "'" + ", " 
			+ "Set PRovince = '" 	+ updatedClient.getProvince() 			+ "'" + ", " 
			+ "Set PostalCode = '" 	+ updatedClient.getPostCode() 			+ "'" +	", " 
			+ "Set Physician = "	+ updatedClient.getPhysician() 			+ ", " 
			+ "Set Physther = "	  	+ updatedClient.getPhysioTherapist() 	+ ", " 
			+ "Set Chiro = " 	  	+ updatedClient.getChiropractor() 		+ ", " 
			+ "Set PrevExp = "	  	+ updatedClient.getExperience() 		+ ", " 
			+ "Set Reason = '" 		+ updatedClient.getReason() 			+ "'" + ", " 
			+ "Set Diet = "	  		+ updatedClient.getDiet() 				+ ", " 
			+ "Set Med = "	  		+ updatedClient.getMedication() 		+ ", " 
			+ "Set Insulin = "	  	+ updatedClient.getInsulin() 			+ ", " 
			+ "Set Unctrl = "	  	+ updatedClient.getUncontrolled() 		+ ", " 
			+ "Set Occupation = '" 	+ updatedClient.getOccupation() 		+ "'" + ", " 
			+ "Set Sports = '" 		+ updatedClient.getSports() 			+ "'" + ", " 
			+ "Set Sleep = '" 		+ updatedClient.getSleepPattern()  		+ "'" + ", " 
			+ "Set Smoking = " 		+ updatedClient.getSmoking()			+ ", " 
			+ "Set Alchohol = " 	+ updatedClient.getAlcohol()			+	", " 
			+ "Set Stress = " 		+ updatedClient.getStress()				+ ", " 
			+ "Set Appetite = " 	+ updatedClient.getAppetite();
	
		return insertString;
    }
	
	
	private String buildSoapString( String clientName, Soap soap )
	{
		String insertString = 
				 	        soap.getKey()	+ ","
				 	+ "'" + clientName 		+ "'" + ","
					+ "'" + soap.getDate() 	+ "'" + ","
					+ "'" + soap.getInfo() 	+ "'";

		return insertString;
	}
	
	
	private String buildSoapUpdateString( Soap soap )
	{
		String insertString = 
					  "Set Date = '" + soap.getDate() 	+ "'" + ", "
					+ "Set Disc = '" + soap.getInfo() 	+ "'";

		return insertString;
	}
	
	//*****************************************************************
	// TESTING ONLY METHODS
	//*****************************************************************
	
	// Be very careful using this!
	public void clearClientTable()
	{
		if ( !DBService.isTesting() )
		{
			return;
		}
		System.out.println("WARNING: Clearing Client Table!");
		sqlCommand = "Delete From Clients;";
		
		try
        {
            sqlStatement.execute( sqlCommand );
        }
        catch ( SQLException e )
        {
            System.out.println( e );
            e.printStackTrace();
        }
	}
	
	
	// Be very careful using this!
	public void clearSoapTable()
	{
		if ( !DBService.isTesting() )
		{
			return;
		}
		System.out.println("WARNING: Clearing Soaps Table!");
		sqlCommand = "Delete From Soaps;";
		
		try
        {
            sqlStatement.execute( sqlCommand );
        }
        catch ( SQLException e )
        {
            System.out.println( e );
            e.printStackTrace();
        }
	}
	
	
	// This will clear both tables and reset the id
	public void resetID()
	{
		if ( !DBService.isTesting() )
		{
			return;
		}
		
		System.out.println("WARNING: Clearing ID!");
		clearClientTable();
		clearSoapTable();
		
		sqlCommand = "Update ID Set ID = 0 Where Key = 0;";
		
		try
        {
            sqlStatement.execute( sqlCommand );
            key = 0;
        }
        catch ( SQLException e )
        {
            System.out.println( e );
            e.printStackTrace();
        }
	}
	
	
	public int getClientCount()
	{
		if ( !DBService.isTesting() )
		{
			return -1;
		}
		
		int count = -1;
		
		sqlCommand = "Select Count(*) From Clients;";
		System.out.println( sqlCommand );
		
		try
        {
			dbResult = sqlStatement.executeQuery( sqlCommand );
			dbResult.next();
			count = dbResult.getInt( 1 );
			System.out.println("Count: " + count );
        }
        catch ( SQLException e )
        {
            System.out.println( e );
            e.printStackTrace();
        }
		
		return count;
	}
	
	
	public int getSoapCount()
	{
		if ( !DBService.isTesting() )
		{
			return -1;
		}
		
		int count = -1;
		
		sqlCommand = "Select Count(*) From Soaps;";
		System.out.println( sqlCommand );
		
		try
        {
			dbResult = sqlStatement.executeQuery( sqlCommand );
			dbResult.next();
			count = dbResult.getInt( 1 );
			System.out.println("Count: " + count );
        }
        catch ( SQLException e )
        {
            System.out.println( e );
            e.printStackTrace();
        }
		
		return count;
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
		Client test    = new Client( "Georgy Georgerson" );
		// We are sleeping because the dates need to be unique! One second makes them unique
		test.addSoap( new Date(), "This was splended! Jolly good show mate!" );
		try { Thread.sleep( 1000 ); } catch ( InterruptedException e ) { e.printStackTrace(); }
		test.addSoap( new Date(), "Woohoo!" );
		try { Thread.sleep( 1000 ); } catch ( InterruptedException e ) { e.printStackTrace(); }
		test.addSoap( new Date(), "Things are looking ship shape captian!" );
		try { Thread.sleep( 1000 ); } catch ( InterruptedException e ) { e.printStackTrace(); }
		test.addSoap( new Date(), "All aboard the boyer express!" );
		insertClient( test );
		try { Thread.sleep( 1000 ); } catch ( InterruptedException e ) { e.printStackTrace(); }
		
		Client test1 = new Client( "Rick Fredrickson" );
		test1.addSoap( "Things are getting all soapy up in here!" );
		insertClient( test1 );
	}
}