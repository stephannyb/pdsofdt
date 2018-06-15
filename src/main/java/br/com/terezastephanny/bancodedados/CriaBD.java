package br.com.terezastephanny.bancodedados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBD extends SQLiteOpenHelper {

    public CriaBD(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    private static final String NOME_BANCO = "banco.db";
    private static final String TABELA = "livros";
    private static final String ID = "_id";
    private static final String TITULO = "titulo";
    private static final String AUTOR = "autor";
    private static final String EDITORA = "editora";
    private static final int VERSAO = 1;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Livros ("+ ID +" integer primary key autoincrement,"+ TITULO + " text,"+ AUTOR + " text,"+ EDITORA + " text"+")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABELA);
        onCreate(db);
    }

    public String getNOME_BANCO(){
        return NOME_BANCO;
    }
    public static String getTABELA(){
        return TABELA;
    }
    public static String getID(){
        return ID;
    }
    public static String getTITULO(){
        return TITULO;
    }
    public static String getAUTOR(){
        return AUTOR;
    }
    public static String getEDITORA() {
        return EDITORA;
    }

}
