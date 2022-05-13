package Utility;

import View.ButtonsPanel;
import View.WelcomeJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinaleJPanel extends JPanel {
    private JLabel title;
    private ButtonsPanel buttonsFinalePanel;
    private GridBagConstraints gcFinalePanel;
    private Container mainContainer;
    private JPanel panel;

    public FinaleJPanel(Container mainContainer, JPanel panelResearch){
            // set mainContainer & panel received
            this.mainContainer = mainContainer;
            this.panel = panelResearch;

            // set title
            title = new JLabel("<html> <u> Que voulez-vous faire? </u> </html>");
            title.setHorizontalAlignment(SwingConstants.CENTER);

            // set layout
            this.setLayout(new GridBagLayout());
            gcFinalePanel = new GridBagConstraints();

            // set buttons panel
            buttonsFinalePanel = new ButtonsPanel("Retour au menu principal", "Recommencer");
            buttonsFinalePanel.addActionListener(new FinaleButtonsListener());

            // add components
            this.add(title, gcFinalePanel);
            gcFinalePanel.gridy = 1;
            this.add(buttonsFinalePanel, gcFinalePanel);

        }


    private class FinaleButtonsListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainContainer.removeAll();
                if(e.getSource() == buttonsFinalePanel.getBack()){
                    mainContainer.add(new WelcomeJPanel());
                }
                if(e.getSource() == buttonsFinalePanel.getNext()){
                    mainContainer.add(panel);
                }
                mainContainer.repaint();
                mainContainer.validate();
            }
        }

    }