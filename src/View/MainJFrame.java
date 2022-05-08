//region packages & imports
package View;

import DataAccess.RacesDataAccess;
import DataAccess.SingletonConnexion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
//endregion

public class MainJFrame extends JFrame {
    private JMenuBar menuBar;
    private JMenu applicationMenu, helpMenu, driversMenu, researchMenu;
    private JMenuItem close, shortcuts, contactsInfos, addDriver, removeDriver, modifyDriver, showDriverList, researchAccident, researchCars, researchRanking;
    private Container frameContainer;

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

        applicationMenu = new JMenu("Application");
        driversMenu = new JMenu("Pilotes");
        researchMenu = new JMenu("Recherches");
        helpMenu = new JMenu("Aide");

        menuBar.add(applicationMenu);
        menuBar.add(driversMenu);
        menuBar.add(researchMenu);
        menuBar.add(helpMenu);

        close = new JMenuItem("Fermeture");
        close.addActionListener(new menuItemListner());

        shortcuts = new JMenuItem("Raccourcis");

        contactsInfos = new JMenuItem("Contact ");
        contactsInfos.addActionListener(new menuItemListner());

        addDriver = new JMenuItem("Ajouter");
        addDriver.addActionListener(new menuItemListner());

        removeDriver = new JMenuItem("Supprimer");
        removeDriver.addActionListener(new menuItemListner());

        modifyDriver = new JMenuItem("Modifier un pilote");
        modifyDriver.addActionListener(new menuItemListner());

        showDriverList = new JMenuItem("Afficher la liste des pilotes");
        showDriverList.addActionListener(new menuItemListner());

        researchAccident = new JMenuItem("Recherche d'accident");
        researchAccident.addActionListener(new menuItemListner());

        researchCars = new JMenuItem("Recherche d'une voiture");
        researchCars.addActionListener(new menuItemListner());

        researchRanking = new JMenuItem("Recherche d'un classement");
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

        frameContainer.add(new WelcomeJPanel());
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
                frameContainer.add(new DriverForm(frameContainer));
            }
            if(actionEvent.getSource() == removeDriver){

            }
            if(actionEvent.getSource() == modifyDriver){

            }
            if(actionEvent.getSource() == showDriverList){
                frameContainer.add(new DriverJTable());
            }
            if(actionEvent.getSource() == researchAccident){
                frameContainer.add(new AccidentsResearchJPanel(frameContainer));
            }
            if(actionEvent.getSource() == researchCars){
                frameContainer.add(new CarsResearchJPanel(frameContainer));
            }
            if(actionEvent.getSource() == researchRanking){
                frameContainer.add(new RankingJPanel(frameContainer));
            }
            frameContainer.repaint();
            frameContainer.validate();
        }
    }
}
