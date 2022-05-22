//region packages & imports
package View;
import Controller.Controller;
import Model.*;
import View.Utility.ButtonsJPanel;
import View.Utility.RankingModel;
import View.Utility.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
//endregion

public class AddDriverRanking extends JPanel {
    private Controller controller;
    private ButtonsJPanel buttonsPanel;
    private GridBagConstraints gc;
    private Container mainContainer;
    private JComboBox circuitsCombobox, datesCombobox, driversComboBox, teamsComboBox, carsComboBox, positionCombobox;
    private DefaultComboBoxModel carsModel, datesModel, positionsModel;
    private RankingModel tableModel;
    private JTable jTable;
    private ArrayList <Team>teamsNameList;
    private ArrayList <String>circuitsNameList, carsNameList;
    private JSpinner record, nbRounds;
    private JPanel circuitDatePanel, teamCarPanel;
    private String currentCircuit, currentDate;

    public AddDriverRanking(Container mainContainer) throws Exception {
        // main container, buttons panel, controller & layout init
        this.mainContainer = mainContainer;
        buttonsPanel = new ButtonsJPanel("Précédent", "Ajouter");
        buttonsPanel.addActionListener(new ButtonListener());

        controller = new Controller();

        this.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        this.gc.insets = new Insets(10,15,10,15);

        // add to the main panel
        gc.gridy = 0;
        this.add(new JLabel("<html> <h3 style = 'font-family: Roboto, sans-serif;'> <u> Séléctionner un pilote à ajouter et dans quel classement : </u> </h3> </html>"));
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
            this.setLayout(new GridLayout(6, 2, 5,5));
            circuitDatePanel = new JPanel();
            teamCarPanel = new JPanel();

            driversComboBox = new JComboBox(controller.getAllDrivers().stream().map(d -> d.getLastNameFirstName()).toArray());

            circuitsNameList = controller.getAllCircuitsNames();
            circuitsNameList.add(0, "Sélectionner...");
            circuitsCombobox = new JComboBox(circuitsNameList.toArray());
            circuitsCombobox.addItemListener(new ComboBoxItemListener());

            datesModel = new DefaultComboBoxModel(controller.getRaceDatesOfACircuit(circuitsCombobox.getItemAt(1).toString()).toArray());
            datesCombobox = new JComboBox(datesModel);
            datesCombobox.addItemListener(new ComboBoxItemListener());
            datesCombobox.setVisible(false);

            teamsNameList = controller.getAllTeams();
            teamsNameList.add(0, new Team("Séléctionner..."));
            teamsComboBox = new JComboBox(teamsNameList.stream().map(t -> t.getName()).toArray());
            teamsComboBox.addItemListener(new ComboBoxItemListener());

            carsNameList = controller.getRemainingCarsInARanking(circuitsCombobox.getSelectedItem().toString(), datesCombobox.getSelectedItem().toString(), teamsComboBox.getSelectedItem().toString());
            carsModel = new DefaultComboBoxModel(carsNameList.toArray());
            carsComboBox = new JComboBox(carsModel);
            carsComboBox.setVisible(false);
            carsComboBox.addItemListener(new ComboBoxItemListener());

            positionCombobox = new JComboBox();


            record = new JSpinner(new SpinnerNumberModel(1.000,0.000,99999,0.001));
            JSpinner.NumberEditor editor = (JSpinner.NumberEditor)record.getEditor();
            DecimalFormat format = editor.getFormat();
            format.setMinimumFractionDigits(3);

            nbRounds = new JSpinner(new SpinnerNumberModel(0,0,20,1));


            circuitDatePanel.add(circuitsCombobox);
            circuitDatePanel.add(datesCombobox);

            teamCarPanel.add(teamsComboBox);
            teamCarPanel.add(carsComboBox);


            // add the line above the table to select the driver to add and in which ranking
            this.add(new JLabel("Pilote : "));
            this.add(driversComboBox);

            this.add(new JLabel("Circuit : "));
            this.add(circuitDatePanel);

            this.add(new JLabel("Position : "));
            this.add(positionCombobox);

            this.add(new JLabel("Equipe : "));
            this.add(teamCarPanel);

            this.add(new JLabel("Record (en secondes)"));
            this.add(record);

            this.add(new JLabel("Nombre de tours avant l'abandon : "));
            this.add(nbRounds);

        }
    }

    public void toggleVisiblily(JComboBox comboBoxParent, JComboBox comboBox){
        if(comboBoxParent.getSelectedIndex() == 0){
            comboBox.setVisible(false);
        } else {
            comboBox.setVisible(true);
        }
    }
    public void updateJTable() throws Exception {
        tableModel = new RankingModel(controller.getARaceRanking(currentCircuit, currentDate));
        jTable.setModel(tableModel);
    }
    public void updatePositions() throws Exception {
        positionsModel = new DefaultComboBoxModel<>(controller.getPositionsRemainingInARanking(currentCircuit,currentDate).toArray());
        positionCombobox.setModel(positionsModel);

    }
    public void updateCars() throws Exception {
        carsNameList = controller.getRemainingCarsInARanking(currentCircuit, currentDate, teamsComboBox.getSelectedItem().toString());
        carsNameList.add(carsNameList.size(), "Ajouter une voiture...");
        carsModel = new DefaultComboBoxModel(carsNameList.toArray());
        carsComboBox.setModel(carsModel);
    }
    public void updateDates() throws Exception{
        datesModel = new DefaultComboBoxModel(controller.getRaceDatesOfACircuit(currentCircuit).toArray());
        datesCombobox.setModel(datesModel);
    }
    public String errorMessage(){
        StringBuilder errorInput = new StringBuilder();

        if(circuitsCombobox.getSelectedIndex() == 0){
            errorInput.append("- Veuillez sélectionner un circuit");
        }
        if(teamsComboBox.getSelectedIndex() == 0){
            errorInput.append("- Veuillez sélectionner une équipe");
        }

        return errorInput.toString();
    }

    private class RankingTable extends JPanel{
        private JLabel title;
        private GridBagConstraints gcTable;
        public RankingTable () throws Exception {

            this.setLayout(new GridBagLayout());
            gcTable = new GridBagConstraints();

            title = new JLabel("Classement");
            title.setFont(new Font("Arial",Font.TRUETYPE_FONT,15));

            tableModel = new RankingModel(controller.getARaceRanking(currentCircuit, currentDate));
            jTable = new JTable(tableModel);

            JScrollPane sp = Utils.centerTableData(jTable);

            this.add(title,gcTable);
            gcTable.gridy = 1;
            this.add(sp, gcTable);
        }
    }
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(errorMessage().equals("")){
                if(e.getSource() == buttonsPanel.getBack()){
                    Utils.addToMainContainer(mainContainer, new WelcomeJPanel());
                }

                if(e.getSource() == buttonsPanel.getNext()){
                    try {
                        int carSerialNumber = controller.getCarFromName(carsComboBox.getSelectedItem().toString());
                        int raceSerialNumber = controller.getARaceNumber(circuitsCombobox.getSelectedItem().toString(), datesCombobox.getSelectedItem().toString());
                        int driverNumber = controller.getADriver(driversComboBox.getSelectedItem().toString()).getNumber();
                        controller.addDriverToRanking(new Ranking(carSerialNumber,raceSerialNumber, Integer.parseInt(nbRounds.getValue().toString()), Integer.parseInt(positionCombobox.getSelectedItem().toString()), driverNumber, Double.parseDouble(record.getValue().toString())));
                        updateJTable();
                    } catch (Exception exception) {
                        Utils.showErrorMessage(exception.getMessage() + " " +exception.getLocalizedMessage());
                    }
                }
            } else {
                Utils.showErrorMessage(errorMessage());
            }
        }
    }
    private class ComboBoxItemListener implements java.awt.event.ItemListener{
        @Override
        public void itemStateChanged(ItemEvent e) {
            try{
                currentCircuit = circuitsCombobox.getSelectedItem().toString();
                updateDates();
                currentDate = datesCombobox.getSelectedItem().toString();
                if(e.getSource() == circuitsCombobox || e.getSource() == datesCombobox){
                    toggleVisiblily(circuitsCombobox, datesCombobox);
                    updatePositions();
                    updateJTable();
                }
                if(e.getSource() == teamsComboBox){
                    toggleVisiblily(teamsComboBox, carsComboBox);
                    updateCars();
                }
                if(e.getSource() == carsComboBox && carsComboBox.getSelectedItem().toString().equals("Ajouter une voiture...")){
                    Utils.addToMainContainer(mainContainer, new FormCar(mainContainer, teamsComboBox.getSelectedItem().toString(), controller));
                }
            } catch (Exception exception) {
                Utils.showErrorMessage(exception.getMessage());
            }
        }
    }

}






