package GUI;

import controller.Controller;
import model.User;
import org.omg.PortableInterceptor.ACTIVE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
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
    public int getRemoveId() {
        return menuBar.getRemoveId();
    }
    public void updateShowAll(List<User> users){
        menuBar.showAll(users);
    }
    public void updateAll(List<User> users){
        menuBar.callUpdateWhole(users);
    }
    public void updateLatestAdded(User newUser){
        menuBar.callUpdateLatest(newUser);
    }

    public User createUserByInsert(){
        return menuBar.createNewInsertUser();
    }

    public void addInsertListener(ActionListener il){
        menuBar.addInsertListener(il);
    }

    public void addShowAllListener(ActionListener sal) {
        menuBar.addShowAllListener(sal);
    }

    public void addUpdateListener(ActionListener ul){
        menuBar.addUpdateListener(ul);
    }
    public void addDeleteByIdListener(ActionListener dbyl){
        menuBar.addDeleteByIdListener(dbyl);
    }
}