package com.itca.eval_practica_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtTitulo, edtDescripcion, edtAutor;
    private Button btnguardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTitulo = (EditText) findViewById(R.id.edtTitulo);
        edtDescripcion = (EditText) findViewById(R.id.edtDescripcion);
        edtAutor = (EditText) findViewById(R.id.edtAutor);
        btnguardar = (Button) findViewById(R.id.btnguardar);

        btnguardar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        EvaluacionSQLiteOpenHelper admin = new EvaluacionSQLiteOpenHelper(this, "EvalNotas", null, 1);
        ContentValues registro = new ContentValues();
        SQLiteDatabase bd = admin.getWritableDatabase();
        int cant;
        Cursor fila;

        switch (view.getId()) {

            case R.id.btnguardar:
               // Toast.makeText(this, "Has hecho clic en el boton Guardar", Toast.LENGTH_SHORT).show();

                String titulo = edtTitulo.getText().toString();
                String descripcion = edtDescripcion.getText().toString();
                String autor = edtAutor.getText().toString();

                if (titulo.isEmpty()) {
                    edtTitulo.setError("Campo Obligatorio");
                } else if (descripcion.isEmpty()) {
                    edtDescripcion.setError("Campo Obligatorio");
                } else if (autor.isEmpty()) {
                    edtAutor.setError("Campo Obligatorio");
                } else {
                    Toast.makeText(this, "Has Superado la Validacion", Toast.LENGTH_SHORT).show();
                }

                registro.put("Titulo", titulo);
                registro.put("Descripcion", descripcion);
                registro.put("Autor", autor);

                bd.insert("tb_Notas", null, registro);
                bd.close();

                edtTitulo.setText("");
                ;
                edtDescripcion.setText("");
                edtAutor.setText("");

                Toast.makeText(this, "Se cargaron las Notas", Toast.LENGTH_SHORT).show();

                break;

            default:

                break;


        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.consultar){
            Intent intent = new Intent(this, registros.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}