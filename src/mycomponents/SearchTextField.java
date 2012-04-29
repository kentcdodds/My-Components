package mycomponents;

import java.awt.Color;
import javax.swing.JTextField;

/**
 *
 * @author Kent
 */
public class SearchTextField extends JTextField {

  private String defaultSearchText;

  /**
   * Constructor
   *
   */
  public SearchTextField() {
    super();
  }

  public void initiate(String defaultSearchText) {
    this.setDefaultSearchText(defaultSearchText);

    setText(defaultSearchText);

    setTextDefault(null);

    addMouseListener(new java.awt.event.MouseAdapter() {

      @Override
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        searchTextFieldMouseClicked(evt);
      }
    });
    addFocusListener(new java.awt.event.FocusAdapter() {

      @Override
      public void focusGained(java.awt.event.FocusEvent evt) {
        searchTextFieldFocusGained(evt);
      }

      @Override
      public void focusLost(java.awt.event.FocusEvent evt) {
        searchTextFieldFocusLost(evt);
      }
    });
    addKeyListener(new java.awt.event.KeyAdapter() {

      @Override
      public void keyTyped(java.awt.event.KeyEvent evt) {
        searchTextFieldKeyTyped(evt);
      }
    });
  }

  private void searchTextFieldFocusGained(java.awt.event.FocusEvent evt) {
    setTextDefault(null);
  }

  private void searchTextFieldFocusLost(java.awt.event.FocusEvent evt) {
    if (getText().equals("")) {
      setTextDefault(null);
    }
  }

  private void searchTextFieldMouseClicked(java.awt.event.MouseEvent evt) {
    if (getText().equals(getDefaultSearchText())) {
      setCaretPosition(0);
    }
  }

  private void searchTextFieldKeyTyped(java.awt.event.KeyEvent evt) {
    setTextDefault(evt.getKeyChar());
  }

  /**
   * Sets the list to be default text if all text is removed, and updates the list.
   *
   * @param <T>
   * @param keyPressed
   */
  private <T extends Object> void setTextDefault(Character keyPressed) {
    String setText = getText().replace(getDefaultSearchText(), "");
    if (setText.isEmpty() && !isCharValid(keyPressed)) {
      setText(getDefaultSearchText());
      setForeground(Color.lightGray);
      if (hasFocus()) {
        setCaretPosition(0);
      }
    } else {
      String selectedText = getSelectedText();
      if (selectedText == null) {
        selectedText = "";
      }
      setText(setText.replace(selectedText, ""));
      setForeground(Color.black);
    }
  }

  /**
   * Checks whether the entered key is a valid character (helps to update the list properly with the
   * information entered)
   *
   * @param c
   * @return
   */
  private boolean isCharValid(Character c) {
    if (c != null) {
      if (Character.isLetterOrDigit(c)
              || Character.isSpaceChar(c)) {
        return true;
      }
    }
    return false;
  }

  /**
   * @return the defaultSearchText
   */
  public String getDefaultSearchText() {
    return defaultSearchText;
  }

  /**
   * @param defaultSearchText the defaultSearchText to set
   */
  public void setDefaultSearchText(String defaultSearchText) {
    this.defaultSearchText = defaultSearchText;
  }
}
