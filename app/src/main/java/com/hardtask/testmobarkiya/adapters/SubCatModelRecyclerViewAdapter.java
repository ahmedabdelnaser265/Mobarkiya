package com.hardtask.testmobarkiya.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hardtask.testmobarkiya.HomeActivity;
import com.hardtask.testmobarkiya.R;
import com.hardtask.testmobarkiya.models.SubCategoryDataModel;

import java.util.ArrayList;

/**
 * Created by it_ah on 27/03/2019.
 */

public class SubCatModelRecyclerViewAdapter extends RecyclerView.Adapter<SubCatModelRecyclerViewAdapter.ViewHolder> {

    public Context context ;

    public ArrayList<SubCategoryDataModel>subCategoryDataModelArrayList ;

    public final onClick listner;

    public final callonClick makeCall1 ;

    Typeface typeface ;

    public SubCatModelRecyclerViewAdapter

            (Context context, ArrayList<SubCategoryDataModel> subCategoryDataModelArrayList,

             onClick listner,callonClick makeCall) {

        this.context = context;

        this.subCategoryDataModelArrayList = subCategoryDataModelArrayList;

        this.listner = listner;

        this.makeCall1 = makeCall;

    }

    public interface onClick
    {
        void OnClick(SubCategoryDataModel item);
    }

    public interface callonClick
    {
        void MakeCall(SubCategoryDataModel item2);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.custom_result_categories,null,false);

        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        view.setLayoutParams(lp);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bindData(subCategoryDataModelArrayList.get(position),listner,makeCall1);

    }

    @Override
    public int getItemCount() {
        return subCategoryDataModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView subCatImage ;

        TextView subCatName ;

        TextView subCatNumber ;

        ImageView subCatCallNumberImg;

        ImageView showMore ;


        public ViewHolder(View itemView) {
            super(itemView);

            subCatImage = (ImageView)itemView.findViewById(R.id.subCatImageResult);

            subCatCallNumberImg = (ImageView)itemView.findViewById(R.id.subCatcallNumberResult);

            subCatName = (TextView)itemView.findViewById(R.id.subCatNameResult);

            subCatNumber = (TextView)itemView.findViewById(R.id.subCatNumberResult);

            showMore= (ImageView)itemView.findViewById(R.id.subCatImgShowMoreResult);

        }

        @SuppressLint("MissingPermission")
        private void bindData(final SubCategoryDataModel item , final onClick listner,final callonClick makeCall)
        {
            Glide.with(context).load(item.getLogo()).into(subCatImage);

            //setTypeFace..,
            typeface = ResourcesCompat.getFont(context,R.font.ge_dinar);


            subCatName.setText(item.getNameAR());

            subCatName.setTypeface(typeface);

            subCatNumber.setText(item.getTele());

            //intent call..,
            subCatCallNumberImg.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    makeCall.MakeCall(item);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listner.OnClick(item);
                }
            });


        }
    }
}
