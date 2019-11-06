package com.hirend.assessment.view.iview;

import com.google.gson.Gson;
import com.hirend.assessment.presenter.ipresenter.IPresenter;
import com.hirend.assessment.utils.CodeSnippets;

/**
 * Created by HirenD on 28/10/19.
 */

public interface IView {

    void showToast(String msg);

    void bindPresenter(IPresenter iPresenter);

    CodeSnippets getCodeSnippet();

    Gson getGson();

}
