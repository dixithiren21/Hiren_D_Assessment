package com.hirend.assessment.presenter.ipresenter;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by HirenD on 28/10/19.
 */

public interface IPresenter {

    void onCreatePresenter(Bundle bundle);

    void onStartPresenter();

    void onStopPresenter();

    void onPausePresenter();

    void onResumePresenter();

    void onDestroyPresenter();

    void onActivityResultPresenter(int requestCode, int resultCode, Intent data);
}
