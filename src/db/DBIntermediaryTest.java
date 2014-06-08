package db;

import static org.junit.Assert.*;
import buisness.Client;

import org.junit.Test;

public class DBIntermediaryTest
{
	@Test
	public void testDBIntermediary()
	{
		DBIntermediary db = new DBIntermediary();
		assertEquals( 0, db.getSize() );
	}


	@Test
	public void testInsertClient()
	{
		System.out.println( "Test Insert" );
		
		DBIntermediary db = new DBIntermediary();
		Client testClient = new Client( "Pat" );
		Boolean didInsert;
		db.insertClient( testClient );

		assertEquals( 1, db.getSize() );

		// Should not allow duplicates
		Client testClient1 = new Client( "Pat" );
		didInsert = db.insertClient( testClient1 );
		assertFalse( didInsert );

		Client testClient2 = new Client( "Pat" );
		didInsert = db.insertClient( testClient2 );
		assertFalse( didInsert );

		assertEquals( 1, db.getSize() );

		// Now that readClient is tested, lets use it to find someone, to assure
		// us that
		// it is in there
		assertEquals( testClient.getName(), db.readClient( "Pat" ).getName() );
		
		System.out.println( "End Test Insert\n" );
	}


	@Test
	public void testDeleteClient()
	{
		System.out.println( "Test Delete" );
		
		// Create a quick DB
		DBIntermediary db = new DBIntermediary();
		Client testClient = new Client( "Pat" );
		db.insertClient( testClient );
		Client testClient1 = new Client( "George" );
		db.insertClient( testClient1 );
		Client testClient2 = new Client( "Fred" );
		db.insertClient( testClient2 );
		Client testClient3 = new Client( "Patirick" );
		db.insertClient( testClient3 );
		Client testClient4 = new Client( "Patty" );
		db.insertClient( testClient4 );

		assertEquals( 5, db.getSize() );
		assertEquals( 5, db.getInterSize() );

		System.out.println( db.DumpDB() );

		// Try some deletes, and try to delete already deleted clients
		db.deleteClient( testClient ); // Pat
		assertEquals( 4, db.getSize() );
		db.deleteClient( testClient1 ); // George
		assertEquals( 3, db.getSize() );
		db.deleteClient( testClient2 ); // Fred
		assertEquals( 2, db.getSize() );
		db.deleteClient( testClient ); // Pat
		assertEquals( 2, db.getSize() );
		db.deleteClient( testClient1 ); // George

		// The two sizes should be the same
		System.out.println( db.getSize() + " " + db.getInterSize() );
		assertEquals( 2, db.getSize() );
		assertEquals( 2, db.getInterSize() );

		System.out.println( db.DumpDB() );

		String dump = db.DumpDB();
		String[] tokens = dump.split( "," );
		assertEquals( 2, tokens.length );
		
		System.out.println( "End Test Delete\n" );
	}


	@Test
	public void testrenameClient()
	{
		System.out.println( "Test Rename" );
		// Build quick DB
		DBIntermediary db = new DBIntermediary();
		Client testClient = new Client( "Pat" );
		db.insertClient( testClient );
		Client testClient1 = new Client( "George" );
		db.insertClient( testClient1 );
		
		System.out.println( db.DumpDB() );
		
		Boolean didRename = db.renameClient( "Pat", "Patty" );
		assertTrue( didRename );
		didRename = db.renameClient( "George", "Curious George" );
		assertTrue( didRename );
		
		assertNotNull( db.readClient( "Patty" ) );
		assertNotNull( db.readClient( "Curious George" ) );
		
		System.out.println( db.DumpDB() );
		
		System.out.println( "End Test Rename\n" );
	}


	@Test
	public void testUpdateClient()
	{
		System.out.println( "Test Update" );
		DBIntermediary db = new DBIntermediary();
		Boolean didUpdate = false;

		// Insert in two clients and then update their parms, making sure they
		// don't change
		Client testClient = new Client( "Pat" );
		testClient.setOccupation( "Doctor" );
		db.insertClient( testClient );
		Client testClient1 = new Client( "George" );
		testClient1.setOccupation( "Superman" );
		db.insertClient( testClient1 );

		System.out.println( db.DumpDB() );

		String oldOcc = db.readClient( "Pat" ).getOccupation();
		System.out.println( oldOcc );

		// Now update something
		Client clientToUpdate = db.readClient( "Pat" );
		clientToUpdate.setOccupation( "Super Awesome Doctor" );

		didUpdate = db.updateClient( clientToUpdate );
		String newOcc = db.readClient( "Pat" ).getOccupation();
		System.out.println( newOcc );
		assertNotEquals( oldOcc, newOcc );
		assertTrue( didUpdate );

		clientToUpdate = db.readClient( "George" );
		oldOcc = clientToUpdate.getOccupation();
		System.out.println( oldOcc );
		clientToUpdate.setOccupation( "Batman" );
		didUpdate = db.updateClient( clientToUpdate );
		newOcc = db.readClient( "George" ).getOccupation();
		System.out.println( newOcc );
		assertNotEquals( oldOcc, newOcc );
		assertTrue( didUpdate );
		
		System.out.println( "End Test Update\n" );
	}


	@Test
	public void testReadClient()
	{
		System.out.println( "Test Read" );
		DBIntermediary db = new DBIntermediary();

		// Test inserting a number of clients (5)
		Client testClient = new Client( "Pat" );
		db.insertClient( testClient );
		Client testClient1 = new Client( "George" );
		db.insertClient( testClient1 );
		Client testClient2 = new Client( "Fred" );
		db.insertClient( testClient2 );
		Client testClient3 = new Client( "Patirick" );
		db.insertClient( testClient3 );
		Client testClient4 = new Client( "Patty" );
		db.insertClient( testClient4 );

		assertEquals( 5, db.getSize() );
		assertEquals( testClient.getName(), db.readClient( "Pat" ).getName() );
		assertEquals( db.getInterSize(), db.getSize() );
		
		System.out.println( "End Test Read\n" );
	}


	@Test
	public void testDumpDB()
	{
		System.out.println( "Test Dump" );
		DBIntermediary db = new DBIntermediary();

		// Test inserting a number of clients (5)
		Client testClient = new Client( "Pat" );
		db.insertClient( testClient );
		Client testClient1 = new Client( "George" );
		db.insertClient( testClient1 );
		Client testClient2 = new Client( "Fred" );
		db.insertClient( testClient2 );
		Client testClient3 = new Client( "Patirick" );
		db.insertClient( testClient3 );
		Client testClient4 = new Client( "Patty" );
		db.insertClient( testClient4 );

		String dump = db.DumpDB();
		String[] tokens = dump.split( "," );

		// System.out.println( dump );
		assertEquals( 5, tokens.length );
		
		System.out.println( "End Test Dump\n" );
	}

}
