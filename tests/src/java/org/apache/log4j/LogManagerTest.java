

package org.apache.log4j;

import junit.framework.TestCase;


/**
 *    Tests for LogManager
 *
 * @author Curt Arnold
 **/
public class LogManagerTest extends TestCase {
  /**
   * Create new instance of LogManagerTest.
   * @param testName test name
   */
  public LogManagerTest(final String testName) {
    super(testName);
  }

  /**
   *  Check value of DEFAULT_CONFIGURATION_FILE.
   *  @deprecated since constant is deprecated
   */
  public void testDefaultConfigurationFile() {
     assertEquals("log4j.properties", LogManager.DEFAULT_CONFIGURATION_FILE);
  }

  /**
   *  Check value of DEFAULT_XML_CONFIGURATION_FILE.
   */
  public void testDefaultXmlConfigurationFile() {
     assertEquals("log4j.xml", LogManager.DEFAULT_XML_CONFIGURATION_FILE);
  }
  
  /**
   *  Check value of DEFAULT_CONFIGURATION_KEY.
   *  @deprecated since constant is deprecated
   */
  public void testDefaultConfigurationKey() {
     assertEquals("log4j.configuration", LogManager.DEFAULT_CONFIGURATION_KEY);
  }
  
  /**
   *  Check value of CONFIGURATOR_CLASS_KEY.
   *  @deprecated since constant is deprecated
   */
  public void testConfiguratorClassKey() {
     assertEquals("log4j.configuratorClass", LogManager.CONFIGURATOR_CLASS_KEY);
  }
  
  /**
   *  Check value of DEFAULT_INIT_OVERRIDE_KEY.
   *  @deprecated since constant is deprecated
   */
  public void testDefaultInitOverrideKey() {
     assertEquals("log4j.defaultInitOverride", LogManager.DEFAULT_INIT_OVERRIDE_KEY);
  }
}
