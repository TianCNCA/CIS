package tests;

import tests.objectTests.ClientTest;
import tests.buisnessTests.*;
import junit.framework.Test;
import junit.framework.TestSuite;




public class AllTests
{
	public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("All tests");
        testObjects();
        testBusiness();
        return suite;
    }

    private static void testObjects()
    {
        suite.addTestSuite(ClientTest.class);

    }

    private static void testBusiness()
    {
        suite.addTestSuite(DataBaseAccessTest.class);
        suite.addTestSuite(DBIntermediaryTest.class);
    }
}
