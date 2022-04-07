package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WelcomeJPanel extends JPanel {
    private JLabel welcomeLabel;
    private ButtonJPanel buttons;
    private GraphicCar graphicCar;

    public WelcomeJPanel(){
        this.setLayout(new BorderLayout());

        welcomeLabel = new JLabel("<html> <h1> Hello racer </h1> </html>");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        buttons = new ButtonJPanel();

        this.graphicCar = new GraphicCar();

        this.add(welcomeLabel, BorderLayout.NORTH);
        //this.add(new JLabel("<html> <h2> voiture </h2> </html>"),BorderLayout.CENTER);
        this.add(buttons,BorderLayout.SOUTH);

        MovementCarThread movementCarThread = new MovementCarThread(this, graphicCar);
        movementCarThread.start();
    }

    public void paint(Graphics g){
        super.paint(g);
        graphicCar.paint(g);
    }

    private class ButtonJPanel extends JPanel{
        private JButton pilotsButton, rankingButton, carsButton;

            public ButtonJPanel(){
            this.setLayout(new FlowLayout());

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
                WelcomeJPanel.this.validate();
            }
        }
    }
}
