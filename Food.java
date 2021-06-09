package com.example.jsonparsingdemo;

public class Food {

    //private String subtitleimage;
    //private String subtitlename;
    private String titleimage;
    private String titlename;

    public Food(String titleimage, String titlename) {
        this.titleimage = titleimage;
        this.titlename = titlename;
    }

    public String getTitleimage() {
        return titleimage;
    }

    public String getTitlename() {
        return titlename;
    }
}
