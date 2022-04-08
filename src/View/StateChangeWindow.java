package View;

import javax.swing.*;
import java.awt.*;

public interface StateChangeWindow {
    void backWindow(JPanel panel, JPanel previousPanel);
    void nextWindow(JPanel mainPanel, GridBagConstraints gc, JPanel ... nextPanels);
}
