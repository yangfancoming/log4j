

package org.apache.log4j;

import junit.framework.TestCase;
import org.apache.log4j.helpers.LogLog;

import java.io.File;

import java.lang.reflect.Method;


/**
 *
 * FileAppender tests.
 *
 * @author Curt Arnold
 */
public class FileAppenderTest extends TestCase {
  /**
   * Tests that any necessary directories are attempted to
   * be created if they don't exist.  See bug 9150.
   *
   */
  public void testDirectoryCreation() {
    //
    //   known to fail on JDK 1.1
    if (!System.getProperty("java.version").startsWith("1.1.")) {
      File newFile = new File("output/newdir/temp.log");
      newFile.delete();

      File newDir = new File("output/newdir");
      newDir.delete();

      org.apache.log4j.FileAppender wa = new org.apache.log4j.FileAppender();
      wa.setFile("output/newdir/temp.log");
      wa.setLayout(new PatternLayout("%m%n"));
      wa.activateOptions();

      assertTrue(new File("output/newdir/temp.log").exists());
    }
  }

  /**
   * Tests that the return type of getThreshold is Priority.
   * @throws Exception
   */
  public void testGetThresholdReturnType() throws Exception {
    Method method = FileAppender.class.getMethod("getThreshold", (Class[]) null);
    assertTrue(method.getReturnType() == Priority.class);
  }

  /**
   * Tests getThreshold and setThreshold.
   */
  public void testgetSetThreshold() {
    FileAppender appender = new FileAppender();
    Priority debug = Level.DEBUG;
    assertNull(appender.getThreshold());
    appender.setThreshold(debug);
    assertTrue(appender.getThreshold() == debug);
  }

  /**
   * Tests isAsSevereAsThreshold.
   */
  public void testIsAsSevereAsThreshold() {
    FileAppender appender = new FileAppender();
    Priority debug = Level.DEBUG;
    assertTrue(appender.isAsSevereAsThreshold(debug));
  }
}
