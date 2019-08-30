

package org.apache.log4j.helpers;

import junit.framework.TestCase;


/**
 *    Tests for LogLog.
 *
 * @author Curt Arnold
 **/
public class LogLogTest extends TestCase {
  /**
   * Create new instance of LogLogTest.
   * @param testName test name
   */
  public LogLogTest(final String testName) {
    super(testName);
  }

  /**
   *  Check value of DEBUG_KEY.
   */
  public void testDebugKey() {
    assertEquals("log4j.debug", LogLog.DEBUG_KEY);
  }

  /**
   *  Check value of CONFIG_DEBUG_KEY.
   *  @deprecated since constant is deprecated
   */
  public void testConfigDebugKey() {
    assertEquals("log4j.configDebug", LogLog.CONFIG_DEBUG_KEY);
  }
}
