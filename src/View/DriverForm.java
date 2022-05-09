//region packages & imports
package View;

import Business.DriverManager;
import DataAccess.DriverDBAccess;
import DataAccess.RacesDataAccess;
import DataAccess.SingletonConnexion;
import Model.Driver;
import Model.Locality;
import Model.Team;

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
import java.util.TimeZone;
import java.util.stream.Collectors;
// endregion

public class DriverForm extends  JPanel{
    private JLabel title;
    private GridBagConstraints gc;
    private Container mainContainer;
    private StringBuilder errorInputMessage;
    private Form form;
    private DriverManager driverManager;
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


    //region inner classes
    private class Form extends JPanel {
        private JTextField lastName, firstName, phoneNumber, streetAddress, city, land, zipCode;
        private ArrayList<JTextField> textFieldsMandatory;
        private JLabel lastNameLabel, firstNameLabel, phoneNumberLabel,
                streetAddressLabel, cityLabel, landLabel, zipCodeLabel, originsLabel, teamsLabel, hasRenewedContractLabel, birthdateLabel;
        private JComboBox origins, teams;
        private JCheckBox hasRenewedContract;
        private JSpinner date;
        private Border border, margin;
        private ArrayList<Team> teamsDB;

        public Form(){
            this.setBounds(10,80,500,150);
            this.setLayout(new GridLayout(11,2, 5,10));
            textFieldsMandatory = new ArrayList<>();
            driverManager = new DriverManager();

            //region JTextfields

            lastName = new JTextField();
            lastName.setToolTipText("Veuillez entrer le nom de famille du pilote (obligatoire)");
            lastName.setName("nom");
            textFieldsMandatory.add(lastName);

            firstName = new JTextField();
            firstName.setToolTipText("Veuillez entrer le prénom du pilote (obligatoire)");
            firstName.setName("prénom");
            textFieldsMandatory.add(firstName);

            phoneNumber = new JTextField();
            phoneNumber.setToolTipText("Veuillez entrer le numéro de téléphone du pilote (facultatif)");

            streetAddress = new JTextField();
            streetAddress.setToolTipText("Veuillez entrer l'adresse du pilote (obligatoire)");
            streetAddress.setName("adresse");
            textFieldsMandatory.add(streetAddress);

            city = new JTextField();
            city.setToolTipText("Veuillez entrer la ville où réside le pilote (obligatoire)");
            city.setName("ville");
            textFieldsMandatory.add(city);

            zipCode = new JTextField();
            zipCode.setToolTipText("Veuillez entrer le code postal de la ville du pilote (obligatoire)");
            zipCode.setName("code postal");
            textFieldsMandatory.add(zipCode);

            land = new JTextField();
            land.setToolTipText("Veuillez entrer le code postal de la ville du pilote (obligatoire)");
            land.setName("pays");
            textFieldsMandatory.add(land);

            lastNameLabel =new JLabel("Nom : ");
            firstNameLabel = new JLabel("Prénom : ");
            phoneNumberLabel = new JLabel("Numéro de téléphone : ");
            streetAddressLabel = new JLabel("Adresse : ");
            cityLabel = new JLabel("Ville : ");
            zipCodeLabel = new JLabel("Code postal : ");
            landLabel = new JLabel("Pays : ");
            //endregion

            // JSpinners
            birthdateLabel = new JLabel("Date de naissance : ");
            date = new JSpinner(new SpinnerDateModel());
            date.setEditor(new JSpinner.DateEditor(date, "dd-MM-yyyy"));

            // Combobox
            origins = new JComboBox(continents);
            origins.setToolTipText("Choisissez l'origine du pilote : ");
            teamsDB = driverManager.getAllTeams();
            teams = new JComboBox(teamsDB.stream().map(t -> t.getName()).toArray());
            teams.setToolTipText("Choisissez l'équipe du pilote");
            originsLabel = new JLabel("Origines : ");
            teamsLabel = new JLabel("Equipes : ");

            // Checkbox
            hasRenewedContract = new JCheckBox();
            hasRenewedContractLabel = new JLabel("A renouvelé son contrat d'engagement : ");
            hasRenewedContract.setToolTipText("Cochez la case si le pilote a renouvelé son contrat d'engagement");

            //region Add all
            this.add(lastNameLabel);
            this.add(lastName);

            this.add(firstNameLabel);
            this.add(firstName);

            this.add(phoneNumberLabel);
            this.add(phoneNumber);

            this.add(streetAddressLabel);
            this.add(streetAddress);

            this.add(zipCodeLabel);
            this.add(zipCode);

            this.add(cityLabel);
            this.add(city);

            this.add(landLabel);
            this.add(land);

            this.add(birthdateLabel);
            this.add(date);

            this.add(originsLabel);
            this.add(origins);

            this.add(teamsLabel);
            this.add(teams);

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
            boolean correct = false;
            for (JTextField textField : textFieldsMandatory) {
                if(textField.getText().equals("")){
                    filled = false;
                    errorInputMessage.append("- Le champs '"+ textField.getName() +"' doit être remplis \n");
                }
            }
            if(filled){
                if(!phoneNumber.getText().matches("\\d{10,12}|\\+?\\d{3,5}(\\/?)(\\d{8}.?)+") || (phoneNumber.getText().length() > 12 && phoneNumber.getText().length() < 10)){
                    errorInputMessage.append("- Le numéro de téléphone entré n'est pas juste (uniquement des chiffres et une taille max de 5 chiffres)\n");
                    errorInputMessage.append((phoneNumber.getText().length() > 12 ? "(trop long)" : ""));
                    phoneNumber.setText("");
                } else {
                    if(!zipCode.getText().matches("\\d{4,5}")){
                        errorInputMessage.append("- Le code postal entrée n'est pas valide (uniquement des chiffres et une taille max de 5 chiffres)\n");
                        zipCode.setText("");
                    } else{
                        if(!lastName.getText().matches("[a-zA-Z-]{2,15}") ||  lastName.getText().length() > 15){
                            errorInputMessage.append("- Le nom de famille entré est trop long ou contient des chiffres\n");
                            lastName.setText("");
                        } else {
                            if(!firstName.getText().matches("[a-zA-Z-]{2,15}") ||  firstName.getText().length() > 15){
                                errorInputMessage.append("- Le prénom de famille entré est trop long ou contient des chiffres\n");
                                firstName.setText("");
                            } else {
                                if(!streetAddress.getText().matches("(\\d{1,3},?\\s?)([a-zA-Z-]+\\s?)+|([a-zA-Z-]+\\s?)+(,?\\s?\\d{1,3})") ||  streetAddress.getText().length() > 30){
                                    errorInputMessage.append("- L'adresse entrée n'est pas valide\n");
                                    errorInputMessage.append(streetAddress.getText().length() > 30 ? "(trop long)" : "ne contient pas de numéro ou de nom)");
                                    streetAddress.setText("");
                                } else {
                                    if(!city.getText().matches("[a-zA-Z-]{4,20}")|| city.getText().length() > 20){
                                        errorInputMessage.append("- La ville entrée n'est pas valide (uniquement des lettres et une taille max de 20 caractères)\n");
                                        city.setText("");
                                    } else {
                                        if(!land.getText().matches("[a-zA-Z-]{4,15}") || land.getText().length() > 15){
                                            errorInputMessage.append("- Le pays entrée n'est pas valide \n");
                                            errorInputMessage.append(land.getText().length() > 15 ? "(trop long)" : "ne contient pas de numéro ou de nom)");
                                            land.setText("");
                                        } else {
                                            correct = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return correct & filled;
        }
        public Driver createDriver(){
            GregorianCalendar birtdate = new GregorianCalendar(Integer.parseInt(new SimpleDateFormat("yyyy").format(date.getValue())), Integer.parseInt(new SimpleDateFormat("MM").format(date.getValue()))-1, Integer.parseInt(new SimpleDateFormat("dd").format(date.getValue())));
            return new Driver(  null,
                     lastName.getText()+" "+firstName.getText(),
                                Long.parseLong(phoneNumber.getText()),
                                streetAddress.getText(),
                                continents[origins.getSelectedIndex()],
                                teamsDB.get(teams.getSelectedIndex()),
                                hasRenewedContract.isSelected(),
                                birtdate,
                                new Locality(null, Integer.parseInt(zipCode.getText()), city.getText(), land.getText()));
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
                        JOptionPane.showMessageDialog(null, "Sauvegarde effectuée", "Information", JOptionPane.INFORMATION_MESSAGE);
                        driverManager.addDriver(form.createDriver());
                    } else {
                        JOptionPane.showMessageDialog(null, errorInputMessage.toString(), "Erreur", JOptionPane.ERROR_MESSAGE);
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
    //endregion
}

