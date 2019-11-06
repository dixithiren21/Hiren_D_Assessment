package com.hirend.assessment.view.activity;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;
import com.hirend.assessment.R;
import com.hirend.assessment.model.MessageEvent;
import com.hirend.assessment.presenter.ipresenter.IPresenter;
import com.hirend.assessment.utils.CodeSnippets;
import com.hirend.assessment.view.iview.IView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by HirenD on 28/10/19.
 */

public class BaseActivity extends AppCompatActivity implements IView {

    protected String TAG = BaseActivity.class.getSimpleName();
    private IPresenter iPresenter;
    private CodeSnippets mCodeSnippet;
    protected View mParentView;
    private Gson gson;
    private TextView textView;

    protected void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        textView = findViewById(R.id.activity_title);
//        textView.setText("Info data");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    // UI updates must run on MainThread
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {
        textView.setText(event.getMessage());
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        mParentView = getWindow().getDecorView().findViewById(android.R.id.content);
        return super.onCreateView(name, context, attrs);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (iPresenter != null) iPresenter.onStartPresenter();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (iPresenter != null) iPresenter.onStopPresenter();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (iPresenter != null) iPresenter.onPausePresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (iPresenter != null) iPresenter.onResumePresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (iPresenter != null) iPresenter.onDestroyPresenter();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (iPresenter != null) iPresenter.onActivityResultPresenter(requestCode, resultCode, data);
    }

    public void bindPresenter(IPresenter iPresenter) {
        this.iPresenter = iPresenter;
    }

    @Override
    public CodeSnippets getCodeSnippet() {
        if (mCodeSnippet == null) {
            mCodeSnippet = new CodeSnippets(this);
            return mCodeSnippet;
        }
        return mCodeSnippet;
    }


    @Override
    public Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }


}
