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
import java.util.*;
import java.util.List;

/**
 * Created by Martin on 2017-04-12.
 *
 *
 */
public class MenuBar extends JMenuBar implements MenuListener{
    private JMenu menuExit;

    private JMenuItem showAllItem;
    private JMenuItem insertItem;
    private JMenuItem updateItem;
    private JMenuItem deleteByIdItem;

    private MainPanel mainPanel;

    public MenuBar(MainPanel mainp) {
        this.mainPanel = mainp;

        JMenu menuDatabase = new JMenu("Database");
        menuDatabase.addMenuListener(this);
        add(menuDatabase);
        add(Box.createHorizontalGlue());

        menuExit = new JMenu("Exit");
        menuExit.setMnemonic(KeyEvent.VK_X);
        menuExit.addMenuListener(this);
        add(menuExit);

        showAllItem = new JMenuItem("Show All");
        menuDatabase.add(showAllItem);

        insertItem = new JMenuItem("Insert Data");
        menuDatabase.add(insertItem);


        updateItem = new JMenuItem("Update");
        menuDatabase.add(updateItem);

        JMenu submenu = new JMenu("Delete..");
        menuDatabase.add(submenu);

        deleteByIdItem = new JMenuItem("Delete Data By Id");
        submenu.add(deleteByIdItem);

    }

    public void showAll(java.util.List<User> users){
        mainPanel.addUsers(users);
    }

    public User createNewInsertUser(){
        User u = null;
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
        if (option == JOptionPane.OK_OPTION) {
            String id = field1.getText();
            String fname = field2.getText();
            String sname = field3.getText();
            String city = field4.getText();
            String age = field5.getText();
            u = new User(Integer.parseInt(id), fname, sname, city, Integer.parseInt(age));
        }
        return u;
    }

    public int getRemoveId() {
        return Integer.parseInt(JOptionPane.showInputDialog("Enter id of the user to remove"));
    }

    public void callUpdateWhole(List<User> users){
        mainPanel.clearTable();
        showAll(users);
    }


    public void callUpdateLatest(User newU){
        mainPanel.addRow(newU);
    }

    @Override
    public void menuSelected(MenuEvent me) {
        if(me.getSource().equals(menuExit)){
            System.exit(0);
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {}

    @Override
    public void menuCanceled(MenuEvent e) {}

    public void addShowAllListener(ActionListener sal) {
        showAllItem.addActionListener(sal);
    }
    public void addInsertListener(ActionListener il) {
        insertItem.addActionListener(il);
    }
    public void addUpdateListener(ActionListener ul){
        updateItem.addActionListener(ul);
    }
    public void addDeleteByIdListener(ActionListener dbyl){
        deleteByIdItem.addActionListener(dbyl);
    }
}
