package GUI;

import controller.Controller;
import model.User;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by Martin on 2017-04-12.
 *
 *
 */
public class MenuBar extends JMenuBar implements MenuListener,ActionListener{
    private JMenu menuExit;

    private JMenuItem showAllItem;
    private JMenuItem insertItem;
    private JMenuItem updateItem;
    private JMenuItem deleteItem;

    private Controller controller;
    private MainPanel mainPanel;
    private boolean showAllPressed;

    public MenuBar(Controller c, MainPanel mainp) {
        this.controller = c;
        this.mainPanel = mainp;
        showAllPressed = false;

        JMenu menuDatabase = new JMenu("Database");
        menuDatabase.addMenuListener(this);
        add(menuDatabase);

//        JMenu filler = new JMenu("");
//        filler.setPreferredSize(new Dimension(360,0));
//        filler.setFocusable(false);
//        filler.setEnabled(false);
//        add(filler);
        //align the Exit menu to the top right corner
        add(Box.createHorizontalGlue());

        menuExit = new JMenu("Exit");
        menuExit.setMnemonic(KeyEvent.VK_X);
        menuExit.addMenuListener(this);
        add(menuExit);
        ButtonGroup group = new ButtonGroup();

        showAllItem = new JMenuItem("Show All");
        showAllItem.addActionListener(this);
        menuDatabase.add(showAllItem);

        insertItem = new JMenuItem("Insert Data");
        insertItem.addActionListener(this);
        menuDatabase.add(insertItem);

        deleteItem = new JMenuItem("Delete Data");
        deleteItem.addActionListener(this);
        menuDatabase.add(deleteItem);

        updateItem = new JMenuItem("Update");
        updateItem.addActionListener(this);
        menuDatabase.add(updateItem);

    }


    @Override
    public void menuSelected(MenuEvent me) {
        if(me.getSource().equals(menuExit)){
            System.exit(0);
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(showAllItem) && !showAllPressed){
            mainPanel.addUsers(controller.getAllUsers());
            showAllPressed = true;
        }else if(e.getSource().equals(insertItem)){
            JTextField field1 = new JTextField();
            JTextField field2 = new JTextField();
            JTextField field3 = new JTextField();
            JTextField field4 = new JTextField();
            JTextField field5 = new JTextField();
            Object[] message = {
                    "Input Id:", field1,
                    "Input Firstname:", field2,
                    "Input Surname:", field3,
                    "Input City:", field4,
                    "Input Age:", field5,
            };
            int option = JOptionPane.showConfirmDialog(this,message,
                    "Enter all your values", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION)
            {
                String id = field1.getText();
                String fname = field2.getText();
                String sname = field3.getText();
                String city = field4.getText();
                String age = field5.getText();
                User u = new User(Integer.parseInt(id),fname,sname,city, Integer.parseInt(age));
                //Calls method to insert into datbase
                if(!controller.insertNewUser(u)){
                    System.out.println("Could not add user, try again and check values");
                }
            }
            callUpdateLatest();
        }else if(e.getSource().equals(updateItem)){
            System.out.println("Update the database");
            callUpdateWhole();
        }else if(e.getSource().equals(deleteItem)){
            System.out.println("delete dis shit");
        }
    }

    /**
     * Would be expensive operation if you would have a large database? Instead of just getting the new row
     */
    private void callUpdateWhole(){
        mainPanel.clearTable();
        mainPanel.addUsers(controller.getAllUsers());
    }

    private void callUpdateLatest(){
        mainPanel.addRow(controller.getLatestAdded());
    }
}
