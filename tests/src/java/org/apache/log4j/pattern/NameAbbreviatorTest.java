

package org.apache.log4j.pattern;

import junit.framework.TestCase;


/**
 * Tests for NameAbbrevator.
 *
 */
public class NameAbbreviatorTest extends TestCase {
  /**
   * Create a new instance.
   *
   * @param name test name
   */
  public NameAbbreviatorTest(final String name) {
    super(name);
  }

  /**
   * Check that getDefaultAbbreviator does not return null.
   *
   */
  public void testGetDefault() {
    NameAbbreviator abbrev = NameAbbreviator.getDefaultAbbreviator();
    assertNotNull(abbrev);
  }

  /**
   * Check that "0" drops all name content.
   *
   */
  public void testZero() {
    NameAbbreviator abbrev = NameAbbreviator.getAbbreviator("0");
    StringBuffer buf = new StringBuffer("DEBUG - ");
    int fieldStart = buf.length();
    buf.append("org.example.foo.bar");
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - ", buf.toString());
  }

  /**
   * Check that getAbbreviator(" ") returns default abbreviator.
   *
   */
  public void testBlank() {
    NameAbbreviator abbrev = NameAbbreviator.getAbbreviator("   ");
    NameAbbreviator defaultAbbrev = NameAbbreviator.getDefaultAbbreviator();
    assertTrue(abbrev == defaultAbbrev);
  }

  /**
   * Check that getAbbreviator("1").abbreviate() drops all but the final name element.
   *
   */
  public void testOne() {
    NameAbbreviator abbrev = NameAbbreviator.getAbbreviator("1");
    StringBuffer buf = new StringBuffer("DEBUG - ");
    int fieldStart = buf.length();
    buf.append("org.example.foo.bar");
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - bar", buf.toString());

    buf.setLength(0);
    buf.append("DEBUG - ");
    fieldStart = buf.length();
    buf.append("bar");
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - bar", buf.toString());

    buf.setLength(0);
    buf.append("DEBUG - ");
    fieldStart = buf.length();
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - ", buf.toString());
  }

  /**
   * Check that blanks are trimmed in evaluating abbreviation pattern.
   */
  public void testBlankOne() {
    NameAbbreviator abbrev = NameAbbreviator.getAbbreviator(" 1 ");
    StringBuffer buf = new StringBuffer("DEBUG - ");
    int fieldStart = buf.length();
    buf.append("org.example.foo.bar");
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - bar", buf.toString());

    buf.setLength(0);
    buf.append("DEBUG - ");
    fieldStart = buf.length();
    buf.append("bar");
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - bar", buf.toString());

    buf.setLength(0);
    buf.append("DEBUG - ");
    fieldStart = buf.length();
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - ", buf.toString());
  }

  /**
   * Check that getAbbreviator("2").abbreviate drops all but the last two elements.
   *
   */
  public void testTwo() {
    NameAbbreviator abbrev = NameAbbreviator.getAbbreviator("2");
    StringBuffer buf = new StringBuffer("DEBUG - ");
    int fieldStart = buf.length();
    buf.append("org.example.foo.bar");
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - foo.bar", buf.toString());

    buf.setLength(0);
    buf.append("DEBUG - ");
    fieldStart = buf.length();
    buf.append("foo.bar");
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - foo.bar", buf.toString());

    buf.setLength(0);
    buf.append("DEBUG - ");
    fieldStart = buf.length();
    buf.append("bar");
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - bar", buf.toString());
  }

  /**
   * Check that getAbbreviator("1.").abbreviate abbreviates non-final elements
   * to one character.
   *
   */
  public void testOneDot() {
    NameAbbreviator abbrev = NameAbbreviator.getAbbreviator("1.");
    StringBuffer buf = new StringBuffer("DEBUG - ");
    int fieldStart = buf.length();
    buf.append("org.example.foo.bar");
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - o.e.f.bar", buf.toString());

    buf.setLength(0);
    buf.append("DEBUG - ");
    fieldStart = buf.length();
    buf.append("org.example.foo.");
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - o.e.f.", buf.toString());


    buf.setLength(0);
    buf.append("DEBUG - ");
    fieldStart = buf.length();
    buf.append("foo.bar");
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - f.bar", buf.toString());

    buf.setLength(0);
    buf.append("DEBUG - ");
    fieldStart = buf.length();
    buf.append("bar");
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - bar", buf.toString());

    buf.setLength(0);
    buf.append("DEBUG - ");
    fieldStart = buf.length();
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - ", buf.toString());

    buf.setLength(0);
    buf.append("DEBUG - ");
    fieldStart = buf.length();
    buf.append(".");
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - .", buf.toString());
  }

