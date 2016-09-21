package com.example.nir.mynotes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

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

        notesService.syncNotes();

        ArrayAdapter<Note> adapter = new ArrayAdapter<Note>(this, android.R.layout.simple_list_item_1, notesService.getNotes());

        gridView.setAdapter(adapter);

        gridView.invalidateViews();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note note = notesService.createNote();
                note.setData(editText.getText().toString());
                notesService.syncNotes();

                gridView.invalidateViews();
                editText.setText("");
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("NirNotes", position+"");
                editText.setText(notesService.getNotes().get(position).getData());
                editText.setSelection(editText.getText().length()); // Change selection position to end of text
                notesService.getNotes().remove(position);
                gridView.invalidateViews();
            }
        });
    }





}
