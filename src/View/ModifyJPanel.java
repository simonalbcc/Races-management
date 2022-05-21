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
    private FormDriver formDriver;
    private Driver driverFromForm, selectedDriver;
    private ButtonsPanel buttonsPanel;

    public ModifyJPanel(Container mainContainer) throws Exception {
        super(mainContainer);
        setNextText("Modifier");
        super.getjTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @Override
    public JPanel operation(int driverNumber, JPanel currentPanel, Container mainContainer) throws Exception {
        selectedDriver = getController().getADriver(driverNumber);

        formDriver =  new FormDriver();
        formDriver.setFilledDriverForm(selectedDriver);
        formDriver.setDisablePK();

        buttonsPanel = new ButtonsPanel("Retour", "Modifier");
        buttonsPanel.getNext().removeActionListener(buttonsPanel.getNext().getAction());
        buttonsPanel.addActionListener(new ModifyButtonListener());
        changePanels(formDriver, buttonsPanel);

        return ModifyJPanel.this;
    }
    private class ModifyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                if(e.getSource() == buttonsPanel.getNext()){
                    if(formDriver.isCorrect()){
                        // create the driver to add and check if locality exists in DB (else -> create a new one)
                        driverFromForm = formDriver.createDriver();
                        getController().updateDriver(driverFromForm);

                        // save message + update db
                        JOptionPane.showMessageDialog(null, "Modification effectuée", "Information", JOptionPane.INFORMATION_MESSAGE);
                        Utils.addToMainContainer(getMainContainer(), new FinaleJPanel(getMainContainer(), new ModifyJPanel(getMainContainer())));
                    }
                } else {
                    Utils.addToMainContainer(getMainContainer(), new ModifyJPanel(getMainContainer()));
                }
            } catch (Exception exception){
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}

