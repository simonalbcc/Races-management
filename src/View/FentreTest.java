package View;

import javax.swing.*;

public class FentreTest extends JFrame {
    public FentreTest (){
        setBounds(10,10,800,800);
        add(new ShowPilotsWindow());
        setVisible(true);
    }


}
