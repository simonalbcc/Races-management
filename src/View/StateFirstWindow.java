package View;

import javax.swing.*;
import java.awt.*;

public class StateFirstWindow implements StateChangeWindow {
    @Override
    public void backWindow(JPanel panel, JPanel previousPanel) {
        panel.add(previousPanel);
    }

    public void nextWindow(JPanel mainPanel, GridBagConstraints gc, JPanel ... newPanels){
        for(JPanel panel : newPanels){
            gc.gridy += 1;
            mainPanel.add(panel, gc);
        }
    }
}
