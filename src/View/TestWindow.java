package View;

import javax.swing.*;
import java.awt.*;

public class TestWindow extends JFrame {

    public TestWindow() throws HeadlessException {
        setBounds(10,10,800,800);
        this.setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(new AccidentsPanel(), BorderLayout.NORTH);
        this.setVisible(true);
    }
}
