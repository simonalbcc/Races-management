package View;

import javax.swing.*;
import java.awt.*;

public class StateRanking4 extends StateRanking{
    public StateRanking4(Container mainContainer) {
        super(mainContainer);
        setMainPanel(createFinalePanel());

        updateWindow();
    }


    @Override
    public JPanel nextWindow() {

    }

    @Override
    public JPanel previousWindow() {
        return new StateRanking1(getMainContainer());
    }
}
