package org.example.User;

public class User
{
    private static User instance;
    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }
    String login;
    String password;
    String firstName;
    String lastName;
    String pesel;
    int id;
    int admin;
    public String getLogin()
    {
        return login;
    }
    public String getFirstName() {return firstName;}
    public String getLastName()
    {
        return lastName;
    }
    public String getPesel()
    {
        return pesel;
    }
    public int getId()
    {
        return id;
    }
    public String getPassword() {return password;}

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public int getAdmin( ){ return admin;}
}
