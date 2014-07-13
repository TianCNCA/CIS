package test.integration;

import java.util.ArrayList;
import java.util.Date;

import app.DBService;
import cis.buisness.Client;
import cis.buisness.ClientHistory;
import cis.buisness.DataAccess;
import cis.buisness.Soap;
import cis.buisness.SoapBox;
import cis.persistance.DataBaseAccess;
import junit.framework.TestCase;

@SuppressWarnings( "unused" )
public final class IntegrationTest extends TestCase
{	
	// We are now testing the DataAccess, i.e. the integration of the DB
	// into the project
	DataAccess database;
	DBService service;
	
	@Override
	protected void setUp() throws Exception 
	{
		super.setUp();
		System.out.println( "Setting up Test DB" );
		service = new DBService();
		service.initializeDB();
		service.setTesting();
		
		database = new DataAccess();
		
		assertEquals( 0, database.getSize() );
	}
	
	
	// Check to see if we can insert real quick
	public void testClientInsert()
	{
		System.out.println( "\nINSERT CLIENT TEST" );
		Client test    = new Client( "Georgy George" );
		database.insertClient( test );
		
		int dbCountt = database.getClientCount();
		
		System.out.println( "Count: " + dbCountt );
		assertEquals( dbCountt, 1 );
		System.out.println( "END INSERT CLIENT TEST\n" );
	}
	
	
	// Test to see if we can read real quick
	public void testReadClient()
	{
		System.out.println( "\nREAD CLIENT TEST" );
		Client test = new Client( "Georgy Georgerson" );
		database.insertClient( test );
		
		Client read = database.readClient( "Georgy Georgerson" );
		
		assertNotNull( read );

		System.out.println( "END READ CLIENT TEST\n" );
	}
	
	
	// Test a client with a fully populated field
	public void testFullClientInfoInsert()
	{
		Client client = new Client( "Pat Patty" );
		client.setActive( true );
		client.setAlcohol( 2 );
		client.setCity( "Winnipeg" );
		client.addSoap( "A soap is all up in this business" );
		client.addSoap( "Soap 2" );
		client.addSoap( "Soap 3" );
		ClientHistory history = new ClientHistory();
		history.setHeadaches( true, "Lot's of headaches" );
		history.setDiabetes( true, "Yup yup yup" );
		client.setHistory( history );
		
		database.insertClient( client );
		
		Client read = database.readClient( client );
		
		// History check
		assertEquals( read.getHistory().getHeadaches().getDisc(), "Lot's of headaches" );
		assertTrue( read.getHistory().getHeadaches().getChecked() );
		assertEquals( read.getHistory().getDiabetes().getDisc(), "Yup yup yup" );
		assertTrue( read.getHistory().getDiabetes().getChecked() );
		
		// Soap check
		assertEquals( read.getSoapBox().getSoapByIndex( 0 ).getInfo(), "A soap is all up in this business" );
		assertEquals( read.getSoapBox().getSoapByIndex( 1 ).getInfo(), "Soap 2" );
		assertEquals( read.getSoapBox().getSoapByIndex( 2 ).getInfo(), "Soap 3" );
		
		// Client check
		assertEquals( read.getCity(), "Winnipeg" );
		assertEquals( read.getAlcohol(), 2 );
		assertTrue( read.getActive() );
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
