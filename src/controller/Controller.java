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
        addListenersToGUI();

        showAllPressed = false;
    }

    private List<User> getAllUsers(){
        return dao.findAll();
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

    private void addListenersToGUI(){
        view.getShowAllItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!showAllPressed){
                    view.updateShowAll(getAllUsers());
                    showAllPressed = true;
                }
            }
        });
        view.getInsertItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User u = view.createUserByInsert();
                if(u!=null){
                    if(insertNewUser(u)){
                        System.out.println("Successfully inserted new User");
                        view.updateLatestAdded(u); //Update the view with new user
                    }
                }
            }
        });
        view.getUpdateItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.updateAll(getAllUsers());
            }
        });
        view.getDeleteByIdItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("pressed delete by id");
            }
        });

    }
}
