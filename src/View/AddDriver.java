//region packages & imports
package View;

import Model.*;
import View.Utility.ButtonsJPanel;
import View.Utility.FinalePanel;
import View.Utility.OperationTemplate;
import View.Utility.Utils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// endregion

public class AddDriver extends OperationTemplate {
    private FormDriver form;
    private ButtonsJPanel buttonsPanel;
    private JButton save;

    public AddDriver(Container mainContainer) throws Exception {
        // container, buttons & form init
        super(mainContainer, "Formulaire d'ajout d'un pilote", false);
        this.form = new FormDriver();

        buttonsPanel = new ButtonsJPanel("Retour", "Réinitialiser");
        save = new JButton("Sauvegarder");
        buttonsPanel.add(save);
        buttonsPanel.addActionListener(new ButtonsFormListener(), save);
        setFillHorizontal(false);
        changePanels(form, buttonsPanel);
    }

    @Override // not necessary in this panel because buttons panel redefined
    public JPanel operation(int driverNumber, JPanel currentPanel, Container mainContainer){
        return null;
    }

    private class ButtonsFormListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == buttonsPanel.getBack()){
                Utils.addToMainContainer(getMainContainer(), new WelcomeJPanel());
            }
            try {
            if(e.getSource() == save){
                String errorMessage = form.errorInputMessageString();
                if(errorMessage.equals("")){
                    // create the driver to add and check if locality exists in DB (else -> create a new one)
                    Driver driver = form.createDriver();
                    getController().addDriver(driver);
                    // save message + update db
                    Utils.showInformationMessage("Pilote ajouté");
                    Utils.addToMainContainer(getMainContainer(), new FinalePanel(getMainContainer(), new AddDriver(getMainContainer())));
                } else {
                    Utils.showErrorMessage(errorMessage);
                    Utils.cleanTextField(form.getWrongTextFields());
                    form.resetErrorInputMessage();
                }
            }
            if(e.getSource() == buttonsPanel.getNext()){
                Utils.addToMainContainer(getMainContainer(), new AddDriver(getMainContainer()));
            }
        } catch (Exception exception) {
                Utils.showErrorMessage(exception.getMessage());
            }
        }
    }

}

