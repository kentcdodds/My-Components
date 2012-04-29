package mycomponents;

/**
 *
 * @param <T>
 * @author Kent
 */
public interface ObjectPanel {

  /**
   * Best practice says set all the values to Strings, send them to the setNullValuesBlank(String...
   * checkNull) method to purify them (avoids null pointer exceptions) and then set all the components to
   * their respective values.
   *
   * @param object
   */
  public void loadInfo(Object object);

  /**
   * This method calls loadInfo(T object) on the object currently represented by the ObjectPanel.
   */
  public void loadInfo();

  /**
   * This method calls setObjectValues(T object) on the object currently represented by the ObjectPanel.
   */
  public void setObjectValues();

  /**
   * This method is to set the values of the object given to the values in the ObjectPanel.
   *
   * @param object
   */
  public void setObjectValues(Object object);

  /**
   * This method is to check that the values entered into the gui are valid for that field
   *
   * @return
   */
  public java.util.List<String> checkValidValues();

  /**
   * Returns the object represented by the panel
   *
   * @return
   */
  public Object getObject();

  /**
   * Sets the object to the given businessObject
   *
   * @param object
   */
  public void setObject(Object object);

  /**
   * Sets the editable state of the components. Useful for viewing / editing
   *
   * @param state
   */
  public void setComponentsFocusable(boolean state);
}
