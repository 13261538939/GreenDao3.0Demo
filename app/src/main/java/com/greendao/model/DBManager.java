package com.greendao.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by itsdon on 16/7/30.
 */
public class DBManager {
    private Context context;
    private DaoMaster.DevOpenHelper devOpenHelper;
    private static DBManager instance;
    private static final String DB_NAME = "test_db";

    private DBManager(Context context){
        this.context = context;
        devOpenHelper = new DaoMaster.DevOpenHelper(context,DB_NAME,null);
    }

    public static DBManager getInstance(Context context){
        if(instance == null){
            synchronized (DBManager.class){
                if(instance == null){
                    instance = new DBManager(context);
                }
            }
        }
        return instance;
    }


    public SQLiteDatabase getReadableDatabase(){
        if(devOpenHelper == null){
            devOpenHelper = new DaoMaster.DevOpenHelper(context,DB_NAME,null);
        }
        SQLiteDatabase db = devOpenHelper.getReadableDatabase();
        return  db;
    }

}
