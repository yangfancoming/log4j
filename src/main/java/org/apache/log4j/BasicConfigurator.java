

// Contibutors: "Luke Blanshard" <Luke@quiq.com>
//              "Mark DONSZELMANN" <Mark.Donszelmann@cern.ch>
//              "Muly Oved" <mulyoved@hotmail.com>

package org.apache.log4j;


/**
   Use this class to quickly configure the package.
   <p>For file based configuration see {@link
   PropertyConfigurator}. For XML based configuration see {@link
   org.apache.log4j.xml.DOMConfigurator DOMConfigurator}.
   @since 0.8.1
*/
public class BasicConfigurator {

  protected BasicConfigurator() {
  }

  /**
     Add a {@link ConsoleAppender} that uses {@link PatternLayout}
     using the {@link PatternLayout#TTCC_CONVERSION_PATTERN} and
     prints to <code>System.out</code> to the root category.  */


  public static void configure() {
    Logger root = Logger.getRootLogger();
    root.addAppender(new ConsoleAppender( new PatternLayout(PatternLayout.TTCC_CONVERSION_PATTERN)));
  }

  /**
     Add <code>appender</code> to the root category.
     @param appender The appender to add to the root category.
  */
  public static void configure(Appender appender) {
    Logger root = Logger.getRootLogger();
    root.addAppender(appender);
  }

  /**
     Reset the default hierarchy to its defaut. It is equivalent to calling
     <code>Category.getDefaultHierarchy().resetConfiguration()</code>.
     See {@link Hierarchy#resetConfiguration()} for more details.  */

  public static void resetConfiguration() {
    LogManager.resetConfiguration();
  }
}
