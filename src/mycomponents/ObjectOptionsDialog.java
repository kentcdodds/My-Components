package mycomponents;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author Kent
 */
public abstract class ObjectOptionsDialog extends JDialog {

  private final JPanel panel;
  public static final int CLOSE = -1;
  public static final int CHOOSE = 0;
  public static final int NEW = 1;
  public static int SAVE = 2;
  private Object chosenBo;
  private String boType;
  private int action = CLOSE;
  private javax.swing.JPanel buttonsPanel = new javax.swing.JPanel();

  public ObjectOptionsDialog(java.awt.Frame parent, boolean modal, String title, JPanel panel, String boType) {
    super(parent, modal);
    this.panel = panel;
    this.boType = boType;
    initComponents(title, panel);
  }

  public ObjectOptionsDialog(java.awt.Dialog parent, boolean modal, String title, JPanel panel, String boType) {
    super(parent, modal);
    this.panel = panel;
    this.boType = boType;
    initComponents(title, panel);
  }

  private void initComponents(String title, JPanel panel) {
    setLayout(new GridBagLayout());
    setTitle(title);
    addUpperPanel(panel);
    setLowerPanel();
  }

  protected void initialize() {
    System.out.println("Please override me in: " + getClass().getName());
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
    getContentPane().add(panel, gridBagConstraints);
  }

  /**
   * Sets the size of this dialog based on the size of the components inside and the padding
   */
  void setTheSize(int widthPadding, int heightPadding) {
    Dimension panelSize = getPanel().getPreferredSize();
    Dimension buttonsSize = buttonsPanel.getPreferredSize();
    int width = panelSize.width;
    if (panelSize.width < buttonsSize.width) {
      width = buttonsSize.width;
    }
    width += widthPadding;
    int height = panelSize.height + buttonsSize.height + heightPadding;
    setPreferredSize(new Dimension(width, height));
    setSize(getPreferredSize());
  }

  private void setLowerPanel() {
    buttonsPanel.setLayout(new java.awt.GridBagLayout());
    java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
    getContentPane().add(buttonsPanel, gridBagConstraints);
  }

  public void addComponentWithPresets(JComponent component) {
    java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = java.awt.GridBagConstraints.RELATIVE;
    gridBagConstraints.gridy = java.awt.GridBagConstraints.RELATIVE;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
    buttonsPanel.add(component, gridBagConstraints);
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
   * @return the panel
   */
  public JPanel getPanel() {
    return panel;
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
}
