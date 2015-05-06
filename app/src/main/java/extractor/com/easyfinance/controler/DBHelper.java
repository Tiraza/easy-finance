package extractor.com.easyfinance.controler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Muryllo Tiraza
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "EASYFINANCE.db";

    public static final String TABELA_DESPESAS = "DESPESAS";
    public static final String DESPESAS_COLUNA_ID = "ID";
    public static final String DESPESAS_COLUNA_TIPO = "TIPO";
    public static final String DESPESAS_COLUNA_DESCRICAO = "DESC";
    public static final String DESPESAS_COLUNA_DATA = "DATA";
    public static final String DESPESAS_COLUNA_VALOR = "VALOR";

    public static final String TABELA_RECEITAS = "RECEITAS";
    public static final String RECEITAS_COLUNA_ID = "ID";
    public static final String RECEITAS_COLUNA_TIPO = "TIPO";
    public static final String RECEITAS_COLUNA_DESCRICAO = "DESC";
    public static final String RECEITAS_COLUNA_DATA = "DATA";
    public static final String RECEITAS_COLUNA_VALOR = "VALOR";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public SQLiteDatabase getDataBase(){
        return this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder CRIA_TABELA_DESPESAS = new StringBuilder();
        CRIA_TABELA_DESPESAS.append("CREATE TABLE ").append(TABELA_DESPESAS).append(" (");
        CRIA_TABELA_DESPESAS.append(DESPESAS_COLUNA_ID).append(" INTEGER PRIMARY KEY, ");
        CRIA_TABELA_DESPESAS.append(DESPESAS_COLUNA_TIPO).append(" INTEGER, ");
        CRIA_TABELA_DESPESAS.append(DESPESAS_COLUNA_DESCRICAO).append(" TEXT, ");
        CRIA_TABELA_DESPESAS.append(DESPESAS_COLUNA_DATA).append(" TEXT, ");
        CRIA_TABELA_DESPESAS.append(DESPESAS_COLUNA_VALOR).append(" REAL)");

        StringBuilder CRIA_TABELA_RECEITAS = new StringBuilder();
        CRIA_TABELA_RECEITAS.append("CREATE TABLE ").append(TABELA_RECEITAS).append(" (");
        CRIA_TABELA_RECEITAS.append(RECEITAS_COLUNA_ID).append(" INTEGER PRIMARY KEY, ");
        CRIA_TABELA_RECEITAS.append(RECEITAS_COLUNA_TIPO).append(" INTEGER, ");
        CRIA_TABELA_RECEITAS.append(RECEITAS_COLUNA_DESCRICAO).append(" TEXT, ");
        CRIA_TABELA_RECEITAS.append(RECEITAS_COLUNA_DATA).append(" TEXT, ");
        CRIA_TABELA_RECEITAS.append(RECEITAS_COLUNA_VALOR).append(" REAL)");

        db.execSQL(CRIA_TABELA_DESPESAS.toString());
        db.execSQL(CRIA_TABELA_RECEITAS.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_DESPESAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_RECEITAS);
        onCreate(db);
    }
}
