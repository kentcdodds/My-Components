package mycomponents;

import com.kentcdodds.javahelper.helpers.StringHelper;
import com.kentcdodds.javahelper.helpers.SwingHelper;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Kent
 */
public class NewObjectDialog extends ObjectOptionsDialog {

  private JButton saveButton = new JButton("Save");
  private JButton cancelButton = new JButton("Cancel");
  /**
   * You can set this to false if you want to save the object later
   */
  private boolean saveOnSave = true;

  public NewObjectDialog(java.awt.Frame parent, boolean modal, String title, JPanel panel, String boType) {
    super(parent, modal, title, panel, boType);
    initialize();
  }

  public NewObjectDialog(java.awt.Dialog parent, boolean modal, String title, JPanel panel, String boType) {
    super(parent, modal, title, panel, boType);
    initialize();
  }

  @Override
  protected void initialize() {
    addComponentWithPresets(saveButton);
    addComponentWithPresets(cancelButton);

    cancelButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cancelButtonActionPerformed(evt);
      }
    });

    saveButton.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(java.awt.event.ActionEvent evt) {
        saveButtonActionPerformed(evt);
      }
    });

    setTheSize(40, 60);
    SwingHelper.centerAndPack(this);
  }

  private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
    setAction(CLOSE);
    this.dispose();
  }

  private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {
    JPanel panel = getPanel();
    ObjectPanel op = null;
    if (panel instanceof ObjectPanel) {
      op = (ObjectPanel) panel;
    } else if (panel instanceof ObjectChooserPanel) {
      op = ((ObjectChooserPanel) panel).getObjectPanel();
    }
    if (op == null) {
      return;
    }
    if (!checkFields(op)) {
      return;
    }
    op.setObjectValues();
    setAction(SAVE);
    this.dispose();
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
   * @return the saveOnSave
   */
  public boolean isSaveOnSave() {
    return saveOnSave;
  }

  /**
   * Setting this to false prevents the dialog from saving the object when the save button is pressed
   *
   * @param saveOnSave the saveOnSave to set
   */
  public void setSaveOnSave(boolean saveOnSave) {
    this.saveOnSave = saveOnSave;
  }
}
