package com.hirend.assessment.presenter;

import android.content.Intent;

import com.hirend.assessment.presenter.ipresenter.IPresenter;
import com.hirend.assessment.view.iview.IView;
import com.perusudroid.rxretro.IResponseListener;

/**
 * Created by HirenD on 28/10/19.
 */

abstract class BasePresenter implements IPresenter,IResponseListener {

    protected String TAG = getClass().getSimpleName();

    private IView iView;

    BasePresenter(IView iView) {
        this.iView = iView;
        iView.bindPresenter(this);
    }


    @Override
    public void onStartPresenter() {

    }

    @Override
    public void onStopPresenter() {

    }

    @Override
    public void onPausePresenter() {

    }

    @Override
    public void onResumePresenter() {

    }

    @Override
    public void onDestroyPresenter() {

    }

    @Override
    public void onActivityResultPresenter(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onSuccess(String s, int i) {

    }

    @Override
    public void onFailure(Throwable throwable, int i) {

    }

    @Override
    public void serverError(String s, int i, int i1) {

    }
}
