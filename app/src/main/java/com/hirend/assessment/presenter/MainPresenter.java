package com.hirend.assessment.presenter;

import android.os.Bundle;
import android.util.Log;

import com.hirend.assessment.dao.InfoDaoModel;
import com.hirend.assessment.dao.InfoModel;
import com.hirend.assessment.model.ApiClient;
import com.hirend.assessment.model.MessageEvent;
import com.hirend.assessment.model.ObservableUser;
import com.hirend.assessment.model.dto.response.Data;
import com.hirend.assessment.model.dto.response.InfoResponse;
import com.hirend.assessment.presenter.ipresenter.IMainPresenter;
import com.hirend.assessment.view.iview.IMainView;
import com.perusudroid.rxretro.IResponseListener;
import com.perusudroid.rxretro.RXRetro;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HirenD on 28/10/19.
 */

public class MainPresenter extends BasePresenter implements IMainPresenter, IResponseListener {

    protected String TAG = MainPresenter.class.getSimpleName();
    private IMainView iMainView;
    private InfoDaoModel infoDaoModel;
    private List<Data> data = new ArrayList<>();
    private ArrayList<ObservableUser> observeList;

    private final CompositeDisposable mDisposable = new CompositeDisposable();


    public MainPresenter(IMainView iMainView, InfoDaoModel infoDaoModel) {
        super(iMainView);
        this.iMainView = iMainView;
        this.infoDaoModel = infoDaoModel;
    }


    @Override
    public void onCreatePresenter(Bundle bundle) {

        /**
         *  if no internet connection, then get data from localDB else, get data from api
         */

        if (iMainView.getCodeSnippet().hasNetworkConnection()) {
            doGetLocalDB();
            iMainView.showToast("No network - Showing local db");
        } else {
            iMainView.showToast("Has network - Api call");
            doNetworkOperations();
        }

    }


    @Override
    public void onStartPresenter() {
        super.onStartPresenter();
    }

    @Override
    public void onStopPresenter() {
        super.onStopPresenter();
        mDisposable.clear();
    }

    private void doNetworkOperations() {

        RXRetro.getInstance().retrofitEnque(ApiClient.getInterface().getInfo(), this, 1);
    }


    /**
     * Getting all data from SQLiteDB and setting in recyclerView
     */
    private void doGetLocalDB() {
        data.clear();
        mDisposable.add(infoDaoModel.getAllInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        UsersfromDb -> {

                            if (UsersfromDb.size() > 0) {
                                observeList = new ArrayList<>();
                                for (int i = 0; i < UsersfromDb.size(); i++) {
                                    observeList.add(new ObservableUser());
                                    data.add(new Data(UsersfromDb.get(i).getTitle(), UsersfromDb.get(i).getDescription(), UsersfromDb.get(i).getImageHref()));
                                    iMainView.refreshRecyclerData(data);

                                }
                            }

                        }
                )
        );
    }


    @Override
    public void doFetchApiData() {
        if (iMainView.getCodeSnippet().hasNetworkConnection()) {
            doGetLocalDB();
        } else {
            doNetworkOperations();
        }
    }


    /*
     On SucessfulApi Response, Clearing SQLiteDB and storing new data fetched from API
     and then getting all data from DB and showing in recycler view
     */

    @Override
    public void onSuccess(String str, int requestId) {

        if (requestId == 1) {
            data.clear();
            InfoResponse info = iMainView.getGson().fromJson(str, InfoResponse.class);
            InfoModel[] users = new InfoModel[info.getRows().size()];
            EventBus.getDefault().postSticky(new MessageEvent(info.getTitle()));

            for (int i = 0; i < users.length; i++) {

                InfoModel user = new InfoModel(info.getRows().get(i).getTitle(),
                        info.getRows().get(i).getDescription(),
                        info.getRows().get(i).getImageHref());
                users[i] = user;
            }


            mDisposable.add(infoDaoModel.deleteAll()
                    .andThen(infoDaoModel.inserUser(users))
                    .andThen(infoDaoModel.getAllInfo())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            UsersfromDb -> {
                                Log.d(TAG, "onStart: " + UsersfromDb.size());
                                if (UsersfromDb.size() > 0) {

                                    observeList = new ArrayList<>();
                                    for (int i = 0; i < UsersfromDb.size(); i++) {
                                        observeList.add(new ObservableUser());

                                        data.add(new Data(UsersfromDb.get(i).getTitle(), UsersfromDb.get(i).getDescription(),
                                                UsersfromDb.get(i).getImageHref()));

                                        iMainView.refreshRecyclerData(data);

                                    }
                                }

                            }
                    )
            );
        }

    }


    /*
      onFailure API response, if data exists in localDB then show it.
     */

    @Override
    public void onFailure(Throwable throwable, int i) {
        iMainView.showToast(throwable.getLocalizedMessage() + " Showing local DB");
        doGetLocalDB();
    }
}
