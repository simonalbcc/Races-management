package View;

import Model.Car;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RankingJPanel extends JPanel {
    private int numWindow;
    private GridBagConstraints gc;
    private Container mainFrame;
    public RankingJPanel(Container mainFrame){

        this.mainFrame = mainFrame;

        // set layout
        this.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();

        // set size
        this.setPreferredSize(new Dimension(400,400));

        // create & add panels
        this.add(new CircuitsPanel(), gc);
        gc.gridy = 1;
        this.add(new ButtonsPanel(),gc);

        // set current current number window
        numWindow = 0;

        this.setBorder(new BasicBorders.FieldBorder(Color.BLACK, Color.black, Color.BLACK, Color.BLACK));


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
    private class FinalePanel extends  JPanel{
        private JLabel title;
        private JButton backMainMenu, restartResearch;
        public FinalePanel(){
            title = new JLabel("<html> <u> What do you want to do ? </u> </html>");
            title.setHorizontalAlignment(SwingConstants.CENTER);
            this.setLayout(new GridLayout(3,1, 10,10));

            backMainMenu = new JButton("Go back to the main menu");
            backMainMenu.addActionListener(new FinalePanelListener());
            restartResearch = new JButton("Restart a research");
            restartResearch.addActionListener(new FinalePanelListener());

            this.add(title);
            this.add(backMainMenu);
            this.add(restartResearch);
        }
        private class FinalePanelListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                RankingJPanel.this.removeAll();
                if(e.getSource() == backMainMenu){
                    RankingJPanel.this.add(new WelcomeJPanel());
                }
                if(e.getSource() == restartResearch){
                    RankingJPanel.this.add(new RankingJPanel(mainFrame));
                }
                RankingJPanel.this.validate();
                RankingJPanel.this.repaint();
            }
        }
    }
    private class ButtonsPanel extends JPanel{
        private JButton back,next, printOut;
        public ButtonsPanel(){
            back = new JButton("<html> <u>B</u>ack <html>");
            back.addActionListener(new ButtonListener());

            next = new JButton("<html> <u>N</u>ext <html>");
            next.addActionListener(new ButtonListener());

            this.add(back);
            this.add(next);

        }
        private class ButtonListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.removeAll();
                if(e.getSource() == back){
                    switch(numWindow){
                        case 0:
                            addPanels(false, new WelcomeJPanel());
                            break;
                        case 1:
                            addPanels(new RankingJPanel(mainFrame));
                            break;
                        case 2:
                            addPanels(new CircuitsPanel());
                            break;
                        case 3:
                            addPanels(new DatePanel());
                            break;
                    }
                    numWindow--;
                }
                if(e.getSource() == next){
                    switch(numWindow){
                        case 0:
                            addPanels(new DatePanel());
                            break;
                        case 1:
                            addPanels(false, new RankingTable(), new FinalePanel());
                            break;
                    }
                    numWindow++;
                }
                mainFrame.repaint();
                mainFrame.validate();
            }
            public void addPanels (boolean hasButtons, JPanel ... panels){
                for(JPanel panel : panels){
                    mainFrame.add(panel);
                }
                if(hasButtons){
                    mainFrame.add(new ButtonsPanel());
                }
            }
            public void addPanels(JPanel ... panels){
                addPanels(true, panels);
            }
        }
    }
}
