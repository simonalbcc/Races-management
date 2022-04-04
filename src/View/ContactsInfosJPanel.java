package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactsInfosJPanel extends JPanel {
    private JLabel contactsInfos;
    private JButton returnButton;

    public ContactsInfosJPanel(){
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
    }

    private class returnListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            ContactsInfosJPanel.this.removeAll();
            ContactsInfosJPanel.this.add(new WelcomeJPanel());
            ContactsInfosJPanel.this.validate();
        }
    }
}
