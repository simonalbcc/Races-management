//region packages & imports
package View.Utility;

import javax.swing.*;
import java.awt.event.ActionListener;
//endregion

public class ButtonsJPanel extends JPanel {

    private JButton back,next;
    public ButtonsJPanel(String txtBackButton, String txtNextButton){
        setBack(txtBackButton);
        setNext(txtNextButton);
    }
    public ButtonsJPanel() {
        setBack(null);
        setNext(null);
    }


    //region getters & setters
    public JButton getBack() {
        return back;
    }
    public void setBack(String txtBackButton){
        if(txtBackButton == null){
            txtBackButton = "Retour";
        }
        back = new JButton(txtBackButton);
        this.add(back);
    }
    public JButton getNext() {
        return next;
    }
    public void setNext(String txtNextButton){
        if(txtNextButton == null){
            txtNextButton = "Suivant";
        }
        next = new JButton(txtNextButton);
        this.add(next);
    }
    //endregion

    //region methods
    public void addActionListener(ActionListener a, JButton...buttons){
        back.addActionListener(a);
        next.addActionListener(a);
        for (JButton button : buttons) {
            button.addActionListener(a);
        }
    }
    //endregion

}

