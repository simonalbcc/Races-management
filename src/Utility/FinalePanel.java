//region packages & imports
package Utility;

import View.WelcomeJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//endregion

public class FinalePanel extends JPanel {

    private JLabel title;
    private ButtonsJPanel buttonsFinalePanel;
    private GridBagConstraints gcFinalePanel;
    private Container mainContainer;
    private JPanel panel, currentPanel;

    public FinalePanel(Container mainContainer, JPanel panelResearch){
        // set mainContainer & panel received
        this.mainContainer = mainContainer;
        this.panel = panelResearch;

        // set title
        title = new JLabel("<html> <h2> <u> Que voulez-vous faire? </u> </h2> </html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        // set layout
        this.setLayout(new GridBagLayout());
        gcFinalePanel = new GridBagConstraints();

        // set buttons panel
        buttonsFinalePanel = new ButtonsJPanel("Retour au menu principal", "Recommencer");
        buttonsFinalePanel.addActionListener(new FinaleButtonsListener());

        // add components to the main panel
        this.add(title, gcFinalePanel);
        gcFinalePanel.gridy = 1;
        this.add(buttonsFinalePanel, gcFinalePanel);

    }

    private class FinaleButtonsListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == buttonsFinalePanel.getBack()){
                    currentPanel = new WelcomeJPanel();
                }
                if(e.getSource() == buttonsFinalePanel.getNext()){
                    currentPanel = panel;
                }
                Utils.addToMainContainer(mainContainer,currentPanel);
            }
        }

    }