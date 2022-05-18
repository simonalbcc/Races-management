package View;

import Controller.Controller;
import Model.Driver;
import Model.Locality;
import Model.Team;
import Utility.Utils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.stream.Collectors;

public class FormDriver extends JPanel {
    //region private attributes & constructor
    private JTextField number, lastName, firstName, phoneNumber, streetName, streetNumber, city, zipCode;
    private ArrayList<JTextField> textFields;
    private ArrayList<Team> teamsDB;
    private JLabel numberLabel, lastNameLabel, firstNameLabel, phoneNumberLabel, streetAddressLabel, cityLabel,
            countryLabel, zipCodeLabel, originsLabel, teamsLabel, hasRenewedContractLabel, birthdateLabel, asterisk;
    private JComboBox country, origin, team;
    private JCheckBox hasRenewedContract;
    private JSpinner date;
    private SpinnerDateModel spinnerDateModel;
    private JPanel addressPanel;
    private Border border, margin;
    private ImageIcon driverIcon, phoneIcone, homeIcone, cityIcone, zipcodeIcone, lastNameIcone, firstNameIcone, countryIcone, originIcone, teamIcone, contractIcone, birthdateIcone;
    private Controller controller;
    private StringBuilder errorInputMessage;
    private static String[] continents = new String[]{"Séléctionner...", "Europe", "Afrique", "Amérique", "Océanie", "Asie"};

