package tests.objectTests;

import java.util.Date;

import app.DBService;
import cis.buisness.DataAccess;
import cis.buisness.Soap;
import cis.buisness.SoapBox;
import junit.framework.TestCase;

public class SoapTest extends TestCase {

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
		Soap test    = new Soap();
		assertEquals(test.getDate(), null);
		assertEquals(null, test.getInfo());
	}
	
	
	@SuppressWarnings("deprecation")
	public void testChanges()
	{
		Soap test    = new Soap();
		Date date = new Date();
		test.setDate(date);
		Soap other = new Soap(date, "info");
		assertEquals(test.compareTo(other), 0);
		
		other.setDate(new Date("12/12/2012"));
		test.setDate(new Date("11/12/2012"));
		assertEquals(test.compareTo(other), -1);
		assertEquals(other.compareTo(test), 1);

		assertEquals(other.getInfo(), "info");
		other.setInfo("changed info");
		assertEquals(other.getInfo(), "changed info");
		
		assertEquals(other.getDate(), new Date("12/12/2012"));
		other.setDate(new Date("1/1/2014"));
		assertEquals(other.getDate(), new Date("1/1/2014"));
	}

	protected void tearDown() throws Exception {
		System.out.println( "Shutting Down Test DB" );
		database.dbResetForTesting();
		service.shutDownDB();
		super.tearDown();
	}

}
