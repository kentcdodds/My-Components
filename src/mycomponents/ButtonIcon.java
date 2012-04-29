package mycomponents;

import com.kentcdodds.javahelper.helpers.SwingHelper;
import java.awt.Cursor;
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Kent
 */
public class ButtonIcon extends JLabel {

  private String imageLocation;
  private ImageIcon regularIcon;
  private ImageIcon focusedIcon;
  private ImageIcon pressedIcon;

  /**
   *
   */
  public ButtonIcon() {
    super();
  }

  /**
   *
   * @param text
   */
  public ButtonIcon(String text) {
    super(text);
  }

  /**
   *
   * @param imageLocation
   * @param regularD
   * @param focusedD
   * @param pressedD
   * @param borderTitle
   */
  public void initialize(String imageLocation, Dimension regularD, Dimension focusedD, Dimension pressedD, String borderTitle) throws IOException {
    this.setImageLocation(imageLocation);
    setRegularIcon(new ImageIcon(SwingHelper.resizeImageFromResource(ButtonIcon.class, imageLocation, regularD.width, regularD.height, true)));
    setFocusedIcon(new ImageIcon(SwingHelper.resizeImageFromResource(ButtonIcon.class, imageLocation, focusedD.width, focusedD.height, true)));
    setPressedIcon(new ImageIcon(SwingHelper.resizeImageFromResource(ButtonIcon.class, imageLocation, pressedD.width, pressedD.height, true)));
    if (borderTitle != null) {
      setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), borderTitle));
    }
    init();
  }

  /**
   *
   * @param size 
   * @param regularLocation 
   * @param focusedLocation 
   * @param pressedLocation 
   * @param borderTitle
   */
  public void initialize(String regularLocation, String focusedLocation, String pressedLocation, Dimension size, String borderTitle) throws IOException {
    this.setImageLocation(regularLocation);
    setRegularIcon(new ImageIcon(SwingHelper.resizeImageFromResource(ButtonIcon.class, regularLocation, size.width, size.height, true)));
    setFocusedIcon(new ImageIcon(SwingHelper.resizeImageFromResource(ButtonIcon.class, focusedLocation, size.width, size.height, true)));
    setPressedIcon(new ImageIcon(SwingHelper.resizeImageFromResource(ButtonIcon.class, pressedLocation, size.width, size.height, true)));
    if (borderTitle != null) {
      setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), borderTitle));
    }
    init();
  }

  /**
   * Sets the focused Dimension and the pressed Dimension to 102.5% and 107.5% greater than the regular
   * dimension (respectively)
   *
   * @param imageLocation
   * @param regularD
   * @param borderTitle
   */
  public void initialize(String imageLocation, Dimension regularD, String borderTitle) throws IOException {
    initialize(imageLocation, regularD, 1.025, 1.075, borderTitle);
  }

  /**
   * Sets the focused Dimension and the pressed Dimension to given percentages for focusPercent and
   * pressedPercent. Percent is represented as a double (so if focusPercent is 1.025 it would be is 102.5% the
   * size of the given regularDimension or 2.5% bigger than the given dimension).
   *
   * @param imageLocation
   * @param regularD
   * @param focusPercent
   * @param pressedPercent
   * @param borderTitle
   * @throws IOException  
   */
  public void initialize(String imageLocation, Dimension regularD, double focusPercent, double pressedPercent, String borderTitle) throws IOException {
    initialize(imageLocation,
            regularD,
            new Dimension((int) (regularD.width * focusPercent), (int) (regularD.height * focusPercent)),
            new Dimension((int) (regularD.width * pressedPercent), (int) (regularD.height * pressedPercent)),
            borderTitle);
  }

  /**
   * Initializes the mouse listeners and the icon
   */
  private void init() {
    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    setIcon(getRegularIcon());

    addMouseListener(new java.awt.event.MouseAdapter() {

      @Override
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        labelMouseEntered();
      }

      @Override
      public void mouseExited(java.awt.event.MouseEvent evt) {
        labelMouseExited();
      }

      @Override
      public void mousePressed(java.awt.event.MouseEvent evt) {
        labelMousePressed();
      }

      @Override
      public void mouseReleased(java.awt.event.MouseEvent evt) {
        labelMouseReleased();
      }
    });
  }

  /**
   * Sets the icon to the pressed dimension size
   */
  private void labelMousePressed() {
    setIcon(getPressedIcon());
  }

  /**
   * Sets the icon to the focused dimension size
   */
  private void labelMouseReleased() {
    setIcon(getFocusedIcon());
  }

  /**
   * Sets the icon to the focused dimension size
   */
  private void labelMouseEntered() {
    setIcon(getFocusedIcon());
  }

  /**
   * Sets the icon to the regular dimension size
   */
  private void labelMouseExited() {
    setIcon(getRegularIcon());
  }

  /**
   * Sets the title of the boarder around this ButtonIcon
   *
   * @param text
   */
  public void setTitle(String text) {
    setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), text));
  }

  /**
   * @return the imageLocation
   */
  public String getImageLocation() {
    return imageLocation;
  }

  /**
   * @param imageLocation the imageLocation to set
   */
  public void setImageLocation(String imageLocation) {
    this.imageLocation = imageLocation;
  }

  /**
   * @return the regularIcon
   */
  public ImageIcon getRegularIcon() {
    return regularIcon;
  }

  /**
   * @param regularIcon the regularIcon to set
   */
  public void setRegularIcon(ImageIcon regularIcon) {
    this.regularIcon = regularIcon;
  }

  /**
   * @return the focusedIcon
   */
  public ImageIcon getFocusedIcon() {
    return focusedIcon;
  }

  /**
   * @param focusedIcon the focusedIcon to set
   */
  public void setFocusedIcon(ImageIcon focusedIcon) {
    this.focusedIcon = focusedIcon;
  }

  /**
   * @return the pressedIcon
   */
  public ImageIcon getPressedIcon() {
    return pressedIcon;
  }

  /**
   * @param pressedIcon the pressedIcon to set
   */
  public void setPressedIcon(ImageIcon pressedIcon) {
    this.pressedIcon = pressedIcon;
  }

}