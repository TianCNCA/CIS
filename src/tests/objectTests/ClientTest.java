package tests.objectTests;

import java.util.Date;

import app.DBService;
import cis.buisness.DataAccess;
import cis.objects.Client;
import cis.objects.Soap;
import cis.objects.SoapBox;
import junit.framework.TestCase;

public final class ClientTest extends TestCase 
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
	
	public void testClients()
	{
		Client test    = new Client( "Gorgina" );
		test.setOccupation( "Nurse" );
		test.setAddress( "Box 1 Billion" );
		test.setProvince( "MB" );
		test.setCity( "Winterpig" );
		test.setActive( true );
		
		assertEquals( test.getOccupation(), "Nurse" );
		assertEquals( test.getAddress(), "Box 1 Billion" );
		assertEquals( test.getProvince(), "MB" );
		assertEquals( test.getCity(), "Winterpig" );
		assertTrue( test.getActive() );
	}
	
	
	public void testClientWithSoap()
	{
		Client test    = new Client( "Gorgina" );
		Soap soap = new Soap( new Date(), "Another bloody Soap" );
		
		// See if the different soap methods work
		test.addSoap( "Soap 1" );
		test.addSoap( soap );
		test.addSoap( new Date(), "Wee more soaps!" );
		
		SoapBox soapBox = test.getSoapBox();		
		
		Boolean found = false;
		
		for ( Soap soaps : soapBox.getSoaps() )
		{
			if ( soaps.getInfo().equals( "Soap 1" ) || 
					soaps.getInfo().equals( "Another bloody Soap" ) || 
					soaps.getInfo().equals( "Wee more soaps!" ) )
			{
				found = true;
			}
			else
			{
				found = false;
			}
		}
		
		assertTrue( found );
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
