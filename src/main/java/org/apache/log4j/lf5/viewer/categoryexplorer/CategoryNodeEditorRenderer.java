
package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTree;

/**
 * CategoryNodeEditorRenderer
 *
 * @author Michael J. Sikorsky
 * @author Robert Shaw
 */

// Contributed by ThoughtWorks Inc.

public class CategoryNodeEditorRenderer extends CategoryNodeRenderer {
  private static final long serialVersionUID = -6094804684259929574L;

  //--------------------------------------------------------------------------
  //   Constants:
  //--------------------------------------------------------------------------

  //--------------------------------------------------------------------------
  //   Protected Variables:
  //--------------------------------------------------------------------------

  //--------------------------------------------------------------------------
  //   Private Variables:
  //--------------------------------------------------------------------------

  //--------------------------------------------------------------------------
  //   Constructors:
  //--------------------------------------------------------------------------

  //--------------------------------------------------------------------------
  //   Public Methods:
  //--------------------------------------------------------------------------

  public Component getTreeCellRendererComponent(
      JTree tree, Object value,
      boolean selected, boolean expanded,
      boolean leaf, int row,
      boolean hasFocus) {
    Component c = super.getTreeCellRendererComponent(tree,
        value, selected, expanded,
        leaf, row, hasFocus);

    return c;
  }

  public JCheckBox getCheckBox() {
    return _checkBox;
  }

  //--------------------------------------------------------------------------
  //   Protected Methods:
  //--------------------------------------------------------------------------

  //--------------------------------------------------------------------------
  //   Private Methods:
  //--------------------------------------------------------------------------

  //--------------------------------------------------------------------------
  //   Nested Top-Level Classes or Interfaces:
  //--------------------------------------------------------------------------

}
