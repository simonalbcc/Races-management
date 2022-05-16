package View;


import Controller.Controller;

import javax.swing.*;
import java.awt.*;

public class AddDriverRanking extends JPanel {
    private JComboBox driversComboBox, teamsComboBox, carsComboBox, circuitsComboBox, racesDatesComboBox, rankingComboBox;
    private Controller controller;
    private ButtonsPanel buttonsPanel;
    private GridBagConstraints gc;

    public AddDriverRanking(Container mainContainer) throws Exception {
        this.setLayout(new GridBagLayout());
        this.controller = new Controller();
        this.buttonsPanel = new ButtonsPanel();
        this.gc = new GridBagConstraints();
        this.gc.insets = new Insets(5,5,5,5);

        this.driversComboBox = new JComboBox(controller.getAllDrivers().stream().map(d -> d.getLastNameFirstName()).toArray());
        this.teamsComboBox = new JComboBox(controller.getAllTeams().stream().map(t -> t.getName()).toArray());

        gc.gridy = 0;
        this.add(driversComboBox,gc);
        this.add(teamsComboBox,gc);

        gc.gridy = 1;
        this.add(buttonsPanel,gc);
    }







}
