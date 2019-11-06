package com.hirend.assessment.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hirend.assessment.R;
import com.hirend.assessment.adapter.InfoAdapter;
import com.hirend.assessment.dao.InfoDaoModel;
import com.hirend.assessment.databinding.FragmentMainBinding;
import com.hirend.assessment.db.InjectDB;
import com.hirend.assessment.model.ViewModelFactory;
import com.hirend.assessment.model.dto.response.Data;
import com.hirend.assessment.presenter.MainPresenter;
import com.hirend.assessment.presenter.ipresenter.IMainPresenter;
import com.hirend.assessment.view.iview.IMainView;

import java.util.List;


public class MainFragment extends BaseFragment implements IMainView {

    protected String TAG = MainFragment.class.getSimpleName();
    private IMainPresenter iMainPresenter;
    private ViewModelFactory mViewModelFactory;
    private InfoDaoModel infoDaoModel;
    private InfoAdapter infoAdapter;
    private AlphaAnimation alphaAnimation, alphaAnimationShowIcon;
    private FragmentMainBinding fragmentMainBinding;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fragmentMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        //bindViews(); - not needed as we use DataBinding
        return fragmentMainBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setAssets();
        configureRoom();
        //initializing presenter
        iMainPresenter = new MainPresenter(this, infoDaoModel);
        iMainPresenter.onCreatePresenter(getActivity().getIntent().getExtras());
    }

    private void configureRoom() {
        mViewModelFactory = InjectDB.provideViewModelFactory(getActivity());
        infoDaoModel = ViewModelProviders.of(this, mViewModelFactory).get(InfoDaoModel.class);
    }


    private void setAssets() {
        //getting layout included in main layout
        fragmentMainBinding.inflate(getLayoutInflater());
        fragmentMainBinding.rv.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fragmentMainBinding.rv.recyclerView.setAdapter(infoAdapter);
        fragmentMainBinding.rv.swipeRefreshLay.setOnRefreshListener(
                () -> iMainPresenter.doFetchApiData()
        );
        alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(700);
        alphaAnimationShowIcon = new AlphaAnimation(0.2f, 1.0f);
        alphaAnimationShowIcon.setDuration(500);
    }


    @Override
    public void refreshRecyclerData(List<Data> newData) {

        if (fragmentMainBinding.rv.swipeRefreshLay.isRefreshing()) {
            fragmentMainBinding.rv.swipeRefreshLay.setRefreshing(false);
        }
        infoAdapter = new InfoAdapter(newData);
        fragmentMainBinding.rv.recyclerView.setAdapter(infoAdapter);
    }
}
