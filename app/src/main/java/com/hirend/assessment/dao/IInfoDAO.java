package com.hirend.assessment.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by HirenD on 28/10/19.
 */


@Dao
public interface IInfoDAO {


    /**
     * Get all the data from database as list.
     */

    @Query("SELECT * FROM info")
    Flowable<List<InfoModel>> getAllInfo();


    /**
     * Insert a info in the database. If the info already exists, replace it.
     *
     * @param info the info to be inserted.
     */

    @Insert
    void insertInfo(InfoModel... info);

    /**
     * Delete all info.
     */
    @Query("DELETE FROM info")
    void deleteAllInfo();
}