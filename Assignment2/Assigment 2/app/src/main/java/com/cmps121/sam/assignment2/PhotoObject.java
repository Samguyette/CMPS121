package com.cmps121.sam.assignment2;


public class PhotoObject {
    private String id;
    private byte[] photo;                            //Photo Object class defined by id, photo, title, and url
    private String title;
    private String url;

    public PhotoObject(byte[] photo, String title, String id, String url) {
        this.title = title;
        this.photo = photo;
        this.id = id;
        this.url = url;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(){
        this.title = title;
    }
    public byte[] getPhoto(){
        return photo;
    }
    public void setPhoto(){
        this.photo = photo;
    }
    public String getid(){
        return id;
    }
    public void setid(){
        this.id = id;
    }
    public String getUrl(){
        return url;
    }
    public void setUrl(){
        this.url = url;
    }
}
