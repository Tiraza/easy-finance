package extractor.com.easyfinance.controler;

import android.app.Application;
import android.content.Context;

/**
 * @author Muryllo Tiraza
 */
public class EasyFinance extends Application{
    private static Context mContext;
    private static DBHelper mDBHelper;

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
}
