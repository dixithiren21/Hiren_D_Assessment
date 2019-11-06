package com.hirend.assessment.datasource;

/**
 * Created by HirenD on 28/10/19.
 */


import com.hirend.assessment.dao.InfoModel;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Access point for managing user data.
 */
public interface IInfoDataSource {


    /**
     * Gets the data from the data source.
     *
     * @return the data from the data source in list
     */
    Flowable<List<InfoModel>> getAllInfo();


    /**
     * Inserts the user into the data source, or, if this is an existing user, updates it.
     *
     * @param user the user to be inserted
     */
    void insertUsers(InfoModel... user);


    /**
     * Deletes all users from the data source.
     */
    void deleteAllInfo();
}