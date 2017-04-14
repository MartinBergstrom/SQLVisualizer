package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 2017-04-10.
 *
 * Retrieve data from SQL database
 */
public class SqlUserDAOImplementation implements UserDAO {
    private static final String TABLE_USERS = "monsterdatabas.dbo.users";

    private DBConnector dbConnector;
    private Connection connect;
    private Statement stmt;


    public SqlUserDAOImplementation() {
        //  new ReadConfig();
        dbConnector = new DBConnector();
        stmt = null;
        try {
             connect = dbConnector.getConnection();
            stmt = connect.createStatement();
            // findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ResultSet executeQueryGetResultset(String query){
        ResultSet rs = null;
        try{
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


    @Override
    public List<User> findAll() {
        ArrayList<User> users = new ArrayList<>();

        try {
            String sql = "SELECT * FROM " + TABLE_USERS;
            ResultSet rs  = executeQueryGetResultset(sql);

            while(rs.next()) {
                users.add( createUserFromRS(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     *
     * @param user
     * @return true if the user was added successfully
     */
    @Override
    public boolean addUser(User user) {
        System.out.println(user.toString());
        String query = "INSERT INTO " + TABLE_USERS +
                " VALUES( " +
                user.getId() + ", " +
                "'"+user.getFirstname() + "', " +
                "'"+user.getSurname() + "', " +
                "'"+user.getCity()+ "', " +
                user.getAge() +
                ");";
        try{
            stmt.execute(query);
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public User getLatestAddedUser() {
        User user = null;
        String query = "SELECT TOP 1 * FROM " + TABLE_USERS +
                " ORDER BY Id DESC;";
        ResultSet rs = executeQueryGetResultset(query);
        try {
            rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        user = createUserFromRS(rs);
        return user;
    }


    /**
     * Help method to create whole User from the resultset
     *
     * @param rs
     * @return the User created from the resultset
     */
    private User createUserFromRS(ResultSet rs){
        User u = null;
        try{
            int id = rs.getInt("Id");
            String firstName = rs.getString("Firstname");
            String lastName = rs.getString("Surname");
            String city = rs.getString("City");
            int age = rs.getInt("age");
            u = new User(id,firstName,lastName,city,age);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public boolean removeUserById(int id) {
        String query = "DELETE FROM " + TABLE_USERS +
                " WHERE Id=" + id + ";";
        try{
            stmt.execute(query);
        }catch (SQLException e){
            return false;
        }
        return true;
    }

    //use thread here to listen to changes at certain intervals like once each 20 sec?
    //how to detect change?
    @Override
    public boolean checkUpdate() {
//        Runnable r = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("do shit");
//            }
//        };
        return false;
    }
}
