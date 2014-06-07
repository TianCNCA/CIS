package buisness;

import java.util.*;

import org.junit.Test;

import static org.junit.Assert.*;
import junit.framework.TestCase;

public class ClientTest extends TestCase 
{
	Client user;
	public ClientTest(String name) 
	{
		super(name);
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
		user = new Client();
		
		assertEquals(true, user.isActive());
		assertEquals(user.lastSoap(), null);
	}

	@Test
	public void testSetUp()
	{
		
	}
	
	protected void tearDown() throws Exception 
	{
		super.tearDown();
	}

}
