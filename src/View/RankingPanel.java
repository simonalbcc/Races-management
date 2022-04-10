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
        // set layout
        this.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        gc.weighty = 1;

        // create states of windows
        stateFirstWindow = new StateNewWindow();
        stateSecondWindow = new StateNewWindow();

        // create & add panels
        this.add(new CircuitsPanel(), gc);
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.PAGE_END;
        this.add(new ButtonsPanel(),gc);

        // set current state current window
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
            title = new JLabel("Ranking");
            title.setFont(new Font("Arial",Font.TRUETYPE_FONT,15));


            // init of headers
            String[] headColumns = new String[]{"Position", "Car number", "Car power", "Name driver", "Record"};

            // init fictive data
            Object[][] data = new Object[][] {
                    {"1", "42", "puissance", "Paris", 1},
                    {"2", "35", "puissance", "Marseille", 2},
                    {"3", "63", "puissance", "Lyon", 3},
                    {"4", "23", "puissance","Nice", 4},
                    {"5", "5","puissance","Dublin", 5}
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
    private class ButtonsPanel extends JPanel{
        private JButton back,next, printOut;
        public ButtonsPanel(){
            back = new JButton("<html> <u>B</u>ack <html>");
            back.addActionListener(new ButtonListener());

            next = new JButton("<html> <u>N</u>ext <html>");
            next.addActionListener(new ButtonListener());

            printOut = new JButton("Print out");
            printOut.addActionListener(new ButtonListener());


            this.add(back);
            this.add(next);

        }
        private class ButtonListener implements ActionListener{
            private ButtonsPanel buttonPanelTable;
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
                       currentState.nextWindow(RankingPanel.this, gc, new DatePanel(), new ButtonsPanel());
                       currentState = stateSecondWindow;
                   } else {
                       buttonPanelTable = new ButtonsPanel();
                       buttonPanelTable.next.setVisible(false);
                       buttonPanelTable.add(printOut);
                       currentState.nextWindow(RankingPanel.this, gc, new RankingTable(), buttonPanelTable);
                   }
                }
                if(e.getSource() == printOut){
                    JOptionPane.showMessageDialog(null, "Ici on sort la table 'prête pour impression'");
                    buttonPanelTable = new ButtonsPanel();

                    buttonPanelTable.back.setText("Go back to the menu");
                    buttonPanelTable.next.setText("Restart a ranking");

                    currentState.nextWindow(RankingPanel.this, gc, buttonPanelTable);
                    currentState = stateFirstWindow;
                }
                RankingPanel.this.repaint();
                RankingPanel.this.validate();
            }
        }
    }
}
