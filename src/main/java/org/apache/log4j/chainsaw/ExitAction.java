
package org.apache.log4j.chainsaw;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.apache.log4j.Logger;

/**
 * Encapsulates the action to exit.
 *
 * @author <a href="mailto:oliver@puppycrawl.com">Oliver Burn</a>
 * @version 1.0
 */
class ExitAction
    extends AbstractAction
{
    /** use to log messages **/
    private static final Logger LOG = Logger.getLogger(ExitAction.class);
    /** The instance to share **/
    public static final ExitAction INSTANCE = new ExitAction();

    /** Stop people creating instances **/
    private ExitAction() {}

    /**
     * Will shutdown the application.
     * @param aIgnore ignored
     */
    public void actionPerformed(ActionEvent aIgnore) {
        LOG.info("shutting down");
        System.exit(0);
    }
}
