package br.com.terezastephanny.bancodedados;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Alterar extends AppCompatActivity {
    EditText livro;
    EditText autor;
    EditText editora;
    Button alterar;
    Button deletar;
    Cursor cursor;
    ControlaBanco crud;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        codigo = this.getIntent().getStringExtra("codigo");

        crud = new  ControlaBanco(getBaseContext());

        livro = (EditText)findViewById(R.id.editText4);
        autor = (EditText)findViewById(R.id.editText5);
        editora = (EditText)findViewById(R.id.editText6);

        alterar = (Button)findViewById(R.id.buttonAlt);

        cursor = crud.carregaDadoById(Integer.parseInt(codigo));
        livro.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBD.getTITULO())));
        autor.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBD.getAUTOR())));
        editora.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBD.getEDITORA())));

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.alteraRegistro(Integer.parseInt(codigo),
                        livro.getText().toString(),autor.getText().toString(),
                        editora.getText().toString());
                Intent intent = new Intent(Alterar.this,Consulta.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Registro Alterado com Sucesso", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        deletar = (Button)findViewById(R.id.buttonDel);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.deletaRegistro(Integer.parseInt(codigo));
                Intent intent = new Intent(Alterar.this,Consulta.class);
                Toast.makeText(getApplicationContext(),"Registro Deletado com Sucesso", Toast.LENGTH_LONG).show();
                startActivity(intent);
                finish();
            }
        });



    }
}
