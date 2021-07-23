package com.zambaapple.MobileTopUp.FoireAuxQuestions;

import java.util.Date;

public class User {
    String username;
    String date;
    String message;
    String UserID;
    boolean responserecu;

    public User(String username, String date, String message, String userID, boolean responserecu) {
        this.username = username;
        this.date = date;
        this.message = message;
        UserID = userID;
        this.responserecu = responserecu;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public boolean isResponserecu() {
        return responserecu;
    }

    public void setResponserecu(boolean responserecu) {
        this.responserecu = responserecu;
    }
}
