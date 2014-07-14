package tests.objectTests;

import java.util.UUID;

import app.DBService;
import cis.business.DataAccess;
import cis.objects.ClientHistory;
import junit.framework.TestCase;

public class ClientHistoryTest extends TestCase {

	private DataAccess database;
	private DBService service;

	protected void setUp() throws Exception {
		super.setUp();
		System.out.println("Setting up Test DB");
		service = new DBService();
		service.initializeDB();
		service.setTesting();

		database = new DataAccess();
		database.dbResetForTesting();

		assertEquals(0, database.getSize());
	}

	public void testInitialize() {
		ClientHistory test = new ClientHistory();
		assertEquals(17, test.length());
		assertEquals(null, test.getKey());
		assertEquals(null, test.getClientID());
		
	}

	public void testChanges() {
		UUID id = UUID.randomUUID();
		UUID key = UUID.randomUUID();
		ClientHistory test = new ClientHistory(id);
		test.setKey(key);
		assertEquals(key, test.getKey());
		assertEquals(id, test.getClientID());
		
		test.setHeart( true, "info about the heart" );		
		test.setTingling( true, "tingling info" );
		test.setBloodPres( true, "blood info" );
		test.setBreathing( true, "breathing info" );
		test.setDiabetes( true, "diabetes info");
		test.setFaintness( true,"faintness info");
		test.setHeadaches( true, "headache info" );
		test.setContactLenses( true, "lenses info" );
		test.setShoes( true, "shoe info");
		test.setVaricose( true, "varicose info" );
		test.setArthritis( true, "arthritis info" );
		test.setCancer( true, "cancer info" );
		test.setDiarrhea( true, "diarrhea info" );
		test.setMeds( true, "meds info" );
		test.setCortisone( true, "cortisone info" );
		test.setSkin( true, "skin info" );
		test.setOther( true, "other info" );

		assertEquals("info about the heart" ,test.getByIndex(0).getDisc());
		assertEquals("tingling info" ,test.getTingling().getDisc());
		assertEquals("blood info" ,test.getBlood().getDisc());
		assertEquals("breathing info" ,test.getBreath().getDisc());
		assertEquals("diabetes info" ,test.getDiabetes().getDisc());
		assertEquals("faintness info" ,test.getFaint().getDisc());
		assertEquals("headache info" ,test.getHeadaches().getDisc());
		assertEquals("lenses info" ,test.getContactLense().getDisc());
		assertEquals("shoe info" ,test.getShoes().getDisc());
		assertEquals("varicose info" ,test.getVaricose().getDisc());
		assertEquals("arthritis info" ,test.getArthritis().getDisc());
		assertEquals("cancer info" ,test.getCancer().getDisc());
		assertEquals("diarrhea info" ,test.getDiarrhea().getDisc());
		assertEquals("meds info" ,test.getMeds().getDisc());
		assertEquals("cortisone info" ,test.getCortisone().getDisc());
		assertEquals("skin info" ,test.getSkin().getDisc());
		assertEquals("other info" ,test.getOther().getDisc());

		assertTrue(test.getByIndex(0).getChecked());
		
		test.setByIndex( false, "different info", 0 );

		assertEquals("different info" ,test.getHeart().getDisc());
		assertFalse(test.getHeart().getChecked());
		
	}

	protected void tearDown() throws Exception {
		System.out.println("Shutting Down Test DB");
		database.dbResetForTesting();
		service.shutDownDB();
		super.tearDown();
	}
}
