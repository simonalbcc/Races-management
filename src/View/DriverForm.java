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
import java.util.ArrayList;
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
        private DatesJSpinner datesJSpinner;
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
            textFieldsMandatory.add(lastName);

            firstName = new JTextField();
            firstName.setToolTipText("Veuillez entrer le prénom du pilote (obligatoire)");
            textFieldsMandatory.add(firstName);

            phoneNumber = new JTextField();
            phoneNumber.setToolTipText("Veuillez entrer le numéro de téléphone du pilote (facultatif)");

            streetAddress = new JTextField();
            streetAddress.setToolTipText("Veuillez entrer l'adresse du pilote (obligatoire)");
            textFieldsMandatory.add(streetAddress);

            city = new JTextField();
            city.setToolTipText("Veuillez entrer la ville où réside le pilote (obligatoire)");
            textFieldsMandatory.add(city);

            zipCode = new JTextField();
            zipCode.setToolTipText("Veuillez entrer le code postal de la ville du pilote (obligatoire)");
            textFieldsMandatory.add(zipCode);

            land = new JTextField();
            land.setToolTipText("Veuillez entrer le code postal de la ville du pilote (obligatoire)");
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
            datesJSpinner = new DatesJSpinner();

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
            this.add(datesJSpinner);

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
            boolean correct = true;
            for (JTextField textField : textFieldsMandatory) {
                if(textField.getText().equals("")){
                    correct = false;
                }
            }
            if(!correct){
                errorInputMessage.append("- Un ou plusieurs champs obligatoires sont vides\n");
            }

            if(!phoneNumber.getText().matches("\\+?\\d{3,5}(\\/?)(\\d{8}.?)+") || (phoneNumber.getText().length() > 12 && phoneNumber.getText().length() < 10)){
                errorInputMessage.append("- Le numéro de téléphone entré n'est pas juste (uniquement des chiffres et une taille max de 5 chiffres)\n");
                correct = false;
            }

            if(!zipCode.getText().matches("\\d{4,5}")){
                errorInputMessage.append("- le code postal entrée n'est pas valide (uniquement des chiffres et une taille max de 5 chiffres)\n");
                correct = false;
            }

            if(!lastName.getText().matches("[a-zA-Z-]{2,10}") ||  lastName.getText().length() > 10){
                errorInputMessage.append("- Le nom de famille entré est trop long ou contient des chiffres\n");
                correct = false;
            }

            if(!firstName.getText().matches("[a-zA-Z-]{2,10}") ||  firstName.getText().length() > 10){
                errorInputMessage.append("- Le prénom de famille entré est trop long ou contient des chiffres\n");
                correct = false;
            }

            if(!streetAddress.getText().matches("(\\d{1,3},)?(\\s?([a-zA-Z]+-?)+){2,}(,\\s?\\d)?") ||  streetAddress.getText().length() < 30){
                errorInputMessage.append("- l'adresse entrée n'est pas valide (uniquement des lettres et une taille max de 30 caractères)\n");
                correct = false;
            }

            if(!city.getText().matches("[a-zA-Z-]{4,20}")){
                errorInputMessage.append("- la ville entrée n'est pas valide (uniquement des lettres et une taille max de 20 caractères)\n");
                correct = false;
            }

            if(!land.getText().matches("[a-zA-Z-]{4,15}")){
                errorInputMessage.append("- le pays entrée n'est pas valide (uniquement des lettres et une taille max de 15 caractères)\n");
                correct = false;
            }

            return correct;
        }
        public Driver createDriver(){
            return new Driver(  null,
                     lastName.getText()+" "+firstName.getText(),
                                Integer.parseInt(phoneNumber.getText()),
                                streetAddress.getText(),
                                continents[origins.getSelectedIndex()],
                                teamsDB.get(teams.getSelectedIndex()),
                                hasRenewedContract.isSelected(),
                                datesJSpinner.getDateSelectedCal(),
                                new Locality(Integer.parseInt(zipCode.getText()), city.getText(), land.getText()));
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

