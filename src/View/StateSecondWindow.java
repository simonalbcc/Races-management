package View;

import javax.swing.*;
import java.awt.*;

public class StateSecondWindow implements StateChangeWindow{
    @Override
    public void backWindow(JPanel panel, JPanel previousPanel) {
        panel.add(previousPanel);
    }
    public void nextWindow(JPanel mainPanel, GridBagConstraints gc, JPanel ... nextPanels){
        for(JPanel panel : nextPanels){
            gc.gridy += 1;
            mainPanel.add(panel, gc);
        }
    }
}
