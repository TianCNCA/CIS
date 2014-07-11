package tests.objectTests;

import java.util.Date;

import app.DBService;
import cis.buisness.DataAccess;
import cis.buisness.Soap;
import cis.buisness.SoapBox;
import junit.framework.TestCase;

public class SoapBoxTest extends TestCase {

	private DataAccess database;
	private DBService  service;

	protected void setUp() throws Exception {
		super.setUp();
		System.out.println( "Setting up Test DB" );
		service = new DBService();
		service.initializeDB();
		service.setTesting();
		
		database = new DataAccess();
		database.dbResetForTesting();
		
		assertEquals( 0, database.getSize() );
	}
	
	public void testInitialize()
	{
		SoapBox test    = new SoapBox();
		assertEquals(test.numSoaps(), 0);
		assertTrue(test.isEmpty());
		assertEquals(null, test.getClientID());
	}
	
	
	public void testInserts()
	{
		SoapBox test    = new SoapBox();
		test.add(new Soap());
		assertEquals(test.numSoaps(), 1);
		Soap soap = new Soap(new Date(), "info");
		test.add(soap);
		assertEquals(test.numSoaps(), 2);
		assertEquals(test.getSoapByIndex(1), soap);
		test.add(new Date(), "different info");
		assertNotSame(test.getSoapByIndex(1), test.getSoapByIndex(2));
	}

	protected void tearDown() throws Exception {
		System.out.println( "Shutting Down Test DB" );
		database.dbResetForTesting();
		service.shutDownDB();
		super.tearDown();
	}

}
