package com.hirend.assessment.datasource;


import com.hirend.assessment.dao.IInfoDAO;
import com.hirend.assessment.dao.InfoModel;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by HirenD on 28/10/19.
 */


public class InfoDataSource implements IInfoDataSource {

    protected String TAG = InfoDataSource.class.getSimpleName();
    private final IInfoDAO mIInfoDao;

    public InfoDataSource(IInfoDAO mIInfoDao) {
        this.mIInfoDao = mIInfoDao;
    }


    @Override
    public Flowable<List<InfoModel>> getAllInfo() {
        return mIInfoDao.getAllInfo();
    }

    @Override
    public void insertUsers(InfoModel... user) {
        mIInfoDao.insertInfo(user);
    }

    @Override
    public void deleteAllInfo() {
        mIInfoDao.deleteAllInfo();
    }


}
