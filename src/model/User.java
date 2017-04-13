package model;

/**
 * User class to represent an User in our database
 */
public class User {
    private int id;
    private String firstname;
    private String surname;
    private String city;
    private int age;

    public User(int id, String firstname, String surname, String city, int age) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.city = city;
        this.age = age;
    }

    public String toString(){
        String res = id + ", " + firstname + ", " + surname + ", " + city + ", " + age;
        return res;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getCity() {
        return city;
    }

    public int getAge(){
        return age;
    }
}
