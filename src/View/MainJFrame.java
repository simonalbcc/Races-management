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
    private JMenu applicationMenu, viewMenu, helpMenu, drivers, research;
    private JMenuItem close, stopAnimation, shortcuts, contactsInfos, addDriver, removeDriver, modifyDriver, showDriverList;
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
        drivers = new JMenu("Driver");
        research = new JMenu("Research");
        viewMenu = new JMenu("View");
        helpMenu = new JMenu("Help");

        menuBar.add(applicationMenu);
        menuBar.add(drivers);
        menuBar.add(research);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);

        close = new JMenuItem("Close");
        close.addActionListener(new menuItemListner());

        stopAnimation = new JMenuItem("Stop Animation");

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
        showDriverList.addActionListener(new menuItemListner());;



        drivers.add(addDriver);
        drivers.add(removeDriver);
        drivers.add(showDriverList);
        drivers.add(modifyDriver);

        applicationMenu.add(close);

        viewMenu.add(stopAnimation);

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
            MainJFrame.this.validate();
        }
    }
}
