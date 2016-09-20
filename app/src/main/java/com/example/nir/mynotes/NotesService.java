package com.example.nir.mynotes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nir on 9/20/2016.
 */
public interface NotesService {

    /* Fetch notes or send notes if changes have been made */
    void syncNotes();

    /* Get list of notes */
    ArrayList<Note> getNotes();

    /* Create a new note */
    Note createNote();
}
