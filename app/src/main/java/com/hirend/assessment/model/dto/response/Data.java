package com.hirend.assessment.model.dto.response;

import androidx.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;

import com.hirend.assessment.R;
import com.squareup.picasso.Picasso;

/**
 * Awesome Pojo Generator
 */
public class Data {
    private static final String TAG = Data.class.getSimpleName();
    private String title;
    private String description;
    private String imageHref;

    public Data(String title, String description, String imageHref) {
        this.title = title;
        this.description = description;
        this.imageHref = imageHref;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }

    public String getImageHref() {
        return imageHref;
    }

    @BindingAdapter({"bind:imageHref"})
    public static void loadImage(ImageView view, String imageUrl) {
        Log.d(TAG, "loadImage: " + imageUrl);
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(view);
    }


}