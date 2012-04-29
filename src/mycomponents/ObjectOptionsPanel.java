package mycomponents;

import com.kentcdodds.javahelper.helpers.StringHelper;
import com.kentcdodds.javahelper.helpers.SwingHelper;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Kent
 */
public class ObjectOptionsPanel extends JPanel {

  public static final int CLOSE = -1;
  public static final int CHOOSE = 0;
  public static final int NEW = 1;
  public static int SAVE = 2;
  private JPanel panel;
  private Object chosenBo;
  private String boType;
  private int action = CLOSE;
  private javax.swing.JPanel componentsPanel = new javax.swing.JPanel();

  public ObjectOptionsPanel(JPanel panel, String boType) {
    this.boType = boType;
    this.panel = panel;
    initComponents(panel);
  }

  private void initComponents(JPanel panel) {
    setLayout(new GridBagLayout());
    addUpperPanel(panel);
    setLowerPanel();
  }

  /**
   * Adds the ObjectChooserPanel to be in 0,0 with insets of 5 anchored to the WEST
   *
   * @param panel
   */
  private void addUpperPanel(javax.swing.JPanel panel) {
    java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    add(panel, gridBagConstraints);
  }

  /**
   * Sets the size of this dialog based on the size of the components inside and the padding
   */
  void setTheSize(int widthPadding, int heightPadding) {
    Dimension panelSize = panel.getPreferredSize();
    Dimension buttonsSize = componentsPanel.getPreferredSize();
    int width = panelSize.width;
    if (panelSize.width < buttonsSize.width) {
      width = buttonsSize.width;
    }
    width += widthPadding;
    int height = panelSize.height + buttonsSize.height + heightPadding;
    setPreferredSize(new Dimension(width, height));
    setSize(getPreferredSize());
  }

  /**
   * Sets the lower panel with the standard buttonsPanel
   */
  private void setLowerPanel() {
    componentsPanel.setLayout(new java.awt.GridBagLayout());
    java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
    add(componentsPanel, gridBagConstraints);
  }

  /**
   * Adds a component to the componentsPanel
   *
   * @param component
   */
  public void addComponentWithPresets(JComponent component) {
    java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = java.awt.GridBagConstraints.RELATIVE;
    gridBagConstraints.gridy = java.awt.GridBagConstraints.RELATIVE;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
    componentsPanel.add(component, gridBagConstraints);
  }

  /**
   * Gets the object from the object panel or the object chooser panel depending on what's in the dialog
   *
   * @return
   */
  public Object getObject() {
    if (panel instanceof ObjectPanel) {
      ObjectPanel objectPanel = (ObjectPanel) panel;
      return objectPanel.getObject();
    } else if (panel instanceof ObjectChooserPanel) {
      ObjectChooserPanel objectChooserPanel = (ObjectChooserPanel) panel;
      return objectChooserPanel.getObjectPanel().getObject();
    }
    return null;
  }

  protected boolean setObjectInfo() {
    ObjectPanel op = null;
    if (panel instanceof ObjectPanel) {
      op = (ObjectPanel) panel;
    } else if (panel instanceof ObjectChooserPanel) {
      op = ((ObjectChooserPanel) panel).getObjectPanel();
    }
    if (op == null) {
      return false;
    }
    if (!checkFields(op)) {
      return false;
    }
    op.setObjectValues();
    setAction(SAVE);
    return true;
  }

  /**
   * Checks the fields of the object panel
   *
   * @param op
   * @return
   */
  public boolean checkFields(ObjectPanel op) {
    java.util.List<String> invalidValues = op.checkValidValues();
    if (invalidValues.size() > 0) {
      String values = StringHelper.splitBy(", ", invalidValues.toArray(new String[invalidValues.size()]));
      String message = SwingHelper.wordWrappedMessage("Please fill in the following field"
              + ((invalidValues.size() > 1) ? "s:" + StringHelper.newline : ": ") + values);
      SwingHelper.showErrorMessage(this, message);
      return false;
    }
    return true;
  }

  /**
   * @return the chosenBo
   */
  public Object getChosenBo() {
    return chosenBo;
  }

  /**
   * @param chosenBo the chosenBo to set
   */
  public void setChosenBo(Object chosenBo) {
    this.chosenBo = chosenBo;
  }

  /**
   * @return the action
   */
  public int getAction() {
    return action;
  }

  /**
   * @param action the action to set
   */
  public void setAction(int action) {
    this.action = action;
  }

  /**
   * @return the boType
   */
  public String getBoType() {
    return boType;
  }

  /**
   * @param boType the boType to set
   */
  public void setBoType(String boType) {
    this.boType = boType;
  }

  /**
   * @return the panel
   */
  public JPanel getPanel() {
    return panel;
  }

  /**
   * @param panel the panel to set
   */
  public void setPanel(JPanel panel) {
    this.panel = panel;
  }
}
