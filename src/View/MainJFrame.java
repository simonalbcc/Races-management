//region packages & imports
package View;

import javax.swing.*;
import javax.swing.event.MenuListener;
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

    public MainJFrame(){
        super("Race Application");
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
        driversMenu = new JMenu("Driver");
        researchMenu = new JMenu("Research");
        helpMenu = new JMenu("Help");

        menuBar.add(applicationMenu);
        menuBar.add(driversMenu);
        menuBar.add(researchMenu);
        menuBar.add(helpMenu);

        close = new JMenuItem("Close");
        close.addActionListener(new menuItemListner());

        shortcuts = new JMenuItem("Shortcuts");

        contactsInfos = new JMenuItem("Contacts Infos");
        contactsInfos.addActionListener(new menuItemListner());

        addDriver = new JMenuItem("Add");
        addDriver.addActionListener(new menuItemListner());

        removeDriver = new JMenuItem("Remove");
        removeDriver.addActionListener(new menuItemListner());

        modifyDriver = new JMenuItem("Modify a driver");
        modifyDriver.addActionListener(new menuItemListner());

        showDriverList = new JMenuItem("Show driver list");
        showDriverList.addActionListener(new menuItemListner());

        researchAccident = new JMenuItem("Research Accident");
        researchAccident.addActionListener(new menuItemListner());

        researchCars = new JMenuItem("Research Cars");
        researchCars.addActionListener(new menuItemListner());

        researchRanking = new JMenuItem("Research Ranking");
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
                frameContainer.add(new ContactsInfosJPanel());
            }
            if(actionEvent.getSource() == addDriver){
                frameContainer.add(new DriverForm());
            }
            if(actionEvent.getSource() == removeDriver){

            }
            if(actionEvent.getSource() == modifyDriver){

            }
            if(actionEvent.getSource() == showDriverList){

            }
            if(actionEvent.getSource() == researchAccident){
                frameContainer.add(new AccidentsJPanel());
            }
            if(actionEvent.getSource() == researchCars){

            }
            if(actionEvent.getSource() == researchRanking){
                frameContainer.add(new RankingJPanel());
            }
            MainJFrame.this.validate();
        }
    }
}
