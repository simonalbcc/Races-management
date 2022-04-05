package View;

import javax.swing.*;
import java.awt.*;

public class TestWindow extends JFrame {

    public TestWindow() throws HeadlessException {
        setBounds(10,10,800,800);
        add(new ShowAccidents());
        this.setVisible(true);
    }
}
