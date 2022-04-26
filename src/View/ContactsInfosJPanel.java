package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactsInfosJPanel extends JPanel {
    private JLabel contactsInfos;
    private JButton returnButton;
    private Container mainContainer;
    private GridBagConstraints gc;

    public ContactsInfosJPanel(Container mainContainer){
        // init main container
        this.mainContainer = mainContainer;

        // set layout + constraints
        this.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        gc.insets = new Insets(20,0,0,0);

        // init text
        contactsInfos = new JLabel("<html> <h2><u> Développeurs : </u></h2> " +
                                        "<h3> Albicocco Simon  (<u>etu45523@henallux.be</u>) </h3> " +
                                        "<h3> De Winter Alexis (<u>etu45773@henallux.be</u>) </h3> " +
                                        "</html>");

        // init button back
        returnButton = new JButton("<html> <u>R</u>etour </html>");
        returnButton.addActionListener(new ReturnListener());

        // add to the panel
        this.add(contactsInfos, gc);
        gc.gridy = 1;
        this.add(returnButton, gc);

        // add to the container
        mainContainer.add(this);

    }

    private class ReturnListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            // manage the back button
            mainContainer.removeAll();
            mainContainer.add(new WelcomeJPanel(mainContainer));
            mainContainer.validate();
        }
    }
}
