package com.guysapp.writely;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.guysapp.writely.Database.NoteDatabase;
import com.guysapp.writely.Model.NotesModel;

import java.util.List;

import shortbread.Shortcut;

@Shortcut(id = "movies", icon = R.drawable.ic_assignment_turned_in_black_24dp, shortLabel = "Add Note")
public class CreateNoteActivity extends AppCompatActivity {

    public static final String TAG = "CreateNoteActivity";
    Toolbar toolbar;
    EditText etNote;
    FloatingActionButton floatingActionButton;
    TextView tx_title;
    String text;
    String id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createnote);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = getWindow();
            int flag = window.getDecorView().getSystemUiVisibility();
            flag |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            window.getDecorView().setSystemUiVisibility(flag);
        }
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateNoteActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tx_title = findViewById(R.id.txTitle);
        Intent intent = getIntent();
        text = intent.getStringExtra("Text");
        id = intent.getStringExtra("Id") ;
        Log.e(TAG,String.valueOf(id));

        if (text != null){
            tx_title.setText("Edit Note");

        }else {
            tx_title.setText("Create Note");
        }
        init();

        etNote.setText(text);
        etNote.setSelection(etNote.getText().length());

        final NoteDatabase db = new NoteDatabase(this);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etNote.getText().toString();
                if (title.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Write text in your note",Toast.LENGTH_LONG).show();
                }else {
                    int count = wordcount(title);
                    Log.i(TAG, "Word Count: " + Integer.toString(count));
                    if (text != null) {
                        tx_title.setText("Edit Note");
                        NotesModel notesModel = new NotesModel(Integer.parseInt(id), title, count);
                        int contacts = db.updateContact(notesModel);
                        Log.e(TAG, title);
                        Log.e(TAG, String.valueOf(contacts));
                        Intent i = new Intent(CreateNoteActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();

                    } else {
                        tx_title.setText("Create Note");
                        db.addContact(new NotesModel(title, count));
                        Intent i = new Intent(CreateNoteActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();

                    }
                }


            }


        });
    }


    static int wordcount(String string)
    {
        int count=0;

        char ch[]= new char[string.length()];
        for(int i=0;i<string.length();i++)
        {
            ch[i]= string.charAt(i);
            if( ((i>0)&&(ch[i]!=' ')&&(ch[i-1]==' ')) || ((ch[0]!=' ')&&(i==0)) )
                count++;
        }
        return count;
    }

    public void init(){

        floatingActionButton = findViewById(R.id.fb_save);
        etNote = findViewById(R.id.etNote);
    }
}
