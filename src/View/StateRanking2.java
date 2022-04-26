package View;

import javax.swing.*;
import java.awt.*;

public class StateRanking2 extends StateRanking {

    public StateRanking2(Container mainContainer) {

        super(mainContainer);
        setMainPanel(createDatePanel());
        updateWindow();
    }

    @Override
    public JPanel nextWindow() {
        return new StateRanking3(getMainContainer());
    }

    @Override
    public JPanel previousWindow() {
        return new StateRanking1(getMainContainer());
    }
}