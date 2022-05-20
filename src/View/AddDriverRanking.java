package View;


import Controller.Controller;
import Model.*;
import Utility.JTableUtils;
import Utility.RankingModel;
import Utility.Utils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddDriverRanking extends JPanel {
    private Controller controller;
    private ButtonsPanel buttonsPanel;
    private GridBagConstraints gc;
    private Container mainContainer;
    private JComboBox circuitsCombobox, datesCombobox, driversComboBox, teamsComboBox, carsComboBox, positionCombobox;
    private DefaultComboBoxModel carsModel, datesModel, positionsModel;
    private RankingModel tableModel;
    private JTable jTable;
    private ArrayList <Team>teamsNameList;
    private ArrayList <String>circuitsNameList, carsNameList;

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

            teamsNameList = controller.getAllTeams();
            teamsNameList.add(0, new Team("Séléctionner..."));
            teamsComboBox = new JComboBox(teamsNameList.stream().map(t -> t.getName()).toArray());
            teamsComboBox.addItemListener(new ComboBoxItemListener());

            carsNameList = controller.getAllCarsName(teamsComboBox.getItemAt(1).toString());
            carsModel = new DefaultComboBoxModel(carsNameList.toArray());
            carsComboBox = new JComboBox(carsModel);
            carsComboBox.setVisible(false);
            carsComboBox.addItemListener(new ComboBoxItemListener());

            circuitsNameList = controller.getAllCircuitsNames();
            circuitsNameList.add(0, "Sélectionner...");
            circuitsCombobox = new JComboBox(circuitsNameList.toArray());
            circuitsCombobox.addItemListener(new ComboBoxItemListener());

            datesModel = new DefaultComboBoxModel(controller.getRaceDatesOfACircuit(circuitsCombobox.getItemAt(1).toString()).toArray());
            datesCombobox = new JComboBox(datesModel);
            datesCombobox.addItemListener(new ComboBoxItemListener());
            datesCombobox.setVisible(false);


            positionCombobox = new JComboBox();
            //zhema la longueur
            this.add(positionCombobox);
            this.add(driversComboBox);
            this.add(teamsComboBox);
            this.add(carsComboBox);
            this.add(circuitsCombobox);
            this.add(datesCombobox);
        }
    }
    public void updateJTable(){
        try {
            tableModel = new RankingModel(controller.getARaceRanking(circuitsCombobox.getSelectedItem().toString(), datesCombobox.getSelectedItem().toString()));
            jTable.setModel(tableModel);
            String date = datesCombobox.getSelectedItem().toString();
            positionsModel = new DefaultComboBoxModel<>(controller.getPositionsRemainingInARanking(circuitsCombobox.getSelectedItem().toString(),date).toArray());
            positionCombobox.setModel(positionsModel);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
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
                        int carSerialNumber = controller.getCarFromName(carsComboBox.getSelectedItem().toString());
                        int raceSerialNumber = controller.getARaceNumber(circuitsCombobox.getSelectedItem().toString(), datesCombobox.getSelectedItem().toString());
                        int driverNumber = controller.getADriver(driversComboBox.getSelectedItem().toString()).getNumber();
                        controller.addDriverToRanking(new Ranking(carSerialNumber,raceSerialNumber,Integer.parseInt(positionCombobox.getSelectedItem().toString()),driverNumber));
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
            if(e.getSource() == carsComboBox && carsComboBox.getSelectedItem() == "Ajouter une voiture..."){
                Utils.addToMainContainer(mainContainer, new FormCar(mainContainer, teamsComboBox.getSelectedItem().toString(), controller));
            }
            if(e.getSource() == teamsComboBox){
                if(teamsComboBox.getSelectedIndex() > 0){
                    try {
                        carsNameList = controller.getAllCarsName(teamsComboBox.getSelectedItem().toString());
                        carsNameList.add(carsNameList.size(), "Ajouter une voiture...");
                        carsModel = new DefaultComboBoxModel(carsNameList.toArray());
                        carsComboBox.setModel(carsModel);
                        carsComboBox.setVisible(true);
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    carsComboBox.setVisible(false);
                }
            }
            if(e.getSource() == circuitsCombobox){
                if(circuitsCombobox.getSelectedIndex() > 0){
                    try {
                        datesModel = new DefaultComboBoxModel(controller.getRaceDatesOfACircuit(circuitsCombobox.getSelectedItem().toString()).toArray());
                        datesCombobox.setModel(datesModel);
                        datesCombobox.setVisible(true);
                        updateJTable();
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    datesCombobox.setVisible(false);
                }
            } else if(e.getSource() == datesCombobox){
                updateJTable();
            }
        }
    }


}


