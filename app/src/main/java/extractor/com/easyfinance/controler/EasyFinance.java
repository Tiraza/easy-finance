package extractor.com.easyfinance.controler;

import android.app.Application;
import android.content.Context;

import extractor.com.easyfinance.model.dao.DespesaDAO;
import extractor.com.easyfinance.model.dao.ReceitaDAO;

/**
 * @author Muryllo Tiraza
 */
public class EasyFinance extends Application{
    private static Context mContext;
    private static DBHelper mDBHelper;
    private static ReceitaDAO mReceitaDAO;
    private static DespesaDAO mDespesaDAO;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mDBHelper = new DBHelper(mContext);
    }

    public static Context getAppContext() {
        return mContext;
    }

    public static DBHelper getAppDBHelper() {
        return mDBHelper;
    }

    public static ReceitaDAO getReceitaDAO(){
        if(mReceitaDAO == null){
            mReceitaDAO = new ReceitaDAO();
        }
        return mReceitaDAO;
    }

    public static DespesaDAO getDespesaDAO(){
        if(mDespesaDAO == null){
            mDespesaDAO = new DespesaDAO();
        }
        return mDespesaDAO;
    }
}
