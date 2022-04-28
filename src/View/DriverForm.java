//region packages & imports
package View;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// endregion

public class DriverForm extends  JPanel{
    private JLabel title;
    private GridBagConstraints gc;
    private Container mainContainer;
    public DriverForm(Container mainContainer) {
        //init container
        this.mainContainer = mainContainer;

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
        this.add(new Form(), gc);
        gc.gridy = 2;
        this.add(new ButtonsForm(), gc);
        //endregion

    }
    //region inner classes
    private class Form extends JPanel {
        private JTextField serialNumber, lastName, firstName, phoneNumber, streetAddress, city, land, zipCode;
        private JLabel serialNumberLabel, lastNameLabel, firstNameLabel, phoneNumberLabel,
                streetAddressLabel, cityLabel, landLabel, zipCodeLabel, originsLabel, teamsLabel, hasRenewedContractLabel, birthdateLabel;
        private JComboBox origins, teams;
        private JCheckBox hasRenewedContract;
        private DatesJSpinner datesJSpinner;
        private Border border, margin;
        // test
        private String[] test = new String[]{"Test", "1", "2"};

        public Form(){
            this.setBounds(10,80,500,150);
            this.setLayout(new GridLayout(11,2, 5,10));

            //region JTextfields
            serialNumber = new JTextField();
            serialNumber.setToolTipText("Veuillez entrer le numéro de série du pilote (obligatoire)");

            lastName = new JTextField();
            lastName.setToolTipText("Veuillez entrer le nom de famille du pilote (obligatoire)");

            firstName = new JTextField();
            firstName.setToolTipText("Veuillez entrer le prénom du pilote (obligatoire)");

            phoneNumber = new JTextField();
            phoneNumber.setToolTipText("Veuillez entrer le numéro de téléphone du pilote (facultatif)");

            streetAddress = new JTextField();
            streetAddress.setToolTipText("Veuillez entrer l'adresse du pilote (obligatoire)");

            city = new JTextField();
            city.setToolTipText("Veuillez entrer la ville où réside le pilote (obligatoire)");

            zipCode = new JTextField();
            zipCode.setToolTipText("Veuillez entrer le code postal de la ville du pilote (obligatoire)");

            land = new JTextField();
            land.setToolTipText("Veuillez entrer le code postal de la ville du pilote (obligatoire)");

            serialNumberLabel = new JLabel("Numéro de série : ");
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
            origins = new JComboBox(test);
            origins.setToolTipText("Choisissez l'origine du pilote : ");
            teams = new JComboBox(test);
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

            this.add(serialNumberLabel);
            this.add(serialNumber);

            this.add(streetAddressLabel);
            this.add(streetAddress);

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
               mainContainer.removeAll();
                if(e.getSource() == back){
                    mainContainer.add(new WelcomeJPanel());
                }
                if(e.getSource() == save){ // !! save in DB
                    // confirmation avec validation
                    // pour l'instant,  retourne simplement dans le menu.
                    mainContainer.add(new WelcomeJPanel());
                }
                if(e.getSource() == reset){
                    mainContainer.add(new DriverForm(mainContainer));
                }
                mainContainer.repaint();
                mainContainer.validate();
            }
        }
    }
    //endregion
}

