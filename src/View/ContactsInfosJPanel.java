//region packages & imports
package View;

import Utility.AddUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//endregion

public class ContactsInfosJPanel extends JPanel {

    //region private attributes & constructor
    private JLabel contactLabel;
    private JButton returnButton;
    private Container mainContainer;
    private GridBagConstraints gc;

    public ContactsInfosJPanel(Container mainContainer){
        // main container init
        this.mainContainer = mainContainer;

        // layout & constraints init
        this.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        gc.insets = new Insets(20,0,0,0);

        // contact label init
        contactLabel = new JLabel("<html> <h2><u> Développeurs : </u></h2> " +
                                        "<h3> Albicocco Simon  (<u>etu45523@henallux.be</u>) </h3> " +
                                        "<h3> De Winter Alexis (<u>etu45773@henallux.be</u>) </h3> " +
                                        "</html>");

        // back button init
        returnButton = new JButton("<html> <u>R</u>etour </html>");
        returnButton.addActionListener(actionEvent -> AddUtils.addToMainContainer(mainContainer, new WelcomeJPanel()));

        // add to the panel with constraints
        this.add(contactLabel, gc);
        gc.gridy = 1;
        this.add(returnButton, gc);
    }
    //endregion

}
