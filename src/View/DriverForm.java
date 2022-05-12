//region packages & imports
package View;

import Controller.Controller;
import Model.*;
import Exception.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
// endregion

public class DriverForm extends  JPanel{
    private JLabel title;
    private GridBagConstraints gc;
    private Container mainContainer;
    private StringBuilder errorInputMessage;
    private Form form;
    private Controller controller;
    private static String[] continents = new String[]{"Europe", "Afrique", "Amérique", "Océanie", "Asie"};

    public DriverForm(Container mainContainer) {
        //init container & form
        this.mainContainer = mainContainer;
        this.form = new Form();

        // set layout & init gc
        this.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();

        // init & set title
        title = new JLabel("<html> <h2> <u> Formulaire d'ajout d'un pilote : </u> </h2> </html>");

        //region  add all
        this.add(title, gc);
        gc.gridy = 1;

        // add padding
        gc.insets = new Insets(30,0,0,0);
        this.add(form, gc);
        gc.gridy = 2;
        this.add(new ButtonsForm(), gc);
        //endregion
    }

    private class Form extends JPanel {
        private JTextField number, lastName, firstName, phoneNumber, streetName, streetNumber, city, country, zipCode;
        private ArrayList<JTextField> textFields;
        private JLabel numberLabel, lastNameLabel, firstNameLabel, phoneNumberLabel, streetAddressLabel, cityLabel, countryLabel, zipCodeLabel, originsLabel, teamsLabel, hasRenewedContractLabel, birthdateLabel;
        private JComboBox origin, team;
        private JCheckBox hasRenewedContract;
        private JSpinner date;
        private JPanel addressPanel;
        private Border border, margin;
        private ArrayList<Team> teamsDB;

        public Form(){
            this.setBounds(10,80,500,150);
            this.setLayout(new GridLayout(13,2, 5,10));
            textFields = new ArrayList<>();
            controller = new Controller();
            addressPanel = new JPanel();

            //region JTextfields

            number = new JTextField();
            number.setToolTipText("Veuillez entrer le numéro du pilote");
            number.setName("numéro");
            textFields.add(number);

            lastName = new JTextField();
            lastName.setToolTipText("Veuillez entrer le nom de famille du pilote (obligatoire)");
            lastName.setName("nom");
            textFields.add(lastName);

            firstName = new JTextField();
            firstName.setToolTipText("Veuillez entrer le prénom du pilote (obligatoire)");
            firstName.setName("prénom");
            textFields.add(firstName);

            phoneNumber = new JTextField();
            phoneNumber.setToolTipText("Veuillez entrer le numéro de téléphone du pilote (facultatif)");
            phoneNumber.setName("numéro de téléphone");
            textFields.add(phoneNumber);

            streetName = new JTextField();
            streetName.setToolTipText("Veuillez entrer le nom de la rue du pilote (obligatoire)");
            streetName.setName("adresse");
            streetName.setPreferredSize(new Dimension(200,40));
            addressPanel.add(streetName);
            textFields.add(streetName);

            streetNumber = new JTextField();
            streetNumber.setToolTipText("Veuillez entrer le numéro de la maison du pilote (obligatoire)");
            streetNumber.setName("adresse");
            streetNumber.setPreferredSize(new Dimension(50,40));
            addressPanel.add(streetNumber);
            textFields.add(streetNumber);

            city = new JTextField();
            city.setToolTipText("Veuillez entrer la ville où réside le pilote (obligatoire)");
            city.setName("ville");
            textFields.add(city);

            zipCode = new JTextField();
            zipCode.setToolTipText("Veuillez entrer le code postal de la ville du pilote (obligatoire)");
            zipCode.setName("code postal");
            textFields.add(zipCode);

            country = new JTextField();
            country.setToolTipText("Veuillez entrer le code postal de la ville du pilote (obligatoire)");
            country.setName("pays");
            textFields.add(country);

            numberLabel = new JLabel("Numéro : ");
            lastNameLabel =new JLabel("Nom : ");
            firstNameLabel = new JLabel("Prénom : ");
            phoneNumberLabel = new JLabel("Numéro de téléphone : ");
            streetAddressLabel = new JLabel("Adresse : ");
            cityLabel = new JLabel("Ville : ");
            zipCodeLabel = new JLabel("Code postal : ");
            countryLabel = new JLabel("Pays : ");
            //endregion

            // JSpinners
            birthdateLabel = new JLabel("Date de naissance : ");
            date = new JSpinner(new SpinnerDateModel());
            date.setEditor(new JSpinner.DateEditor(date, "dd-MM-yyyy"));

            // Combobox
            origin = new JComboBox(continents);
            origin.setToolTipText("Choisissez l'origine du pilote : ");

            teamsDB = controller.getAllTeams();
            team = new JComboBox(teamsDB.stream().map(t -> t.getName()).toArray());
            team.setToolTipText("Choisissez l'équipe du pilote");

            originsLabel = new JLabel("Origines : ");
            teamsLabel = new JLabel("Equipes : ");

            // Checkbox
            hasRenewedContract = new JCheckBox();
            hasRenewedContractLabel = new JLabel("A renouvelé son contrat d'engagement : ");
            hasRenewedContract.setToolTipText("Cochez la case si le pilote a renouvelé son contrat d'engagement");

            //region Add all
            this.add(numberLabel);
            this.add(number);

            this.add(lastNameLabel);
            this.add(lastName);

            this.add(firstNameLabel);
            this.add(firstName);

            this.add(phoneNumberLabel);
            this.add(phoneNumber);

            this.add(streetAddressLabel);
            this.add(addressPanel);

            this.add(zipCodeLabel);
            this.add(zipCode);

            this.add(cityLabel);
            this.add(city);

            this.add(countryLabel);
            this.add(country);

            this.add(birthdateLabel);
            this.add(date);

            this.add(originsLabel);
            this.add(origin);

            this.add(teamsLabel);
            this.add(team);

            this.add(hasRenewedContractLabel);
            this.add(hasRenewedContract);
            //endregion


            // set border
            border = BorderFactory.createRaisedBevelBorder();
            margin = new EmptyBorder(10,10,10,10);
            this.setBorder(new CompoundBorder(border, margin));
        }

