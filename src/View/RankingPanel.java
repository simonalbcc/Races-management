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

        stateFirstWindow = new StateNewWindow();
        stateSecondWindow = new StateNewWindow();

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
    private class RankingTable extends JPanel{
        private JTable jTable;
        private JLabel title;
        public RankingTable (){
            // init layout
            this.setLayout(new GridBagLayout());

            // init title
            title = new JLabel("List drivers");
            title.setFont(new Font("Arial",Font.TRUETYPE_FONT,20));


            // init of headers
            String[] headColumns = new String[]{"Date", "Name", "Address", "Locality", "Team"};

            // init fictive data
            Object[][] data = new Object[][] {
                    {"01/02/2003", "Thomas", "Address 1", "Paris", 1},
                    {"02/02/2003", "Jean", "Address 2", "Marseille", 2},
                    {"03/02/2003", "Yvon", "Address 3", "Lyon", 3},
                    {"04/02/2003", "Yohan", "Address 4","Nice", 4},
                    {"05/02/2003", "Merlin","Address 5","Dublin", 5}
            };
            jTable = new JTable(data, headColumns);

            JScrollPane sp = new JScrollPane(jTable);
            sp.setPreferredSize(new Dimension(300, 250));
            jTable.setFillsViewportHeight(true);


            this.add(sp, gc);
            gc.gridy = 1;
            this.add(title, gc);
        }

    }
    private class ButtonPanel extends JPanel{
        private JButton back,next, printOut;
        public ButtonPanel(){
            back = new JButton("Back");
            back.addActionListener(new ButtonListener());

            next = new JButton("Next");
            next.addActionListener(new ButtonListener());

            printOut = new JButton("Print out");
            printOut.addActionListener(new ButtonListener());


            this.add(back);
            this.add(next);

        }
        private class ButtonListener implements ActionListener{
            ButtonPanel buttonPanelTable;
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
                       currentState = stateSecondWindow;
                   } else {
                       buttonPanelTable = new ButtonPanel();
                       buttonPanelTable.next.setVisible(false);
                       buttonPanelTable.add(printOut);
                       currentState.nextWindow(RankingPanel.this, gc, new RankingTable(), buttonPanelTable);
                   }
                }
                if(e.getSource() == printOut){
                    JOptionPane.showMessageDialog(null, "Ici on sort la table 'prête pour impression'");
                    buttonPanelTable = new ButtonPanel();

                    buttonPanelTable.back.setText("Go back to the menu");
                    buttonPanelTable.next.setText("Restart a ranking");

                    currentState.nextWindow(RankingPanel.this, gc, buttonPanelTable);
                    currentState = stateFirstWindow;
                }
                RankingPanel.this.validate();
            }
        }
    }
}
