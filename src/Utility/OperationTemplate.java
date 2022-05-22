//region packages & imports
package Utility;

import Controller.Controller;
import Model.Driver;
import View.AddDriver;
import View.DriverJTable;
import View.WelcomeJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//endregion

public abstract class OperationTemplate extends JPanel {

    private DriverJTable driverJTable;
    private Container mainContainer;
    private Controller controller;
    private ButtonsJPanel buttonsPanel;
    private GridBagConstraints gc;
    private JPanel currentPanel;
    private JLabel title;
    private boolean fillHorizontal;

    public OperationTemplate(Container mainContainer, String title, boolean fillHorizontal) throws Exception {

        // init layout with constraints, controller, container and title
        this.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.NONE;
        gc.weightx = 1;

        this.controller = new Controller();
        this.mainContainer = mainContainer;
        this.title = new JLabel();
        setTitleText(title);

        // changing functionality of basic displaying drivers panel -> designed for table display
        this.driverJTable = new DriverJTable(mainContainer);
        this.buttonsPanel = driverJTable.getButtonsPanel();
        this.buttonsPanel.getNext().setVisible(true);
        this.buttonsPanel.addActionListener(new ButtonListener());

        // add title, panel then buttons panels
        changePanels(driverJTable, buttonsPanel);

    }


    //region setters & getters
    public ButtonsJPanel getButtonsPanel() {
        return buttonsPanel;
    }
    public Controller getController() {
        return controller;
    }
    public Container getMainContainer(){return mainContainer;}
    public JTable getjTable(){return driverJTable.getjTable();}
    public void setFillHorizontal(boolean fillHorizontal){
        this.fillHorizontal = fillHorizontal;
    }
    public void setNextText(String action){
        this.getButtonsPanel().getNext().setText(action+" l'élément séléctionné");
    }
    public void setTitleText(String title) {
        this.title.setText("<html> <h2 style = 'font-family: Roboto, sans-serif;'> <u> "+title+" : </u> </h2> </html>");
    }
    //endregion

    //region inner classe
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == buttonsPanel.getNext()){
                // get selected line
                ListSelectionModel listSelect = driverJTable.getjTable().getSelectionModel();
                int[] listSelectedDrivers = listSelect.getSelectedIndices();
                int lengthListSelectedDrivers = listSelectedDrivers.length ;

                // check if 1 line is selected
                if(lengthListSelectedDrivers == 0){
                    Utils.showErrorMessage("Veuillez séléctionner une ligne");
                } else {
                    try {
                        int indSelected = 0;
                        while(indSelected < lengthListSelectedDrivers) {
                            currentPanel = operation(Integer.parseInt(driverJTable.getjTable().getValueAt(listSelectedDrivers[indSelected], 0).toString()), currentPanel, mainContainer);
                            indSelected++;
                        }
                    } catch (Exception exception) {
                        Utils.showErrorMessage(exception.getMessage());
                    }
                }
            } else {
                currentPanel = new WelcomeJPanel();
            }
            // add the current panel to main container
            Utils.addToMainContainer(mainContainer, currentPanel);
        }
    }
    //endregion

    //region abstract methods
    public abstract JPanel operation(int driverNumber, JPanel currentPanel,  Container mainContainer) throws Exception;
    //endregion

    //region other methods
    public void changePanels(JPanel panel, ButtonsJPanel buttonsPanel){
        this.removeAll();
        this.buttonsPanel = buttonsPanel;
        if(this.fillHorizontal){
            gc.fill = GridBagConstraints.HORIZONTAL;
        }
        gc.gridy = 0;
        this.add(this.title, gc);
        gc.gridy = 1;
        this.add(panel, gc);
        gc.gridy = 2;
        this.add(buttonsPanel, gc);
    }
    //endregion

}


