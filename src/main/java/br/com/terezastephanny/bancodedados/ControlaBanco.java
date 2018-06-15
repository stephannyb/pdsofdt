package br.com.terezastephanny.bancodedados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ControlaBanco {

    private SQLiteDatabase bd;
    private CriaBD banco;

    public ControlaBanco(Context context){
        banco = new CriaBD(context);
    }

    public String insereDado (String titulo, String autor, String editora){
        ContentValues valores;
        long resultado;

        bd = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBD.getTITULO(), titulo);
        valores.put(CriaBD.getAUTOR(), autor);
        valores.put(CriaBD.getEDITORA(), editora);

        resultado = bd.insert(CriaBD.getTABELA(), null, valores);
        bd.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {banco.getID(), banco.getTITULO()};
        bd = banco.getReadableDatabase();
        cursor = bd.query(banco.getTABELA(), campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        bd.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {banco.getID(),banco.getTITULO(),banco.getAUTOR(),banco.getEDITORA()};
        String where = CriaBD.getID() + "=" + id;
        bd = banco.getReadableDatabase();
        cursor = bd.query(CriaBD.getTABELA(),campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
       bd.close();
        return cursor;
    }

    public void alteraRegistro(int id, String titulo, String autor, String editora){
        ContentValues valores;
        String where;

        bd = banco.getWritableDatabase();

        where = CriaBD.getID() + "=" + id;

        valores = new ContentValues();
        valores.put(CriaBD.getTITULO(), titulo);
        valores.put(CriaBD.getAUTOR(), autor);
        valores.put(CriaBD.getEDITORA(), editora);

        bd.update(CriaBD.getTABELA(),valores,where,null);
        bd.close();
    }

    public void deletaRegistro(int id){
        String where = CriaBD.getID() + "=" + id;
        bd = banco.getReadableDatabase();
        bd.delete(CriaBD.getTABELA(),where,null);
        bd.close();
    }

}
