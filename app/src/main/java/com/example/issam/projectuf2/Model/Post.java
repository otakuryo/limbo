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
    public String titulo;
    public String fIni;
    public String fEnd;
    public String vYou;
    public String lat;
    public String lon;


    public Post(){}

    public Post(String uid, String content, String author, String authorPicUrl,String titulo,String fIni,String fEnd,String vYou,String lat,String lon) {
        this.uid = uid;
        this.content = content;
        this.author = author;
        this.authorPicUrl = authorPicUrl;
        this.titulo = titulo;
        this.fIni=fIni;
        this.fEnd=fEnd;
        this.vYou=vYou;
        this.lat=lat;
        this.lon=lon;

    }

    public Post(String uid, String content, String author, String authorPicUrl, String mediaUrl, String mediaType,String titulo,String fIni,String fEnd,String vYou,String lat,String lon) {
        this.uid = uid;
        this.content = content;
        this.author = author;
        this.authorPicUrl = authorPicUrl;
        this.mediaUrl = mediaUrl;
        this.mediaType = mediaType;
        this.titulo = titulo;
        this.fIni=fIni;
        this.fEnd=fEnd;
        this.vYou=vYou;
        this.lat=lat;
        this.lon=lon;
    }
}
