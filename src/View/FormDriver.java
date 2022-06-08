//region packages & imports
package View;

import Controller.Controller;
import Model.Driver;
import Model.Locality;
import Model.Team;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
//endregion

public class FormDriver extends JPanel {
    private JTextField number, lastName, firstName, phoneNumber, streetName, streetNumber;
    private JLabel numberLabel, lastNameLabel, firstNameLabel, phoneNumberLabel, streetAddressLabel, cityLabel, countryLabel, zipCodeLabel, originsLabel, teamsLabel, hasRenewedContractLabel, birthdateLabel, asterisk;
    private ImageIcon driverIcon, phoneIcone, homeIcone, cityIcone, zipcodeIcone, lastNameIcone, firstNameIcone, countryIcone, originIcone, teamIcone, contractIcone, birthdateIcone;

    private JComboBox country, origin, team, city, zipcode;
    private JCheckBox hasRenewedContract;

    private JSpinner date;
    private SpinnerDateModel spinnerDateModel;

    private JPanel addressJPanel, localityJPanel;
    private Border border, margin;
    private Controller controller;
    private StringBuilder errorInputMessage;

    private ArrayList<Team> teamsListDB;
    private ArrayList<JComboBox> comboBoxes;
    private ArrayList<JTextField> wrongTextFields;
    private ArrayList<String> countriesListDB;
    private HashMap<Integer, String> localities;
    private static String[] continents = new String[]{"Séléctionner...", "Europe", "Afrique", "Amérique", "Océanie", "Asie"};

