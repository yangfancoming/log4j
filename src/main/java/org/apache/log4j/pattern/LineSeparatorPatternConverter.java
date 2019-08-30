

package org.apache.log4j.pattern;

import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;


/**
 * Formats a line separator.
 *
 * @author Ceki G&uuml;lc&uuml;
 */
public final class LineSeparatorPatternConverter
  extends LoggingEventPatternConverter {
  /**
   * Singleton.
   */
  private static final LineSeparatorPatternConverter INSTANCE =
    new LineSeparatorPatternConverter();

  /**
   * Line separator.
   */
  private final String lineSep;

  /**
   * Private constructor.
   */
  private LineSeparatorPatternConverter() {
    super("Line Sep", "lineSep");
    lineSep = Layout.LINE_SEP;
  }

  /**
   * Obtains an instance of pattern converter.
   * @param options options, may be null.
   * @return instance of pattern converter.
   */
  public static LineSeparatorPatternConverter newInstance(
    final String[] options) {
    return INSTANCE;
  }

  /**
   * {@inheritDoc}
   */
  public void format(LoggingEvent event, final StringBuffer toAppendTo) {
    toAppendTo.append(lineSep);
  }

  /**
   * {@inheritDoc}
   */
  public void format(final Object obj, final StringBuffer toAppendTo) {
    toAppendTo.append(lineSep);
  }
}
