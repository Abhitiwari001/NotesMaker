package com.abhishek.sharedpreferencehw;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class notesadapter extends RecyclerView.Adapter<notesadapter.notesHolder> {
private Context context;
private ArrayList<notes>note;

    public notesadapter(Context context, ArrayList<notes> note) {
        this.context = context;
        this.note = note;
    }

    @NonNull
    @Override
    public notesHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater li=LayoutInflater.from(context);
View InflatedView =li.inflate(R.layout.rowitem,viewGroup,false);
        return new notesHolder(InflatedView);
    }

    @Override
    public void onBindViewHolder(@NonNull final notesHolder notesHolder, final int position) {
        final notes curentNotes=note.get(position);
        notesHolder.tHolder.setText(curentNotes.getT());
       // notesHolder.tHolder.setBackgroundColor(Color.GREEN);
notesHolder.tHolder.setOnLongClickListener(new View.OnLongClickListener() {

    @Override
    public boolean onLongClick(View view) {
        note.remove(position);
        notifyDataSetChanged();
        Toast.makeText(view.getContext(),"Item Deleted",Toast.LENGTH_SHORT).show();
        return false;
    }
});
        notesHolder.tHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.swap(note,position,note.size()-1);
                notifyItemMoved(position, note.size()-1);
                notesHolder.tHolder.setBackgroundColor(Color.RED);
            }
        });



    }



    @Override
    public int getItemCount() {
        return note.size();
    }

    class notesHolder extends RecyclerView.ViewHolder {
        TextView tHolder;

        public notesHolder(@NonNull View itemView) {
            super(itemView);
       tHolder=itemView.findViewById(R.id.tv);

        }
    }




}
