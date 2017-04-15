package controller;

import GUI.GUIMain;
import GUI.MainPanel;
import GUI.MenuBar;
import model.User;
import model.UserDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by Martin on 2017-04-10.
 *
 * Controller class to connect the model and the view,
 * adds actionlisteners to the different GUI elements
 *
 */
public class Controller{
    private UserDAO dao;

    //Is this the best way to connect views? all logic to bind views here?
    //Otherwise lots of calls beteween the views and references?
    private GUIMain view;
    private MenuBar menuBar;
    private MainPanel mainPanel;

    private boolean showAllPressed;

    public Controller(UserDAO dao, GUIMain view) {
        this.dao = dao;
        this.view = view;

        menuBar = view.getMenubar();
        mainPanel = view.getMainPanel();

        //add the listeners to the items in MenuBar
        menuBar.addShowAllListener(new ShowAllListener());
        menuBar.addInsertListener(new InsertListener());
        menuBar.addUpdateListener(new UpdateListener());
        menuBar.addDeleteByIdListener(new DeleteByIdListener());
        menuBar.addUpdateRTListener(new UpdateRTListener());

        showAllPressed = false;
    }
    /**
     * Help method to update the view
     */
    private void updateAll(){
        mainPanel.clearTable();
        mainPanel.addUsers(getAllUsers());
    }
    /**
     * Help method to retrieve all the users from the DAO
     *
     * @return a list of all the users currently in the DAO
     */
    private List<User> getAllUsers(){
        return dao.findAll();
    }
    /**
     * Help method to remove a user in the DAO with the specified id
     *
     * @param id id of the user to remove
     * @return true if the user was successfully removed
     */
    private boolean removeUserById(int id){
        return dao.removeUserById(id);
    }
    /**
     * Help method to insert a new user in the DAO
     *
     * @param user the new User to insert
     * @return true if the user was successfully added
     */
    private boolean insertNewUser(User user){
        return dao.addUser(user);
    }
    //maybbe use wait/notify instead of this polling(?)
    private void startUpdateThread(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println("thread running");
                    if(dao.checkUpdate()){
                        updateAll();
                        System.out.println("detected change, called update");
                    }
                    try{
                        Thread.sleep(4000);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(r).start();
    }

    ///// INNER LISTENER CLASSES /////
        private class ShowAllListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!showAllPressed){
                    mainPanel.addUsers(getAllUsers());
                    showAllPressed = true;
                }
            }
        }

        private class InsertListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                User u = menuBar.createNewInsertUser();
                if(u!=null){
                    if(insertNewUser(u)){
                        System.out.println("Successfully inserted new User");
                        //calls the mainpanel to update the view with a new row
                        mainPanel.addRow(u);
                    }
                }
            }
        }

        private class UpdateListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAll();
            }
        }

        private class DeleteByIdListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("pressed delete by id");
                int id = menuBar.getRemoveId();
                if(removeUserById(id)){
                    System.out.println("Successfully removed user with id: " + id);
                    updateAll();
                }
            }
        }

        private class UpdateRTListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                startUpdateThread();
            }
        }
}
