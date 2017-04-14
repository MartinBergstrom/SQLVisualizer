package GUI;

import controller.Controller;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Martin on 2017-04-10.
 *
 * The main JFrame window using all the GUI classes
 */
public class GUIMain extends JFrame{
    private MainPanel mainPanel;
    private MenuBar menuBar;

    public GUIMain(String title){
        super(title);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500,400);
        setLayout(new FlowLayout());

        mainPanel = new MainPanel(new FlowLayout());
        menuBar = new MenuBar(mainPanel);
        setJMenuBar(menuBar);

        add(mainPanel);

        setVisible(true);
    }

    public User createUserByInsert(){
        return menuBar.createNewInsertUser();
    }
    public void updateLatestAdded(User newUser){
        menuBar.callUpdateLatest(newUser);
    }

    public JMenuItem getShowAllItem(){
        return menuBar.getShowAllItem();
    }

    public JMenuItem getInsertItem(){
        return menuBar.getInsertItem();
    }

    public JMenuItem getUpdateItem(){
        return menuBar.getUpdateItem();
    }

    public JMenuItem getDeleteByIdItem(){
        return menuBar.getDeleteByIdItem();
    }

    public void updateShowAll(List<User> users){
        menuBar.showAll(users);
    }
    public void updateAll(List<User> users){
        menuBar.callUpdateWhole(users);
    }
}
