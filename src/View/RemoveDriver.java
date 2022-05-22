//region packages & imports
package View;


import Utility.FinalePanel;
import Utility.OperationTemplate;

import javax.swing.*;
import java.awt.*;
//endregion

public class RemoveDriver extends OperationTemplate {
    public RemoveDriver(Container mainContainer) throws Exception {
        super(mainContainer, "Suppression d'un pilote", true);
        setNextText("Supprimer");
    }

    //region abstract methods
    @Override
    public JPanel operation(int driverNumber, JPanel currentPanel, Container mainContainer) throws Exception {
        try {
            int result = JOptionPane.showConfirmDialog(null, "Êtes-vous sûrs de vouloir continuer? Les données seront perdues.\n"+getController().getADriver(driverNumber).toString(), "Avertissement", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if (result == 0) {
                JOptionPane.showMessageDialog(null, "Supprimé", "Information", JOptionPane.INFORMATION_MESSAGE);
                getController().deleteDriver(driverNumber);
                currentPanel = new FinalePanel(mainContainer, new RemoveDriver(mainContainer));
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la suppression (erreur détaillée : "+exception.getMessage()+")", "Erreur", JOptionPane.ERROR_MESSAGE); // à changer
        }
        return currentPanel;
    }
    //endregion
}
