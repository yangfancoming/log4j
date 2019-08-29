

package org.apache.log4j.util;

import java.util.regex.Pattern;


public class EnhancedLineNumberFilter implements Filter {
  private Pattern linePattern;
  private Pattern nativePattern;

  public EnhancedLineNumberFilter() {
      linePattern = Pattern.compile("\\(.*:\\d{1,4}\\)");
      nativePattern = Pattern.compile("\\(Native Method\\)");
  }

  public String filter(final String in) {

    if (linePattern.matcher(in).find()) {
        return linePattern.matcher(in).replaceAll("(X)");
    } else if (nativePattern.matcher(in).find()) {
        return nativePattern.matcher(in).replaceAll("(X)");
    } else {
      return in;
    }
  }
}
