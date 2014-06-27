package tests.objectTests;

import cis.buisness.Client;
import junit.framework.TestCase;

//import org.junit.Test;


public class ClientTest extends TestCase 
{
	Client user;
	public ClientTest(String name) 
	{
		super(name);
	}

	@Override
	protected void setUp() throws Exception 
	{
		super.setUp();
		user = new Client();
		
		assertEquals(true, user.isActive());
		assertEquals(user.lastSoap(), null);
	}

	public void testSetUp()
	{
		
	}
	
	@Override
	protected void tearDown() throws Exception 
	{
		super.tearDown();
	}

}
