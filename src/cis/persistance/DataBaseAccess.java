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
import cis.buisness.ClientHistory;
import cis.buisness.HistoryItem;
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
			
			if ( getIDCount() <= 0 )
			{
				System.out.println("Recreating ID" );
				try
		        {
					sqlCommand = "Insert into ID values (0,0);";
			        sqlStatement.execute( sqlCommand );
			        sqlCommand = "Insert into ID values (1,0);";
			        sqlStatement.execute( sqlCommand );
		        }
		        catch ( SQLException e )
		        {
		        	initiated = false;
			        System.out.println( e );
		        }
			}
			
			try
	        {
				sqlCommand = "SELECT id FROM ID where key = 0;";
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
			if ( key > -1)
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
		
		if ( client.getKey() < 0 )
		{
			client.setKey( DBService.getCurrentKey() );
		}
		
		try
		{
			insertString = buildClientString( client );			
			sqlCommand = "Insert into Clients " + "Values(" + insertString + ");";
			System.out.println( sqlCommand );
			didInsert = sqlStatement.execute( sqlCommand );
			
			key++;			
			dbSize++;
			
			didInsert = true;
		}
		catch ( SQLException ex )
		{
			System.out.println( ex );
		}
		
		if ( didInsert )
		{
			// Now insert the soaps from the client
			insertSoapBox( client.getSoaps() );
			
			// Now insert the history of the client
			insertHistory( client.getHistory() );
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
		Boolean 		didUpdate = false;
		String  		updateString, where;
		SoapBox 		soaps = updatedClient.getSoaps();
		ClientHistory 	history = updatedClient.getHistory();
		int 			result;
		
		if ( updatedClient.getName().equals( "" ) || updatedClient.getName() == null )
		{
			System.out.println("Invalid Client Update");
			return false;
		}
		
		try
        {
			updateString = buildClientUpdateString( updatedClient );
			where 		 = "WHERE id = " + updatedClient.getKey();
			sqlCommand 	 = "UPDATE clients " + updateString + " " + where + ";";
			System.out.println(sqlCommand);
			result 		 = sqlStatement.executeUpdate( sqlCommand );
			
			if ( result == 1 )
			{
				didUpdate = updateSoap( soaps );
				didUpdate = updateHistory( history );
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
		int 	smoking, alcohol, stress, appetite, key, age;
		
		clientName = parseForSQLValid( clientName );
		
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
	        	DOB 		= dbResult.getString( "DOB" );
	        	reason 		= dbResult.getString( "Reason" );
	        	occupation 	= dbResult.getString( "Occupation" );
	        	sports 		= dbResult.getString( "Sports" );
	        	sleep 		= dbResult.getString( "Sleep" );
	        	homePhone 	= dbResult.getString( "Homephone" );
	        	workPhone 	= dbResult.getString( "Workphone" );
	        	age 		= dbResult.getInt( "Age" );
	        	smoking 	= dbResult.getInt( "Smoking" );
	        	alcohol 	= dbResult.getInt( "Alcohol" );
	        	stress 		= dbResult.getInt( "Stress" );
	        	appetite 	= dbResult.getInt( "Appetite" );
	        	key 		= dbResult.getInt( "ID" );
	        	
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
	        	newClient.setAge( age );
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
		
		if ( newClient != null )
		{
			SoapBox 		soap;
			ClientHistory 	history;
			
			soap 	= readSoaps( clientName );
			history = readHistory( clientName );
			
			newClient.setSoaps( soap );
			newClient.setHistory( history );
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
	 * METHOD:			readSoap
	 *
	 * PURPOSE:			This method will take a client name String, and search
	 * 					for the appropriate soaps. Returns a whole list of them
	------------------------------------------------------*/
	@SuppressWarnings( "deprecation" )
    public SoapBox readSoaps( String clientName )
	{
		SoapBox soap = new SoapBox( clientName );
		Client 	newClient = null;
		String 	date, disc;
		int 	key;
		
		clientName = parseForSQLValid( clientName );
		
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
	        	key = dbResult.getInt( "Id" );
	        	date = dbResult.getString( "Date" );
	        	disc = dbResult.getString( "Disc" );
	        	tempSoap.setDate( new Date( date ) );
	        	tempSoap.setInfo( disc );
	        	tempSoap.setKey( key );
	        	
	        	soap.add( tempSoap );
	        }
        }
        catch ( SQLException e )
        {
        	System.out.println( e );
        }
		
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
		
		if ( soap.getKey() < 0 )
		{
			soap.setKey( DBService.getCurrentKey() );
		}
		
		insertString = buildSoapString( clientName, soap );			 
		sqlCommand 		= "INSERT into SOAPS " + "VALUES (" + insertString + ");";
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
			
			if ( soap.getKey() < 0 )
			{
				soap.setKey( DBService.getCurrentKey() );
			}
			
			insertString 	= buildSoapString( clientName, soap );					 
			sqlCommand 		= "INSERT into SOAPS " + "VALUES (" + insertString + ")";
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
	public Boolean updateSoap( SoapBox updatedSoap )
	{
		Boolean didUpdate = false;
		String  updateString, where, clientName;
		int 	result;
		
		clientName = updatedSoap.getClientName();
		
		if ( clientName.equals( "" ) || clientName == null )
		{
			System.out.println("Invalid Soap Update");
			return false;
		}
		
		try
        {
			for ( int i = 0; i < updatedSoap.numSoaps(); i++ )
			{
				Soap tempSoap = updatedSoap.getSoapByIndex( i );
				
				if ( tempSoap != null )
				{
					updateString = buildSoapUpdateString( tempSoap );
					where 		 = "WHERE id = " + tempSoap.getKey();
					sqlCommand 	 = "UPDATE SOAPS " + updateString + " " + where + ";";
					System.out.println(sqlCommand);
					result 		 = sqlStatement.executeUpdate( sqlCommand );
					
					if ( result == 1 )
					{
						didUpdate = true;
					}
				}
			}
			
        }
        catch ( SQLException e )
        {
	        System.out.println( e );
        }
		
		return didUpdate;
	}
	
	
	public ClientHistory readHistory( String clientName )
    {
		ClientHistory history = new ClientHistory( clientName );
		
		Boolean b_heart = false;
		Boolean b_tingle = false;
		Boolean b_blood = false;
		Boolean b_breath = false;
		Boolean b_diabetes = false;
		Boolean b_faint = false;
		Boolean b_head = false;
		Boolean b_cont = false;
		Boolean b_shoes = false;
		Boolean b_varc = false;
		Boolean b_arth = false;
		Boolean b_cancer = false;
		Boolean b_diarrhea = false;
		Boolean b_meds = false;
		Boolean b_cort = false;
		Boolean b_skin = false;
		Boolean b_other = false;
		String 	str_heart = "";
		String 	str_tingle = "";
		String 	str_blood = "";
		String 	str_breath = "";
		String 	str_diabetes = "";
		String 	str_faint = "";
		String 	str_head = "";
		String 	str_cont = "";
		String 	str_shoes  = "";
		String 	str_varc = "";
		String 	str_arth = "";
		String 	str_cancer = "";
		String 	str_diarrhea = "";
		String 	str_meds = "";
		String 	str_cort = "";
		String 	str_skin = "";
		String 	str_other = "";
		int 	key1 = -1; 
		int 	key2 = -1;
		
		
		clientName = parseForSQLValid( clientName );
		
		if ( clientName.equals( "" ) || clientName == null )
		{
			return null;
		}
		
		try
        {
			sqlCommand 	= "SELECT * FROM HISTORYBOOL WHERE Name = '" + clientName + "';";
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
	        	b_heart 	= dbResult.getBoolean( "Heart" );
	        	b_tingle 	= dbResult.getBoolean( "Tingl" );
	        	b_blood 	= dbResult.getBoolean( "Blood" );
	        	b_breath 	= dbResult.getBoolean( "Breath" );
	        	b_diabetes 	= dbResult.getBoolean( "Diabetes" );
	        	b_faint 	= dbResult.getBoolean( "Faint" );
	        	b_head 		= dbResult.getBoolean( "Headaches" );
	        	b_cont 		= dbResult.getBoolean( "Contact" );
	        	b_shoes 	= dbResult.getBoolean( "Shoes" );
	        	b_varc 		= dbResult.getBoolean( "Varicose" );
	        	b_arth 		= dbResult.getBoolean( "Arthritis" );
	        	b_cancer 	= dbResult.getBoolean( "Cancer" );
	        	b_diarrhea  = dbResult.getBoolean( "Diarrhea" );
	        	b_meds 		= dbResult.getBoolean( "Meds" );
	        	b_cort 		= dbResult.getBoolean( "Coritsone" );
	        	b_skin 		= dbResult.getBoolean( "Skin" );
	        	b_other 	= dbResult.getBoolean( "Other" );
	        	key1 		= dbResult.getInt( "Key" );
	        }
        }
        catch ( SQLException e )
        {
        	System.out.println( e );
        }
		
		try
        {
			sqlCommand 	= "SELECT * FROM HISTORYDISC WHERE Name = '" + clientName + "';";
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
		    	str_heart 	= dbResult.getString( "Heart" );
		    	str_tingle 	= dbResult.getString( "Tingl" );
		    	str_blood 	= dbResult.getString( "Blood" );
		    	str_breath 	= dbResult.getString( "Breath" );
		    	str_diabetes= dbResult.getString( "Diabetes" );
		    	str_faint 	= dbResult.getString( "Faint" );
		    	str_head 	= dbResult.getString( "Headaches" );
		    	str_cont 	= dbResult.getString( "Contact" );
		    	str_shoes 	= dbResult.getString( "Shoes" );
		    	str_varc 	= dbResult.getString( "Varicose" );
		    	str_arth 	= dbResult.getString( "Arthritis" );
		    	str_cancer 	= dbResult.getString( "Cancer" );
		    	str_diarrhea= dbResult.getString( "Diarrhea" );
		    	str_meds 	= dbResult.getString( "Meds" );
		    	str_cort 	= dbResult.getString( "Coritsone" );
		    	str_skin 	= dbResult.getString( "Skin" );
		    	str_other	= dbResult.getString( "Other" );
		    	key2 		= dbResult.getInt( "Key" );
	        }
        }
		catch ( SQLException e )
        {
        	System.out.println( e );
        }
		
		if ( key1 == key2 && key1 != -1 && key2 != -1 )
		{
	        history.setHeart( b_heart, str_heart );
	    	history.setTingling( b_tingle, str_tingle );
	    	history.setBloodPres( b_blood, str_blood );
	    	history.setBreathing( b_breath, str_breath );
	    	history.setDiabetes( b_diabetes, str_diabetes );
	    	history.setFaintness( b_faint, str_faint );
	    	history.setHeadaches( b_head, str_head );
	    	history.setContactLenses( b_cont, str_cont );
	    	history.setShoes( b_shoes, str_shoes );
	    	history.setVaricose( b_varc, str_varc );
	    	history.setArthritis( b_arth, str_arth );
	    	history.setCancer( b_cancer, str_cancer );
	    	history.setDiarrhea( b_diarrhea, str_diarrhea );
	    	history.setMeds( b_meds, str_meds );
	    	history.setCortisone( b_cort, str_cort );
	    	history.setSkin( b_skin, str_skin );
	    	history.setOther( b_other, str_other );
	    	history.setKey( key1 );
		}
		else
		{
			System.out.println("Error Reading History, Key Mismatch!");
			history = new ClientHistory( clientName );
		}
		
		System.out.println("History key: " + key1);
	    
	    return history;
    }


	public Boolean insertHistory( ClientHistory history )
    {
		Boolean didInsert = false;
		String  insertStringBool, insertStringDisc;
		String clientName = history.getName();
		
		if ( clientName.equals( "" ) || clientName == null )
		{
			return false;
		}
		
		if ( history.getKey() < 0 )
		{
			history.setKey( DBService.getCurrentKey() );
		}
		
		insertStringBool = buildBoolHistString( history );			 
		sqlCommand 		= "INSERT into HISTORYBOOL " + "VALUES (" + insertStringBool + ");";
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
		
		insertStringDisc = buildDiscHistString( history );
		sqlCommand 		= "INSERT into HISTORYDISC " + "VALUES (" + insertStringDisc + ");";
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


	public Boolean updateHistory( ClientHistory updateHistory )
    {
		Boolean didUpdate = false;
		String  updateString, where, clientName;
		int 	result;
		
		clientName = updateHistory.getName();
		
		if ( clientName.equals( "" ) || clientName == null )
		{
			System.out.println("Invalid History Update");
			return false;
		}
		
		System.out.println("History key1: " + updateHistory.getKey() );
		
		try
        {
			updateString = buildHistBoolUpdateString( updateHistory );
			where 		 = "WHERE key = " + updateHistory.getKey();
			sqlCommand 	 = "UPDATE HistoryBool " + updateString + " " + where + ";";
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
		
		if ( didUpdate )
		{
			try
	        {
				updateString = buildHistDiscUpdateString( updateHistory );
				where 		 = "WHERE key = " + updateHistory.getKey();
				sqlCommand 	 = "UPDATE HistoryDisc " + updateString + " " + where + ";";
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
		System.out.println("KEY INCR");
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
				  client.getKey()								+ ","
				+ parseForSQLQuery( client.getName() )	 		+ "," 
				+ parseForSQLQuery( client.getDOB() ) 			+ ","
			    +  					client.getAge()				+ ","
				+ parseForSQLQuery( client.getHomePhone() )		+ ","
			    + parseForSQLQuery( client.getWorkPhone() )		+ ","
				+ parseForSQLQuery( client.getAddress() )		+ "," 
				+ parseForSQLQuery( client.getCity() )			+ "," 
				+ parseForSQLQuery( client.getProvince() )		+ "," 
				+ parseForSQLQuery( client.getPostCode() )		+ "," 
			  	+ 					client.getPhysician() 		+ "," 
			  	+ 					client.getPhysioTherapist() + "," 
			  	+ 					client.getChiropractor() 	+ "," 
			  	+ 					client.getExperience() 		+ "," 
				+ parseForSQLQuery( client.getReason()	)		+ "," 
			  	+ 					client.getDiet() 			+ "," 
			  	+ 					client.getMedication() 		+ "," 
			  	+ 					client.getInsulin() 		+ "," 
			  	+ 					client.getUncontrolled() 	+ "," 
				+ parseForSQLQuery( client.getOccupation()	) 	+ "," 
				+ parseForSQLQuery( client.getSports()	)		+ "," 
				+ parseForSQLQuery( client.getSleepPattern() ) 	+ "," 
				+ 					client.getSmoking() 		+ "," 
				+ 					client.getAlcohol() 		+ "," 
				+ 					client.getStress()  		+ "," 
				+ 					client.getAppetite();
		
		return insertString;
	}
	
	
	private String buildClientUpdateString( Client updatedClient )
    {
		String insertString = 
		      "SET DOB = " 			+ parseForSQLQuery( updatedClient.getDOB().toString() ) + ","
		    + "Age = "			+ 					updatedClient.getAge() 				+ ","
			+ "HomePhone = " 	+ parseForSQLQuery( updatedClient.getHomePhone() )		+ ","
		    + "WorkPhone = " 	+ parseForSQLQuery( updatedClient.getWorkPhone() )		+ ","
			+ "Address = " 		+ parseForSQLQuery( updatedClient.getAddress() )		+ "," 
			+ "City = " 		+ parseForSQLQuery( updatedClient.getCity() )			+ "," 
			+ "Province = " 	+ parseForSQLQuery( updatedClient.getProvince() )		+ "," 
			+ "PostalCode = " 	+ parseForSQLQuery( updatedClient.getPostCode() )		+ "," 
			+ "Physichian = "	+ 					updatedClient.getPhysician() 		+ "," 
			+ "Physther = "	  	+ 					updatedClient.getPhysioTherapist()  + "," 
			+ "Chiro = " 	  	+ 					updatedClient.getChiropractor() 	+ "," 
			+ "PrevExp = "	  	+ 					updatedClient.getExperience() 		+ "," 
			+ "Reason = " 		+ parseForSQLQuery( updatedClient.getReason() )			+ "," 
			+ "Diet = "	  		+ 					updatedClient.getDiet() 			+ "," 
			+ "Med = "	  		+ 					updatedClient.getMedication() 		+ "," 
			+ "Insulin = "	  	+ 					updatedClient.getInsulin() 			+ "," 
			+ "Unctrl = "	  	+ 					updatedClient.getUncontrolled() 	+ "," 
			+ "Occupation = " 	+ parseForSQLQuery( updatedClient.getOccupation() )		+ "," 
			+ "Sports = " 		+ parseForSQLQuery( updatedClient.getSports() )			+ "," 
			+ "Sleep = " 		+ parseForSQLQuery( updatedClient.getSleepPattern() )	+ "," 
			+ "Smoking = " 		+ 					updatedClient.getSmoking()			+ "," 
			+ "Alcohol = " 		+ 					updatedClient.getAlcohol()			+ "," 
			+ "Stress = " 		+ 					updatedClient.getStress()			+ "," 
			+ "Appetite = " 	+ 					updatedClient.getAppetite();
	
		return insertString;
    }
	
	
	private String buildSoapString( String clientName, Soap soap )
	{
		String insertString = 
				 	  soap.getKey()									+ ","
				 	+ parseForSQLQuery( clientName )				+ ","
					+ parseForSQLQuery( soap.getDate().toString() )	+ ","
					+ parseForSQLQuery( soap.getInfo() );

		return insertString;
	}
	
	
	private String buildSoapUpdateString( Soap soap )
	{
		String updateString = 
					  "Set Date = " + parseForSQLQuery( soap.getDate().toString() ) + ","
					+ "Disc = " + parseForSQLQuery( soap.getInfo() );

		return updateString;
	}
	
	
	private String buildBoolHistString( ClientHistory history )
    {
	    String 	insertString = history.getKey() + "," + 
	    		parseForSQLQuery( history.getName() ) + ",";
	    int 	i;
	    
	    for ( i = 0; i < history.length() - 1; i++ )
	    {
	    	insertString += history.getByIndex( i ).getChecked() + ",";
	    }
	    
	    //i++;
	    insertString += history.getByIndex( i ).getChecked();
	    
	    return insertString;
    }
	
	
	private String buildHistDiscUpdateString( ClientHistory updateHistory )
    {
		String insertString = 
			        "Set Heart = " 	+ parseForSQLQuery( updateHistory.getHeart().getDisc() )		+ ","
			      + "Tingl = " 		+ parseForSQLQuery( updateHistory.getTingling().getDisc() ) 	+ ","
			      + "Blood = " 		+ parseForSQLQuery( updateHistory.getBlood().getDisc() )		+ ","
			      + "Breath = "		+ parseForSQLQuery( updateHistory.getBreath().getDisc() )		+ ","
			      + "Diabetes = " 	+ parseForSQLQuery( updateHistory.getDiabetes().getDisc() ) 	+ ","
			      + "Faint = " 		+ parseForSQLQuery( updateHistory.getFaint().getDisc() )		+ ","
			      + "Headaches = "  + parseForSQLQuery( updateHistory.getHeadaches().getDisc() ) 	+ ","
			      + "Contact = "    + parseForSQLQuery( updateHistory.getContactLense().getDisc() ) + ","
			      + "Shoes = " 		+ parseForSQLQuery( updateHistory.getShoes().getDisc() )		+ ","
			      + "Varicose = "	+ parseForSQLQuery( updateHistory.getVaricose().getDisc() ) 	+ ","
			      + "Arthritis = " 	+ parseForSQLQuery( updateHistory.getArthritis().getDisc() ) 	+ ","
			      + "Cancer = " 	+ parseForSQLQuery( updateHistory.getCancer().getDisc() )		+ ","
			      + "Diarrhea = " 	+ parseForSQLQuery( updateHistory.getDiarrhea().getDisc() ) 	+ ","
			      + "Meds = " 		+ parseForSQLQuery( updateHistory.getMeds().getDisc() ) 		+ ","
			      + "Coritsone = " 	+ parseForSQLQuery( updateHistory.getCortisone().getDisc() ) 	+ ","
			      + "Skin = " 		+ parseForSQLQuery( updateHistory.getSkin().getDisc() ) 		+ ","
			      + "Other = " 		+ parseForSQLQuery( updateHistory.getOther().getDisc() );
		
			return insertString;
    }
	
	
	private String buildDiscHistString( ClientHistory history )
    {
	    String 		insertString = history.getKey() + "," + 
	    			parseForSQLQuery( history.getName() ) + ",";
	    HistoryItem item;
	    int 		i;
	    
	    for ( i = 0; i < history.length() - 1; i++ )
	    {
	    	item = history.getByIndex( i );
	    	insertString += parseForSQLQuery( item.getDisc() ) + ",";
	    }
	    
	    //i++;
	    item = history.getByIndex( i );
	    
	    insertString += parseForSQLQuery( item.getDisc() );
	    
	    return insertString;
    }
	
	
	private String buildHistBoolUpdateString( ClientHistory updateHistory )
    {
		String insertString = 
			        "Set Heart = " 		+ updateHistory.getHeart().getChecked() 		+ ","
			      + "Tingl = " 		+ updateHistory.getTingling().getChecked() 		+ ","
			      + "Blood = " 		+ updateHistory.getBlood().getChecked() 		+ ","
			      + "Breath = "		+ updateHistory.getBreath().getChecked() 		+ ","
			      + "Diabetes = " 	+ updateHistory.getDiabetes().getChecked() 		+ ","
			      + "Faint = " 		+ updateHistory.getFaint().getChecked() 		+ ","
			      + "Headaches = "  + updateHistory.getHeadaches().getChecked() 	+ ","
			      + "Contact = "    + updateHistory.getContactLense().getChecked() 	+ ","
			      + "Shoes = " 		+ updateHistory.getShoes().getChecked() 		+ ","
			      + "Varicose = "	+ updateHistory.getVaricose().getChecked() 		+ ","
			      + "Arthritis = " 	+ updateHistory.getArthritis().getChecked() 	+ ","
			      + "Cancer = " 	+ updateHistory.getCancer().getChecked() 		+ ","
			      + "Diarrhea = " 	+ updateHistory.getDiarrhea().getChecked() 		+ ","
			      + "Meds = " 		+ updateHistory.getMeds().getChecked() 			+ ","
			      + "Coritsone = " 	+ updateHistory.getCortisone().getChecked() 	+ ","
			      + "Skin = " 		+ updateHistory.getSkin().getChecked() 			+ ","
			      + "Other = " 		+ updateHistory.getOther().getChecked();
		
			return insertString;
    }
	
	
	/*------------------------------------------------------
	* METHOD:			parseStringForSQL
	*
	* PURPOSE:			replaces some unfriendly SQL strings 
	* 					with friendly versions, adds string quotes
	* 					This method is for writing
	------------------------------------------------------*/
	private String parseForSQLQuery( String input )
	{
		String parsedString = "'";
		String finalString = null;
		
		if ( input != null )
		{
			input = input.trim();
			parsedString = input.replace( "'", "''" );
		}
		else
		{
			parsedString = "null";
		}
		
		finalString = "'" + parsedString + "'";
		
		return finalString;
	}
	
	
	/*------------------------------------------------------
	* METHOD:			parseStringForSQL
	*
	* PURPOSE:			replaces some unfriendly SQL strings 
	* 					with friendly versions
	------------------------------------------------------*/
	private String parseForSQLValid( String input )
	{
		String finalString = null;
		
		if ( input != null )
		{
			input = input.trim();
			finalString = input.replace( "'", "''" );
		}
		else
		{
			finalString = "null";
		}
		
		return finalString;
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
	
	
	// Be very careful using this!
	public void clearHistTable()
	{
		if ( !DBService.isTesting() )
		{
			return;
		}
		
		System.out.println("WARNING: Clearing History Table!");
		sqlCommand = "Delete From HistoryDisc;";
		
		try
        {
            sqlStatement.execute( sqlCommand );
        }
        catch ( SQLException e )
        {
            System.out.println( e );
            e.printStackTrace();
        }
		
		sqlCommand = "Delete From HistoryBool;";
		
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
		clearHistTable();
		
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
//		if ( !DBService.isTesting() )
//		{
//			System.out.println( "TESTING" );
//			return -1;
//		}
		
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
//		if ( !DBService.isTesting() )
//		{
//			System.out.println( "TESTING" );
//			return -1;
//		}
		
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
	
	
	public int getIDCount()
	{
		int count = -1;
		
		sqlCommand = "Select Count(*) From ID;";
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
	
	
	public int getHistCount()
	{
//		if ( !DBService.isTesting() )
//		{
//			return -1;
//		}
		
		int count = -1;
		
		sqlCommand = "Select Count(*) From HistoryBool;";
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
	
	
	public void genMockDatabase()
	{
		int shouldUpdate = 0;
		
		try
        {
			sqlCommand = "SELECT id FROM ID where key = 1;";
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
	        	shouldUpdate	= dbResult.getInt( "ID" );
	        }
        }
		catch ( SQLException e )
		{
			System.out.println( e );
			e.printStackTrace();
		}
		
		if ( shouldUpdate == 0 )
		{
		
			Client one = new Client( "Pat Ricky" );
			Client two = new Client( "George Curious" );
			Client three = new Client( "Fred Freddy" );
			Client four = new Client( "Patty Rick" );
			Client five = new Client( "Travis Almighty" );
			ClientHistory history = new ClientHistory( "Pat Ricky" );
			history.setByIndex( true, "Everything in ship shape", 2 );
			one.setHistory( history );
			two.setDOB( new Date().toString() );
			insertClient( one );
			insertClient( two );
			insertClient( three );
			insertClient( four );
			insertClient( five );
			Client test    = new Client( "Georgy Georgerson" );
			test.addSoap( new Date(), "This was splended! Jolly good show mate!" );
			test.addSoap( new Date(), "Woohoo!" );
			test.addSoap( new Date(), "Things are looking ship shape captian!" );
			test.addSoap( new Date(), "All aboard the boyer express!" );
			insertClient( test );
			
			Client test1 = new Client( "Rick Fredrickson" );
			test1.addSoap( "Things are getting all soapy up in here!" );
			insertClient( test1 );
		}
		
		try
        {
			sqlCommand = "Update ID set id = 1 where key = 1;";
	        sqlStatement.execute( sqlCommand );
        }
        catch ( SQLException e )
        {
	        System.out.println( e );
        }
	}


	public int getCurrentKeyNoUpdate()
    {
	    return key;
    }
}