

package org.apache.log4j.pattern;

import org.apache.log4j.spi.LoggingEvent;


public class Num343PatternConverter extends LoggingEventPatternConverter {
  
  private Num343PatternConverter() {
      super("Num34", "num34");
  }
  private static final Num343PatternConverter INSTANCE = new Num343PatternConverter();

  public static PatternConverter newInstance(final String[] options) {
      return INSTANCE;
  }
    
  public void format(LoggingEvent event, StringBuffer toAppendTo) {
    toAppendTo.append("343");
  }
  
}
