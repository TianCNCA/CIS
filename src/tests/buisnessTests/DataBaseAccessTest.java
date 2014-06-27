package tests.buisnessTests;

import java.util.ArrayList;
import java.util.Date;

import app.DBService;
import cis.buisness.Client;
import cis.buisness.DataAccess;
import cis.buisness.Soap;
import cis.buisness.SoapBox;
import cis.persistance.DataBaseAccess;
import junit.framework.TestCase;

@SuppressWarnings( "unused" )
public final class DataBaseAccessTest extends TestCase
{	
	private DataAccess database;
	private DBService  service;
	
	@Override
	protected void setUp() throws Exception 
	{
		super.setUp();
		System.out.println( "Setting up Test DB" );
		service = new DBService();
		service.initializeDB();
		service.setTesting();
		
		database = new DataAccess();
		database.dbResetForTesting();
		
		assertEquals( 0, database.getSize() );
	}
	
	
	// Basic client insert test :)
	public void testClientInsertOne()
	{
		database.dbResetForTesting();
		System.out.println( "\nINSERT CLIENT TEST" );
		Client test    = new Client( "Georgy George" );
		database.insertClient( test );
		
		int dbCountt = database.getClientCount();
		
		System.out.println( "Count: " + dbCountt );
		assertEquals( dbCountt, 1 );
		System.out.println( "END INSERT CLIENT TEST\n" );
	}
	
	
	// Just check to see if we can have more than one client :)
	public void testInsertTwoClients()
	{
		database.dbResetForTesting();
		System.out.println( "\nINSERT CLIENT TEST" );
		Client test    = new Client( "Georgy George" );
		Client test2   = new Client( "George Patterson" );
		database.insertClient( test );
		database.insertClient( test2 );

		int dbCountt = database.getClientCount();
		
		System.out.println( "Count: " + dbCountt );
		assertEquals( dbCountt, 2 );
		System.out.println( "END INSERT CLIENT TEST\n" );
	}
	
	
	// This test shouldn't allow duplicate names
	public void testInsertClientWithDuplicate()
	{
		database.dbResetForTesting();
		System.out.println( "\nINSERT CLIENT TEST" );
		Client test    = new Client( "Georgy George" );
		Client test2   = new Client( "George Patterson" );
		Client test3    = new Client( "Georgy George" );
		database.insertClient( test );
		database.insertClient( test2 );
		database.insertClient( test3 );

		int dbCountt = database.getClientCount();
		
		System.out.println( "Count: " + dbCountt );
		assertEquals( dbCountt, 2 );
		System.out.println( "END INSERT CLIENT TEST\n" );
	}
	
	
	public void testInsertClientWithSoap()
	{
		database.dbResetForTesting();
		System.out.println( "\nINSERT CLIENT TEST" );
		Client test    = new Client( "Georgy George Fredrickson" );
		test.addSoap( "Things are getting soapy up in here!" );
		database.insertClient( test );

		int dbCountc = database.getClientCount();
		int dbCounts = database.getSoapCount();
		
		System.out.println( "Client Count: " + dbCountc + " Soap Count: " + dbCounts );
		assertEquals( dbCountc, 1 );
		assertEquals( dbCounts, 1 );
		System.out.println( "END INSERT CLIENT TEST\n" );
	}
	
	
	public void testInsertClientWithMultipleSoaps()
	{
		database.dbResetForTesting();
		System.out.println( "\nINSERT SOAP TEST" );
		
		Client test    = new Client( "Georgy Georgerson" );
		
		// We are sleeping because the dates need to be unique! One second makes them unique
		test.addSoap( new Date(), "This was splended! Jolly good show mate!" );
		test.addSoap( new Date(), "Woohoo!" );
		test.addSoap( new Date(), "Things are looking ship shape captian!" );
		test.addSoap( new Date(), "All aboard the boyer express!" );
		
		database.insertClient( test );
		
		int dbCountc = database.getClientCount();
		int dbCounts = database.getSoapCount();
		
		System.out.println( "Client Count: " + dbCountc + " Soap Count: " + dbCounts );
		assertEquals( dbCountc, 1 );
		assertEquals( dbCounts, 4 );
		System.out.println( "END INSERT SOAP TEST\n" );
	}
	
	
	// Make sure we can read with a string
	public void testReadClient()
	{
		database.dbResetForTesting();
		System.out.println( "\nREAD CLIENT TEST" );
		Client test = new Client( "Georgy Georgerson" );
		test.setOccupation( "Nurse" );
		test.setAddress( "Box 1 Billion" );
		test.setProvince( "MB" );
		test.setCity( "Winterpig" );
		test.setActive( true );
		database.insertClient( test );
		
		Client read = database.readClient( "Georgy Georgerson" );
		assertEquals( read.getOccupation(), "Nurse" );
		assertEquals( read.getAddress(), "Box 1 Billion" );
		assertEquals( read.getProvince(), "MB" );
		assertEquals( read.getCity(), "Winterpig" );
		assertTrue( read.getActive() );

		System.out.println( "END READ CLIENT TEST\n" );
	}
	
	
	public void testReadClientWithClass()
	{
		database.dbResetForTesting();
		System.out.println( "\nREAD CLIENT TEST" );
		Client test    = new Client( "Gorgina Gerald" );
		test.setOccupation( "Nurse" );
		test.setAddress( "Box 1 Billion" );
		test.setProvince( "MB" );
		test.setCity( "Winterpig" );
		test.setActive( true );
		database.insertClient( test );
		
		// Ooooooh subtle
		Client read = database.readClient( test );
		System.out.println( read.getOccupation() + " " + read.getAddress() + " " + read.getProvince() + " " + read.getCity() );
		assertEquals( read.getOccupation(), "Nurse" );
		assertEquals( read.getAddress(), "Box 1 Billion" );
		assertEquals( read.getProvince(), "MB" );
		assertEquals( read.getCity(), "Winterpig" );
		assertTrue( read.getActive() );

		System.out.println( "END READ CLIENT TEST\n" );
	}
	
	
	public void testFailReadClient()
	{
		database.dbResetForTesting();
		System.out.println( "\nREAD CLIENT TEST" );
		Client test    = new Client( "Gorgina" );
		test.setOccupation( "Nurse" );
		test.setAddress( "Box 1 Billion" );
		test.setProvince( "MB" );
		test.setCity( "Winterpig" );
		test.setActive( true );
		database.insertClient( test );
		
		// Ooooooh subtle
		Client read = database.readClient( "Gorgina Fred" );
		assertNull( read );

		System.out.println( "END READ CLIENT TEST\n" );
	}
	
	
	public void testInsertSoap()
	{
		database.dbResetForTesting();
		System.out.println( "\nREAD SOAP TEST" );
		SoapBox soapbox = new SoapBox( "Patty Rick" );
		soapbox.add( new Date(), "Everything seems to be well" );
		
		database.insertSoapBox( soapbox );
		int size = database.getSoapCount();
		
		assertEquals( size, 1 );

		System.out.println( "END READ SOAP TEST\n" );
	}
	
	
	public void testInsertManySoap()
	{
		database.dbResetForTesting();
		System.out.println( "\nREAD SOAP TEST" );
		SoapBox soapbox = new SoapBox( "Patty Rick" );
		soapbox.add( new Date(), "Everything seems to be well" );
		soapbox.add( new Date(), "Land ho" );
		soapbox.add( new Date(), "Treasure Planet" );
		soapbox.add( new Date(), "Don't ask about all this..." );
		
		SoapBox soapbox2 = new SoapBox( "Hubert" );
		soapbox2.add( new Date(), "Hubert seems nice" );
		
		database.insertSoapBox( soapbox );
		int size = database.getSoapCount();
		System.out.println( "Size: " +  size );
		assertEquals( size, 4 );
		
		database.insertSoapBox( soapbox2 );
		size = database.getSoapCount();
		assertEquals( size, 5 );

		System.out.println( "END READ SOAP TEST\n" );
	}
	
	
	public void testReadSoap()
	{
		database.dbResetForTesting();
		System.out.println( "\nREAD SOAP TEST" );
		SoapBox soapbox = new SoapBox( "Patty Rick" );
		soapbox.add( new Date(), "Everything seems to be well" );
		
		database.insertSoapBox( soapbox );
		
		SoapBox read = database.readSoaps( "Patty Rick" );
		ArrayList<Soap> soaps = read.getSoaps();
		
		assertEquals( soaps.size(), 1 );
		assertEquals( soaps.get(0).getInfo(), "Everything seems to be well" );

		System.out.println( "END READ SOAP TEST\n" );
	}
	
