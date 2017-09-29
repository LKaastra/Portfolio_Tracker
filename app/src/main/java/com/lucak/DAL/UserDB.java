package com.lucak.DAL;

import com.lucak.classes.User;

import java.util.ArrayList;

/**
 * Created by Lucak on 9/28/2017.
 */

//to be re written when adding a database

public class UserDB {
    private static ArrayList<User> userList = new ArrayList<User>();
    private static int counter = 1;

    public static void AddUser(User user){
        user.setUser_Id(counter);
        userList.add(user);
        stepCount();
    }

    private static void stepCount(){
        counter++;
    }

    public static int compareUser(User user){
        int verified = 0;
        for (User u : userList) {
            if (user.getUser_Name().equals(u.getUser_Name()) && user.getPassword().equals(u.getPassword())){
                verified = 1;
            }

        }
        return verified;
    }



}
