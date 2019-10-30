package com.example.myapplication.conexao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.model.Bovino;

import java.util.ArrayList;
import java.util.List;


public class Conexao extends SQLiteOpenHelper {
    private final static String nome_banco = "fazenda";
    private final static int versao_banco =2;

    public Conexao(@Nullable Context context) {
        super(context, nome_banco, null, versao_banco);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlcreate = "CREATE TABLE venda(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "brinco TEXT," +
                "peso DOUBLE, " +
                "raca TEXT, " +
                "data TEXT, " +
                "valor DOUBLE," +
                "nome TEXT, " +
                "telefone TEXT)";
        db.execSQL(sqlcreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String droptable = "DROP TABLE venda";
        db.execSQL(droptable);
        onCreate(db);
    }

    public String create(Bovino bovino)
    {
        long result;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("brinco",bovino.getBrinco());
        valores.put("peso",bovino.getPeso());
        valores.put("raca",bovino.getRaca());
        valores.put("data",bovino.getData());
        valores.put("valor",bovino.getValor());
        valores.put("nome",bovino.getValor());
        valores.put("telefone",bovino.getTelefone());

        result = db.insert("venda",null,valores);
        db.close();
        if(result==-1)
        {
            return "Erro ao registrar venda";
        }
        else
            {
                return "Venda registrada com sucesso!!!";
            }
    }
    public String update(Bovino bovino)
    {
        long result;
        SQLiteDatabase db = getWritableDatabase();
        String meuwhere = "id="+bovino.getId();
        ContentValues valores = new ContentValues();
        valores.put("brinco",bovino.getBrinco());
        valores.put("peso",bovino.getPeso());
        valores.put("raca",bovino.getRaca());
        valores.put("data",bovino.getData());
        valores.put("valor",bovino.getValor());
        valores.put("nome",bovino.getValor());
        valores.put("telefone",bovino.getTelefone());

        result = db.update("venda",valores,meuwhere,null);
        db.close();
        if(result==-1)
        {
            return "Erro ao alterar venda.";
        }
        else
        {
            return "Venda alterada com sucesso!!!";
        }

    }
    public String delete(int id)
    {
        long result;
        SQLiteDatabase db = getReadableDatabase();
        String meuwhere = "id="+id;
        result = db.delete("venda",meuwhere,null);
        db.close();
        if(result==-1)
        {
            return "Erro ao excluir venda.";
        }
        else
        {
            return "Venda excluida com sucesso!!!";
        }

    }
    public List<Bovino> getBovinos()
    {
        List<Bovino> lista = new ArrayList<Bovino>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from venda";
        Cursor rs = db.rawQuery(sql,null);
        if(rs.moveToFirst())
        {
            do{
                Bovino b = new Bovino();

                b.setId(rs.getInt(0));
                b.setBrinco(rs.getString(1));
                b.setPeso(rs.getDouble(2));
                b.setRaca(rs.getString(3));
                b.setData(rs.getString(4));
                b.setValor(rs.getDouble(5));
                b.setNome(rs.getString(6));
                b.setTelefone(rs.getString(7));


                lista.add(b);
            }while (rs.moveToNext());
        }
        return lista;
    }

    public Bovino exibir(int id)
    {
        Bovino b = null;
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from venda where id="+id;
        Cursor rs = db.rawQuery(sql,null);
        if(rs.moveToFirst())
        {
            b = new Bovino();

            b.setId(rs.getInt(0));
            b.setBrinco(rs.getString(1));
            b.setPeso(rs.getDouble(2));
            b.setRaca(rs.getString(3));
            b.setData(rs.getString(4));
            b.setValor(rs.getDouble(5));
            b.setNome(rs.getString(6));
            b.setTelefone(rs.getString(7));


        }
        return b;
    }




}
