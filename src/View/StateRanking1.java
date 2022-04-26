package View;

import javax.swing.*;
import java.awt.*;

public class StateRanking1 extends StateRanking {
    public StateRanking1(Container mainContainer) {
        super(mainContainer);
        setMainPanel(createCircuitsPanel());
        updateWindow();
    }

    @Override
    public JPanel nextWindow() {
        return new StateRanking2(getMainContainer());
    }

    @Override
    public JPanel previousWindow() {
        return new WelcomeJPanel(getMainContainer());
    }

}
