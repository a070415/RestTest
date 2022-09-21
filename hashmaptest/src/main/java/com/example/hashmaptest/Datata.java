package com.example.hashmaptest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datata {

    public Datata(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("content")
    @Expose
    private String content;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Datata{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

