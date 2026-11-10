package tests;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import utiliity.RestFWLogger;

public class demotest {
	
	private static final Logger logger =
            RestFWLogger.getLogger(demotest.class);

	@Test
	public void testcase1() {
		RestFWLogger.startTestCase(logger, "testcase1");
		logger.info("Executing test case 1");
		RestFWLogger.endTestCase(logger, "testcase1");
	}
	
	@Test
	public void testcase2() {
		RestFWLogger.startTestCase(logger, "testcase2");
		logger.info("Executing test case 2");
		RestFWLogger.endTestCase(logger, "testcase2");
	}
}
