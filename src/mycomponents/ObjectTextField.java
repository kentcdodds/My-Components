package mycomponents;

import javax.swing.JTextField;

/**
 *
 * @author Kent
 */
public class ObjectTextField extends JTextField {

  private Object object;

  public ObjectTextField() {
    super();
  }

  /**
   * Sets the text in this field to object.toString();
   */
  public void setDefaultText() {
    String name;
    if (object != null) {
      name = object.toString();
    } else {
      name = "(Choose new)";
    }
    setText(name);
  }

  /**
   * @return the object
   */
  public Object getObject() {
    return object;
  }

  /**
   * @param object the object to set
   */
  public void setObject(Object object) {
    this.object = object;
    setDefaultText();
  }
}
