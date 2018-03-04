package com.example.issam.projectuf2.Model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    public String userId;
    public String displayName;
    public String email;
    public String photoUrl;

    public User() {}

    public User(String userId, String displayName, String email, String photoUrl){
        this.userId = userId;
        this.displayName = displayName;
        this.email = email;
        this.photoUrl = photoUrl;
    }
}