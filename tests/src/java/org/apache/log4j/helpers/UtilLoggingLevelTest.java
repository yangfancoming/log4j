

package org.apache.log4j.helpers;

import junit.framework.*;


/**
 * Unit tests for UtilLoggingLevel.
 */

public class UtilLoggingLevelTest extends TestCase {

    /**
     * Create new instance of test.
     *
     * @param testName test name
     */
    public UtilLoggingLevelTest(final String testName) {
        super(testName);
    }

    /**
     * Test toLevel("fiNeSt").
     */
    public void testToLevelFINEST() {
        assertSame(UtilLoggingLevel.FINEST, UtilLoggingLevel.toLevel("fiNeSt"));
    }

}

