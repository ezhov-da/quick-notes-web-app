package ru.ezhov;

/**
 *
 * @author deonisius
 */
public class User {

    private int id;
    private String email;
    private String pass;

    public User(int id, String email, String pass) {
        this.id = id;
        this.email = email;
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public int getId() {
        return id;
    }

}
