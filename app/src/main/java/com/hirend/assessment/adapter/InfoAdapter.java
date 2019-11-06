package com.hirend.assessment.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hirend.assessment.R;
import com.hirend.assessment.databinding.RowInfoBinding;
import com.hirend.assessment.model.dto.response.Data;

import java.util.List;

/**
 * Created by HirenD on 28/10/19.
 */

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> {

    protected String TAG = InfoAdapter.class.getSimpleName();
    private List<Data> oldData;

    public InfoAdapter(List<Data> newData) {
        this.oldData = newData;
    }

    @Override
    public InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // inflating layout with DataBindingUtil
        RowInfoBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.row_info,
                        parent, false);

        return new InfoViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(InfoViewHolder holder, int position) {
        /*
        As we use DataBinding, not required to set text for each view.
         */
        Data myData = oldData.get(position);
        holder.rowInfoBinding.setInfoData(myData);
        holder.rowInfoBinding.executePendingBindings();
        holder.itemView.setTag(myData);
    }

    @Override
    public int getItemCount() {
        return oldData == null ? 0 : oldData.size();
    }


    public class InfoViewHolder extends RecyclerView.ViewHolder {

        private RowInfoBinding rowInfoBinding;

        public InfoViewHolder(RowInfoBinding binding) {
            super(binding.getRoot());
            this.rowInfoBinding = binding;

            /*
            As we use DataBinding, not required to inflate view and so reduced lot of code!
            */

        }

    }
}
