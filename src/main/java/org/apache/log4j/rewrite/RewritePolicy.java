package org.apache.log4j.rewrite;

import org.apache.log4j.spi.LoggingEvent;



/**
 * This interface is implemented to provide a rewrite
 * strategy for RewriteAppender.  RewriteAppender will
 * call the rewrite method with a source logging event.
 * The strategy may return that event, create a new event
 * or return null to suppress the logging request.
 */
public interface RewritePolicy {
    /**
     * Rewrite a logging event.
     * @param source a logging event that may be returned or
     * used to create a new logging event.
     * @return a logging event or null to suppress processing.
     */
    LoggingEvent rewrite(final LoggingEvent source);
}
