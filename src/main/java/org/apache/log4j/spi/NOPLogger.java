
package org.apache.log4j.spi;

import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 * No-operation implementation of Logger used by NOPLoggerRepository.
 * @since 1.2.15
 */
public final class NOPLogger extends Logger {
    /**
     * Create instance of Logger.
     * @param repo repository, may not be null.
     * @param name name, may not be null, use "root" for root logger.
     */
    public NOPLogger(NOPLoggerRepository repo, final String name) {
        super(name);
        this.repository = repo;
        this.level = Level.OFF;
        this.parent = this;
    }

    @Override

    public void addAppender(final Appender newAppender) {
    }

    @Override
    public void assertLog(final boolean assertion, final String msg) {
    }


    @Override
    public void callAppenders(final LoggingEvent event) {
    }

    void closeNestedAppenders() {
    }

    @Override
    public void debug(final Object message) {
    }


    @Override
    public void debug(final Object message, final Throwable t) {
    }

    @Override
    public void error(final Object message) {
    }

    @Override
    public void error(final Object message, final Throwable t) {
    }


    @Override
    public void fatal(final Object message) {
    }

    @Override
    public void fatal(final Object message, final Throwable t) {
    }


    @Override
    public Enumeration getAllAppenders() {
        return new Vector().elements();
    }

    @Override
    public Appender getAppender(final String name) {
        return null;
    }

    @Override
    public Level getEffectiveLevel() {
        return Level.OFF;
    }

    @Override
    public Priority getChainedPriority() {
        return getEffectiveLevel();
    }

    @Override
    public ResourceBundle getResourceBundle() {
        return null;
    }


    @Override
    public void info(final Object message) {
    }

    @Override
    public void info(final Object message, final Throwable t) {
    }

    @Override
    public boolean isAttached(Appender appender) {
        return false;
    }

    @Override
    public boolean isDebugEnabled() {
        return false;
    }

    @Override
    public boolean isEnabledFor(final Priority level) {
        return false;
    }

    @Override
    public boolean isInfoEnabled() {
        return false;
    }


    @Override
    public void l7dlog(final Priority priority, final String key, final Throwable t) {
    }

    @Override
    public void l7dlog(final Priority priority, final String key,  final Object[] params, final Throwable t) {
    }

    @Override
    public void log(final Priority priority, final Object message, final Throwable t) {
    }

    @Override
    public void log(final Priority priority, final Object message) {
    }

    @Override
    public void log(final String callerFQCN, final Priority level, final Object message, final Throwable t) {
    }

    @Override
    public void removeAllAppenders() {
    }


    @Override
    public void removeAppender(Appender appender) {
    }

    @Override
    public void removeAppender(final String name) {
    }

    @Override
    public void setLevel(final Level level) {
    }


    @Override
    public void setPriority(final Priority priority) {
    }

    @Override
    public void setResourceBundle(final ResourceBundle bundle) {
    }

    @Override
    public void warn(final Object message) {
    }

    @Override
    public void warn(final Object message, final Throwable t) {
    }

    @Override
    public void trace(Object message) {
    }

    @Override
    public void trace(Object message, Throwable t) {
    }

    @Override
    public boolean isTraceEnabled() {
        return false;
    }


}
