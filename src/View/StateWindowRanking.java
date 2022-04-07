package View;

import javax.swing.*;

public class StateWindowRanking implements StateChangeWindow {
    @Override
    public void backWindow(JPanel panel) {
        panel.add(new WelcomeJPanel());
    }
}
