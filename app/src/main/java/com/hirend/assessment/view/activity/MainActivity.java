package com.hirend.assessment.view.activity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.hirend.assessment.R;
import com.hirend.assessment.databinding.ActivityMainBinding;
import com.hirend.assessment.view.fragment.MainFragment;


/**
 * Created by HirenD on 28/10/19.
 */

public class MainActivity extends BaseActivity {

    protected String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //inflating layout with DataBindingUtil
        activityMainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        setToolbar();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }

    }

}
