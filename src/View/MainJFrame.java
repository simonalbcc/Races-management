//region packages & imports
package View;

import DataAccess.SingletonConnexion;
import Utility.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//endregion

public class MainJFrame extends JFrame {
    //region privates attributes & constructor
    private JMenuBar menuBar;
    private JMenu applicationMenu, helpMenu, driversMenu, researchMenu , rankingManagementMenu;
    private JMenuItem close, contactsInfos, addDriver, removeDriver, modifyDriver, showDriverList, researchAccident, researchCars, researchRanking, rankingManagement;
    private Container frameContainer;

    public MainJFrame(){
        // basic init
        super("Application de gestion de courses automobiles");
        addWindowListener(new WindowAdapter() {
                              public void windowClosing(WindowEvent e) {
                                  System.exit(0);
                              }
                          }
        );
        setBounds(10,10,1100,1000);

        frameContainer = this.getContentPane();
        this.setResizable(false);

        //region menuBar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        applicationMenu = new JMenu("Application");
        driversMenu = new JMenu("Pilotes");
        researchMenu = new JMenu("Recherches");
        helpMenu = new JMenu("Aide");
        rankingManagementMenu = new JMenu("Gestion d'un classement");


        // add to bar menu
        menuBar.add(applicationMenu);
        menuBar.add(driversMenu);
        menuBar.add(researchMenu);
        menuBar.add(helpMenu);
        menuBar.add(rankingManagementMenu);

        // add to menu
        close = new JMenuItem("Fermeture");
        close.addActionListener(new MenuItemListner());

        contactsInfos = new JMenuItem("Contact ");
        contactsInfos.addActionListener(new MenuItemListner());

        addDriver = new JMenuItem("Ajouter");
        addDriver.addActionListener(new MenuItemListner());

        removeDriver = new JMenuItem("Supprimer");
        removeDriver.addActionListener(new MenuItemListner());

        modifyDriver = new JMenuItem("Modifier un pilote");
        modifyDriver.addActionListener(new MenuItemListner());

        showDriverList = new JMenuItem("Afficher la liste des pilotes");
        showDriverList.addActionListener(new MenuItemListner());

        researchAccident = new JMenuItem("Recherche d'accident");
        researchAccident.addActionListener(new MenuItemListner());

        researchCars = new JMenuItem("Recherche d'une voiture");
        researchCars.addActionListener(new MenuItemListner());

        researchRanking = new JMenuItem("Recherche d'un classement");
        researchRanking.addActionListener(new MenuItemListner());

        rankingManagement = new JMenuItem("Ajout d'un pilote à un classement");
        rankingManagement.addActionListener(new MenuItemListner());


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

        helpMenu.add(contactsInfos);

        rankingManagementMenu.add(rankingManagement);
        //endregion

        // add to window
        frameContainer.add(new WelcomeJPanel());
        setVisible(true);
    }

    //region inner classe 
    private class MenuItemListner implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            try{
                JPanel currentPanel = new JPanel();
                if(actionEvent.getSource() == close){
                    SingletonConnexion.getInstance().close();
                    System.exit(0);
                }
                if(actionEvent.getSource() == contactsInfos){
                    currentPanel = new ContactsInfosJPanel(frameContainer);
                }
                if(actionEvent.getSource() == addDriver){
                    currentPanel = new AddDriver(frameContainer);
                }
                if(actionEvent.getSource() == removeDriver){
                    currentPanel = new RemoveDriver(frameContainer);
                }
                if(actionEvent.getSource() == modifyDriver){
                    currentPanel = new ModifyJPanel(frameContainer);
                }
                if(actionEvent.getSource() == showDriverList){
                    currentPanel = new DriverJTable(frameContainer);
                }
                if(actionEvent.getSource() == researchAccident){
                    currentPanel = new ResearchAccidentsJPanel(frameContainer);
                }
                if(actionEvent.getSource() == researchCars){
                    currentPanel = new ResearchCarJPanel(frameContainer);
                }
                if(actionEvent.getSource() == researchRanking){
                    currentPanel = new ResearchRankingJPanel(frameContainer);
                }
                if(actionEvent.getSource() == rankingManagement){
                    currentPanel = new AddDriverRanking(frameContainer);
                }
                Utils.addToMainContainer(frameContainer, currentPanel);
            }catch (Exception exception){
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
