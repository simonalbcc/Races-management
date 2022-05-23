//region packages & imports
package View;
import Controller.Controller;
import Model.Car;
import Model.Team;
import View.Utility.ButtonsJPanel;
import View.Utility.Utils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
//endregion

public class FormCar extends JPanel {
    private JButton save;
    private Form formCar;
    private ButtonsJPanel buttonsPanel;
    private GridBagConstraints gc;
    private Container mainContainer;
    private Controller controller;

    public FormCar (Container mainContainer, String teamName, Controller controller) {
        // container,controller, errorMessage, buttons & form init
        this.mainContainer = mainContainer;
        this.controller = controller;

        buttonsPanel = new ButtonsJPanel("Retour", "Réinitialiser");
        save = new JButton("Sauvegarder");
        buttonsPanel.add(save);
        buttonsPanel.addActionListener(new ButtonsFormListener(), save);

        formCar = new Form(teamName);

        // layout & gc init
        this.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();

        // add all to the main panel
        this.add(new JLabel("<html> <h2> <u> Formulaire d'ajout d'une voiture : </u> </h2> </html>"), gc);
        gc.gridy = 1;
        gc.insets = new Insets(30,0,0,0);
        this.add(formCar, gc);
        gc.gridy = 2;
        this.add(buttonsPanel, gc);
    }

    private class Form extends JPanel {
        private JTextField number,averageConsumption, power, membership, name;
        private ArrayList<JTextField> wrongTextFields;
        private JLabel numberLabel, averageConsumptionLabel, powerLabel, membershipLabel, nameLabel ;
        private String teamName;
        private Border border, margin;
        private StringBuilder errorInput;

        public Form (String teamName)  {
            // set layout, design, bounds & init textfields
            this.setBounds(10,80,1000,300);
            this.setLayout(new GridLayout(5,2, 5,10));

            wrongTextFields = new ArrayList<>();
            errorInput = new StringBuilder();

            border = BorderFactory.createRaisedBevelBorder();
            margin = new EmptyBorder(10,10,10,10);
            this.setBorder(new CompoundBorder(border, margin));

            this.teamName = teamName;

            // region init textfield & label
            number = new JTextField();
            number.setToolTipText("Veuillez entrer le numéro de la voiture (obligatoire)");
            number.setName("numéro");
            numberLabel = new JLabel("Numéro * : ");

            averageConsumption = new JTextField();
            averageConsumption.setToolTipText("Veuillez entrer la consommation moyenne de la voiture (obligatoire)");
            averageConsumption.setName("consommation moyenne");
            averageConsumptionLabel = new JLabel("Consommation moyenne * : ");

            power = new JTextField();
            power.setToolTipText("Veuillez entrer la puissance de la voiture (obligatoire)");
            power.setName("puissance");
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
            nameLabel = new JLabel("Nom * : ");
            //endregion

            //region add on the form panel
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
            //endregion

        }
        public Car createCar() throws Exception {
            return new Car(Integer.parseInt(number.getText()), Double.parseDouble(averageConsumption.getText()), Integer.parseInt(power.getText()), new Team(membership.getText()), name.getText());
        }
        public StringBuilder errorMessage(){
            String regName = "^[a-zA-Z0-9_.-]*";
            String regNumber = "\\d+";

            validateInput(regNumber, 4, number);
            validateInput(regNumber, 4, averageConsumption);
            validateInput(regNumber, 4, power);
            validateInput(regName, 20, name);

            return errorInput;
        }

        public void validateInput(String regex, Integer size, JTextField textField){
            String field = "- Le champs '"+textField.getName()+"'";
            if(textField.getText().trim().equals("")){
                errorInput.append(field + " est obligatoire\n");
                wrongTextFields.add(textField);
                // check if contains what's asked -> numbers/letters
            } else if(!textField.getText().trim().matches(regex)){
                errorInput.append(field + " contient des "+(textField.equals(name) ? "":"lettres ou autres ")+"caractères invalides\n");
                wrongTextFields.add(textField);
                // then it's a size issue
            } else if(textField.getText().length() > size){
                errorInput.append(field + " est trop long\n");
                wrongTextFields.add(textField);
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
                    StringBuilder error = formCar.errorMessage();
                    if(error.toString().equals("")){
                        // create the car to add
                        Car car = formCar.createCar();
                        controller.addCar(car);

                        // save message + update db
                        Utils.showInformationMessage("Ajout de la voiture effectué");
                        Utils.addToMainContainer(mainContainer, new AddDriverRanking(mainContainer));
                    } else {
                        Utils.showErrorMessage(error.toString());
                        error.setLength(0);
                    }
                }
                if(e.getSource() == buttonsPanel.getNext()){
                    Utils.cleanTextField(formCar.wrongTextFields);
                }
            }catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
