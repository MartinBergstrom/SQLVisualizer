package GUI;

import model.User;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by Martin on 2017-04-12.
 *
 * Class to show the MenuBar
 *
 */
public class MenuBar extends JMenuBar implements MenuListener{
    private JMenu menuExit;
    private JMenu menuUpdateThread;
    //Items in the Database menu
    private JMenuItem showAllItem;
    private JMenuItem insertItem;
    private JMenuItem updateItem;
    private JMenuItem deleteByIdItem;
    //Items in the update menu
    private JMenuItem startRTUpdate;

    public MenuBar() {
        JMenu menuDatabase = new JMenu("Database");
        menuDatabase.addMenuListener(this);
        add(menuDatabase);

        menuUpdateThread = new JMenu("RT Update");
        add(menuUpdateThread);

        //add filler to set exit menu in right corner
        add(Box.createHorizontalGlue());

        menuExit = new JMenu("Exit");
        menuExit.setMnemonic(KeyEvent.VK_X);
        menuExit.addMenuListener(this);
        add(menuExit);

        //Menu items for database//
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
        // / / / / / / / / / / / / / /

        //Menu items for Update
        startRTUpdate = new JMenuItem("Start RT update");
        menuUpdateThread.add(startRTUpdate);
        // / / / / / / / / / / / / / /

    }

    /**
     * JOptionPane pop-up to let the user add a new user to the database by filling in allt the information required
     *
     * @return the newly created user or null if the a field is empty or cancel button was pressed
     */
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
            //check that all fields are valid
            if(!id.equals("") && !fname.equals("") && !sname.equals("") && !city.equals("") &&!age.equals("")){
                u = new User(Integer.parseInt(id), fname, sname, city, Integer.parseInt(age));
            }else{
                System.out.println("ERROR, NOT ALL FIELDS WERE SUBMITTED PROPERLY");
                JOptionPane.showMessageDialog(this,"Error, validate inputs in the fields and try again. " +
                        "All fields must be submitted");
            }
        }
        return u;
    }

    public int getRemoveId() {
        return Integer.parseInt(JOptionPane.showInputDialog("Enter id of the user to remove"));
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

    ///// Methods to add the listeners to the different menu items /////
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
    public void addUpdateRTListener(ActionListener urtl){
        startRTUpdate.addActionListener(urtl);
    }
}
