
package org.apache.log4j.rewrite;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

/**
 * This policy rewrites events by adding
 * a user-specified list of properties to the event.
 * Existing properties are not modified.
 *
 * The combination of the RewriteAppender and this policy
 * performs the same actions as the PropertyFilter from log4j 1.3.
 */

public class PropertyRewritePolicy implements RewritePolicy {
    private Map properties = Collections.EMPTY_MAP;
    public PropertyRewritePolicy() {
    }

    /**
     * Set a string representing the property name/value pairs.
     * 
     * Form: propname1=propvalue1,propname2=propvalue2
     * 
     * @param props
     */
    public void setProperties(String props) {
        Map hashTable = new HashMap();
        StringTokenizer pairs = new StringTokenizer(props, ",");
        while (pairs.hasMoreTokens()) {
            StringTokenizer entry = new StringTokenizer(pairs.nextToken(), "=");
            hashTable.put(entry.nextElement().toString().trim(), entry.nextElement().toString().trim());
        }
        synchronized(this) {
            properties = hashTable;
        }
    }


    public LoggingEvent rewrite(final LoggingEvent source) {
        if (!properties.isEmpty()) {
            Map rewriteProps = new HashMap(source.getProperties());
            for(Iterator iter = properties.entrySet().iterator();
                    iter.hasNext();
                    ) {
                Map.Entry entry = (Map.Entry) iter.next();
                if (!rewriteProps.containsKey(entry.getKey())) {
                    rewriteProps.put(entry.getKey(), entry.getValue());
                }
            }

            return new LoggingEvent(
                    source.getFQNOfLoggerClass(),
                    source.getLogger() != null ? source.getLogger(): Logger.getLogger(source.getLoggerName()), 
                    source.getTimeStamp(),
                    source.getLevel(),
                    source.getMessage(),
                    source.getThreadName(),
                    source.getThrowableInformation(),
                    source.getNDC(),
                    source.getLocationInformation(),
                    rewriteProps);
        }
        return source;
    }



}
