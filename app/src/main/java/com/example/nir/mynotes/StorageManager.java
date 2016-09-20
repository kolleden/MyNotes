package com.example.nir.mynotes;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Nir on 9/20/2016.
 */
public class StorageManager implements NotesService {
    final String DELIMITER = "//...NirNotes...//";
    final String FILE_NAME = "NirNotes.txt";

    Context context;
    boolean updated = false;
    ArrayList<Note> notes;


    public StorageManager(Context context) {
        this.context = context;
    }

    @Override
    public void syncNotes() {
        if(updated) {
            // Send notes to storage
            updated = false;
            saveNotes();
            return;
        }
        fetchNotes();
    }

    @Override
    public ArrayList<Note> getNotes() {
        return null;
    }

    @Override
    public Note createNote() {
        updated = true;
        return null;
    }

    public void fetchNotes() {
        notes = new ArrayList<>();
        File f = new File(context.getFilesDir(), FILE_NAME;
        try {
            f.createNewFile();

            Scanner scanner = new Scanner(context.openFileInput(FILE_NAME));
            scanner.useDelimiter(DELIMITER);
            while(scanner.hasNext()) {
                notes.add(new Note(scanner.next()));
            }
            scanner.close();

        } catch (Exception e) {}
    }

    public void saveNotes() {
        try {
            PrintWriter pw = new PrintWriter(context.openFileOutput(FILE_NAME, context.MODE_PRIVATE));

            for(Note note: notes) {
                pw.write(note.toString());
                pw.write(DELIMITER);
            }

            pw.close();
        } catch (Exception e) {

        }
    }
}