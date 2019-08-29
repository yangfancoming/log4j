

package org.apache.log4j;
import org.apache.log4j.helpers.PatternParser;

/**

  Example showing how to extend EnhancedPatternLayout to recognize additional
  conversion characters.  
  
  <p>In this case MyPatternLayout recognizes %# conversion pattern. It
  outputs the value of an internal counter which is also incremented
  at each call.

  <p>See <a href=doc-files/MyPatternLayout.java><b>source</b></a> code
  for more details.

  @see MyPatternParser
  @see org.apache.log4j.EnhancedPatternLayout
 @author Anders Kristensen
*/
public class EnhancedMyPatternLayout extends EnhancedPatternLayout {
  public
  EnhancedMyPatternLayout() {
    this(DEFAULT_CONVERSION_PATTERN);
  }

  public
  EnhancedMyPatternLayout(String pattern) {
    super(pattern);
  }
    
  public
  PatternParser createPatternParser(String pattern) {
    return new MyPatternParser(
      pattern == null ? DEFAULT_CONVERSION_PATTERN : pattern);
  }
}
