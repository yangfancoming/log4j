

package org.apache.log4j.defaultInit;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Test;
import java.util.Enumeration;
import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class TestCase2 extends TestCase {

  public TestCase2(String name) {
    super(name);
  }

  public void setUp() {
  }

  public void tearDown() {
    LogManager.shutdown();
  }

  public void xmlTest() {
    Logger root = Logger.getRootLogger();
    boolean rootIsConfigured = root.getAllAppenders().hasMoreElements();
    assertTrue(rootIsConfigured);
    Enumeration e = root.getAllAppenders();
    Appender appender = (Appender) e.nextElement();
    assertEquals(appender.getName(), "D1");
  }

  public static Test suite() {
    TestSuite suite = new TestSuite();
    suite.addTest(new TestCase2("xmlTest"));
    return suite;
  }

}

