package View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ButtonsPanel extends JPanel {
    private JButton back,next;
    public ButtonsPanel(String txtBackButton, String txtNextButton){
        back = new JButton("<html> <u>"+txtBackButton.substring(0,1)+"</u>"+txtBackButton.substring(1)+"<html>");
        next = new JButton("<html> <u>"+txtNextButton.substring(0,1)+"</u>"+txtNextButton.substring(1)+"<html>");

        this.add(back);
        this.add(next);
    }

    public JButton getBack() {
        return back;
    }
    public JButton getNext() {
        return next;
    }

    public void addActionListener(ActionListener a){
        back.addActionListener(a);
        next.addActionListener(a);
    }

}

