package View;


import Controller.Controller;
import Model.Team;
import Exception.DataException;
import Utility.JTableUtils;
import Utility.RankingModel;
import Utility.Utils;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
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
            positionsModel = new DefaultComboBoxModel<>(controller.getPositionsRemainingInARanking(controller.getARaceNumber(circuitsCombobox.getSelectedItem().toString(),controller.getRaceDatesOfACircuit(circuitsCombobox.getSelectedItem().toString()).get(datesCombobox.getSelectedIndex()))).toArray());
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
            if(e.getSource() == carsComboBox && carsComboBox.getSelectedItem() == "Ajouter une voiture..."){
                Utils.addToMainContainer(mainContainer, new AddCar(teamsComboBox.getSelectedItem().toString()));
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
    private class AddCar extends JPanel {
        private JTextField number,averageConsumption, power, membership, name;
        private ArrayList<JTextField> textFields;
        private JLabel numberLabel, averageConsumptionLabel, powerLabel, membershipLabel, nameLabel ;
        private Controller controller;
        private String teamName;
        private Border border, margin;

        public AddCar (String teamName) {
            this.setBounds(10,80,500,150);
            this.setLayout(new GridLayout(13,2, 5,10));

            textFields = new ArrayList<>();
            border = BorderFactory.createRaisedBevelBorder();
            margin = new EmptyBorder(10,10,10,10);
            this.setBorder(new CompoundBorder(border, margin));

            this.teamName = teamName;

            number = new JTextField();
            number.setToolTipText("Veuillez entrer le numéro de la voiture (obligatoire)");
            number.setName("numéro");
            textFields.add(number);
            numberLabel = new JLabel("Numéro * : ");

            averageConsumption = new JTextField();
            averageConsumption.setToolTipText("Veuillez entrer la consommation moyenne de la voiture (obligatoire)");
            averageConsumption.setName("consommation moyenne");
            textFields.add(averageConsumption);
            averageConsumptionLabel = new JLabel("Consommation moyenne * : ");

            power = new JTextField();
            power.setToolTipText("Veuillez entrer la puissance de la voiture (obligatoire)");
            power.setName("puissance");
            textFields.add(power);
            powerLabel = new JLabel("Puissance * : ");

            membership = new JTextField();
            membership.setToolTipText("Équipe choisie");
            membership.setName("équipe");
            membershipLabel = new JLabel("Équipe : ");
            membership.setText(teamName);
            membership.setEditable(false);

            name = new JTextField();
            name.setToolTipText("Veuillez entrer le nom de la voiture (obligatoire)");
            name.setName("nom");
            textFields.add(name);
            nameLabel = new JLabel("Nom * : ");


            this.add(numberLabel);
            this.add(number);

            this.add(averageConsumptionLabel);
            this.add(averageConsumption);

            this.add(powerLabel);
            this.add(power);

            this.add(membershipLabel);
            this.add(membership);

            this.add(nameLabel);
            this.add(name);
        }

        public boolean isCorrect(StringBuilder errorInputMessage){
            AddCar.Checker checkerFactory = new AddCar.Checker();
            String regex;
            for (JTextField textField : textFields) {
                regex = checkerFactory.createRegex(textField.getName());

                if(textField.getText().equals("")){
                    errorInputMessage.append("- Le champs '"+ textField.getName() +"' doit être rempli \n");
                } else if(!textField.getText().matches(regex)){
                    errorInputMessage.append("- Le champs '"+ textField.getName() +" est invalide ("+checkerFactory.selectErrorReasonForRegex(textField.getName())+")\n");

                } else if(textField.getText().length() > checkerFactory.chooseSize(textField.getName())){
                    errorInputMessage.append("- Le champs '"+ textField.getName() +" est invalide (trop long)\n");
                }
            }
            System.out.println(errorInputMessage);
            return errorInputMessage.equals("");
        }

        private class Checker{
            public Checker(){}
            String createRegex(String textFieldName){
                switch (textFieldName){
                    case "numéro":
                    case "puissance": return "(?!\\s)\\d+(?!\\s)";
                    case "consommation moyenne": return "(?!\\s)\\d*.?\\d{0,2}(?!\\s)";
                    case "nom":
                        return "[A-ZÀ-ÖØà-ÿa-z][à-ÿa-z]{1,6}\\s[A-ZÀ-ÖØà-ÿa-z][à-ÿa-z]{1,9}|[A-ZÀ-ÖØà-ÿa-z][à-ÿa-z]+|(\\s?[À-ÖØà-ÿ-a-zA-Z-]+\\s?)+";
                    default:return "";
                }
            }
            Integer chooseSize(String textFieldName){
                switch (textFieldName){
                    case "numéro": return 4;
                    case "consommation moyenne": return 5;
                    case "puissance": return 4;
                    case "nom": return 20;

                    default:return 0;
                }
            }
            String selectErrorReasonForRegex(String textFieldName){
                switch (textFieldName){
                    case "numéro":
                    case "puissance":
                        return "contient des lettres ou caractères invalides";
                    case "consommation moyenne":
                        return "contient des lettres ou a plus de 2 chiffres après la virgule";
                    case "nom":
                        return "contient des chiffres ou caractères invalides";
                    default:return "";
                }
            }
        }
    }
}

// à optimiser
