package View;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RankingPanel extends JPanel {
    private JLabel circuitsLabel;
    private JComboBox circuitsCombobox;
    private JButton back;
    private  StateChangeWindow stateWindowRanking;
    private  StateChangeWindow currentState;


    public RankingPanel (){

        circuitsLabel = new JLabel("Select the circuit : ");
        circuitsCombobox = new JComboBox(new String[]{"test", "test"});
        circuitsCombobox.setPreferredSize(new Dimension(100,30));
        back = new JButton("Back");
        back.addActionListener(new ButtonListener());

        this.add(circuitsLabel);
        this.add(circuitsCombobox);
        this.add(back);

        currentState = new StateWindowRanking();

    }
    private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            RankingPanel.this.removeAll();
            currentState.backWindow(RankingPanel.this);
        }
    }
}
