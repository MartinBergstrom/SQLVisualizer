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
 * The view has an instance of the controller and the controller acts on the model
 */
public class Controller{
    private UserDAO dao;

    public Controller(UserDAO dao) {
        this.dao = dao;
    }

    public List<User> getAllUsers(){
        return dao.findAll();
    }

    public User getLatestAdded(){
        User u = dao.getLatestAddedUser();
        System.out.println(u.toString());
        return u;
    }

    public boolean insertNewUser(User user){
        return dao.addUser(user);
    }

    public void sayHi(){
        System.out.println("Hi");
    }
}
