package com.hirend.assessment.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.hirend.assessment.dao.InfoModel;
import com.hirend.assessment.dao.IInfoDAO;


/**
 * Created by HirenD on 28/10/19.
 */

@Database(entities = {InfoModel.class}, version = 1, exportSchema = false)
public abstract class InfoDB extends RoomDatabase {
    private static volatile InfoDB INSTANCE;

    public abstract IInfoDAO iuserDao();

    public static InfoDB getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (InfoDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            InfoDB.class, "Info.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
