//region packages & imports
package View;

import Model.Driver;
import Utility.FinaleJPanel;
import Utility.OperationTemplate;
import Utility.Utils;
import javax.swing.*;
import java.awt.*;


import static Utility.Utils.toSingleton;
//endregion

public class ModifyJPanel extends OperationTemplate {
    public ModifyJPanel(Container mainContainer) throws Exception {
        super(mainContainer);
        setNextText("Modifier");
    }

    //region abstract methods
    @Override
    public JPanel operation(int driverNumber, JPanel currentPanel, Container mainContainer) throws Exception {
        Driver driver = null;
        try {
            driver = getController().getAllDrivers().stream().filter(d -> d.getSerialNumber() == driverNumber).collect(toSingleton());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur",  JOptionPane.ERROR_MESSAGE);
        }

        AddDriver addDriver =  new AddDriver(mainContainer);
        addDriver.getForm().setFilledDriverForm(driver);

        ButtonsPanel buttonsPanel = new ButtonsPanel("Retour", "Modifier");
        buttonsPanel.getNext().removeActionListener(buttonsPanel.getNext().getAction());
        buttonsPanel.getNext().addActionListener(e -> {
            if(addDriver.getForm().isCorrect( addDriver.getErrorInputMessage())){
                // create the driver to add and check if locality exists in DB (else -> create a new one)
                try {
                Driver driverFromForm = addDriver.getForm().createDriver();
                    getController().deleteDriver(driverNumber);
                    getController().addDriver(driverFromForm);

                // save message + update db
                JOptionPane.showMessageDialog(null, "Modification effectuée", "Information", JOptionPane.INFORMATION_MESSAGE);
                Utils.addToMainContainer(mainContainer, new FinaleJPanel(mainContainer, new AddDriver(mainContainer)));
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, addDriver.getErrorInputMessage().toString(), "Erreur", JOptionPane.ERROR_MESSAGE);
                addDriver.getForm().cleanWrongTextField();
                addDriver.getErrorInputMessage().setLength(0);
            }
        });
        addDriver.changeButtonsPanel(buttonsPanel);

        return addDriver;

    }
    //endregion
}
