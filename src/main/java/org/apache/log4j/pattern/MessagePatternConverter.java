

package org.apache.log4j.pattern;

import org.apache.log4j.spi.LoggingEvent;


/**
 * Return the event's rendered message in a StringBuffer.
 *
 * @author Ceki G&uuml;lc&uuml;
 */
public final class MessagePatternConverter extends LoggingEventPatternConverter {
  /**
   * Singleton.
   */
  private static final MessagePatternConverter INSTANCE =
    new MessagePatternConverter();

  /**
   * Private constructor.
   */
  private MessagePatternConverter() {
    super("Message", "message");
  }

  /**
   * Obtains an instance of pattern converter.
   * @param options options, may be null.
   * @return instance of pattern converter.
   */
  public static MessagePatternConverter newInstance(
    final String[] options) {
    return INSTANCE;
  }

  /**
   * {@inheritDoc}
   */
  public void format(final LoggingEvent event, final StringBuffer toAppendTo) {
    toAppendTo.append(event.getRenderedMessage());
  }
}
