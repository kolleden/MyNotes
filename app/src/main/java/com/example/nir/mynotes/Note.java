package com.example.nir.mynotes;

/**
 * Created by Nir on 9/17/2016.
 */
public class Note {
    private String data;

    public Note(String data) {
        this.data = data;
    }

    public String toString() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}