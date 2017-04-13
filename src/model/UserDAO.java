package model;

import java.util.List;

/**
 * Created by Martin on 2017-04-10.
 *
 * This DAO is used to seperate the implementation of information retrieving from the rest
 * You could implement this with a class that works with reading/writing to files etc..
 */
public interface UserDAO {

    public List<User> findAll();
    public boolean addUser(User user);
    public User getLatestAddedUser();
    public boolean checkUpdate();
    public boolean removeUserById();

    public boolean updateUserByName(String username);
    public boolean updateUserById(int id);
}
