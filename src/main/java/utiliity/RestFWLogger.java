package utiliity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RestFWLogger {
	
	// Prevent object creation
	private RestFWLogger() {
    }

	// Return logger associated with the calling class
    public static Logger getLogger(Class<?> cls) {
        return LogManager.getLogger(cls);
    }

    public static void startTestCase(Logger logger, String testName) {
    	logger.info("****************************************************************************************");
    	logger.info("****************************************************************************************");
    	logger.info("$$$$$$$$$$$$$$$$$$$$$            " + testName + "         $$$$$$$$$$$$$$$$$$$$$$$$$");
    	logger.info("****************************************************************************************");
    	logger.info("****************************************************************************************");
    }

    public static void endTestCase(Logger logger, String testName) {
    	logger.info("XXXXXXXXXXXXXXXXXXXXXXX             " + "-E---N---D-" + "             XXXXXXXXXXXXXXXXXXXXXX");
    	logger.info("XXXXXXXXXXXXXXXXXXXXXXX***********************************************XXXXXXXXXXXXXXXXXXXXXX");
    	logger.info("XXXXXXXXXXXXXXXXXXXXXXX***********************************************XXXXXXXXXXXXXXXXXXXXXX");
    	logger.info("X");
    	logger.info("X");
    	logger.info("X");
    	logger.info("X");
    	logger.info("X");
    
    
    }

}
