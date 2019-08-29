
package org.apache.log4j.lf5;


/**
 * An interface for classes which filters LogRecords.  Implementations
 * represent a rule or condition which LogRecords may pass or fail.
 * @see LogRecord
 *
 * @author Richard Wan
 */

// Contributed by ThoughtWorks Inc.

public interface LogRecordFilter {
  //--------------------------------------------------------------------------
  //   Constants:
  //--------------------------------------------------------------------------

  //--------------------------------------------------------------------------
  //   Public Methods:
  //--------------------------------------------------------------------------

  /**
   * @return true if the specified LogRecord satisfies whatever condition
   * implementing class tests for.
   */
  public boolean passes(LogRecord record);

}

