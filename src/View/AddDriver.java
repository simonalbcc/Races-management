//region packages & imports
package View;

import Controller.Controller;
import Model.*;
import Utility.FinaleJPanel;
import Utility.Utils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// endregion

public class AddDriver extends JPanel {
    //region privates attributes & constructor
    private JLabel title;
    private GridBagConstraints gc;
    private Container mainContainer;
    private StringBuilder errorInputMessage;
    private FormDriver form;
    private Controller controller;
    private ButtonsPanel buttonsPanel;
    private JButton save;

    public AddDriver(Container mainContainer) throws Exception {
        // container, buttons & form init
        this.mainContainer = mainContainer;
        this.form = new FormDriver();
        this.errorInputMessage = new StringBuilder("Action requise : \n");

        buttonsPanel = new ButtonsPanel("Retour", "Réinitialiser");
        save = new JButton("Sauvegarder");
        buttonsPanel.add(save);
        buttonsPanel.addActionListener(new ButtonsFormListener(), save);

        // layout & gc init
        this.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();

        // title init
        title = new JLabel("<html> <h3> <u> Formulaire d'ajout d'un pilote : </u> </h3> </html>");

        // add all
        this.add(title, gc);
        gc.gridy = 1;
        gc.insets = new Insets(30,0,0,0);
        this.add(form, gc);
        gc.gridy = 2;
        this.add(buttonsPanel, gc);
    }

    //endregion


    public FormDriver getForm() {
        return form;
    }
    public StringBuilder getErrorInputMessage() {
        return errorInputMessage;
    }
    public void changeButtonsPanel(ButtonsPanel buttonsPanel){
        this.remove(2);
        this.buttonsPanel = buttonsPanel;
        this.add(buttonsPanel, gc);
    }

    private class ButtonsFormListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == buttonsPanel.getNext()){
                Utils.addToMainContainer(mainContainer, new WelcomeJPanel());
            }
            try {
            if(e.getSource() == save){
                if(form.isCorrect(errorInputMessage)){
                    // create the driver to add and check if locality exists in DB (else -> create a new one)
                    Driver driver = form.createDriver();
                    controller.addDriver(driver);
                    // save message + update db
                    JOptionPane.showMessageDialog(null, "Ajout effectué", "Information", JOptionPane.INFORMATION_MESSAGE);
                    Utils.addToMainContainer(mainContainer, new FinaleJPanel(mainContainer, new AddDriver(mainContainer)));
                } else {
                    JOptionPane.showMessageDialog(null, errorInputMessage.toString(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    form.cleanWrongTextField();
                    errorInputMessage.setLength(0);
                }
            }
            if(e.getSource() == buttonsPanel.getNext()){
                Utils.addToMainContainer(mainContainer, new AddDriver(mainContainer));
            }
        }catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

