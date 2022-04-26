package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactsInfosJPanel extends JPanel {
    private JLabel contactsInfos;
    private JButton returnButton;
    private Container mainContainer;

    public ContactsInfosJPanel(Container mainContainer){

        this.mainContainer = mainContainer;

        this.setLayout(new BorderLayout());
        contactsInfos = new JLabel("<html> <style> h2 {font-style : italic}</style> <h1><u>Developers :</u></h1> " +
                                        "<h2 >Albicocco Simon</h2>" +
                                        "<p> email : etu45523@henallux.be </p>" +
                                        "<h2>De Winter Alexis</h2>" +
                                        "<p> email : etu45773@henallux.be </p>" +
                                        "</html>");
        returnButton = new JButton("retour");
        returnButton.addActionListener(new returnListener());
        this.add(contactsInfos,BorderLayout.NORTH);
        this.add(returnButton,BorderLayout.SOUTH);

        mainContainer.add(this);

    }

    private class returnListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            mainContainer.removeAll();
            mainContainer.add(new WelcomeJPanel(mainContainer));
            mainContainer.validate();
        }
    }
}
