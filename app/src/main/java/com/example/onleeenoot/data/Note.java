package com.example.onleeenoot.data;

public class Note {

    private long id;

    private String text;

    public Note() {

    }

    public Note(String text) {
        this.text = text;
    }

    public Note(long id, String text) {
        this.id = id;
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public long getId() {
        return id;
    }
}
