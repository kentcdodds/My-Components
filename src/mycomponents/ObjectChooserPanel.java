package mycomponents;

import com.kentcdodds.javahelper.helpers.ReflectionHelper;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;

/**
 *
 * @author Kent
 */
public class ObjectChooserPanel extends javax.swing.JPanel {

  private List list;
  private DefaultListModel listModel = new DefaultListModel();
  private JPanel op;
  private ObjectPanel objectPanel = null;
  private int panelCusion = 20;

  /**
   * Creates new form ObjectChooserPanel. This is kind of a round-about way to do it but so we can add the
   * ObjectPanel to the contentPane, we have to have it given as a JPanel. We can't up-cast it from
   * ObjectPanel to JPanel, but we can down-cast it from JPanel to ObjectPanel
   *
   * @param defaultSearchText
   * @param op
   * @param list
   */
  public ObjectChooserPanel(String defaultSearchText, JPanel op, List list) {
    super();
    this.op = op;
    if (op instanceof ObjectPanel) {
      objectPanel = (ObjectPanel) op;
    }
    this.list = list;
    initComponents();
    updateList(list);
    searchField.initiate(defaultSearchText);
    searchField.getDocument().addDocumentListener(new MySearchDocumentListener(this));
    scrollPane.setSize(scrollPane.getPreferredSize());
    Dimension opSize = op.getPreferredSize();
    int scrollPaneWidth = scrollPane.getSize().width;
    int minHeight = scrollPane.getSize().height + searchField.getSize().height + 30;

    int width = opSize.width + scrollPaneWidth + panelCusion;
    int height = opSize.height;
    if (minHeight > height) {
      height = minHeight;
    }
    setPreferredSize(new Dimension(width, height));
    myInitComponents();
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this
   * code. The content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        innerPanel = new javax.swing.JPanel();
        searchField = new mycomponents.SearchTextField();
        scrollPane = new javax.swing.JScrollPane();
        jList = new javax.swing.JList();

        setMinimumSize(new java.awt.Dimension(500, 300));
        setPreferredSize(new java.awt.Dimension(550, 500));
        setLayout(new java.awt.GridBagLayout());

        innerPanel.setLayout(new java.awt.GridBagLayout());

        searchField.setForeground(new java.awt.Color(204, 204, 204));
        searchField.setText("Search");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        innerPanel.add(searchField, gridBagConstraints);

        scrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Objects"));
        scrollPane.setMaximumSize(new java.awt.Dimension(200, 200));
        scrollPane.setMinimumSize(new java.awt.Dimension(200, 200));
        scrollPane.setPreferredSize(new java.awt.Dimension(200, 200));

        jList.setModel(listModel);
        jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListValueChanged(evt);
            }
        });
        scrollPane.setViewportView(jList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        innerPanel.add(scrollPane, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(innerPanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

  private void jListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListValueChanged
    Object selected = getSelectedBO();
    if (selected != null) {
      objectPanel.setObject(selected);
    }
  }//GEN-LAST:event_jListValueChanged

  /**
   * Initiates the components I want to initiate. Adds the ObjectPanel to the panel.
   */
  private void myInitComponents() {
    java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
    gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
    add(op, gridBagConstraints);
  }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel innerPanel;
    private javax.swing.JList jList;
    private javax.swing.JScrollPane scrollPane;
    private mycomponents.SearchTextField searchField;
    // End of variables declaration//GEN-END:variables

  /**
   * Updates the customer list
   *
   * @param list
   */
  private void updateList(List<Object> list) {
    listModel.clear();
    for (Object t : list) {
      listModel.addElement(t);
    }
  }

  public void updateMatchingList() {
    String matchText = searchField.getText().replace(searchField.getDefaultSearchText(), "");
    updateMatchingList(matchText, list, (DefaultListModel) listModel);
  }

  /**
   * Updates the product list with the match in the searchproductTextField
   *
   * @param text
   * @param list
   * @param model
   */
  private void updateMatchingList(String text, List<Object> list, DefaultListModel model) {
    try {
      updateList(getMatching(text, list));
    } catch (Exception ex) {
      Logger.getLogger(ObjectChooserPanel.class.getName()).log(Level.SEVERE, null, ex);
//      String listOf = "";
//      if (!list.isEmpty()) {
//        listOf = list.get(0).getReadableClassName();
//      }
//      JOptionPane.showMessageDialog(this, "There's been an error updating the " + listOf + "!", "Error!", JOptionPane.ERROR_MESSAGE);
    }
  }

  public List<Object> getMatching(String match, List<Object> list) throws IllegalArgumentException, IllegalAccessException {
    List<Object> matchList = new ArrayList<Object>();
    for (Object t : list) {
      if (ReflectionHelper.matches(t, t.getClass(), 10, match, null)) {
        matchList.add(t);
      }
    }
    return matchList;
  }

  /**
   * @return the jList
   */
  public javax.swing.JList getjList() {
    return jList;
  }

  /**
   * @param jList the jList to set
   */
  public void setjList(javax.swing.JList jList) {
    this.jList = jList;
  }

  public Object getSelectedBO() {
    return (Object) jList.getSelectedValue();
  }

  /**
   * @return the searchField
   */
  public SearchTextField getSearchField() {
    if (searchField instanceof SearchTextField) {
      SearchTextField searchTextField = (SearchTextField) searchField;
      return searchTextField;
    }
    return null;
  }

  /**
   * @param searchField the searchField to set
   */
  public void setSearchField(SearchTextField searchField) {
    this.searchField = searchField;
  }

  /**
   *
   * @param title
   */
  public void setListTitle(String title) {
    scrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), title));
  }

  /**
   *
   * @return scrollPane
   */
  public javax.swing.JScrollPane getScrollPane() {
    return scrollPane;
  }

  /**
   * @return the list
   */
  public List getList() {
    return list;
  }

  /**
   * @param list the list to set
   */
  public void setList(List list) {
    this.list = list;
  }

  /**
   * @return the objectPanel
   */
  public ObjectPanel getObjectPanel() {
    return objectPanel;
  }

  /**
   * @param objectPanel the objectPanel to set
   */
  public void setObjectPanel(ObjectPanel objectPanel) {
    this.objectPanel = objectPanel;
  }
}
