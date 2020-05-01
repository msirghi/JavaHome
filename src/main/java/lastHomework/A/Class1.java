package lastHomework.A;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Class1 {

  private static final Logger logger = LogManager.getLogger("Class1");

  public static void main(String[] args) {
    logger.warn("Warn");
    logger.info("Info");
    logger.error("Error");
    logger.fatal("Fatal");
  }

}
