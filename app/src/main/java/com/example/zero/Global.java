package com.example.zero;

public class Global {
    static String likes;
    static int image_total;

    public Global(String likes) {
    this.likes=likes;
    }
    public static int getImage_total() {
        return image_total;
    }
    public void setImage_total(int image_total) {
      image_total = image_total;
    }
    public static String getLikes() {

        return likes;
    }
    public void setLikes(String likes)
    {
        this.likes = likes;
    }
}
