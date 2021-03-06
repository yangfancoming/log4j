

package org.apache.log4j;

import org.apache.log4j.spi.OptionHandler;
import org.apache.log4j.spi.LoggingEvent;

/**
 Extend this abstract class to create your own log layout format.
 Layout是对日志行格式的抽象
 Layout抽象成一个模板，比较常用的PatternLayout，HTMLLayout都是该类子类
 */

public abstract class Layout implements OptionHandler {

    // Note that the line.separator property can be looked up even by  applets.
    public final static String LINE_SEP = System.getProperty("line.separator");
    public final static int LINE_SEP_LEN  = LINE_SEP.length();

    /**
     Implement this method to create your own layout format.
     */
    public  abstract  String format(LoggingEvent event);

    /**
     Returns the content type output by this layout. The base class
     returns "text/plain".
     */
    public String getContentType() {
        return "text/plain";
    }

    /**
     Returns the header for the layout format. The base class returns
     <code>null</code>.  */
    public String getHeader() {
        return null;
    }

    /**
     Returns the footer for the layout format. The base class returns
     <code>null</code>.  */
    public String getFooter() {
        return null;
    }

    /**
     If the layout handles the throwable object contained within {@link LoggingEvent}, then the layout should return
     <code>false</code>. Otherwise, if the layout ignores throwable
     object, then the layout should return <code>true</code>.
     If ignoresThrowable is true, the appender is responsible for rendering the throwable.
     <p>The {@link SimpleLayout}, {@link TTCCLayout}, {@link
    PatternLayout} all return <code>true</code>. The {@link
    org.apache.log4j.xml.XMLLayout} returns <code>false</code>.

     @since 0.8.4 */
    public abstract boolean ignoresThrowable();

}
