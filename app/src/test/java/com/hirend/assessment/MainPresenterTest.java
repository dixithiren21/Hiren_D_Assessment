package com.hirend.assessment;

import com.hirend.assessment.model.ApiInterface;
import com.hirend.assessment.model.dto.response.InfoResponse;
import com.hirend.assessment.presenter.MainPresenter;
import com.hirend.assessment.view.iview.IMainView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by HirenD on 28/10/19.
 */

public class MainPresenterTest {
    @Mock
    private ApiInterface apiInterface;

    @Mock
    private IMainView view;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void fetchValidDataShouldLoadIntoView() {

        InfoResponse data = new InfoResponse(null, null);

//        when(apiInterface.getInfo()).thenReturn(Observable.just((Response<ResponseBody>)));

        MainPresenter mainPresenter = new MainPresenter(view, null);

        mainPresenter.doFetchApiData();

        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view, times(1)).getGson();
        inOrder.verify(view, times(1)).getCodeSnippet();
    }

    @Test
    public void fetchErrorShouldReturnErrorToView() {

        Exception exception = new Exception();

//        when(apiInterface.getInfo()).thenReturn(Observable.<InfoResponse>error(exception));

        MainPresenter mainPresenter = new MainPresenter(view, null);

        mainPresenter.doFetchApiData();

        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view, times(1)).getCodeSnippet();
        inOrder.verify(view, times(1)).getGson();
        verify(view, never()).getCodeSnippet();
    }
}