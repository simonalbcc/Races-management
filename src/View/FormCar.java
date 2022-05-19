package View;

import Controller.Controller;
import Model.Car;
import Model.Team;
import Utility.Utils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormCar extends JPanel {
    private StringBuilder errorInputMessage;
    private JButton save;
    private JLabel title;
    private Form formCar;
    private ButtonsPanel buttonsPanel;
    private GridBagConstraints gc;
    private Container mainContainer;
    private Controller controller;

    public FormCar (Container mainContainer, String teamName, Controller controller) {
        // container, buttons & form init
        this.mainContainer = mainContainer;
        this.controller = controller;
        this.errorInputMessage = new StringBuilder();
        buttonsPanel = new ButtonsPanel("Retour", "Réinitialiser");
        save = new JButton("Sauvegarder");
        buttonsPanel.add(save);
        buttonsPanel.addActionListener(new ButtonsFormListener(), save);
        formCar = new Form(teamName);
        // layout & gc init
        this.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();

        // title init
        title = new JLabel("<html> <h2> <u> Formulaire d'ajout d'une voiture : </u> </h2> </html>");

        // add all
        this.add(title, gc);
        gc.gridy = 1;
        gc.insets = new Insets(30,0,0,0);
        this.add(formCar, gc);
        gc.gridy = 2;
        this.add(buttonsPanel, gc);
    }

    private class Form extends JPanel {
        private JTextField number,averageConsumption, power, membership, name;
        private ArrayList<JTextField> textFields;
        private JLabel numberLabel, averageConsumptionLabel, powerLabel, membershipLabel, nameLabel ;
        private String teamName;
        private Border border, margin;

        public Form (String teamName)  {
            this.setBounds(10,80,500,150);
            this.setLayout(new GridLayout(5,2, 5,10));

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
            Checker checkerFactory = new Checker();
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
            return errorInputMessage.toString().equals("");
        }
        public Car createCar() {
            return new Car(Integer.parseInt(number.getText()), Double.parseDouble(averageConsumption.getText()), Integer.parseInt(power.getText()), new Team(membership.getText()), name.getText());
        }
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
    private class ButtonsFormListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(e.getSource() == buttonsPanel.getBack()){
                    Utils.addToMainContainer(mainContainer, new AddDriverRanking(mainContainer));
                }
                if(e.getSource() == save){
                    if(formCar.isCorrect(errorInputMessage)){
                        // create the car to add
                        Car car = formCar.createCar();

                        controller.addCar(car);
                        // save message + update db
                        JOptionPane.showMessageDialog(null, "Ajout effectué", "Information", JOptionPane.INFORMATION_MESSAGE);
                        Utils.addToMainContainer(mainContainer, new AddDriverRanking(mainContainer));
                    } else {
                        JOptionPane.showMessageDialog(null, errorInputMessage.toString(), "Erreur", JOptionPane.ERROR_MESSAGE);
                        errorInputMessage.setLength(0);
                    }
                }
                if(e.getSource() == buttonsPanel.getNext()){
                    Utils.cleanTextField(formCar.textFields);
                }
            }catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}