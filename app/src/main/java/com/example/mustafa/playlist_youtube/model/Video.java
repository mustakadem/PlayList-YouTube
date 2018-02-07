package com.example.mustafa.playlist_youtube.model;


public class Video {
    private String title;
    private String author;
    private String image;
    private String views;
    private String id;

    public Video() {

    }

    public Video(String title, String author, String image, String views, String id) {
        this.title = title;
        this.author = author;
        this.image = image;
        this.views = views;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString(){
        return getTitle()+ ": " +getAuthor()+ ". " + getViews();
    }
}
