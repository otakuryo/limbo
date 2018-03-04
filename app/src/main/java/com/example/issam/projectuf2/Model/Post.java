package com.example.issam.projectuf2.Model;

import java.util.HashMap;

/**
 * Created by issam on 30/01/2018.
 */

public class Post {
    public String uid;
    public String content;
    public String author;
    public String authorPicUrl;
   public HashMap<String, Boolean> likes;
   public String mediaUrl;
   public String mediaType;

    public Post(){}

    public Post(String uid, String content, String author, String authorPicUrl) {
        this.uid = uid;
        this.content = content;
        this.author = author;
        this.authorPicUrl = authorPicUrl;

    }

    public Post(String uid, String content, String author, String authorPicUrl, String mediaUrl, String mediaType) {
        this.uid = uid;
        this.content = content;
        this.author = author;
        this.authorPicUrl = authorPicUrl;
        this.mediaUrl = mediaUrl;
        this.mediaType = mediaType;
    }
}
