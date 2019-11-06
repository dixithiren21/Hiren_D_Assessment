package com.hirend.assessment.view.iview;

import com.hirend.assessment.model.dto.response.Data;

import java.util.List;

/**
 * Created by HirenD on 28/10/19.
 */

public interface IMainView extends IView{

    void refreshRecyclerData(List<Data> newData);
}
