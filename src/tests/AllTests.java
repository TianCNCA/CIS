package tests;

import tests.integration.IntegrationTest;
import tests.objectTests.*;
import tests.persistanceTests.*;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests
{
	public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("All tests");
        testObjects();
        testPersistance();
        return suite;
    }
    
    
    private static void testObjects()
    {
        suite.addTestSuite(ClientTest.class);
        suite.addTestSuite(SoapBoxTest.class);
        suite.addTestSuite(SoapTest.class);
        suite.addTestSuite(HistoryItemTest.class);
        suite.addTestSuite(ClientHistoryTest.class);
    }

    private static void testPersistance()
    {
        suite.addTestSuite(DataBaseAccessTest.class);
        suite.addTestSuite(DBIntermediaryTest.class);
    }
}
