package extractor.com.easyfinance.controler.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import extractor.com.easyfinance.controler.DBHelper;
import extractor.com.easyfinance.controler.EasyFinance;
import extractor.com.easyfinance.model.Receita;

/**
 * @author Muryllo Tiraza
 */
public class ReceitaDAO {
    private SQLiteDatabase database;

    public ReceitaDAO() {
        database = EasyFinance.getAppDBHelper().getWritableDatabase();
    }

    public void fecharConexao(){
        if(database.isOpen()){
            database.close();
        }
    }

    public Boolean inseir(Receita receita){
        ContentValues contentValues = getContentValues(receita);
        database.insert(DBHelper.TABELA_RECEITAS, null, contentValues);
        return true;
    }

    public Boolean editar(Receita receita){
        ContentValues contentValues = getContentValues(receita);
        database.update(DBHelper.TABELA_RECEITAS, contentValues, "ID = ?", new String[] {receita.getID().toString()});
        return true;
    }

    public Boolean deletar(Integer id){
        database.delete(DBHelper.TABELA_RECEITAS, "ID = ?", new String[] {id.toString()});
        return true;
    }

    public Receita get(Integer id){
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM ").append(DBHelper.TABELA_RECEITAS);
        query.append(" WHERE ").append(DBHelper.RECEITAS_COLUNA_ID).append(" = ").append(id.toString());

        Cursor cursor = database.rawQuery(query.toString(), null);
        cursor.moveToFirst();

        Receita receita = preencheReceita(cursor);

        if(!cursor.isClosed()){
            cursor.close();
        }
        return receita;
    }

    public ArrayList<Receita> getList(){
        ArrayList<Receita> receitas = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM " + DBHelper.TABELA_RECEITAS, null);
        cursor.moveToFirst();

        Receita receita;
        while(!cursor.isAfterLast()){
            receita = preencheReceita(cursor);
            receitas.add(receita);
            cursor.moveToNext();
        }

        if(!cursor.isClosed()){
            cursor.close();
        }

        return receitas;
    }

    private ContentValues getContentValues(Receita receita){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.DESPESAS_COLUNA_DESCRICAO, receita.getDescricao());
        contentValues.put(DBHelper.DESPESAS_COLUNA_DATA, receita.getData());
        contentValues.put(DBHelper.DESPESAS_COLUNA_VALOR, receita.getValor());
        contentValues.put(DBHelper.DESPESAS_COLUNA_TIPO, receita.getTipo());
        return contentValues;
    }

    private Receita preencheReceita(Cursor cursor){
        Receita receita = new Receita();
        receita.setID(cursor.getInt(cursor.getColumnIndex(DBHelper.DESPESAS_COLUNA_ID)));
        receita.setDescricao(cursor.getString(cursor.getColumnIndex(DBHelper.DESPESAS_COLUNA_DESCRICAO)));
        receita.setData(cursor.getString(cursor.getColumnIndex(DBHelper.DESPESAS_COLUNA_DATA)));
        receita.setTipo(cursor.getInt(cursor.getColumnIndex(DBHelper.DESPESAS_COLUNA_TIPO)));
        receita.setValor(cursor.getDouble(cursor.getColumnIndex(DBHelper.DESPESAS_COLUNA_VALOR)));
        return receita;
    }
}
