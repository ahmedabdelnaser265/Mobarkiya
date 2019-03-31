package com.hardtask.testmobarkiya.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hardtask.testmobarkiya.R;
import com.hardtask.testmobarkiya.models.CategoryModel;

import java.util.ArrayList;

/**
 * Created by it_ah on 27/03/2019.
 */

public class CatModelRecyclerViewAdapter extends RecyclerView.Adapter<CatModelRecyclerViewAdapter.ViewHolder> {

    public Context context ;

    public ArrayList<CategoryModel>catDataModelList ;

    public final onClick Listner ;


    Typeface typeface ;


    public CatModelRecyclerViewAdapter
            (Context context, ArrayList<CategoryModel> catDataModelList, onClick listner) {

        this.context = context;

        this.catDataModelList = catDataModelList;

        Listner = listner;

    }

    public interface onClick
    {
        public void OnClick (CategoryModel item);
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



             View view = LayoutInflater.from(context).inflate(R.layout.custom_category,null,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bindData(catDataModelList.get(position),Listner);
    }

    @Override
    public int getItemCount() {
        return catDataModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView catName ;

        ImageView catAds ;

        LinearLayout customLayuot ;

        public ViewHolder(View itemView) {
            super(itemView);

            catName = (TextView)itemView.findViewById(R.id.customCategoryName);

            customLayuot = (LinearLayout)itemView.findViewById(R.id.customLayoutDisplayAds);

            catAds = (ImageView)itemView.findViewById(R.id.customCategoryAds);

            //setTypeFace..,
            typeface = ResourcesCompat.getFont(context,R.font.ge_dinar);

            catName.setTypeface(typeface);
        }


        //Binding Data ..,
        private void bindData(final CategoryModel item , final onClick listner)
        {
            catName.setText(item.getCatNameAR());

            catName.setTypeface(typeface);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listner.OnClick(item);

                }
            });


        }
    }
}
