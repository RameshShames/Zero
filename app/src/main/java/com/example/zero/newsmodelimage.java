package com.example.zero;
public class newsmodelimage {
    private String id,imageurl;

    public newsmodelimage() {
    }

    public newsmodelimage(String id, String imageurl) {
        this.id = id;
        this.imageurl = imageurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
