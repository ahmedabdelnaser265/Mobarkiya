package com.hardtask.testmobarkiya.models;

/**
 * Created by it_ah on 28/03/2019.
 */

public class UserDataModel
{
    Integer userID ;

    public UserDataModel(Integer userID) {
        this.userID = userID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
