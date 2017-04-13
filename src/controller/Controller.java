package controller;

import model.User;
import model.UserDAO;
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
        return dao.getLatestAddedUser();
    }

    public boolean insertNewUser(User user){
        return dao.addUser(user);
    }

    public void sayHi(){
        System.out.println("Hi");
    }
}
