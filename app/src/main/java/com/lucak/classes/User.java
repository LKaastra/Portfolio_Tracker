package com.lucak.classes;

/**
 * Created by Lucak on 9/28/2017.
 */

public class User {
    private int user_Id;
    private String user_Name;
    private String password;
    private String email;
    private String firstName;
    private String lastName;


    public User(int user_Id, String user_Name, String password, String email, String firstName, String lastName){
        this.user_Id = user_Id;
        this.user_Name = user_Name;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String user_Name, String password){
        this.user_Name = user_Name;
        this.password = password;
    }
    public User() {
        this.user_Id = 0;
        this.user_Name = "";
        this.password = "";
        this.email = "";
    }

    public User(String user_Name, String password, String email){
        this.user_Id = 0;
        this.user_Name = user_Name;
        this.password = password;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(int user_Id) {
        this.user_Id = user_Id;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




}
