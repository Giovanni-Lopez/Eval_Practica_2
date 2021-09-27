package com.itca.eval_practica_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class actualizar_borrar extends AppCompatActivity {

    private EditText edtTitulo, edtDescripcion, edtAutor;
    private String texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_borrar);

        EvaluacionSQLiteOpenHelper admin = new EvaluacionSQLiteOpenHelper(this, "EvalNotas", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        edtTitulo = (EditText) findViewById(R.id.edtTitulo);
        edtDescripcion = (EditText) findViewById(R.id.edtDescripcion);
        edtAutor = (EditText) findViewById(R.id.edtAutor);

        String recibe = getIntent().getStringExtra("key");

        edtTitulo.setText(recibe);
        texto = edtTitulo.getText().toString();

        Cursor registros = bd.rawQuery
                ("select Descripcion, Autor from tb_Notas where Titulo = '" + texto + "'", null);

        if (registros.moveToFirst()){
            edtDescripcion.setText(registros.getString(0));
            edtAutor.setText(registros.getString(1));
        }
    }

    public void delete(View view) {
        EvaluacionSQLiteOpenHelper admin = new EvaluacionSQLiteOpenHelper(this, "EvalNotas", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String Titulo = edtTitulo.getText().toString();
        String Descripcion = edtDescripcion.getText().toString();
        String Autor = edtAutor.getText().toString();


        AlertDialog.Builder alerta = new AlertDialog.Builder(actualizar_borrar.this);
        alerta.setIcon(R.drawable.ic_baseline_warning_24);
        alerta.setTitle("Eliminar");
        alerta.setMessage("Seguro que quiere borras estos campos de la Base de Datos?");
        alerta.setCancelable(false);

        alerta.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                int cantidad = bd.delete("tb_Notas", "Titulo = '" + texto + "'", null);
                bd.close();

                edtTitulo.setText("");
                edtDescripcion.setText("");
                edtAutor.setText("");

                Toast.makeText(getApplicationContext(), "Datos borrado exitosamente!!!", Toast.LENGTH_SHORT).show();

            }
        });


        alerta.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).show();


    }

    public void regresar(View view) {

        Intent a = new Intent(this, registros.class);
        startActivity(a);

    }

    public void update(View view) {
        EvaluacionSQLiteOpenHelper admin = new EvaluacionSQLiteOpenHelper(this, "EvalNotas", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String Titulo = edtTitulo.getText().toString();
        String Descripcion = edtDescripcion.getText().toString();
        String Autor = edtAutor.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("Titulo", Titulo);
        registro.put("Descripcion", Descripcion);
        registro.put("Autor", Autor);

        int cantidad = bd.update("tb_Notas", registro, "Titulo = '" + texto + "'", null);
        bd.close();

        if (cantidad == 1) {
            Toast.makeText(this, "Actualización Exitosa!!!!!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Actualización Fallida!!!!!", Toast.LENGTH_SHORT).show();
        }

    }

}