    public FormDriver() throws Exception {
        //region set bounds, init & pretty borders
        this.setBounds(10,80,500,150);
        this.setLayout(new GridLayout(13,2, 5,10));

        controller = new Controller();
        addressJPanel = new JPanel();
        localityJPanel = new JPanel();
        errorInputMessage = new StringBuilder();

        border = BorderFactory.createRaisedBevelBorder();
        margin = new EmptyBorder(10,10,10,10);
        this.setBorder(new CompoundBorder(border, margin));
        //endregion

        //region textfields

        number = new JTextField();
        number.setToolTipText("Veuillez entrer le numéro du pilote (obligatoire)");
        number.setName("numéro");

        lastName = new JTextField();
        lastName.setToolTipText("Veuillez entrer le nom de famille du pilote (obligatoire)");
        lastName.setName("nom");

        firstName = new JTextField();
        firstName.setToolTipText("Veuillez entrer le prénom du pilote (obligatoire)");
        firstName.setName("prénom");

        phoneNumber = new JTextField();
        phoneNumber.setToolTipText("Veuillez entrer le numéro de téléphone du pilote (facultatif)");
        phoneNumber.setName("numéro de téléphone");

        streetName = new JTextField();
        streetName.setToolTipText("Veuillez entrer le nom de la rue du pilote (obligatoire)");
        streetName.setName("nom de rue");
        streetName.setPreferredSize(new Dimension(200,40));
        addressJPanel.add(streetName);

        streetNumber = new JTextField();
        streetNumber.setToolTipText("Veuillez entrer le numéro de la maison du pilote (obligatoire)");
        streetNumber.setName("numéro maison");
        streetNumber.setPreferredSize(new Dimension(50,40));
        addressJPanel.add(streetNumber);
        //endregion

        //region spinners
        spinnerDateModel = new SpinnerDateModel();
        spinnerDateModel.setStart(new GregorianCalendar(LocalDate.now().getYear()-70, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()).getTime());
        spinnerDateModel.setValue(new GregorianCalendar(LocalDate.now().getYear()-18, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()).getTime());
        spinnerDateModel.setEnd(new GregorianCalendar(LocalDate.now().getYear()-18, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()).getTime());

        date = new JSpinner(spinnerDateModel);
        date.setEditor(new JSpinner.DateEditor(date, "dd-MM-yyyy"));

        //endregion

        //region combobox
        comboBoxes = new ArrayList<>();
        localities = controller.getLocalitiesName();

        city = new JComboBox(localities.values().toArray());
        city.addItemListener(new CityItemListener());
        city.setToolTipText("Veuillez entrer la ville où réside le pilote (obligatoire)");
        city.setName("ville");
        comboBoxes.add(city);
        city.setPreferredSize(new Dimension(150,40));

        zipcode = new JComboBox(localities.keySet().toArray());
        zipcode.setToolTipText("code postal de la ville du pilote, varie en fonction de la ville");
        zipcode.setEnabled(false);
        zipcode.setPreferredSize(new Dimension(100,40));

        localityJPanel.add(city);
        localityJPanel.add(zipcode);

        origin = new JComboBox(continents);
        origin.setToolTipText("Choisissez l'origine du pilote (obligatoire)");
        origin.setName("origine");
        comboBoxes.add(origin);

        countriesListDB = controller.getCountries();
        countriesListDB.add(0, "Séléctionner...");
        country = new JComboBox(countriesListDB.toArray());
        country.setToolTipText("Veuillez entrer le code postal de la ville du pilote (obligatoire)");
        country.setName("pays");
        comboBoxes.add(country);

        teamsListDB = controller.getAllTeams();
        teamsListDB.add(0, new Team("Séléctionner..."));
        team = new JComboBox(teamsListDB.stream().map(t -> t.getName()).toArray());
        team.setToolTipText("Choisissez l'équipe du pilote");
        team.setName("équipe");
        comboBoxes.add(team);
        //endregion

        //region checkbox
        hasRenewedContract = new JCheckBox();
        hasRenewedContract.setToolTipText("Cochez la case si le pilote a renouvelé son contrat d'engagement");
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

        birthdateLabel = new JLabel("<html> Date de naissance    <font size = '2'> (18 ans minimum)</font>* : </html>");
        birthdateIcone = new ImageIcon("images\\birthdate.png");
        birthdateIcone.setImage(birthdateIcone.getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
        birthdateLabel.setIcon(birthdateIcone);

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

        originsLabel = new JLabel("Origine * : ");
        originIcone = new ImageIcon("images\\origin.png");
        originIcone.setImage(originIcone.getImage().getScaledInstance(30,40, Image.SCALE_SMOOTH));
        originsLabel.setIcon(originIcone);

        teamsLabel = new JLabel("Equipe * : ");
        teamIcone = new ImageIcon("images\\team.png");
        teamIcone.setImage(teamIcone.getImage().getScaledInstance(40,40, Image.SCALE_SMOOTH));
        teamsLabel.setIcon(teamIcone);

        hasRenewedContractLabel = new JLabel("A renouvelé son contrat d'engagement : ");
        contractIcone = new ImageIcon("images\\contract.png");
        contractIcone.setImage(contractIcone.getImage().getScaledInstance(40,40, Image.SCALE_SMOOTH));
        hasRenewedContractLabel.setIcon(contractIcone);

        asterisk = new JLabel("<html> <font size = '2' color = 'red'> *champs obligatoires </font>");

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
        this.add(addressJPanel);

        this.add(cityLabel);
        this.add(localityJPanel);

        this.add(countryLabel);
        this.add(country);

        this.add(teamsLabel);
        this.add(team);

        this.add(hasRenewedContractLabel);
        this.add(hasRenewedContract);

        this.add(asterisk);

        //endregion

    }

    //region methods
    public ArrayList<JTextField> getWrongTextFields() {
        return wrongTextFields;
    }
    public void resetErrorInputMessage(){
        errorInputMessage.delete(0, errorInputMessage.length());
    }
    public void setFilledDriverForm(Driver driver){
        number.setText(driver.getNumber().toString());

        int lastSpace = driver.getLastNameFirstName().trim().lastIndexOf(" ");
        lastName.setText(driver.getLastNameFirstName().trim().substring(0,lastSpace));
        firstName.setText(driver.getLastNameFirstName().trim().substring(lastSpace + 1));

        spinnerDateModel.setValue(driver.getBirthdate().getTime());
        date.setModel(spinnerDateModel);

        origin.setSelectedItem(driver.getNationality());
        phoneNumber.setText(driver.getPhoneNumber());

        String numberOnly = Arrays.stream(driver.getStreetAndNumber().split("([A-Za-zÀ-ÖØ-öø-ÿ]\s?)+")).collect(Collectors.joining());
        streetNumber.setText(numberOnly.trim());
        streetName.setText(driver.getStreetAndNumber().replace(numberOnly, ""));

        zipcode.setSelectedItem(driver.getHome().getPostalCode().toString());

        city.setSelectedItem(driver.getHome().getCity());
        country.setSelectedItem(driver.getHome().getCountry());
        team.setSelectedItem(driver.getTeam().getName());
        hasRenewedContract.setSelected(driver.getHasRenewedCommitmentContract());
    }
    public void setDisablePK(){
        number.setEnabled(false);
    }
    public Driver createDriver() throws Exception {
        GregorianCalendar birthdate = new GregorianCalendar(Integer.parseInt(new SimpleDateFormat("yyyy").format(date.getValue())), Integer.parseInt(new SimpleDateFormat("MM").format(date.getValue()))-1, Integer.parseInt(new SimpleDateFormat("dd").format(date.getValue())));
        Locality locality = new Locality(null, Integer.parseInt(zipcode.getSelectedItem().toString()), city.getSelectedItem().toString(), country.getSelectedItem().toString());

        Driver driver = new Driver(  Integer.parseInt(number.getText()),
                lastName.getText()+" "+firstName.getText(),
                (phoneNumber.getText().equals("") ? null : phoneNumber.getText()),
                streetName.getText().concat(" "+streetNumber.getText()),
                continents[origin.getSelectedIndex()],
                teamsListDB.get(team.getSelectedIndex()),
                hasRenewedContract.isSelected(),
                birthdate,
                locality);

            driver.getHome().setNumber(controller.getNumberLocality(driver.getHome()));
        return driver;
    }
    public String errorInputMessageString(){
        wrongTextFields = new ArrayList<>();

        String regNumber = "\\d+";
        String regString = "[A-ZÀ-ÖØà-ÿa-z'-]{1,6}\\s[A-ZÀ-ÖØà-ÿa-z'-]{1,9}|[A-ZÀ-ÖØà-ÿa-z'-]{1,14}";

        validateInput(regNumber, 3, number);
        validateInput(regNumber, 10, phoneNumber);
        validateInput(regString, 15, lastName);
        validateInput(regString, 15, firstName);
        validateInput("(\s?("+regString+")+\s?)+", 25, streetName);
        validateInput(regNumber, 3, streetNumber);

        for(JComboBox jComboBox : comboBoxes){
            if(jComboBox.getSelectedIndex() == 0){
                errorInputMessage.append("- Vous devez séléctionner une valeur pour le champs '"+jComboBox.getName()+"' \n");
            }
        }

        return errorInputMessage.toString();
    }
    public void validateInput(String regex, Integer size, JTextField textField){
        String field = "- Le champs '"+textField.getName()+"'";
        // phone number is facultative -> can't be treated as the others, moreover -> size mustn't be under 9 and not over 10
        if(textField.equals(phoneNumber)){
            if(!phoneNumber.getText().trim().equals("")){
                if(!phoneNumber.getText().trim().matches(regex)){
                    errorInputMessage.append(field + " contient des lettres ou autres caractères invalides (pas de . / ou indicatif téléphonique)\n");
                    wrongTextFields.add(textField);
                } else if(phoneNumber.getText().trim().length() > size){
                    errorInputMessage.append(field + " est trop long\n");
                    wrongTextFields.add(textField);
                } else if(phoneNumber.getText().trim().length() < 9){
                    errorInputMessage.append(field + "  est trop court\n");
                    wrongTextFields.add(textField);
                }
            }
        } else if(textField.getText().trim().equals("")){
            errorInputMessage.append(field + " est obligatoire\n");
            wrongTextFields.add(textField);
            // check if contains what's asked -> numbers/letters
        } else if(!textField.getText().trim().matches(regex)){
            errorInputMessage.append(field + " contient des "+ (textField.equals(number) || textField.equals(streetNumber) ? "lettres":"chiffres" )+ " ou autres caractères invalides\n");
            wrongTextFields.add(textField);
            // then it's a size issue
        } else if(textField.getText().length() > size){
            errorInputMessage.append(field + " est trop long\n");
            wrongTextFields.add(textField);
        }
    }
    private class CityItemListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
            zipcode.setSelectedIndex(city.getSelectedIndex());
        }
    }
    //endregion

}