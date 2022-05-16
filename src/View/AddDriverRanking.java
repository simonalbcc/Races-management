package View;


import Controller.Controller;

import javax.swing.*;
import java.awt.*;

public class AddDriverRanking extends JPanel {
    private JComboBox driversComboBox, teamsComboBox, carsComboBox, circuitsComboBox, racesDatesComboBox, rankingComboBox;
    private Controller controller;
    private ButtonsPanel buttonsPanel;
    private GridBagConstraints gc;

    public AddDriverRanking(Container mainContainer){
        this.setLayout(new GridBagLayout());
        this.controller = new Controller();
        this.gc = new GridBagConstraints();

        this.driversComboBox = new JComboBox(controller.getAllDriversNames().toArray());
        try{
            this.teamsComboBox = new JComboBox(controller.getAllTeamsNames().toArray());
        } catch (Exception exception){
            exception.getMessage();
        }

        this.buttonsPanel = new ButtonsPanel();

        gc.gridy = 0;
        this.add(driversComboBox,gc);
        this.add(teamsComboBox,gc);

        gc.gridy = 1;
        this.add(buttonsPanel,gc);
    }

}
