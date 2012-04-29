package helper;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JPanel;
import mycomponents.NewObjectDialog;
import mycomponents.ObjectPanel;

/**
 *
 * @author Kent
 */
public class MyComponentsHelper {

  /**
   * Creates a NewObjectDialog loaded with the given ObjectPanel and parented to the given parent with the
   * given title
   *
   * @param op the ObjectPanel loaded into the dialog
   * @param parent the parent of the dialog
   * @param modal the modal state of the dialog
   * @param title the title of the dialog
   * @return the created NewObjectDialog
   */
  public static NewObjectDialog createNewObjectDialog(ObjectPanel op, Window parent, boolean modal, String title) {
    throw new UnsupportedOperationException("This is not yet supported");
    //    if (parent instanceof Dialog) {
//      Dialog dialog = (Dialog) parent;
//      return new NewObjectDialog(dialog, modal, title, (JPanel) op);
//    } else if (parent instanceof Frame) {
//      Frame frame = (Frame) parent;
//      return new NewObjectDialog(frame, modal, title, (JPanel) op);
//    } else if (parent == null) {
//      return new NewObjectDialog(new JDialog(), modal, title, (JPanel) op);
//    }
//    return null;
  }

  /**
   * Creates a new instance of the object panel of the given BusinessObject name. Useful for when a user
   * presses the "new" button in an ObjectOptionsPanel
   *
   * @param className the name of the class this ObjectPanel represents (like "Customer")
   * @return
   */
  public static JPanel getObjectPanel(String className) {
    throw new UnsupportedOperationException("This is not yet supported");
//    try {
//      Class<?> klass = Class.forName(className);
//      Object newInstance = klass.newInstance();
//      if (newInstance instanceof ObjectPanel) {
//        JPanel objectPanel = (JPanel) newInstance;
//        return objectPanel;
//      }
//    } catch (InstantiationException ex) {
//      Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
//    } catch (IllegalAccessException ex) {
//      Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
//    }
//    return null;
  }
}
