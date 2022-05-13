//region packages & imports
package View;

import java.awt.*;
//endregion

public class RemoveDriver extends OperationTemplate {
    public RemoveDriver(Container mainContainer){
        super(mainContainer);
        setNextText("Supprimer");
        setTxtConfirmDialog("Supprimé");
    }

    //region abstract methods
    public void changeNextText() {
        this.getButtonsPanel().getNext().setText("Supprimer l'élément séléctionné");
    }

    @Override
    public void operation(int driverNumber) {

    }
    //endregion
}
