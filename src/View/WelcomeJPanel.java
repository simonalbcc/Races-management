package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WelcomeJPanel extends JPanel {
    private JLabel welcomeLabel;
    private ButtonJPanel buttons;
    private CarPanel carPanel;
    private GridBagConstraints gc;

    public WelcomeJPanel(){
        this.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();

        welcomeLabel = new JLabel("<html> <h1> Hello racer </h1> </html>");
        carPanel = new CarPanel();
        buttons = new ButtonJPanel();

        gc.weighty = 1;
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.PAGE_START;
        this.add(welcomeLabel, gc);

        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.CENTER;
        gc.weightx = 1;
        this.add(carPanel,gc);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.PAGE_END;
        this.add(buttons, gc);
    }

    private class ButtonJPanel extends JPanel{
        private JButton pilotsButton, rankingButton, carsButton;
            public ButtonJPanel(){
            pilotsButton = new JButton("Show pilots");
            pilotsButton.addActionListener(new ButtonListener());

            rankingButton = new JButton("Show ranking");
            rankingButton.addActionListener(new ButtonListener());

            carsButton = new JButton("Show cars");

            this.add(pilotsButton);
            this.add(rankingButton);
            this.add(carsButton);
        }
        private class ButtonListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                WelcomeJPanel.this.removeAll();
                if(e.getSource() == pilotsButton){
                    WelcomeJPanel.this.add(new AccidentsPanel());
                }
                if(e.getSource() == rankingButton){
                    WelcomeJPanel.this.add(new RankingPanel());
                }
                WelcomeJPanel.this.repaint();
                WelcomeJPanel.this.validate();
            }
        }
    }

    private class CarPanel extends JPanel{
        public GraphicCar graphicCar;

        public CarPanel(){
            setLayout(new FlowLayout());
            graphicCar = new GraphicCar();
            MovementCarThread movementCarThread = new MovementCarThread(this, graphicCar);
            movementCarThread.start();
        }
        public void paint(Graphics g){
            super.paint(g);
            graphicCar.paint(g);
        }
    }
}
