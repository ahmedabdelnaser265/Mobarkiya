package com.hardtask.testmobarkiya.adapters;

import android.app.FragmentManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hardtask.testmobarkiya.R;
import com.hardtask.testmobarkiya.models.Shopphoto;

import java.util.ArrayList;


/**
 * Created by it_ah on 29/03/2019.
 */

public class PhotoGalleryAdapter extends PagerAdapter {

    public Context context ;

    LayoutInflater layoutInflater;

    FragmentManager fragmentManager ;

    public ArrayList<Shopphoto>detailsProfileDataModelArrayList;

    public PhotoGalleryAdapter(Context context, ArrayList<Shopphoto> detailsProfileDataModelArrayList) {
        this.context = context;
        this.detailsProfileDataModelArrayList = detailsProfileDataModelArrayList;
    }


    @Override
    public int getCount() {
        return detailsProfileDataModelArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {

        layoutInflater= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View mview = layoutInflater.inflate(R.layout.photo_gallery_custom,null,false);

        Shopphoto shopphoto = detailsProfileDataModelArrayList.get(position);

        ImageView catImage ;

        catImage = (ImageView)mview.findViewById(R.id.customViewImagesAsGallery);

        Glide.with(context).load(shopphoto.getPhotoname()).into(catImage);

        //init view pager ..
        ViewPager viewPager = (ViewPager)container;

        viewPager.addView(mview);

        return mview ;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        //init view pager ..
        ViewPager viewPager = (ViewPager)container;

        View view = (View)object;

        viewPager.removeView(view);
    }
}
