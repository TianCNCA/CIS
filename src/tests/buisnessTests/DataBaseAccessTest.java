package tests.buisnessTests;

import java.util.ArrayList;

import cis.buisness.Client;
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
	
//	public void testDB()
//	{
//		
//	}
	
	public void testInsert()
	{
		System.out.println( "INSERT TEST" );
		Client test    = new Client( "wweee" );
		Boolean insert = database.insertClient( test );
		//assertTrue( insert );
		System.out.println( "END INSERT TEST\n" );
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
