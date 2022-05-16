//region packages & imports
package View;


import Utility.FinaleJPanel;
import Utility.OperationTemplate;

import javax.swing.*;
import java.awt.*;
//endregion

public class RemoveDriver extends OperationTemplate {
    public RemoveDriver(Container mainContainer){
        super(mainContainer);
        setNextText("Supprimer");
    }

    //region abstract methods
    @Override
    public JPanel operation(int driverNumber, JPanel currentPanel, Container mainContainer) {
        try {
            getController().deleteDriver(driverNumber);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la suppression (erreur détaillée : "+exception.getMessage()+")", "Erreur", JOptionPane.ERROR_MESSAGE); // à changer
        }
        int result = JOptionPane.showConfirmDialog(null, "Êtes-vous sûrs de vouloir continuer? Les données seront perdues.", "Avertissement", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if (result == 0) {
            JOptionPane.showMessageDialog(null, "Supprimé", "Information", JOptionPane.INFORMATION_MESSAGE);
            currentPanel = new FinaleJPanel(mainContainer, new RemoveDriver(mainContainer));
        }
        return currentPanel;
    }
    //endregion
}
