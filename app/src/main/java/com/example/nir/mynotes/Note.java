package com.example.nir.mynotes;

/**
 * Created by Nir on 9/17/2016.
 */
public class Note {
    public String data;
    public int id;

    public Note(int id, String data) {
        this.data = data;
        this.id = id;
    }

    public String toString() {
        return data;
    }
}