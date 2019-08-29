

package org.apache.log4j.spi;

import org.apache.log4j.Category;
import org.apache.log4j.DefaultThrowableRenderer;

/**
  * ThrowableInformation is log4j's internal representation of
  * throwables. It essentially consists of a string array, called
  * 'rep', where the first element, that is rep[0], represents the
  * string representation of the throwable (i.e. the value you get
  * when you do throwable.toString()) and subsequent elements
  * correspond the stack trace with the top most entry of the stack
  * corresponding to the second entry of the 'rep' array that is
  * rep[1].
  *
  * @author Ceki G&uuml;lc&uuml;
  *
  * */
public class ThrowableInformation implements java.io.Serializable {

  static final long serialVersionUID = -4748765566864322735L;

  private transient Throwable throwable;
  private transient Category category;
  private String[] rep;

  public
  ThrowableInformation(Throwable throwable) {
    this.throwable = throwable;
  }

    /**
     * Create a new instance.
     * @param throwable throwable, may not be null.
     * @param category category used to obtain ThrowableRenderer, may be null.
     * @since 1.2.16
     */
  public ThrowableInformation(Throwable throwable, Category category) {
      this.throwable = throwable;
      this.category = category;
  }

    /**
     * Create new instance.
     * @since 1.2.15
     * @param r String representation of throwable.
     */
  public ThrowableInformation(final String[] r) {
      if (r != null) {
        rep = (String[]) r.clone();
      }
  }


  public
  Throwable getThrowable() {
    return throwable;
  }

  public synchronized String[] getThrowableStrRep() {
    if(rep == null) {
      ThrowableRenderer renderer = null;
      if (category != null) {
          LoggerRepository repo = category.getLoggerRepository();
          if (repo instanceof ThrowableRendererSupport) {
              renderer = ((ThrowableRendererSupport) repo).getThrowableRenderer();
          }
      }
      if (renderer == null) {
          rep = DefaultThrowableRenderer.render(throwable);
      } else {
          rep = renderer.doRender(throwable);
      }
    }
    return (String[]) rep.clone();
  }
}


