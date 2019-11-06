package com.hirend.assessment.model;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.hirend.assessment.dao.InfoDaoModel;
import com.hirend.assessment.datasource.IInfoDataSource;

/**
 * Created by HirenD on 28/10/19.
 */


public class ViewModelFactory implements ViewModelProvider.Factory {

    protected String TAG = ViewModelFactory.class.getSimpleName();
    private final IInfoDataSource mIInfoDataSource;

    public ViewModelFactory(IInfoDataSource mIInfoDataSource) {
        this.mIInfoDataSource = mIInfoDataSource;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(InfoDaoModel.class)) {
            return (T) new InfoDaoModel(mIInfoDataSource);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}