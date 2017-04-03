package com.avdsoft.a403sqlite.models;

/**
 * Created by AVDSOFT on 03.04.2017;
 * project - 403SQLite;
 */

public class UserModel {
    private int userId = 0;
    private String userName;
    private String userLastName;
    private String userAge;
    private String userDescr;
    private String userAddress;

    public UserModel(String userName, String userLastName, String userAge, String userDescr, String userAddress) {
        this.userName = userName;
        this.userLastName = userLastName;
        this.userAge = userAge;
        this.userDescr = userDescr;
        this.userAddress = userAddress;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public String getUserAge() {
        return userAge;
    }

    public String getUserDescr() {
        return userDescr;
    }

    public String getUserAddress() {
        return userAddress;
    }
}
