package GUI;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Martin on 2017-04-10.
 *
 * The main JFrame window using all the GUI classes
 */
public class GUIMain extends JFrame{
    private Controller controller;
    private MainPanel mainPanel;
    private MenuBar menuBar;

    public GUIMain(String title, Controller c){
        super(title);
        this.controller = c;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500,400);
        setLayout(new FlowLayout());

        mainPanel = new MainPanel(new FlowLayout());
        menuBar = new MenuBar(controller, mainPanel);
        setJMenuBar(menuBar);

        add(mainPanel);

        setVisible(true);
    }
}
