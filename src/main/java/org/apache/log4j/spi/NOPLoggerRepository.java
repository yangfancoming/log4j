
package org.apache.log4j.spi;

import org.apache.log4j.Level;
import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.apache.log4j.Appender;

import java.util.Enumeration;
import java.util.Vector;

/**
 *  No-operation implementation of LoggerRepository which is used when
 *  LogManager.repositorySelector is erroneously nulled during class reloading.
 *  @since 1.2.15
 */
public final class NOPLoggerRepository implements LoggerRepository {

    public void addHierarchyEventListener(final HierarchyEventListener listener) {
    }

    public boolean isDisabled(final int level) {
        return true;
    }


    public void setThreshold(final Level level) {
    }


    public void setThreshold(final String val) {
    }


    public void emitNoAppenderWarning(final Category cat) {
    }


    public Level getThreshold() {
        return Level.OFF;
    }


    public Logger getLogger(final String name) {
        return new NOPLogger(this, name);
    }


    public Logger getLogger(final String name, final LoggerFactory factory) {
        return new NOPLogger(this, name);
    }


    public Logger getRootLogger() {
        return new NOPLogger(this, "root");
    }


    public Logger exists(final String name) {
        return null;
    }


    public void shutdown() {
    }


    public Enumeration getCurrentLoggers() {
        return new Vector().elements();
    }


    public Enumeration getCurrentCategories() {
        return getCurrentLoggers();
    }


    public  void fireAddAppenderEvent(Category logger, Appender appender) {
    }

    public void resetConfiguration() {
    }
}
