package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveDriver extends JPanel {
    private DriverJTable driverJTable;
    private Container mainContainer;
    private JTable jTable;
    private ButtonsPanel buttonsPanel;
    private Controller controller;

    public RemoveDriver(Container mainContainer){
        this.setLayout(new BorderLayout());
        this.mainContainer = mainContainer;

        this.controller = new Controller();

        this.driverJTable = new DriverJTable();
        this.jTable = driverJTable.getjTable();

        this.buttonsPanel = new ButtonsPanel("retour au menu principal","Supprimer l'élément séléctionné");
        this.buttonsPanel.addActionListener(new ButtonListener());


        this.add(driverJTable, BorderLayout.CENTER);
        this.add(buttonsPanel,BorderLayout.SOUTH);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainContainer.removeAll();
            if(e.getSource() == buttonsPanel.getNext()){
                ListSelectionModel listSelect = jTable.getSelectionModel();
                int selectedItem = listSelect.getMinSelectionIndex();
                int driverNumber = Integer.parseInt(jTable.getValueAt(selectedItem,0).toString());
                controller.deleteDriver(driverNumber);

            }
            mainContainer.add(new WelcomeJPanel());
            mainContainer.repaint();
            mainContainer.validate();
        }
    }
}


