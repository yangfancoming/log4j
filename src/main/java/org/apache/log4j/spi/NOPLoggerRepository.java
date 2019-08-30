
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

    @Override
    public void addHierarchyEventListener(final HierarchyEventListener listener) {
    }
    @Override
    public boolean isDisabled(final int level) {
        return true;
    }

    @Override
    public void setThreshold(final Level level) {
    }

    @Override
    public void setThreshold(final String val) {
    }

    @Override
    public void emitNoAppenderWarning(final Category cat) {
    }

    @Override
    public Level getThreshold() {
        return Level.OFF;
    }

    @Override
    public Logger getLogger(final String name) {
        return new NOPLogger(this, name);
    }

    @Override
    public Logger getLogger(final String name, final LoggerFactory factory) {
        return new NOPLogger(this, name);
    }

    @Override
    public Logger getRootLogger() {
        return new NOPLogger(this, "root");
    }

    @Override
    public Logger exists(final String name) {
        return null;
    }

    @Override
    public void shutdown() {
    }

    @Override
    public Enumeration getCurrentLoggers() {
        return new Vector().elements();
    }

    @Override
    public Enumeration getCurrentCategories() {
        return getCurrentLoggers();
    }

    @Override
    public  void fireAddAppenderEvent(Category logger, Appender appender) {
    }
    @Override
    public void resetConfiguration() {
    }
}
