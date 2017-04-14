package controller;

import GUI.GUIMain;
import model.User;
import model.UserDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Martin on 2017-04-10.
 *
 * Controller class to connect the model and the view,
 * adds actionlisteners to the different GUI elements
 *
 */
public class Controller{
    private UserDAO dao;
    private GUIMain view;

    private boolean showAllPressed;

    public Controller(UserDAO dao, GUIMain view) {
        this.dao = dao;
        this.view = view;

        view.addShowAllListener(new ShowAllListener());
        view.addInsertListener(new InsertListener());
        view.addUpdateListener(new UpdateListener());
        view.addDeleteByIdListener(new DeleteByIdListener());
        //addListenersToGUI();

        showAllPressed = false;
    }

    private void updateAll(){
        view.updateAll(getAllUsers());
    }

    private List<User> getAllUsers(){
        return dao.findAll();
    }

    private boolean removeUserById(int id){
        return dao.removeUserById(id);
    }

    public User getLatestAdded(){
        return dao.getLatestAddedUser();
    }

    private boolean insertNewUser(User user){
        return dao.addUser(user);
    }

    public void sayHi(){
        System.out.println("Hi");
    }

    ///// INNER LISTENER CLASSES /////
        class ShowAllListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!showAllPressed){
                    view.updateShowAll(getAllUsers());
                    showAllPressed = true;
                }
            }
        }

        class InsertListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                User u = view.createUserByInsert();
                if(u!=null){
                    if(insertNewUser(u)){
                        System.out.println("Successfully inserted new User");
                        view.updateLatestAdded(u);
                    }
                }
            }
        }

        class UpdateListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAll();
            }
        }

        class DeleteByIdListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("pressed delete by id");
                int id = view.getRemoveId();
                if(removeUserById(id)){
                    System.out.println("Successfully removed");
                    updateAll();
                }
            }
        }
}
