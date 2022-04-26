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
        title = new JLabel("<html> <h1> <u> Form to add a driver : </u> </h1> </html>");

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
        private JTextField serialNumber, lastName, firstName, phoneNumber, streetAddress, city, state, zipCode;
        private JLabel serialNumberLabel, lastNameLabel, firstNameLabel, phoneNumberLabel,
                streetAddressLabel, cityLabel, stateLabel, zipCodeLabel, originsLabel, teamsLabel, hasRenewedContractLabel, birthdateLabel;
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
            serialNumber.setToolTipText("Enter the serial number of the driver, it's a required field");

            lastName = new JTextField();
            lastName.setToolTipText("Enter the driver last name, it's a required field");

            firstName = new JTextField();
            firstName.setToolTipText("Enter the driver first name, it's a required field");

            phoneNumber = new JTextField();
            phoneNumber.setToolTipText("Enter the driver phone number, this field is facultative");

            streetAddress = new JTextField();
            streetAddress.setToolTipText("Enter the driver street address, it's a required field");

            city = new JTextField();
            city.setToolTipText("Enter the city where the driver lives, it's a required field");

            zipCode = new JTextField();
            zipCode.setToolTipText("Enter the zipcode of the driver city, it's a required field");

            state = new JTextField();
            state.setToolTipText("Enter the state where the driver lives, it's a required field");

            serialNumberLabel = new JLabel("Serial number : ");
            lastNameLabel =new JLabel("Last name : ");
            firstNameLabel = new JLabel("First name : ");
            phoneNumberLabel = new JLabel("Phone number : ");
            streetAddressLabel = new JLabel("Street address : ");
            cityLabel = new JLabel("City : ");
            zipCodeLabel = new JLabel("Zipcode : ");
            stateLabel = new JLabel("State : ");
            //endregion

            // JSpinners
            birthdateLabel = new JLabel("Birthdate : ");
            datesJSpinner = new DatesJSpinner();

            // Combobox
            origins = new JComboBox(test);
            origins.setToolTipText("Choose the origin of the driver");
            teams = new JComboBox(test);
            teams.setToolTipText("Choose the team of the driver ");
            originsLabel = new JLabel("Origin : ");
            teamsLabel = new JLabel("Team : ");

            // Checkbox
            hasRenewedContract = new JCheckBox();
            hasRenewedContractLabel = new JLabel("Has renewed commitment contract : ");
            hasRenewedContract.setToolTipText("Check the case if the driver has renewed commitment contract");

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

            this.add(stateLabel);
            this.add(state);

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
            back = new JButton("<html> <u>B</u>ack <html>");
            back.addActionListener(new ButtonsFormListener());

            reset = new JButton("<html> <u>R</u>eset <html>");
            reset.addActionListener(new ButtonsFormListener());

            save = new JButton("<html> <u>S</u>ave <html>");
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
                    mainContainer.add(new WelcomeJPanel(mainContainer));
                }
                if(e.getSource() == save){ // !! save in DB
                    // confirmation avec validation
                    // pour l'instant,  retourne simplement dans le menu.
                    mainContainer.add(new WelcomeJPanel(mainContainer));
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

