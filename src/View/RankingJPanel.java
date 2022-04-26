package View;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RankingJPanel extends JPanel {
    private Container mainContainer;
    private JPanel[] panels;
    private JPanel currentPanel;
    private int iPosition;

    public RankingJPanel(Container mainContainer){
        // init container
        this.mainContainer = mainContainer;

        // set black borders
        this.setBorder(new BasicBorders.FieldBorder(Color.BLACK, Color.black, Color.BLACK, Color.BLACK));

        //init panels & current
        this.panels = new JPanel[]{new WelcomeJPanel(mainContainer), new CircuitsPanel(), new DatePanel(), new RankingTable()};
        iPosition = 1;
        setCurrentPanel();

        //update window for the first one
        updateWindow();

    }

    public void updateWindow(){
        mainContainer.removeAll();
        setCurrentPanel();
        if(iPosition == 0){
            mainContainer.add(currentPanel);
        } else {
            mainContainer.add(new BasicPanel(currentPanel, new ButtonsPanel()));
        }
        mainContainer.repaint();
        mainContainer.validate();
    }

    public void setCurrentPanel(){
        this.currentPanel = panels[iPosition];
    }

    public void nextWindow(){
        iPosition++;
    }
    public void previousWindow(){
        iPosition--;
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

                this.setBorder(new BasicBorders.FieldBorder(Color.BLACK, Color.black, Color.BLACK, Color.BLACK));
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
        }private class FinalePanelListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainContainer.removeAll();
                if(e.getSource() == backMainMenu){
                    mainContainer.add(new WelcomeJPanel(mainContainer));
                }
                if(e.getSource() == restartResearch){
                    mainContainer.add(new RankingJPanel(mainContainer));
                }
                mainContainer.repaint();
                mainContainer.validate();
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
                if(e.getSource() == next){
                    nextWindow();
                }
                 if(e.getSource() == back){
                    previousWindow();
                }
                 updateWindow();
            }
        }
    }


}