package tests;

import tests.integration.IntegrationTest;
import junit.framework.Test;
import junit.framework.TestSuite;

public class RunIntegrationTest {
	public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("All tests");
        testIntegration();
        return suite;
    }
	
	
    private static void testIntegration()
    {
    	suite.addTestSuite(IntegrationTest.class);
    }

}
