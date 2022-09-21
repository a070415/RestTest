package com.example.recyclertest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TestItem {

    @SerializedName("body")
    public List<Data> body;

    @Override
    public String toString() {
        return "TestItem{" +
                "body=" + body +
                '}';
    }
}
