package com.guysapp.writely.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.guysapp.writely.CreateNoteActivity;
import com.guysapp.writely.Database.NoteDatabase;
import com.guysapp.writely.MainActivity;
import com.guysapp.writely.Model.NotesModel;
import com.guysapp.writely.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {



    String TAG = "AdapterClass";
    Context context;
    private List<NotesModel> note;

    public NotesAdapter(Context context, List<NotesModel> note){

        this.context = context;
        this.note = note;



    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.notes_row,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {



        viewHolder.nTitle.setText(note.get(i).getnTitle());
        viewHolder.nTimestamp.setText(note.get(i).getnWords()+" "+" Words");
        final String noteText = note.get(i).getnTitle();
        final String nID = String.valueOf(note.get(i).get_id());
        final String words = String.valueOf(note.get(i).getnWords());
        Log.e(TAG,String.valueOf(nID));
            viewHolder.linear_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent i = new Intent(context, CreateNoteActivity.class);
                    i.putExtra("Text",noteText);
                    i.putExtra("Id",nID);
                    Log.e(TAG,String.valueOf(nID));
                    context.startActivity(i);
                }
            });
        viewHolder.linear_item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int id = Integer.parseInt(nID);
                int count = Integer.parseInt(words);
                viewHolder.db.deleteContact(new NotesModel(id,noteText,count));
                Toast.makeText(v.getContext(),"Successfully Deleted",Toast.LENGTH_LONG).show();
                removeItem(i);
                return false;
            }
        });


    }
    public void removeItem(int position){
        note.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, note.size());
    }
    @Override
    public int getItemCount() {
        return note.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        NoteDatabase db;
        LinearLayout linear_item;
        TextView nTitle, nTimestamp;
        public ViewHolder(final View view){
            super(view);

             db = new NoteDatabase(view.getContext());
            linear_item = view.findViewById(R.id.linear_item);
            nTitle = view.findViewById(R.id.tx_title);
            nTimestamp = view.findViewById(R.id.tx_time);



        }
    }
}
