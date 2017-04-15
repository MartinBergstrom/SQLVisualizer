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
        menuBar = new MenuBar();
        setJMenuBar(menuBar);

        add(mainPanel);

        setVisible(true);
    }

    public MenuBar getMenubar(){
        return menuBar;
    }

    public MainPanel getMainPanel(){
        return mainPanel;
    }
}