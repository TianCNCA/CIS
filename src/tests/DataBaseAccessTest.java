package tests;

import java.util.ArrayList;
import java.util.Date;

import cis.buisness.Client;
import cis.buisness.SoapBox;
import cis.persistance.DataBaseAccess;
import junit.framework.TestCase;

public final class DataBaseAccessTest extends TestCase
{	
	private DataBaseAccess database;
	
	@Override
	protected void setUp() throws Exception 
	{
		super.setUp();
		database = new DataBaseAccess();
		database.init();
		
		assertEquals( 0, database.getSize() );
	}
	
	public void testClientInsert()
	{
		System.out.println( "INSERT CLIENT TEST" );
		Client test    = new Client( "Georgy George" );
		Boolean insert = database.insertClient( test );
		System.out.println(insert);
		//assertTrue( insert );
		System.out.println( "END INSERT CLIENT TEST\n" );
	}
	
	
	public void testSoapInsert()
	{
		System.out.println( "INSERT SOAP TEST" );
		
		Client test    = new Client( "Georgy Georgerson" );
		
		//SoapBox testBox = new SoapBox( test.getName() );
		test.addSoap( new Date(), "This was splended! Jolly good show mate!" );
		test.addSoap( new Date(), "Woohoo!" );
		test.addSoap( new Date(), "Things are looking ship shape captian!" );
		test.addSoap( new Date(), "All aboard the boyer express!" );
		
		Boolean insert = database.insertClient( test );
		//Boolean insert = database.insertSoap( testBox );
		System.out.println(insert);
		//assertTrue( insert );
		System.out.println( "END INSERT SOAP TEST\n" );
	}
	
	
	
	public void testAllClientsList()
	{
		System.out.println( "Test All Clients\n" );
		
		ArrayList<Client> listOfClients;
		
		listOfClients = database.getAllClients();
		
		System.out.println( listOfClients );
		
		System.out.println( "End Test All Clients\n" );
	}
	
	@Override
	protected void tearDown() throws Exception 
	{
		super.tearDown();
		database.shutdownDB();
	}
}
