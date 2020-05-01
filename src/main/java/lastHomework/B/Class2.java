package lastHomework.B;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Class2 {

  private static final Logger logger = LogManager.getLogger(Class2.class.getName());

  public static void main(String[] args) {

    logger.trace("Trace");
    logger.warn("Warn");
    logger.info("Info");
    logger.error("Error");
    logger.fatal("Fatal");
  }
}
