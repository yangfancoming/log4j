

package org.apache.log4j.spi;

import org.apache.log4j.Appender;
import org.apache.log4j.Category;

/**
   Listen to events occuring within a {@link
   org.apache.log4j.Hierarchy Hierarchy}.

   @author Ceki G&uuml;lc&uuml;
   @since 1.2
   
 */
public interface HierarchyEventListener {

 
  //public
  //void categoryCreationEvent(Category cat);


  public
  void addAppenderEvent(Category cat, Appender appender);

  public
  void removeAppenderEvent(Category cat, Appender appender);


}
