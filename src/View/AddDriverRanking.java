package View;


import Controller.Controller;
import Model.Circuit;
import Model.Driver;
import Utility.FinaleJPanel;
import Utility.JTableUtils;
import Utility.RankingModel;
import Utility.Utils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class AddDriverRanking extends JPanel {
    private Controller controller;
    private ButtonsPanel buttonsPanel;
    private GridBagConstraints gc;
    private Container mainContainer;
    private JComboBox circuitsCombobox, datesCombobox, driversComboBox, teamsComboBox, carsComboBox, positionCombobox;
    private DefaultComboBoxModel carsModel, datesModel, positionsModel;
    private RankingModel tableModel;
    private JTable jTable;

    public AddDriverRanking(Container mainContainer) throws Exception {

        this.mainContainer = mainContainer;
        buttonsPanel = new ButtonsPanel("Précédent", "Ajouter");
        buttonsPanel.addActionListener(new ButtonListener());

        controller = new Controller();

        this.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        this.gc.insets = new Insets(10,15,10,15);


        gc.gridy = 0;
        this.add(new JLabel("Sélectionner le pilote à ajouter et dans quelle course"));
        gc.gridy = 1;
        this.add(new AddPanel(), gc);
        gc.gridy = 2;
        this.add(new RankingTable(), gc);
        gc.gridy = 3;
        this.add(buttonsPanel, gc);
        Utils.addToMainContainer(mainContainer, AddDriverRanking.this);

    }

    private class AddPanel extends JPanel{
        public AddPanel () throws Exception {
            driversComboBox = new JComboBox(controller.getAllDrivers().stream().map(d -> d.getLastNameFirstName()).toArray());
            teamsComboBox = new JComboBox(controller.getAllTeams().stream().map(t -> t.getName()).toArray());
            teamsComboBox.addItemListener(new ComboBoxItemListener());
            carsModel = new DefaultComboBoxModel(controller.getAllCarsName(teamsComboBox.getSelectedItem().toString()).toArray());
            carsComboBox = new JComboBox(carsModel);

            circuitsCombobox = new JComboBox(controller.getAllCircuitsNames().toArray());
            circuitsCombobox.addItemListener(new ComboBoxItemListener());
            datesModel = new DefaultComboBoxModel(controller.getRaceDatesOfACircuit(circuitsCombobox.getSelectedItem().toString()).toArray());
            datesCombobox = new JComboBox(datesModel);
            datesCombobox.addItemListener(new ComboBoxItemListener());

            positionsModel = new DefaultComboBoxModel<>(controller.getPositionsRemainingInARanking(controller.getARaceNumber(circuitsCombobox.getSelectedItem().toString(),controller.getRaceDatesOfACircuit(circuitsCombobox.getSelectedItem().toString()).get(datesCombobox.getSelectedIndex()))).toArray());
            positionCombobox = new JComboBox(positionsModel);
            //zhema la longueur
            this.add(positionCombobox);
            this.add(driversComboBox);
            this.add(teamsComboBox);
            this.add(carsComboBox);
            this.add(circuitsCombobox);
            this.add(datesCombobox);
        }
    }
    private class RankingTable extends JPanel{
        private JLabel title;
        private GridBagConstraints gcTable;
        public RankingTable () throws Exception {

            this.setLayout(new GridBagLayout());
            gcTable = new GridBagConstraints();


            title = new JLabel("Classement");
            title.setFont(new Font("Arial",Font.TRUETYPE_FONT,15));

            tableModel = new RankingModel(controller.getARaceRanking(circuitsCombobox.getSelectedItem().toString(), datesCombobox.getSelectedItem().toString()));
            jTable = new JTable(tableModel);

            JScrollPane sp = new JTableUtils().centerTableData(jTable);

            this.add(title,gcTable);
            gcTable.gridy = 1;
            this.add(sp, gcTable);
        }
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == buttonsPanel.getBack()){
                Utils.addToMainContainer(mainContainer, new WelcomeJPanel());
            }

            if(e.getSource() == buttonsPanel.getNext()){
                try {
                    try {
                        controller.addDriverToRanking(controller.getADriver(driversComboBox.getSelectedItem().toString()));
                        tableModel = new RankingModel(controller.getARaceRanking(circuitsCombobox.getSelectedItem().toString(), datesCombobox.getSelectedItem().toString()));
                        jTable.setModel(tableModel);
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    private class ComboBoxItemListener implements java.awt.event.ItemListener{
        @Override
        public void itemStateChanged(ItemEvent e) {
            if(e.getSource() == teamsComboBox){
                try {
                    carsModel = new DefaultComboBoxModel(controller.getAllCarsName(teamsComboBox.getSelectedItem().toString()).toArray());
                    carsComboBox.setModel(carsModel);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
            if(e.getSource() == circuitsCombobox){
                try {
                    datesModel = new DefaultComboBoxModel(controller.getRaceDatesOfACircuit(circuitsCombobox.getSelectedItem().toString()).toArray());
                    datesCombobox.setModel(datesModel);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
            try {
                tableModel = new RankingModel(controller.getARaceRanking(circuitsCombobox.getSelectedItem().toString(), datesCombobox.getSelectedItem().toString()));
                positionsModel = new DefaultComboBoxModel<>(controller.getPositionsRemainingInARanking(controller.getARaceNumber(circuitsCombobox.getSelectedItem().toString(),controller.getRaceDatesOfACircuit(circuitsCombobox.getSelectedItem().toString()).get(datesCombobox.getSelectedIndex()))).toArray());
                positionCombobox.setModel(positionsModel);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }

            jTable.setModel(tableModel);
        }
    }
}

// à optimiser
