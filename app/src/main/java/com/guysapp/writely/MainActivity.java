package com.guysapp.writely;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.UpdateFrom;
import com.guysapp.writely.Adapter.NotesAdapter;
import com.guysapp.writely.Database.NoteDatabase;
import com.guysapp.writely.Fragments.BottomSheetFragment;
import com.guysapp.writely.Model.NotesModel;

import java.util.ArrayList;
import java.util.List;

import static com.guysapp.writely.Fragments.BottomSheetFragment.*;


public class MainActivity extends AppCompatActivity {


    Toolbar toolbar;
    ArrayList<NotesModel> noteList;
    NotesModel notesModel;
    LottieAnimationView animationView;
    TextView txMsg;
    String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppUpdater appUpdater = new AppUpdater(this)
                .setUpdateFrom(UpdateFrom.GOOGLE_PLAY);
        appUpdater.start();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = getWindow();
            int flag = window.getDecorView().getSystemUiVisibility();
            flag |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            window.getDecorView().setSystemUiVisibility(flag);
        }
        animationView = findViewById(R.id.animationView);

        txMsg = findViewById(R.id.tx_msg);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_dehaze_black_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final BottomSheetDialogFragment myBottomSheet = newInstance("Modal Bottom Sheet");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBottomSheet.show(getSupportFragmentManager(), myBottomSheet.getTag());
            }
        });
        RecyclerView recyclerView = findViewById(R.id.recyclerToday);
        NoteDatabase db = new NoteDatabase(this);

        List<NotesModel> contacts = db.getAllContacts();
       // List<NotesModel> note_list = new ArrayList<NotesModel>();




        if (contacts.size()==0){
            txMsg.setText("Create your first note");
            animationView.setVisibility(View.VISIBLE);
        }else {
            animationView.setVisibility(View.GONE);
        }

        //Log.e(TAG,contacts.get(0).getnTitle());

        NotesAdapter adapter = new NotesAdapter(this,contacts);
        // Log.e(TAG,note_list.get(1).getnTitle());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    // Get a new or existing ViewModel from the ViewModelProvider.


    FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
            startActivity(intent);
            finish();
        }
    });
}

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher_foreground)
                .setTitle("Closing Write.ly")
                .setMessage("Are you sure want to close write.ly?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }


}
