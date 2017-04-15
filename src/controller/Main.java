package controller;

import GUI.GUIMain;
import model.ReadConfig;
import model.SqlUserDAOImplementation;
import model.UserDAO;

/**
 * Created by Martin on 2017-04-10.
 *
 * Initialize classes with MVC-pattern
 */
public class Main {
    public static void main(String[] args) {
        UserDAO model = new SqlUserDAOImplementation();
        GUIMain view = new GUIMain("SQL database Visualizeeerr of doom");

        Controller controller = new Controller(model, view);

    }
}
