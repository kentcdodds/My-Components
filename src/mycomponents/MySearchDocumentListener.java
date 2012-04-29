package mycomponents;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Kent
 */
public class MySearchDocumentListener implements DocumentListener {

  private final Object owner;

  /**
   *
   * @param owner
   */
  public MySearchDocumentListener(Object owner) {
    this.owner = owner;
  }

  @Override
  public void insertUpdate(DocumentEvent e) {
    invokeMethod();
  }

  @Override
  public void removeUpdate(DocumentEvent e) {
    invokeMethod();
  }

  @Override
  public void changedUpdate(DocumentEvent e) {
    invokeMethod();
  }

  private void invokeMethod() {
    if (owner instanceof ObjectChooserPanel) {
      ObjectChooserPanel objectChooserPanel = (ObjectChooserPanel) owner;
      objectChooserPanel.updateMatchingList();
    }
  }
}