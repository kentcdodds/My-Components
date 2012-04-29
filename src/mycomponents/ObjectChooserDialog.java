package mycomponents;

import com.kentcdodds.javahelper.helpers.SwingHelper;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Kent
 */
public class ObjectChooserDialog extends ObjectOptionsDialog {

  private JButton closeButton = new JButton("Close");
  private JButton newButton = new JButton("New");
  private JButton chooseButton = new JButton("Choose");

  public ObjectChooserDialog(java.awt.Frame parent, boolean modal, String title, JPanel panel, String boType) {
    super(parent, modal, title, panel, boType);
    initialize();
  }

  public ObjectChooserDialog(java.awt.Dialog parent, boolean modal, String title, JPanel panel, String boType) {
    super(parent, modal, title, panel, boType);
    initialize();
  }

  @Override
  protected void initialize() {
    addComponentWithPresets(closeButton);
    addComponentWithPresets(newButton);
    addComponentWithPresets(chooseButton);
    closeButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(java.awt.event.ActionEvent evt) {
        closeButtonActionPerformed(evt);
      }
    });

    chooseButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(java.awt.event.ActionEvent evt) {
        chooseButtonActionPerformed(evt);
      }
    });

    newButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(java.awt.event.ActionEvent evt) {
        newButtonActionPerformed(evt);
      }
    });
    setTheSize(40, 60);
    SwingHelper.centerAndPack(this);
  }

  private void closeButtonActionPerformed(ActionEvent evt) {
    setAction(CLOSE);
    this.dispose();
  }

  private void chooseButtonActionPerformed(ActionEvent evt) {
    if (getPanel() instanceof ObjectChooserPanel) {
      ObjectChooserPanel objectChooserPanel = (ObjectChooserPanel) getPanel();
      Object selectedBO = objectChooserPanel.getSelectedBO();
      if (selectedBO == null) {
        JOptionPane.showMessageDialog(this, "Please select an item in the the list",
                "No item selected", JOptionPane.ERROR_MESSAGE);
        return;
      }
      setChosenBo(selectedBO);
      setAction(CHOOSE);
    }
    this.dispose();
  }

  private void newButtonActionPerformed(ActionEvent evt) {
    throw new UnsupportedOperationException("This is not yet supported");
//    setAction(NEW);
//    String boType = getBoType();
//    JPanel objectPanel = MyComponentsHelper.getObjectPanel(boType);
//    NewObjectDialog nod = new NewObjectDialog(this, true, "New " + boType, objectPanel, boType);
//    nod.setVisible(true);
//    if (nod.getAction() == ObjectOptionsDialog.SAVE) {
//      JPanel panel = getPanel();
//      if (panel instanceof ObjectChooserPanel) {
//        ObjectChooserPanel objectChooserPanel = (ObjectChooserPanel) panel;
//        objectChooserPanel.getList().add(nod.getObject());
//      }
//    }
  }
}
