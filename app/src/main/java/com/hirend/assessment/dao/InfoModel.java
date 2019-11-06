package com.hirend.assessment.dao;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by HirenD on 28/10/19.
 */


@Entity(tableName = "info")
public class InfoModel {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "title")/*Title*/
    private String title;

    @ColumnInfo(name = "description")/*desciption*/
    private String description;

    @ColumnInfo(name = "imageHref")/*Pic*/
    private String imageHref;


    public InfoModel(@NonNull String title, String description, String imageHref) {
        this.description = description;
        this.title = title;
        this.imageHref = imageHref;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
