package controller;

import GUI.GUIMain;
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
    private GUIMain view;

    private boolean showAllPressed;

    public Controller(UserDAO dao, GUIMain view) {
        this.dao = dao;
        this.view = view;

        //add the listeners to the GUI elements
        view.addShowAllListener(new ShowAllListener());
        view.addInsertListener(new InsertListener());
        view.addUpdateListener(new UpdateListener());
        view.addDeleteByIdListener(new DeleteByIdListener());
        view.addUpdateRTListener(new UpdateRTListener());

        showAllPressed = false;
    }

    /**
     * Help method to update the view
     */
    private void updateAll(){
        view.updateAll(getAllUsers());
    }

    private List<User> getAllUsers(){
        return dao.findAll();
    }

    private boolean removeUserById(int id){
        return dao.removeUserById(id);
    }


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
                    view.updateShowAll(getAllUsers());
                    showAllPressed = true;
                }
            }
        }

        private class InsertListener implements ActionListener{
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
                int id = view.getRemoveId();
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
