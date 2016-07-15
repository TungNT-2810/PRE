package com.zyuternity.erp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zyuternity.erp.R;
import com.zyuternity.erp.adapters.view_holders.InstructorViewHolder;
import com.zyuternity.erp.databases.model.InstructorModel;

import java.util.List;

/**
 * Created by ZYuTernity on 7/14/2016.
 */
public class InstructorRecyclerViewAdapter extends RecyclerView.Adapter<InstructorViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<InstructorModel> instructorModelList;

    public InstructorRecyclerViewAdapter(List<InstructorModel> instructorModelList) {
        this.instructorModelList = instructorModelList;
    }

    public void setContext(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(this.context);
    }


    @Override
    public InstructorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_instructor, parent, false);
        InstructorViewHolder instructorViewHolder = new InstructorViewHolder(view);
        return instructorViewHolder;
    }

    @Override
    public void onBindViewHolder(InstructorViewHolder holder, int position) {
        holder.setData(instructorModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return instructorModelList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
