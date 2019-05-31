package com.ukeje.testingapi.responses;

import com.google.gson.annotations.SerializedName;

public class RetroUsers {

    //Give the field a custom username
    @SerializedName("name")
    private String name;

    public RetroUsers(String name){
        this.name = name;
    }

    //Retrieve the data using setters/getters methods

    public  String getUsers(){
        return name;
    }

    public void setUsers(String name){
        this.name = name;
    }
}
