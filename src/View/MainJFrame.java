//region packages & imports
package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//endregion

public class MainJFrame extends JFrame {
    private JMenuBar menuBar;
    private JMenu applicationMenu, helpMenu, driversMenu, researchMenu;
    private JMenuItem close, shortcuts, contactsInfos, addDriver, removeDriver, modifyDriver, showDriverList, researchAccident, researchCars, researchRanking;
    private Container frameContainer;
    private DriverJTable driverJTable;

    public MainJFrame(){
        super("Application de gestion de courses automobiles");
        addWindowListener(new WindowAdapter() {
                              public void windowClosing(WindowEvent e) {
                                  System.exit(0);
                              }
                          }
        );
        setBounds(10,10,1000,1000);

        frameContainer = this.getContentPane();

//region menuBar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        applicationMenu = new JMenu("<html> <u>A</u>pplication </html> ");
        driversMenu = new JMenu("<html> <u>P</u>ilotes </html>");
        researchMenu = new JMenu("<html> <u>R</u>echerches </html>");
        helpMenu = new JMenu("<html> <u>A</u>ide </html>");

        menuBar.add(applicationMenu);
        menuBar.add(driversMenu);
        menuBar.add(researchMenu);
        menuBar.add(helpMenu);

        close = new JMenuItem("<html> <u>F</u>ermeture </html>");
        close.addActionListener(new menuItemListner());

        shortcuts = new JMenuItem("<html> <u>R</u>accourcis </html>");

        contactsInfos = new JMenuItem("<html> <u>C</u>ontact </html>");
        contactsInfos.addActionListener(new menuItemListner());

        addDriver = new JMenuItem("<html> <u>A</u>jouter </html>");
        addDriver.addActionListener(new menuItemListner());

        removeDriver = new JMenuItem("<html> <u>S</u>upprimer </html>");
        removeDriver.addActionListener(new menuItemListner());

        modifyDriver = new JMenuItem("<html> <u>M</u>odifier un pilote </html>");
        modifyDriver.addActionListener(new menuItemListner());

        showDriverList = new JMenuItem("<html> <u>A</u>fficher la liste des pilotes </html>");
        showDriverList.addActionListener(new menuItemListner());

        researchAccident = new JMenuItem("<html> <u>R</u>echerche d'accident </html>");
        researchAccident.addActionListener(new menuItemListner());

        researchCars = new JMenuItem("<html> <u>R</u>echerche d'une voiture </html>");
        researchCars.addActionListener(new menuItemListner());

        researchRanking = new JMenuItem("<html> <u>R</u>echerche d'un classement </html>");
        researchRanking.addActionListener(new menuItemListner());

        driversMenu.add(addDriver);
        driversMenu.addSeparator();
        driversMenu.add(removeDriver);
        driversMenu.addSeparator();
        driversMenu.add(showDriverList);
        driversMenu.addSeparator();
        driversMenu.add(modifyDriver);

        applicationMenu.add(close);

        researchMenu.add(researchAccident);
        researchMenu.addSeparator();
        researchMenu.add(researchCars);
        researchMenu.addSeparator();
        researchMenu.add(researchRanking);

        helpMenu.add(shortcuts);
        helpMenu.addSeparator();
        helpMenu.add(contactsInfos);
//endregion

        frameContainer.add(new WelcomeJPanel(frameContainer));
        setVisible(true);
        //setResizable(false);
    }

    private class menuItemListner implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            frameContainer.removeAll();
            if(actionEvent.getSource() == close){
                System.exit(0);
            }

            if(actionEvent.getSource() == contactsInfos){
                frameContainer.add(new ContactsInfosJPanel(frameContainer));
            }
            if(actionEvent.getSource() == addDriver){
                frameContainer.add(new DriverForm(frameContainer)); // aura  besoin de driverJTable
            }
            if(actionEvent.getSource() == removeDriver){ // aura  besoin de driverJTable

            }
            if(actionEvent.getSource() == modifyDriver){ // aura  besoin de driverJTable

            }
            if(actionEvent.getSource() == showDriverList){
                driverJTable = new DriverJTable();
                frameContainer.add(driverJTable);
            }
            if(actionEvent.getSource() == researchAccident){
                frameContainer.add(new AccidentsResearchJPanel(frameContainer));
            }
            if(actionEvent.getSource() == researchCars){

            }
            if(actionEvent.getSource() == researchRanking){
                frameContainer.add(new RankingJPanel(frameContainer));
            }
            frameContainer.repaint();
            frameContainer.validate();
        }
    }
}
