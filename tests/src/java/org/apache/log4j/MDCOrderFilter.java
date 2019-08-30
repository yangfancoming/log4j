

package org.apache.log4j;

import org.apache.log4j.util.Filter;

/**
 * This class switches MDC values into the order
 * (unreasonably) expected by the witness files.
 */
public class MDCOrderFilter implements Filter {

    /**
     * Unexpected orders of keys.
     * Note expected values are "va-one-one" and "va-one-two".
     */
  private static final String[] patterns =
          new String[] {
                  "{key2,va12}{key1,va11}",
                  "{key2,value2}{key1,value1}"
          };

    /**
     * Replacement values.
     */
  private static final String[] replacements =
            new String[] {
                    "{key1,va11}{key2,va12}",
                    "{key1,value1}{key2,value2}"
            };

  /**
   *  Switch order of MDC keys when not in expected order.
   */
  public String filter(final String in) {
    if (in == null) {
      return null;
    }

    for(int i = 0; i < patterns.length; i++) {
        int ipos = in.indexOf(patterns[i]);
        if (ipos >= 1) {
            return in.substring(0, ipos)
                    + replacements[i]
                    + in.substring(ipos + patterns[i].length());
        }
    }
    return in;
  }
}
