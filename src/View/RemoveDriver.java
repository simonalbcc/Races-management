//region packages & imports
package View;

import javax.swing.*;
import java.awt.*;
//endregion

public class RemoveDriver extends OperationTemplate {
    public RemoveDriver(Container mainContainer){
        super(mainContainer);
        setNextText("Supprimer");
        setTxtConfirmDialog("Supprimé");
    }

    //region abstract methods
    @Override
    public void operation(int driverNumber) throws Exception {
        getController().deleteDriver(driverNumber);
    }
    //endregion
}
