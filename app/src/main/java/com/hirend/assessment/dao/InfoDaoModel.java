package com.hirend.assessment.dao;

import androidx.lifecycle.ViewModel;

import com.hirend.assessment.datasource.IInfoDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.internal.operators.completable.CompletableFromAction;

/**
 * Created by HirenD on 28/10/19.
 */

public class InfoDaoModel extends ViewModel {

    protected String TAG = InfoDaoModel.class.getSimpleName();
    private final IInfoDataSource mIInfoDataSource;

    public InfoDaoModel(IInfoDataSource mIInfoDataSource) {
        this.mIInfoDataSource = mIInfoDataSource;
    }

    /**
     * Get all data from db
     *
     * @return a {@link Flowable} that will emit every time the data has been updated.
     */
    public Flowable<List<InfoModel>> getAllInfo() {

        return mIInfoDataSource.getAllInfo();
    }

    /**
     * @param user - Insert InfoModel class directly
     * @return a {@link Completable} that completes when the user name is updated
     */

    public Completable inserUser(final InfoModel... user) {
        return new CompletableFromAction(() -> {

            mIInfoDataSource.insertUsers(user);
        });
    }


    /**
     * @return a {@link Completable}
     */


    public Completable deleteAll() {
        return new CompletableFromAction(mIInfoDataSource::deleteAllInfo);
    }


}