
package org.apache.log4j;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.apache.log4j.spi.LoggingEventTest;


/**
 * Suite of log4j class level unit tests.
 *
 */
public class CoreTestSuite {
    /**
     * Constructs test suite.
     * @return test suite
     */
    public static Test suite() {
        TestSuite s = new TestSuite();
        s.addTestSuite(LoggingEventTest.class);
        s.addTestSuite(org.apache.log4j.LevelTest.class);
        s.addTestSuite(org.apache.log4j.PriorityTest.class);
        s.addTestSuite(org.apache.log4j.CategoryTest.class);
        s.addTestSuite(org.apache.log4j.FileAppenderTest.class);
        s.addTestSuite(org.apache.log4j.LogManagerTest.class);
        s.addTestSuite(org.apache.log4j.helpers.LogLogTest.class);
        s.addTestSuite(org.apache.log4j.LayoutTest.class);
        s.addTestSuite(org.apache.log4j.helpers.DateLayoutTest.class);
        s.addTestSuite(org.apache.log4j.TTCCLayoutTest.class);
        s.addTestSuite(org.apache.log4j.xml.XMLLayoutTest.class);
        s.addTestSuite(org.apache.log4j.HTMLLayoutTest.class);
        s.addTestSuite(org.apache.log4j.PatternLayoutTest.class);
        s.addTestSuite(org.apache.log4j.spi.LoggingEventTest.class);
        s.addTestSuite(org.apache.log4j.spi.ThrowableInformationTest.class);
        s.addTestSuite(org.apache.log4j.spi.LocationInfoTest.class);
        s.addTestSuite(org.apache.log4j.PropertyConfiguratorTest.class);
        s.addTestSuite(org.apache.log4j.net.SMTPAppenderTest.class);
        s.addTestSuite(org.apache.log4j.net.TelnetAppenderTest.class);
        s.addTestSuite(org.apache.log4j.DefaultThrowableRendererTest.class);
        s.addTestSuite(org.apache.log4j.EnhancedThrowableRendererTest.class);
        s.addTestSuite(org.apache.log4j.TestLogXF.class);
        s.addTestSuite(org.apache.log4j.TestLogMF.class);
        s.addTestSuite(org.apache.log4j.TestLogSF.class);
        s.addTestSuite(org.apache.log4j.pattern.CachedDateFormatTest.class);
        s.addTestSuite(org.apache.log4j.pattern.FormattingInfoTest.class);
        s.addTestSuite(org.apache.log4j.pattern.NameAbbreviatorTest.class);
        s.addTestSuite(org.apache.log4j.pattern.PatternParserTest.class);
        s.addTestSuite(org.apache.log4j.helpers.UtilLoggingLevelTest.class);
        return s;
    }
}
