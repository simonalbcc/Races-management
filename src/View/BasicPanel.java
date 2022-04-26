package View;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;

public class BasicPanel extends JPanel{
    private GridBagConstraints gc;
    public BasicPanel (JPanel...panels){
        // init layout
        this.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();

        for(JPanel panel : panels){
            gc.gridy++;
            this.add(panel, gc);
        }
    }
}

