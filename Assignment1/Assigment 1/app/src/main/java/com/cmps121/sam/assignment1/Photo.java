package com.cmps121.sam.assignment1;

public class Photo {
    private String name;
    private String year;                            //Photo Object class defined by name year and photographer strings
    private String photographer;

    public Photo(String name, String year, String photographer) {
        this.name = name;
        this.year = year;
        this.photographer = photographer;
    }
    public String getName(){
        return name;
    }
    public void setName(){
        this.name = name;
    }
    public String getYear(){
        return year;
    }
    public void setYear(){
        this.year = year;
    }
    public String getPhotographer(){
        return photographer;
    }
    public void setPhotographer(){
        this.photographer = photographer;
    }
}
