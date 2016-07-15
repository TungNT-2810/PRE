package com.zyuternity.erp.adapters.view_holders;

import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zyuternity.erp.R;
import com.zyuternity.erp.databases.model.InstructorModel;

/**
 * Created by ZYuTernity on 7/14/2016.
 */
public class InstructorViewHolder extends RecyclerView.ViewHolder {

    private ImageView instructorImage;
    private TextView instructorName;
    private TextView instructorCode;

    public InstructorViewHolder(View itemView) {
        super(itemView);
        instructorImage = (ImageView) itemView.findViewById(R.id.imv_instructor);
        instructorName = (TextView) itemView.findViewById(R.id.tv_name);
        instructorCode = (TextView) itemView.findViewById(R.id.tv_code);

    }

    public void setData(InstructorModel instructorModel){
        if (instructorModel != null) {
            byte[] decodedBytes = Base64.decode(instructorModel.getImage(), 0);
            instructorImage.setImageBitmap(BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length));
            instructorName.setText(instructorModel.getName());
            instructorCode.setText(instructorModel.getCode());
        }
    }
}
