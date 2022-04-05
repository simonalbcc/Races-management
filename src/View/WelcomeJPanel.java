package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class WelcomeJPanel extends JPanel {
    private JLabel welcomeLabel;
    private ButtonJPanel buttons;

    public WelcomeJPanel(){
        this.setLayout(new BorderLayout());

        welcomeLabel = new JLabel("<html> <h1> Hello racer </h1> </html>");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        buttons = new ButtonJPanel();


        this.add(welcomeLabel, BorderLayout.NORTH);
        //this.add(new JLabel("<html> <h2> voiture </h2> </html>"),BorderLayout.CENTER);
        this.add(buttons,BorderLayout.SOUTH);

    }


    private class ButtonJPanel extends JPanel{
        private JButton pilotsButton, rankingButton, carsButton;

            public ButtonJPanel(){
            this.setLayout(new FlowLayout());

            pilotsButton = new JButton("Show pilots");
            pilotsButton.addActionListener(new ButtonListener());
            rankingButton = new JButton("Show ranking");
            carsButton = new JButton("Show cars");

            this.add(pilotsButton);
            this.add(rankingButton);
            this.add(carsButton);
        }
    }
    private class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            WelcomeJPanel.this.removeAll();
            if(e.getSource() == buttons.pilotsButton){
                WelcomeJPanel.this.add(new AccidentsPanel());
            }
            WelcomeJPanel.this.validate();
        }
    }
}
