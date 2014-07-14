package tests.objectTests;

import app.DBService;
import cis.business.DataAccess;
import cis.objects.HistoryItem;
import junit.framework.TestCase;

public class HistoryItemTest extends TestCase {

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
		HistoryItem test    = new HistoryItem();
		assertEquals(test.getDisc(), "");
		assertFalse(test.getChecked());
	}
	
	public void testChanges()
	{
		HistoryItem test = new HistoryItem( "info", false );
		assertEquals(test.getDisc(), "info");
		assertFalse(test.getChecked());
		
		test.setCheck(true);
		test.setDisc("new info");
		assertEquals(test.getDisc(), "new info");
		assertTrue(test.getChecked());
		
		test.setCheckAndDisc(false, null);
		assertEquals(test.getDisc(), "");
		assertFalse(test.getChecked());
		
		test.setCheck(false);
		assertFalse(test.getChecked());
	}

	protected void tearDown() throws Exception {
		System.out.println( "Shutting Down Test DB" );
		database.dbResetForTesting();
		service.shutDownDB();
		super.tearDown();
	}
}
