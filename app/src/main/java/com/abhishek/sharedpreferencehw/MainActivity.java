package com.abhishek.sharedpreferencehw;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ArrayList<notes>note;
    notesadapter contactAdapter;
    RecyclerView rv;
Button b;
EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        load();
         contactAdapter = new notesadapter(this, note);
         rv= findViewById(R.id.rv);
        b=findViewById(R.id.bt);
        et=findViewById(R.id.et);
         rv.setLayoutManager(new LinearLayoutManager(this));


        rv.setAdapter(contactAdapter);
        add();

    }
private void savedata(){
    SharedPreferences sp=getSharedPreferences("Abhishek",MODE_PRIVATE);
        SharedPreferences.Editor editor= sp.edit();
    Gson gson= new Gson();
    String json = gson.toJson(note);
    editor.putString("list",json);
    editor.apply();
}
public void load(){
        SharedPreferences sharedPreferences = getSharedPreferences("Abhishek",MODE_PRIVATE);
        Gson gson= new Gson();
        String json= sharedPreferences.getString("list",null);
        Type type= new TypeToken<ArrayList<notes>>() {}.getType();
        note=gson.fromJson(json,type);
        if(note==null){
            note=new ArrayList<>();
        }
}
    public void add(){
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s= et.getText().toString();
                notes n= new notes(s);
                note.add(n);
                contactAdapter.notifyDataSetChanged();
                savedata();
                et.setText(null);

        }
        });
    }


}
