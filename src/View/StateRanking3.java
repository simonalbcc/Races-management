package View;

import javax.swing.*;
import java.awt.*;

public class StateRanking3 extends StateRanking {
    public StateRanking3(Container mainContainer) {
        super(mainContainer);
        setMainPanel(createRankingTable());
        updateWindow();
    }


    @Override
    public JPanel nextWindow() {
        return new StateRanking4(getMainContainer());
    }

    @Override
    public JPanel previousWindow() {
        return new StateRanking2(getMainContainer());
    }
}