  /**
   * Check that getAbbreviator("1~.").abbreviate abbreviates non-final elements
   * to one character and a tilde.
   *
   */
  public void testOneTildeDot() {
    NameAbbreviator abbrev = NameAbbreviator.getAbbreviator("1~.");
    StringBuffer buf = new StringBuffer("DEBUG - ");
    int fieldStart = buf.length();
    buf.append("org.example.foo.bar");
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - o~.e~.f~.bar", buf.toString());

    buf.setLength(0);
    buf.append("DEBUG - ");
    fieldStart = buf.length();
    buf.append("org.example.foo.");
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - o~.e~.f~.", buf.toString());

    buf.setLength(0);
    buf.append("DEBUG - ");
    fieldStart = buf.length();
    buf.append("foo.bar");
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - f~.bar", buf.toString());

    buf.setLength(0);
    buf.append("DEBUG - ");
    fieldStart = buf.length();
    buf.append("bar");
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - bar", buf.toString());

    buf.setLength(0);
    buf.append("DEBUG - ");
    fieldStart = buf.length();
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - ", buf.toString());


    buf.setLength(0);
    buf.append("DEBUG - ");
    fieldStart = buf.length();
    buf.append(".");
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - .", buf.toString());


    buf.setLength(0);
    buf.append("DEBUG - ");
    fieldStart = buf.length();
    buf.append("o.e.f.bar");
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - o.e.f.bar", buf.toString());
  }

  /**
   * Check that getAbbreviator("1.*.2").abbreviate drops all but the first
   * character from the first element, uses all of the second element and
   * drops all but the first two characters of the rest of the non-final elements.
   *
   */
  public void testMulti() {
    NameAbbreviator abbrev = NameAbbreviator.getAbbreviator("1.*.2");
    StringBuffer buf = new StringBuffer("DEBUG - ");
    int fieldStart = buf.length();
    buf.append("org.example.foo.bar");
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - o.example.fo.bar", buf.toString());

    buf.setLength(0);
    buf.append("DEBUG - ");
    fieldStart = buf.length();
    buf.append("org.example.foo.");
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - o.example.fo.", buf.toString());

    buf.setLength(0);
    buf.append("DEBUG - ");
    fieldStart = buf.length();
    buf.append("foo.bar");
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - f.bar", buf.toString());

    buf.setLength(0);
    buf.append("DEBUG - ");
    fieldStart = buf.length();
    buf.append("bar");
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - bar", buf.toString());

    buf.setLength(0);
    buf.append("DEBUG - ");
    fieldStart = buf.length();
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - ", buf.toString());

    buf.setLength(0);
    buf.append("DEBUG - ");
    fieldStart = buf.length();
    buf.append(".");
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - .", buf.toString());
  }

  /**
   * Check that getAbbreviator("-1").abbreviate() drops first name element.
   *
   */
  public void testMinusOne() {
    NameAbbreviator abbrev = NameAbbreviator.getAbbreviator("-1");
    StringBuffer buf = new StringBuffer("DEBUG - ");
    int fieldStart = buf.length();
    buf.append("org.example.foo.bar");
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - example.foo.bar", buf.toString());

    buf.setLength(0);
    buf.append("DEBUG - ");
    fieldStart = buf.length();
    buf.append("bar");
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - bar", buf.toString());

    buf.setLength(0);
    buf.append("DEBUG - ");
    fieldStart = buf.length();
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - ", buf.toString());

    buf.setLength(0);
    buf.append("DEBUG - ");
    fieldStart = buf.length();
    buf.append(".");
    abbrev.abbreviate(fieldStart, buf);
    assertEquals("DEBUG - ", buf.toString());

  }

}
