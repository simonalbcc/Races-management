//region packages & imports
package View;

import Utility.FinaleJPanel;

import javax.swing.*;
import java.awt.*;
//endregion

public class ModifyJPanel extends OperationTemplate {
    public ModifyJPanel(Container mainContainer){
        super(mainContainer);
        setNextText("Modifier");
        setTxtConfirmDialog("Modifié");
    }

    //region abstract methods
    @Override
    public void operation(int driverNumber) {

    }
    //endregion
}