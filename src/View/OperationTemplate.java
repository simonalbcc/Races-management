//region packages & imports
package View;

import Controller.Controller;
import Utility.AddUtils;
import Utility.FinaleJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//endregion

public abstract class OperationTemplate extends JPanel {
    //region privates attributes & constructor
    private DriverJTable driverJTable;
    private Container mainContainer;
    private Controller controller;
    private ButtonsPanel buttonsPanel;
    private GridBagConstraints gc;
    private JPanel currentPanel;
    private String txtConfirmDialog;

    public OperationTemplate(Container mainContainer){
        // init layout, constraints, controller & container
        this.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1;
        this.controller = new Controller();
        this.mainContainer = mainContainer;

        // changing functionality of basic displaying drivers panel
        this.driverJTable = new DriverJTable(mainContainer);
        this.buttonsPanel = driverJTable.getButtonsPanel();
        this.buttonsPanel.getNext().setVisible(true);
        this.buttonsPanel.addActionListener(new ButtonListener());

        // add table then buttons panels
        this.add(driverJTable, gc);
        gc.gridy = 1;
        this.add(buttonsPanel, gc);
    }
    //endregion
    //region setters & getters
    public ButtonsPanel getButtonsPanel() {
        return buttonsPanel;
    }
    public Controller getController() {
        return controller;
    }
    public void setCurrentPanel(JPanel currentPanel) {
        this.currentPanel = currentPanel;
    }
    public void setTxtConfirmDialog(String txt){
        this.txtConfirmDialog = txt;
    }
    public void setNextText(String action){
        this.getButtonsPanel().getNext().setText(action+" l'élément séléctionné");
    }

    //endregion
    //region inner classe
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == buttonsPanel.getNext()){
                // get selected line
                ListSelectionModel listSelect = driverJTable.getjTable().getSelectionModel();
                int selectedItem = listSelect.getMinSelectionIndex();
                int driverNumber = Integer.parseInt(driverJTable.getjTable().getValueAt(selectedItem,0).toString());
                try {
                    operation(driverNumber);
                    int result = JOptionPane.showConfirmDialog(null, "Êtes-vous sûrs de vouloir continuer? Les données seront perdues.", "Avertissement", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                    if(result == 0) {
                        JOptionPane.showMessageDialog(null, txtConfirmDialog, "Information", JOptionPane.INFORMATION_MESSAGE);
                        currentPanel = new FinaleJPanel(mainContainer, new RemoveDriver(mainContainer));
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // back to the menu
                currentPanel = new WelcomeJPanel();
            }
            // add the panel to main container
            AddUtils.addToMainContainer(mainContainer, currentPanel);
        }
    }
    //endregion
    //region abstract methods
    public abstract void operation(int driverNumber) throws Exception;
    //endregion
}


