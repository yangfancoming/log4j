

package org.apache.log4j.spi;

import java.util.Enumeration;

import org.apache.log4j.Appender;
import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
   A <code>LoggerRepository</code> is used to create and retrieve
   <code>Loggers</code>. The relation between loggers in a repository
   depends on the repository but typically loggers are arranged in a
   named hierarchy.

   <p>In addition to the creational methods, a
   <code>LoggerRepository</code> can be queried for existing loggers,
   can act as a point of registry for events related to loggers.

   @author Ceki G&uuml;lc&uuml;
   @since 1.2 */
public interface LoggerRepository {

  /**
     Add a {@link HierarchyEventListener} event to the repository.
  */
  public
  void addHierarchyEventListener(HierarchyEventListener listener);

  /**
     Returns whether this repository is disabled for a given
     level. The answer depends on the repository threshold and the
     <code>level</code> parameter. See also {@link #setThreshold}
     method.  */
  boolean isDisabled(int level);

  /**
     Set the repository-wide threshold. All logging requests below the
     threshold are immediately dropped. By default, the threshold is
     set to <code>Level.ALL</code> which has the lowest possible rank.  */
  public
  void setThreshold(Level level);

  /**
      Another form of {@link #setThreshold(Level)} accepting a string
      parameter instead of a <code>Level</code>. */
  public
  void setThreshold(String val);

  public
  void emitNoAppenderWarning(Category cat);

  /**
     Get the repository-wide threshold. See {@link
     #setThreshold(Level)} for an explanation. */
  public
  Level getThreshold();

  public
  Logger getLogger(String name);

  public
  Logger getLogger(String name, LoggerFactory factory);

  public
  Logger getRootLogger();

  public
  abstract
  Logger exists(String name);

  public
  abstract
  void shutdown();

  public
  Enumeration getCurrentLoggers();

  /**
     Deprecated. Please use {@link #getCurrentLoggers} instead.  */
  public
  Enumeration getCurrentCategories();


  public
  abstract
  void fireAddAppenderEvent(Category logger, Appender appender);

  public
  abstract
  void resetConfiguration();

}
