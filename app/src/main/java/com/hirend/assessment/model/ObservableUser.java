package com.hirend.assessment.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;

/**
 * Created by HirenD on 28/10/19.
 */

public class ObservableUser extends BaseObservable implements Parcelable {

    protected String TAG = ObservableUser.class.getSimpleName();
    private String id;
    private String title;
    private String description;
    private String imageHref;


    public ObservableUser() {
    }


    protected ObservableUser(Parcel in) {
        id = in.readString();
        title = in.readString();
        description = in.readString();
        imageHref = in.readString();

    }

    public static final Creator<ObservableUser> CREATOR = new Creator<ObservableUser>() {
        @Override
        public ObservableUser createFromParcel(Parcel in) {
            return new ObservableUser(in);
        }

        @Override
        public ObservableUser[] newArray(int size) {
            return new ObservableUser[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(imageHref);

    }
}