	// Check to see if we can update a clients information field
	public void testUpdateClient()
	{
		database.dbResetForTesting();
		System.out.println( "\nUPDATE CLIENT TEST" );
		Client test    = new Client( "Fredwina Fredders" );
		test.setCity( "Citttty" );
		test.setOccupation( "Super Awesome Lawyer Doctor" );
		database.insertClient( test );
		
		int size = database.getClientCount();
		assertEquals( 1, size );
		
		Client updatedClient = database.readClient( "Fredwina Fredders" );
		updatedClient.setCity( "Dallas Baby!" );
		updatedClient.setOccupation( "Super Duper Awesome Lawyer Doctor Guy" );
		database.updateClient( updatedClient );
		
		Client updateResult = database.readClient( "Fredwina Fredders" );
		String city = updatedClient.getCity();
		String occup= updatedClient.getOccupation();
		
		System.out.println( "City: " + city + " Occupation: " + occup );
		
		assertEquals( city, "Dallas Baby!" );
		assertEquals( occup, "Super Duper Awesome Lawyer Doctor Guy" );

		System.out.println( "END UPDATE CLIENT TEST\n" );
	}
	
	public void testAllClientsList()
	{
		database.dbResetForTesting();
		System.out.println( "Test All Clients\n" );
		Client test1    = new Client( "Fredwina Fredders" );
		Client test2    = new Client( "Gorgina Gerald" );
		Client test3    = new Client( "Georgy George" );
		Client test4   	= new Client( "George Patterson" );
		Client one 		= new Client( "Pat Ricky" );
		Client two 		= new Client( "George Curious" );
		Client three 	= new Client( "Fred Freddy" );
		Client four 	= new Client( "Patty Rick" );
		Client five 	= new Client( "Travis Almighty" );
		database.insertClient( five );
		database.insertClient( four );
		database.insertClient( three );
		database.insertClient( two );
		database.insertClient( one );
		database.insertClient( test4 );
		database.insertClient( test3 );
		database.insertClient( test2 );
		database.insertClient( test1 );
		database.insertClient( five );
		database.insertClient( four );
		database.insertClient( three );
		database.insertClient( two );
		database.insertClient( one );
		database.insertClient( test4 );
		database.insertClient( test3 );
		database.insertClient( test2 );
		database.insertClient( test1 );
		
		ArrayList<Client> listOfClients;
		
		listOfClients = database.getAllClients();
		
		assertEquals( listOfClients.size(), 9 );
		
		System.out.println( listOfClients );
		
		System.out.println( "End Test All Clients\n" );
	}
	
	
	public void testUpdateSoap()
	{
		database.dbResetForTesting();
		System.out.println( "\nUPDATE SOAP TEST" );
		SoapBox test = new SoapBox( "Patty Rick" );
		test.add( new Date(), "Everything seems to be well" );
		
		System.out.println("Update ID: " + test.getSoapByIndex( 0 ).getKey() );
		
		database.insertSoapBox( test );
		
		SoapBox soapBox = database.readSoaps( "Patty Rick" );
		Soap temp = soapBox.getSoapByIndex( 0 );
		
		temp.setInfo( "Everything is going even better!" );
		
		soapBox.updateSoap( temp );
		
		database.updateSoap( soapBox );
		
		soapBox = database.readSoaps( "Patty Rick" );
		
		String info = soapBox.getSoapByIndex( 0 ).getInfo();
		System.out.println( info );
		Boolean equal = info.equals( "Everything is going even better!" );
		assertTrue( equal );

		System.out.println( "END UPDATE SOAP TEST\n" );
	}
	
	@Override
	protected void tearDown() throws Exception 
	{
		System.out.println( "Shutting Down Test DB" );
		database.dbResetForTesting();
		service.shutDownDB();
		super.tearDown();
	}
}
