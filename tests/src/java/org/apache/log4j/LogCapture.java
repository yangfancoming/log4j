
package org.apache.log4j;

import junit.framework.Assert;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.VectorAppender;
import org.apache.log4j.spi.LoggingEvent;

import java.util.Vector;


/**
 * Helper class to set up and capture log messages.
 */
public class LogCapture {
    /**
     * Appender.
     */
    private final VectorAppender appender;

    /**
     * Expected level.
     */
    private final Level level;

    /**
     * Creates new instance of LogCapture.
     *
     */
    public LogCapture(final Level level) {
        this.level = level;

        Logger root = Logger.getRootLogger();
        appender = new VectorAppender();
        root.addAppender(appender);
    }

    /**
     * Get message.
     * @return rendered message, null if no logging event captured.
     */
    public String getMessage() {
        Vector vector = appender.getVector();
        String msg = null;

        switch (vector.size()) {
        case 0:
            break;

        case 1:

            LoggingEvent event = (LoggingEvent) vector.elementAt(0);
            Assert.assertNotNull(event);
            Assert.assertEquals(level, event.getLevel());
            msg = event.getRenderedMessage();

            break;

        default:
            Assert.fail("More than one request captured");
        }

        return msg;
    }
}
