

package org.apache.log4j.pattern;

/**
 * The class implements the pre log4j 1.3 org.apache.log4j.helpers.PatternConverter
 * contract by delegating to the log4j 1.3 pattern implementation.
 *
 *
 * @author Curt Arnold
 *
 */
public final class BridgePatternParser
  extends org.apache.log4j.helpers.PatternParser {


  /**
   * Create a new instance.
   * @param conversionPattern pattern, may not be null.
   */
  public BridgePatternParser(
    final String conversionPattern) {
    super(conversionPattern);
  }

  /**
   * Create new pattern converter.
   * @return pattern converter.
   */
  public org.apache.log4j.helpers.PatternConverter parse() {
    return new BridgePatternConverter(pattern);
  }
}
