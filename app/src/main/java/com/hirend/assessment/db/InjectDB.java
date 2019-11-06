package com.hirend.assessment.db;

import android.content.Context;

import com.hirend.assessment.datasource.IInfoDataSource;
import com.hirend.assessment.datasource.InfoDataSource;
import com.hirend.assessment.model.ViewModelFactory;

/**
 * Created by HirenD on 28/10/19.
 */


public class InjectDB {

    public static IInfoDataSource provideUserDataSource(Context context) {
        InfoDB database = InfoDB.getInstance(context);
        return new InfoDataSource(database.iuserDao());
    }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        IInfoDataSource dataSource = provideUserDataSource(context);
        return new ViewModelFactory(dataSource);
    }
}
