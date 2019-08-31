

package org.apache.log4j;

import java.util.Vector;
import org.apache.log4j.spi.LoggingEvent;

/**
 An appender that appends logging events to a vector.
 */
public class VectorAppender extends AppenderSkeleton {

    public Vector vector;

    public VectorAppender() {
        vector = new Vector();
    }

    /**
     Does nothing.
     */
    public void activateOptions() {
    }

    /**
     This method is called by the {@link AppenderSkeleton#doAppend}  method.
     */
    public void append(LoggingEvent event) {
        //System.out.println("---Vector appender called with message ["+event.getRenderedMessage()+"].");
        //System.out.flush();
        try {
            Thread.sleep(100);
        } catch(Exception e) {
        }
        vector.addElement(event);
    }

    public Vector getVector() {
        return vector;
    }

    public synchronized void close() {
        if(this.closed)
            return;
        this.closed = true;
    }


    public boolean isClosed() {
        return closed;
    }

    public boolean requiresLayout() {
        return false;
    }
}