    public FormDriver() throws Exception {
        // set bounds, init & pretty borders
        this.setBounds(10,80,500,150);
        this.setLayout(new GridLayout(13,2, 5,10));

        textFields = new ArrayList<>();
        controller = new Controller();
        addressPanel = new JPanel();

        border = BorderFactory.createRaisedBevelBorder();
        margin = new EmptyBorder(10,10,10,10);
        this.setBorder(new CompoundBorder(border, margin));

        //region textfields

        number = new JTextField();
        number.setToolTipText("Veuillez entrer le numéro du pilote (obligatoire)");
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
        //endregion

        //region images & labels
        numberLabel = new JLabel("Numéro * : ");
        driverIcon = new ImageIcon("images\\driver.png");
        driverIcon.setImage(driverIcon.getImage().getScaledInstance(50,40, Image.SCALE_SMOOTH));
        numberLabel.setIcon(driverIcon);

        lastNameLabel =new JLabel("\tNom * : ");
        lastNameIcone = new ImageIcon("images\\lastname.png");
        lastNameIcone.setImage(lastNameIcone.getImage().getScaledInstance(40,30, Image.SCALE_SMOOTH));
        lastNameLabel.setIcon(lastNameIcone);

        firstNameLabel = new JLabel("\tPrénom * : ");
        firstNameIcone = new ImageIcon("images\\firstName.png");
        firstNameIcone.setImage(firstNameIcone.getImage().getScaledInstance(40,30, Image.SCALE_SMOOTH));
        firstNameLabel.setIcon(firstNameIcone);

        phoneNumberLabel = new JLabel("Numéro de téléphone : ");
        phoneIcone = new ImageIcon("images\\phone.png");
        phoneIcone.setImage(phoneIcone.getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
        phoneNumberLabel.setIcon(phoneIcone);

        streetAddressLabel = new JLabel("Adresse * : ");
        homeIcone = new ImageIcon("images\\home.png");
        homeIcone.setImage(homeIcone.getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
        streetAddressLabel.setIcon(homeIcone);

        cityLabel = new JLabel("Ville * : ");
        cityIcone = new ImageIcon("images\\city.png");
        cityIcone.setImage(cityIcone.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH));
        cityLabel.setIcon(cityIcone);

        zipCodeLabel = new JLabel("Code postal * : ");
        zipcodeIcone = new ImageIcon("images\\zipcode.png");
        zipcodeIcone.setImage(zipcodeIcone.getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
        zipCodeLabel.setIcon(zipcodeIcone);

        countryLabel = new JLabel("Pays * : ");
        countryIcone = new ImageIcon("images\\country.png");
        countryIcone.setImage(countryIcone.getImage().getScaledInstance(30,40, Image.SCALE_SMOOTH));
        countryLabel.setIcon(countryIcone);

        asterisk = new JLabel("<html> <font size = '2' color = 'red'> *champs obligatoires </font>");

        //endregion

        //region spinners
        spinnerDateModel = new SpinnerDateModel();
        spinnerDateModel.setStart(new GregorianCalendar(LocalDate.now().getYear()-70, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()).getTime());
        spinnerDateModel.setValue(new GregorianCalendar(LocalDate.now().getYear()-18, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()).getTime());
        spinnerDateModel.setEnd(new GregorianCalendar(LocalDate.now().getYear()-18, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()).getTime());

        date = new JSpinner(spinnerDateModel);
        date.setEditor(new JSpinner.DateEditor(date, "dd-MM-yyyy"));

        birthdateLabel = new JLabel("<html> Date de naissance    <font size = '2'> (18 ans minimum)</font>* : </html>");
        birthdateIcone = new ImageIcon("images\\birthdate.png");
        birthdateIcone.setImage(birthdateIcone.getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
        birthdateLabel.setIcon(birthdateIcone);
        //endregion

        //region combobox
        origin = new JComboBox(continents);
        origin.setToolTipText("Choisissez l'origine du pilote (obligatoire)");

        country = new JComboBox(Utils.getCountriesArray().toArray());
        country.setToolTipText("Veuillez entrer le code postal de la ville du pilote (obligatoire)");
        country.setName("pays");

        teamsDB = controller.getAllTeams();

        teamsDB.add(0, new Team("Séléctionner..."));
        team = new JComboBox(teamsDB.stream().map(t -> t.getName()).toArray());
        team.setToolTipText("Choisissez l'équipe du pilote");


        // images & labels
        originsLabel = new JLabel("Origine * : ");
        originIcone = new ImageIcon("images\\origin.png");
        originIcone.setImage(originIcone.getImage().getScaledInstance(30,40, Image.SCALE_SMOOTH));
        originsLabel.setIcon(originIcone);

        teamsLabel = new JLabel("Equipe * : ");
        teamIcone = new ImageIcon("images\\team.png");
        teamIcone.setImage(teamIcone.getImage().getScaledInstance(40,40, Image.SCALE_SMOOTH));
        teamsLabel.setIcon(teamIcone);
        //endregion

        //region checkbox
        hasRenewedContract = new JCheckBox();
        hasRenewedContract.setToolTipText("Cochez la case si le pilote a renouvelé son contrat d'engagement");

        hasRenewedContractLabel = new JLabel("A renouvelé son contrat d'engagement : ");
        contractIcone = new ImageIcon("images\\contract.png");
        contractIcone.setImage(contractIcone.getImage().getScaledInstance(40,40, Image.SCALE_SMOOTH));
        hasRenewedContractLabel.setIcon(contractIcone);
        //endregion

        //region add all
        this.add(numberLabel);
        this.add(number);

        this.add(lastNameLabel);
        this.add(lastName);

        this.add(firstNameLabel);
        this.add(firstName);

        this.add(birthdateLabel);
        this.add(date);

        this.add(originsLabel);
        this.add(origin);

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

        this.add(teamsLabel);
        this.add(team);

        this.add(hasRenewedContractLabel);
        this.add(hasRenewedContract);

        this.add(asterisk);

        //endregion
    }

    public ArrayList<JTextField> getTextFields() {
        return textFields;
    }
    public void setFilledDriverForm(Driver driver){
        number.setText(driver.getNumber().toString());

        int lastSpace = driver.getLastNameFirstName().trim().lastIndexOf(" ");
        lastName.setText(driver.getLastNameFirstName().trim().substring(0,lastSpace));
        firstName.setText(driver.getLastNameFirstName().trim().substring(lastSpace + 1));

        spinnerDateModel.setValue(driver.getBirthdate().getTime());
        date.setModel(spinnerDateModel);

        origin.setSelectedItem(driver.getNationality());
        phoneNumber.setText(driver.getPhoneNumber().toString());

        String numberOnly = Arrays.stream(driver.getStreetAndNumber().split("([A-Za-zÀ-ÖØ-öø-ÿ]\s?)+")).collect(Collectors.joining());
        streetNumber.setText(numberOnly);
        streetName.setText(driver.getStreetAndNumber().replace(numberOnly, ""));

        zipCode.setText(driver.getHome().getPostalCode().toString());

        city.setText(driver.getHome().getCity());
        country.setSelectedItem(driver.getHome().getCountry());
        team.setSelectedItem(driver.getTeam().getName());
        hasRenewedContract.setSelected(driver.isHasRenewedCommitmentContract());
    }
    public boolean isCorrect(StringBuilder errorInputMessage){
        boolean filled = true;
        for (JTextField textField : textFields) {
            if(!textField.getName().equals("numéro de téléphone") && textField.getText().equals("")){
                filled = false;
                errorInputMessage.append("- Le champs '"+ textField.getName() +"' doit être remplis \n");
            }
        }
        if(filled){
            textFields.clear();
            if(!number.getText().matches("\\d{1,3}")){
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
            if(!lastName.getText().trim().matches("[A-ZÀ-ÖØà-ÿa-z][à-ÿa-z]{1,6}\\s[A-ZÀ-ÖØà-ÿa-z][à-ÿa-z]{1,9}|[A-ZÀ-ÖØà-ÿa-z][à-ÿa-z]{1,14}")){
                errorInputMessage.append("- Le nom de famille entré est invalide ("+(lastName.getText().length() > 15 ? "trop long" : "doit contenir uniquement des lettres")+")\n");
                textFields.add(lastName);
            }
            if(!firstName.getText().trim().replaceAll("\\s", "").matches("^[A-ZÀ-ÖØà-ÿa-z][à-ÿa-z]{1,6}\\s[A-ZÀ-ÖØà-ÿa-z][à-ÿa-z]{1,9}|^[A-ZÀ-ÖØà-ÿa-z][à-ÿa-z]{1,14}?")){
                errorInputMessage.append("- Le prénom entré est invalide ("+(firstName.getText().length() > 15 ? "trop long" : "doit contenir uniquement des lettres")+")\n");
                textFields.add(firstName);
            }
            if(!streetName.getText().matches("(\\s?[À-ÖØà-ÿ-a-zA-Z-]+\\s?)+") ||  streetName.getText().length() > 25){
                errorInputMessage.append("- Le nom de la rue entré est invalide ("+(streetName.getText().length() > 25 ? "trop long" : "doit contenir uniquement des lettres")+")\n");
                textFields.add(streetName);
            }
            if(!streetNumber.getText().matches("\\d{1,3}")){
                errorInputMessage.append("- Le numéro du domicile entré est invalide ("+(streetNumber.getText().length() > 3 ? "trop long" : "doit contenir uniquement des chiffres")+")\n");
                textFields.add(streetNumber);
            }
            if(!city.getText().matches("[À-ÖØà-ÿ-a-zA-Z-]{4,20}")){
                errorInputMessage.append("- La ville entrée n'est pas valide ("+(city.getText().length() > 20 ? "trop long" : "doit contenir uniquement des lettres")+")\n");
                textFields.add(city);
            }
            if(country.getSelectedIndex() == 0){
                errorInputMessage.append("- Vous devez séléctionner un pays\n");
            }
            if(origin.getSelectedIndex() == 0){
                errorInputMessage.append("- Vous devez séléctionner un pays\n");
            }
            if(team.getSelectedIndex() == 0){
                errorInputMessage.append("- Vous devez séléctionner une équipe\n");
            }
        }
        return (textFields.size() < 1 & filled) && dateIsCorrect() && (origin.getSelectedIndex() != 0 && country.getSelectedIndex() != 0);
    }
    public Driver createDriver() throws Exception {
        GregorianCalendar birthdate = new GregorianCalendar(Integer.parseInt(new SimpleDateFormat("yyyy").format(date.getValue())), Integer.parseInt(new SimpleDateFormat("MM").format(date.getValue()))-1, Integer.parseInt(new SimpleDateFormat("dd").format(date.getValue())));
        Locality locality = new Locality(null, Integer.parseInt(zipCode.getText()), city.getText(), country.getSelectedItem().toString());

        Driver driver = new Driver(  Integer.parseInt(number.getText()),
                lastName.getText()+" "+firstName.getText(),
                (phoneNumber.getText().equals("") ? null : phoneNumber.getText()),
                streetName.getText().concat(" "+streetNumber.getText()),
                continents[origin.getSelectedIndex()],
                teamsDB.get(team.getSelectedIndex()),
                hasRenewedContract.isSelected(),
                birthdate,
                locality);;
        System.out.println(controller.getNumberLocality(driver.getHome()));
            if(controller.getNumberLocality(driver.getHome())== null){
                controller.createLocality(driver.getHome());
            }
            driver.getHome().setNumber(controller.getNumberLocality(driver.getHome()));
        return driver;
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
    public void setDisablePK(){
        number.setEnabled(false);
    }
}