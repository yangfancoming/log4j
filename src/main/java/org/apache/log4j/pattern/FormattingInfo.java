

package org.apache.log4j.pattern;


/**
 * Modifies the output of a pattern converter for a specified minimum
 * and maximum width and alignment.
 *
 *
 *  @author <a href=mailto:jim_cakalic@na.biomerieux.com>Jim Cakalic</a>
 *  @author Ceki G&uuml;lc&uuml;
 *  @author Curt Arnold
 *
 */
public final class FormattingInfo {
  /**
   *  Array of spaces.
   */
  private static final char[] SPACES =
    new char[] { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };

  /**
   * Default instance.
   */
  private static final FormattingInfo DEFAULT =
    new FormattingInfo(false, 0, Integer.MAX_VALUE);

  /**
   * Minimum length.
   */
  private final int minLength;

  /**
   * Maximum length.
   */
  private final int maxLength;

  /**
   * Alignment.
   */
  private final boolean leftAlign;

  /**
   * Creates new instance.
   * @param leftAlign left align if true.
   * @param minLength minimum length.
   * @param maxLength maximum length.
   */
  public FormattingInfo(
    final boolean leftAlign, final int minLength, final int maxLength) {
    this.leftAlign = leftAlign;
    this.minLength = minLength;
    this.maxLength = maxLength;
  }

  /**
   * Gets default instance.
   * @return default instance.
   */
  public static FormattingInfo getDefault() {
    return DEFAULT;
  }

  /**
   * Determine if left aligned.
   * @return true if left aligned.
   */
  public boolean isLeftAligned() {
    return leftAlign;
  }

  /**
   * Get minimum length.
   * @return minimum length.
   */
  public int getMinLength() {
    return minLength;
  }

  /**
   * Get maximum length.
   * @return maximum length.
   */
  public int getMaxLength() {
    return maxLength;
  }

  /**
   * Adjust the content of the buffer based on the specified lengths and alignment.
   *
   * @param fieldStart start of field in buffer.
   * @param buffer buffer to be modified.
   */
  public void format(final int fieldStart, final StringBuffer buffer) {
    final int rawLength = buffer.length() - fieldStart;

    if (rawLength > maxLength) {
      buffer.delete(fieldStart, buffer.length() - maxLength);
    } else if (rawLength < minLength) {
      if (leftAlign) {
        final int fieldEnd = buffer.length();
        buffer.setLength(fieldStart + minLength);

        for (int i = fieldEnd; i < buffer.length(); i++) {
          buffer.setCharAt(i, ' ');
        }
      } else {
        int padLength = minLength - rawLength;

        for (; padLength > 8; padLength -= 8) {
          buffer.insert(fieldStart, SPACES);
        }

        buffer.insert(fieldStart, SPACES, 0, padLength);
      }
    }
  }
}
