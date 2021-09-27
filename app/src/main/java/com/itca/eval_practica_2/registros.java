package com.itca.eval_practica_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class registros extends AppCompatActivity {

    private ListView list;
    ArrayList<String> listinfo;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);

        EvaluacionSQLiteOpenHelper admin = new EvaluacionSQLiteOpenHelper(this, "EvalNotas", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        list = findViewById(R.id.list1);

        listinfo= admin.llenarinfo();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listinfo);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(registros.this, actualizar_borrar.class);

                String title = (String) list.getItemAtPosition(position);

                intent.putExtra("key",title);

                startActivity(intent);
            }
        });
    }

    public void back(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    }