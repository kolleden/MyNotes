package com.example.nir.mynotes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = (EditText)findViewById(R.id.addNoteEdit);
        Button button = (Button)findViewById(R.id.addNote);
        final GridView gridView = (GridView)findViewById(R.id.notesview);
        final NotesService notesService = new StorageManager(this);

        ArrayAdapter<Note> adapter = new ArrayAdapter<Note>(this, android.R.layout.simple_list_item_1, notesService.getNotes());
        gridView.setAdapter(adapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note note = notesService.createNote();
                note.data = editText.toString();
                notesService.syncNotes();

                gridView.invalidateViews();
                editText.setText("");
            }
        });
    }





}