        public boolean isCorrect(){
            errorInputMessage = new StringBuilder("Action requise : \n");
            boolean filled = true;
            for (JTextField textField : textFields) {
                if(!textField.getName().equals("numéro de téléphone") && textField.getText().equals("")){
                    filled = false;
                    errorInputMessage.append("- Le champs '"+ textField.getName() +"' doit être remplis \n");
                }
            }
            if(filled){
                textFields.clear();
                if(!number.getText().matches("\\d{3}")){
                    errorInputMessage.append("- Le numéro du pilote entré est invalide ("+ (number.getText().length() > 3 ? "trop long" : "contient des lettres") +")\n");
                    textFields.add(number);
                }
                if(!phoneNumber.getText().equals("") && !phoneNumber.getText().matches("\\d{4}\\.?\\/?(\\d+\\.?){3}")){
                    errorInputMessage.append("- Le numéro de téléphone entré est invalide ("+ (phoneNumber.getText().length() > 10 ? "trop long" : "contient des lettres") +")\n");
                    textFields.add(phoneNumber);
                }
                if(!zipCode.getText().matches("\\d{4,5}") && !zipCode.getClass().getSimpleName().equals("Integer")){
                    errorInputMessage.append("- Le code postal entré est invalide ("+ (zipCode.getText().length() > 5 ? "trop long" : "ne contient pas de chiffres") +")\n");
                    textFields.add(zipCode);
                }
                if(!lastName.getText().matches("[a-zA-Z-]{2,15}")){
                    errorInputMessage.append("- Le nom de famille entré est invalide ("+(lastName.getText().length() > 15 ? "trop long" : "doit contenir uniquement des lettres")+")\n");
                    textFields.add(lastName);
                }
                if(!firstName.getText().matches("[a-zA-Z-]{2,15}")){
                    errorInputMessage.append("- Le prénom entré est invalide ("+(firstName.getText().length() > 15 ? "trop long" : "doit contenir uniquement des lettres")+")\n");
                    textFields.add(firstName);
                }
                if(!streetName.getText().matches("(\\s?[a-zA-Z-]+\\s?)+") ||  streetName.getText().length() > 25){
                    errorInputMessage.append("- Le nom de la rue entré est invalide ("+(streetName.getText().length() > 25 ? "trop long" : "doit contenir uniquement des lettres")+")\n");
                    textFields.add(streetName);
                }
                if(!streetNumber.getText().matches("\\d{1,3}")){
                    errorInputMessage.append("- Le numéro du domicile entré est invalide ("+(streetNumber.getText().length() > 3 ? "trop long" : "doit contenir uniquement des chiffres")+")\n");
                    textFields.add(streetNumber);
                }
                if(!city.getText().matches("[a-zA-Z-]{4,20}")){
                    errorInputMessage.append("- La ville entrée n'est pas valide ("+(city.getText().length() > 20 ? "trop long" : "doit contenir uniquement des lettres")+")\n");
                    textFields.add(city);
                }
                if(!country.getText().matches("[a-zA-Z-]{4,15}")){
                    errorInputMessage.append("- Le pays entrée n'est pas valide ("+(country.getText().length() > 15 ? "trop long" : "doit contenir uniquement des lettres")+")\n");
                    textFields.add(country);
                }
            }
            return (textFields.size() < 1 & filled) && dateIsCorrect();
        }
        public Driver createDriver(){
            GregorianCalendar birtdate = new GregorianCalendar(Integer.parseInt(new SimpleDateFormat("yyyy").format(date.getValue())), Integer.parseInt(new SimpleDateFormat("MM").format(date.getValue()))-1, Integer.parseInt(new SimpleDateFormat("dd").format(date.getValue())));
            Locality locality = new Locality(null, Integer.parseInt(zipCode.getText()), city.getText(), country.getText());


            return new Driver(  Integer.parseInt(number.getText()),
                     lastName.getText()+" "+firstName.getText(),
                                phoneNumber.getText(),
                                streetName.getText().concat(" , "+streetNumber.getText()),
                                continents[origin.getSelectedIndex()],
                                teamsDB.get(team.getSelectedIndex()),
                                hasRenewedContract.isSelected(),
                                birtdate,
                                locality);
        }
        public boolean dateIsCorrect(){
            boolean correct = false;
            GregorianCalendar birthdate;
            Date current;

            birthdate = new GregorianCalendar(Integer.parseInt(new SimpleDateFormat("yyyy").format(date.getValue())), Integer.parseInt(new SimpleDateFormat("MM").format(date.getValue()))-1, Integer.parseInt(new SimpleDateFormat("dd").format(date.getValue())));
            current = new Date();

            if(birthdate.getTime().after(current)){
                errorInputMessage.append(" - La date de début est après la date de ce jour");
            } else {
                correct = true;
            }
            return correct;
        }
        public void cleanWrongTextField(){
            for (JTextField textField: textFields) {
                textField.setText("");
            }
        }
    }
    private class ButtonsForm extends JPanel{
        private JButton back, reset, save;
        public ButtonsForm(){
            back = new JButton("<html> <u>R</u>etour <html>");
            back.addActionListener(new ButtonsFormListener());

            reset = new JButton("<html> <u>R</u>éinitialiser <html>");
            reset.addActionListener(new ButtonsFormListener());

            save = new JButton("<html> <u>S</u>auvegarder <html>");
            save.addActionListener(new ButtonsFormListener());

            this.add(back);
            this.add(reset);
            this.add(save);

        }

        private class ButtonsFormListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == back){
                    mainContainer.removeAll();
                    mainContainer.add(new WelcomeJPanel());
                }
                if(e.getSource() == save){
                    if(form.isCorrect()){
                        mainContainer.removeAll();
                        Driver driver = form.createDriver();
                        if(controller.getNumberLocality(driver.getHome())== null){
                            controller.createLocality(driver.getHome());
                        }
                        driver.getHome().setNumber(controller.getNumberLocality(driver.getHome()));
                        try {
                            controller.addDriver(driver);
                        } catch (Exception exception) {
                            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                        JOptionPane.showMessageDialog(null, "Sauvegarde effectuée", "Information", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, errorInputMessage.toString(), "Erreur", JOptionPane.ERROR_MESSAGE);
                        form.cleanWrongTextField();
                    }
                }
                if(e.getSource() == reset){
                    mainContainer.removeAll();
                    mainContainer.add(new DriverForm(mainContainer));
                }
                mainContainer.repaint();
                mainContainer.validate();
            }
        }
    }

}

