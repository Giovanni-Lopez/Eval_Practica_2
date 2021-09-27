package com.itca.eval_practica_2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class EvaluacionSQLiteOpenHelper extends SQLiteOpenHelper {

    public EvaluacionSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "EvalNotas", factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tb_notas(id INTEGER not null primary key AUTOINCREMENT, Titulo text, Descripcion text, Autor text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists tb_Notas");
        onCreate(db);

    }

    public ArrayList llenarinfo(){

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> lista = new ArrayList<>();

        Cursor registros = db.rawQuery ("SELECT * FROM tb_Notas",null);

        if (registros.moveToFirst()){
            do {
                lista.add(registros.getString(1));
            }while (registros.moveToNext());
        }

        return lista;
    }

}
