
package org.apache.log4j.spi;
import java.io.Writer;

/**
  * NullWriter is an obsolete class provided only for
  *  binary compatibility with earlier versions of log4j and should not be used.
  *
  * @deprecated
  */
class NullWriter extends Writer {

  public void close() {
    // blank
  }

  public void flush() {
    // blank
  }

  public void write(char[] cbuf, int off, int len) {
    // blank
  }
}
