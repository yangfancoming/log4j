

package org.apache.log4j.lf5;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.LoggerRepository;

/**
 * The DefaultLF5Configuratorprovides a default configuration for the LF5Appender.
 * 
 *
 * Note: The preferred method for configuring a LF5Appender is to use the LF5Managerclass.
 * This class ensures that configuration does not occur multiple times, and improves system performance.
 * Reconfiguring the monitor multiple times can result in unexpected behavior.
 */

// Contributed by ThoughtWorks Inc.

public class DefaultLF5Configurator implements Configurator {
  //--------------------------------------------------------------------------
  // Constants:
  //--------------------------------------------------------------------------

  //--------------------------------------------------------------------------
  // Protected Variables:
  //--------------------------------------------------------------------------

  //--------------------------------------------------------------------------
  // Private Variables:
  //--------------------------------------------------------------------------

  //--------------------------------------------------------------------------
  // Constructors:
  //--------------------------------------------------------------------------
  /**
   * This class should never be instantiated! It implements the 
   * Configurator
   * interface, but does not provide the same functionality as full
   * configurator class.
   */
  private DefaultLF5Configurator() {

  }

  //--------------------------------------------------------------------------
  // Public Methods:
  //--------------------------------------------------------------------------
  /**
   * This method configures the LF5Appenderusing a
   * default configuration file. The default configuration file is
   * <bold>defaultconfig.properties</bold>.
   * @throws java.io.IOException
   */
  public static void configure() throws IOException {
    String resource = "/org/apache/log4j/lf5/config/defaultconfig.properties";
    URL configFileResource = DefaultLF5Configurator.class.getResource(resource);
    if (configFileResource != null) {
      PropertyConfigurator.configure(configFileResource);
    } else {
      throw new IOException("Error: Unable to open the resource" +  resource);
    }

  }

  /**
   * This is a dummy method that will throw an IllegalStateExceptionif used.
   * 这是一个虚拟方法，如果使用它，将引发IllegalstateException
   * @since 1.2.17
   */
  @Override
  public void doConfigure(InputStream inputStream, LoggerRepository repository) {
    throw new IllegalStateException("This class should NOT be instantiated!");
  }

  /**
   * This is a dummy method that will throw an IllegalStateExceptionif used.
   * 这是一个虚拟方法，如果使用它，将引发IllegalstateException
   */
  @Override
  public void doConfigure(URL configURL, LoggerRepository repository) {
    throw new IllegalStateException("This class should NOT be instantiated!");
  }

  //--------------------------------------------------------------------------
  // Protected Methods:
  //--------------------------------------------------------------------------

  //--------------------------------------------------------------------------
  // Private Methods:
  //--------------------------------------------------------------------------

  //--------------------------------------------------------------------------
  // Nested Top-Level Classes or Interfaces:
  //--------------------------------------------------------------------------

}