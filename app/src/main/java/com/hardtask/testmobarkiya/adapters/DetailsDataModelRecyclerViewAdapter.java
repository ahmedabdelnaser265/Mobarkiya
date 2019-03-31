package com.hardtask.testmobarkiya.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hardtask.testmobarkiya.R;
import com.hardtask.testmobarkiya.models.DetailsProfileDataModel;
import com.hardtask.testmobarkiya.models.Shopphoto;

import java.util.ArrayList;

/**
 * Created by it_ah on 28/03/2019.
 */

public class DetailsDataModelRecyclerViewAdapter extends RecyclerView.Adapter<DetailsDataModelRecyclerViewAdapter.ViewHolder>{

    public Context context ;

    public ArrayList<Shopphoto>shopphotoArrayList ;

    public final onClick listner ;


    public interface onClick
    {
        public void OnClick (Shopphoto item);
    }

    public DetailsDataModelRecyclerViewAdapter(Context context, ArrayList<Shopphoto>
            shopphotoArrayList, onClick listner) {

        this.context = context;

        this.shopphotoArrayList = shopphotoArrayList;

        this.listner = listner;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(context).inflate(R.layout.photo_gallery_details,null,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.bindData(shopphotoArrayList.get(position),listner);

//        Toast.makeText(context, "pos"+position, Toast.LENGTH_SHORT).show();

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(context, "This position is :"+position, Toast.LENGTH_SHORT).show();

                final AlertDialog alertDialog ;

                AlertDialog.Builder builder ;

                builder = new AlertDialog.Builder(context,R.style.DialogTheme);

                View view = LayoutInflater.from(context).inflate

                        (R.layout.custom_alert_display_view_pager,null,false);

                builder.setView(view);

                alertDialog = builder.create();

                ViewPager viewPager = (ViewPager)view.findViewById(R.id.myCustomViewPagerDisplayViewPager);

                PhotoGalleryAdapter adapter = new PhotoGalleryAdapter(context,shopphotoArrayList);

                viewPager.setAdapter(adapter);

                viewPager.setCurrentItem(position);

                alertDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return shopphotoArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView ;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView)itemView.findViewById(R.id.customPhotoGallery);
        }

        private void bindData(final Shopphoto item , final onClick listner)
        {
            Glide.with(context).load(item.getPhotoname()).into(imageView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listner.OnClick(item);
                }
            });
        }
    }
}
