//region packages & imports
package View;

import Model.Driver;
import Utility.FinaleJPanel;
import Utility.OperationTemplate;
import Utility.Utils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//endregion

public class ModifyJPanel extends OperationTemplate {
    private AddDriver addDriver;
    private Driver driverFromForm, selectedDriver;

    public ModifyJPanel(Container mainContainer) throws Exception {
        super(mainContainer);
        setNextText("Modifier");
        super.getjTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    //region abstract methods
    @Override
    public JPanel operation(int driverNumber, JPanel currentPanel, Container mainContainer) throws Exception {
        try {
            selectedDriver = getController().getADriver(driverNumber);

            addDriver =  new AddDriver(mainContainer);
            addDriver.getForm().setFilledDriverForm(selectedDriver);
            addDriver.getForm().setDisablePK();

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur",  JOptionPane.ERROR_MESSAGE);
        }

        ButtonsPanel buttonsPanel = new ButtonsPanel("Retour", "Modifier");
        buttonsPanel.getNext().removeActionListener(buttonsPanel.getNext().getAction());
        buttonsPanel.getNext().addActionListener(new ModifyButtonListener());
        addDriver.changeButtonsPanel(buttonsPanel);

        return addDriver;
    }

    private class ModifyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == getButtonsPanel().getNext()){
                if(addDriver.getForm().isCorrect( addDriver.getErrorInputMessage())){
                    // create the driver to add and check if locality exists in DB (else -> create a new one)
                    try {
                        driverFromForm = addDriver.getForm().createDriver();
                        getController().updateDriver(driverFromForm);

                        // save message + update db
                        JOptionPane.showMessageDialog(null, "Modification effectuée", "Information", JOptionPane.INFORMATION_MESSAGE);
                        Utils.addToMainContainer(getMainContainer(), new FinaleJPanel(getMainContainer(), new AddDriver(getMainContainer())));
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, addDriver.getErrorInputMessage().toString(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    Utils.cleanWrongTextField( addDriver.getForm().getTextFields());
                    addDriver.getErrorInputMessage().setLength(0);
                }
            }
        }
    }


    //endregion
}
