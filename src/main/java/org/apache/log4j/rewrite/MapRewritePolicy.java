
package org.apache.log4j.rewrite;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

/**
 * This policy rewrites events where the message of the
 * original event implementes java.util.Map.
 * All other events are passed through unmodified.
 * If the map contains a "message" entry, the value will be
 * used as the message for the rewritten event.  The rewritten
 * event will have a property set that is the combination of the
 * original property set and the other members of the message map.
 * If both the original property set and the message map
 * contain the same entry, the value from the message map
 * will overwrite the original property set.
 *
 * The combination of the RewriteAppender and this policy
 * performs the same actions as the MapFilter from log4j 1.3. 
 */
public class MapRewritePolicy implements RewritePolicy {

    public LoggingEvent rewrite(final LoggingEvent source) {
        Object msg = source.getMessage();
        if (msg instanceof Map) {
            Map props = new HashMap(source.getProperties());
            Map eventProps = (Map) msg;
            //
            //   if the map sent in the logging request
            //      has "message" entry, use that as the message body
            //      otherwise, use the entire map.
            //
            Object newMsg = eventProps.get("message");
            if (newMsg == null) {
                newMsg = msg;
            }

            for(Iterator iter = eventProps.entrySet().iterator();
                    iter.hasNext();
                  ) {
                Map.Entry entry = (Map.Entry) iter.next();
                if (!("message".equals(entry.getKey()))) {
                    props.put(entry.getKey(), entry.getValue());
                }
            }

            return new LoggingEvent(
                    source.getFQNOfLoggerClass(),
                    source.getLogger() != null ? source.getLogger(): Logger.getLogger(source.getLoggerName()), 
                    source.getTimeStamp(),
                    source.getLevel(),
                    newMsg,
                    source.getThreadName(),
                    source.getThrowableInformation(),
                    source.getNDC(),
                    source.getLocationInformation(),
                    props);
        } else {
            return source;
        }

    }
}
