package extractor.com.easyfinance.model.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import extractor.com.easyfinance.controler.DBHelper;
import extractor.com.easyfinance.controler.EasyFinance;
import extractor.com.easyfinance.model.entities.Despesa;

/**
 * @author Muryllo Tiraza
 */
public class DespesaDAO  {

    private SQLiteDatabase database;

    public DespesaDAO() {
        database = EasyFinance.getAppDBHelper().getWritableDatabase();
    }

    public void fecharConexao(){
        if(database.isOpen()){
            database.close();
        }
    }

    public Boolean inseir(Despesa despesa){
        ContentValues contentValues = getContentValues(despesa);
        database.insert(DBHelper.TABELA_DESPESAS, null, contentValues);
        return true;
    }

    public Boolean editar(Despesa despesa){
        ContentValues contentValues = getContentValues(despesa);
        database.update(DBHelper.TABELA_DESPESAS, contentValues, "ID = ?", new String[] {despesa.getID().toString()});
        return true;
    }

    public Boolean deletar(Integer id){
        database.delete(DBHelper.TABELA_DESPESAS, "ID = ?", new String[] {id.toString()});
        return true;
    }

    public Despesa get(Integer id){
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM ").append(DBHelper.TABELA_DESPESAS);
        query.append(" WHERE ").append(DBHelper.DESPESAS_COLUNA_ID).append(" = ").append(id.toString());

        Cursor cursor = database.rawQuery(query.toString(), null);
        cursor.moveToFirst();

        Despesa despesa = preencheDespesa(cursor);

        if(!cursor.isClosed()){
            cursor.close();
        }
        return despesa;
    }

    public ArrayList<Despesa> getList(){
        ArrayList<Despesa> despesas = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM " + DBHelper.TABELA_DESPESAS, null);
        cursor.moveToFirst();

        Despesa despesa;
        while(!cursor.isAfterLast()){
            despesa = preencheDespesa(cursor);
            despesas.add(despesa);
            cursor.moveToNext();
        }

        if(!cursor.isClosed()){
            cursor.close();
        }

        return despesas;
    }

    private ContentValues getContentValues(Despesa despesa){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.DESPESAS_COLUNA_DESCRICAO, despesa.getDescricao());
        contentValues.put(DBHelper.DESPESAS_COLUNA_DATA, despesa.getData());
        contentValues.put(DBHelper.DESPESAS_COLUNA_VALOR, despesa.getValor());
        contentValues.put(DBHelper.DESPESAS_COLUNA_TIPO, despesa.getTipo());
        return contentValues;
    }

    private Despesa preencheDespesa(Cursor cursor){
        Despesa despesa = new Despesa();
        despesa.setID(cursor.getInt(cursor.getColumnIndex(DBHelper.DESPESAS_COLUNA_ID)));
        despesa.setDescricao(cursor.getString(cursor.getColumnIndex(DBHelper.DESPESAS_COLUNA_DESCRICAO)));
        despesa.setData(cursor.getString(cursor.getColumnIndex(DBHelper.DESPESAS_COLUNA_DATA)));
        despesa.setTipo(cursor.getInt(cursor.getColumnIndex(DBHelper.DESPESAS_COLUNA_TIPO)));
        despesa.setValor(cursor.getDouble(cursor.getColumnIndex(DBHelper.DESPESAS_COLUNA_VALOR)));
        return despesa;
    }
}
