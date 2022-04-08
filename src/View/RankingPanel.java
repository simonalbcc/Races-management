package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RankingPanel extends JPanel {
    private  StateChangeWindow stateFirstWindow, stateSecondWindow;
    private  StateChangeWindow currentState;
    private GridBagConstraints gc;
    public RankingPanel (){
        this.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        gc.weighty = 1;

        stateFirstWindow = new StateFirstWindow();

        this.add(new CircuitsPanel(), gc);
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.PAGE_END;
        this.add(new ButtonPanel(),gc);
        currentState = stateFirstWindow;

    }
    private class CircuitsPanel extends JPanel{
        private JLabel circuitsLabel;
        private JComboBox circuitsCombobox;
            public CircuitsPanel(){

                circuitsLabel = new JLabel("Select the circuit : ");
                circuitsCombobox = new JComboBox(new String[]{"test", "test"});
                circuitsCombobox.setPreferredSize(new Dimension(100,30));

                this.add(circuitsLabel);
                this.add(circuitsCombobox);
            }
    }
    private class DatePanel extends JPanel{
        private JLabel datesLabel;
        private JComboBox datesCombobox;
        public DatePanel(){
            datesLabel = new JLabel("Select the date : ");
            datesCombobox = new JComboBox(new String[]{"test", "test"});
            datesCombobox.setPreferredSize(new Dimension(100,30));
            this.add(datesLabel);
            this.add(datesCombobox);
        }
    }
    private class ButtonPanel extends JPanel{
        private JButton back,next;
        public ButtonPanel(){
            back = new JButton("Back");
            back.addActionListener(new ButtonListener());

            next = new JButton("Next");
            next.addActionListener(new ButtonListener());

            this.add(back);
            this.add(next);

        }
        private class ButtonListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                RankingPanel.this.removeAll();
                if(e.getSource() == back ){
                    if(currentState.equals(stateFirstWindow)){
                        currentState.backWindow(RankingPanel.this, new WelcomeJPanel());
                    } else {
                        currentState.backWindow(RankingPanel.this, new RankingPanel());
                    }
                }
                if(e.getSource() == next){
                   if(currentState.equals(stateFirstWindow)){
                       currentState.nextWindow(RankingPanel.this, gc, new DatePanel(), new ButtonPanel());
                   } else {

                   }
                }
                RankingPanel.this.validate();
            }
        }
    }
}
