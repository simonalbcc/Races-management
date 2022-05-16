//region packages & imports
package Utility;

import Controller.Controller;
import View.ButtonsPanel;
import View.DriverJTable;
import View.WelcomeJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//endregion

public abstract class OperationTemplate<mainContainer> extends JPanel {
    //region privates attributes & constructor
    private DriverJTable driverJTable;
    private Container mainContainer;
    private Controller controller;
    private ButtonsPanel buttonsPanel;
    private GridBagConstraints gc;
    private JPanel currentPanel;

    public OperationTemplate(Container mainContainer) throws Exception {
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
                // check if 1 line is selected
                if(selectedItem == -1){
                    JOptionPane.showMessageDialog(null, "Veuillez séléctionner une ligne", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        currentPanel = operation(Integer.parseInt(driverJTable.getjTable().getValueAt(selectedItem,0).toString()), currentPanel, mainContainer);
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                currentPanel = new WelcomeJPanel();
            }
            // add the panel to main container
            Utils.addToMainContainer(mainContainer, currentPanel);
        }
    }
    //endregion
    //region abstract methods
    public abstract JPanel operation(int driverNumber, JPanel currentPanel,  Container mainContainer) throws Exception;
    //endregion
}


