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
        final ArrayList<Note> notes = new ArrayList<Note>();

        ArrayAdapter<Note> adapter = new ArrayAdapter<Note>(this, android.R.layout.simple_list_item_1, notes);
        gridView.setAdapter(adapter);

        notes.add(new Note(3, readFile("MyNotes")));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notes.add(new Note(2, editText.getText().toString()));

                createFile("MyNotes", editText.getText().toString());

                gridView.invalidateViews();
                editText.setText("");
            }
        });
    }

    public void createFile(String filename, String data) {
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readFile(String filename) {
        FileInputStream inputStream;
        StringBuffer content = new StringBuffer("");
        byte[] buffer = new byte[1024];
        int len;
        try {
            inputStream = openFileInput(filename);
            while((len = inputStream.read(buffer)) != -1) {
                content.append(new String(buffer, 0, len));
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

}
