package com.hirend.assessment.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.hirend.assessment.presenter.ipresenter.IPresenter;
import com.hirend.assessment.utils.CodeSnippets;
import com.hirend.assessment.view.iview.IView;

/**
 * Created by HirenD on 28/10/19.
 */

public class BaseFragment extends Fragment implements IView {

    protected String TAG = BaseFragment.class.getSimpleName();
    private IPresenter iPresenter;
    private CodeSnippets mCodeSnippet;
    private Gson gson;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (iPresenter != null) iPresenter.onStartPresenter();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (iPresenter != null) iPresenter.onStopPresenter();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (iPresenter != null) iPresenter.onPausePresenter();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (iPresenter != null) iPresenter.onResumePresenter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (iPresenter != null) iPresenter.onDestroyPresenter();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (iPresenter != null) iPresenter.onActivityResultPresenter(requestCode, resultCode, data);
    }

    public void bindPresenter(IPresenter iPresenter) {
        this.iPresenter = iPresenter;
    }

    @Override
    public CodeSnippets getCodeSnippet() {
        if (mCodeSnippet == null) {
            mCodeSnippet = new CodeSnippets(getActivity());
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
