package View;

import Model.Ranking;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public abstract class StateRanking extends JPanel{
    private GridBagConstraints gc;
    private JPanel mainPanel, buttonsPanel;
    private Container mainContainer;

    public StateRanking (Container mainContainer){
        //init panels
        this.buttonsPanel = createButtonsPanel();
        this.mainContainer = mainContainer;

        // init layout
        this.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();

        this.setBorder(new BasicBorders.FieldBorder(Color.BLACK, Color.black, Color.BLACK, Color.BLACK));
    }

    public Container getMainContainer() {
        return mainContainer;
    }

    public void changeTextButtons(String back, String next){

    }

    public void setMainPanel(JPanel panel){
        this.mainPanel = panel;
    }
    public void setPanels(){
        this.add(mainPanel, gc);
        gc.gridy = 1;
        this.add(buttonsPanel, gc);
    }

    public void updateWindow(){
        setPanels();
        mainContainer.add(StateRanking.this);
        mainContainer.repaint();
        mainContainer.validate();
    }

    private class CircuitsPanel extends JPanel{
        private JLabel circuitsLabel;
        private JComboBox circuitsCombobox;
            public CircuitsPanel(){
                this.setLayout(new GridBagLayout());

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

            this.setBorder(new BasicBorders.FieldBorder(Color.BLACK, Color.black, Color.BLACK, Color.BLACK));
        }
    }
    private class RankingTable extends JPanel{
        private JTable jTable;
        private JLabel title;
        public RankingTable (){
            // init layout
            this.setLayout(new GridLayout(2,1));

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

            this.add(title);
            this.add(sp);
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
        }

        private class FinalePanelListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                getMainContainer().removeAll();
                if(e.getSource() == backMainMenu){
                    getMainContainer().add(new WelcomeJPanel(mainContainer));
                }
                if(e.getSource() == restartResearch){
                    getMainContainer().add(new RankingJPanel(mainContainer));
                }
                getMainContainer().repaint();
                getMainContainer().validate();
            }
        }
    }
    private class ButtonsPanel extends JPanel{
        private JButton back,next;
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
                mainContainer.removeAll();
                if(e.getSource() == next){
                    nextWindow();
                }
                 if(e.getSource() == back){
                    previousWindow();
                }
                 mainContainer.repaint();
                 mainContainer.validate();
            }
        }
    }

    public CircuitsPanel createCircuitsPanel(){
        return new CircuitsPanel();
    }
    public DatePanel createDatePanel(){
        return new DatePanel();
    }
    public RankingTable createRankingTable(){return new RankingTable();}
    public FinalePanel createFinalePanel(){return new FinalePanel();}
    public ButtonsPanel createButtonsPanel(){return new ButtonsPanel();}

    public abstract JPanel nextWindow();
    public abstract JPanel previousWindow();



}
