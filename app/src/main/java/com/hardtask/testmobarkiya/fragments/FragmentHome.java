package com.hardtask.testmobarkiya.fragments;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.hardtask.testmobarkiya.HomeActivity;
import com.hardtask.testmobarkiya.R;
import com.hardtask.testmobarkiya.adapters.CatModelRecyclerViewAdapter;
import com.hardtask.testmobarkiya.models.CategoryModel;
import com.hardtask.testmobarkiya.networks.CallApi;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.hardtask.testmobarkiya.HomeActivity.showHideLayout;
import static com.hardtask.testmobarkiya.HomeActivity.slideDownLayout;
import static com.hardtask.testmobarkiya.HomeActivity.slideUpLayout;

public class FragmentHome extends Fragment {

    RecyclerView catRecyclerView ;

    private ArrayList<CategoryModel> categoryModelArrayList;

    CatModelRecyclerViewAdapter catAdapter ;

    public static TextView homeText ;

    Typeface typeface ;

    android.support.v4.app.FragmentManager mfragmentmanager;

    static FrameLayout container ;

    SpotsDialog spotsDialog;

    public Context context ;

    public static ImageView shareImageHome ,searchImageHome,likeImageHome,disLikeHome ;

    boolean isUserScrolling ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.home_fagment, container, false);

        context = container.getContext();

        mfragmentmanager = getActivity().getSupportFragmentManager();

        initViews(view);

        //getCategories..
        getCategories(String.valueOf(0),"YRW76GXj3f63ElMx",String.valueOf(1));


        return view ;
    }

    private void initViews(View view)

    {

        //Load animation
        final Animation slide_down = AnimationUtils.loadAnimation(context,
                R.anim.slide_down);

        final Animation slide_up = AnimationUtils.loadAnimation(context,
                R.anim.slide_up);

// Start animation

        //setTypeFace..,
        typeface = ResourcesCompat.getFont(context,R.font.ge_dinar);

        catRecyclerView = (RecyclerView)view.findViewById(R.id.catRecyclerViewHome);

        categoryModelArrayList = new ArrayList<>();

        spotsDialog = new SpotsDialog(context,R.style.Custom);

        showHideLayout.setVisibility(View.VISIBLE);

        //setup recyclerView hide show ..,
        catRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                    // Do something
                } else if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    // Do something
                } else {
                    // Do something
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                super.onScrolled(recyclerView, dx, dy);


                    if(dy > 0){
                        //Scroll list Down

                        showHideLayout.startAnimation(slide_up);

                        showHideLayout.setVisibility(View.GONE);



                    }
                    else{

                            //This Your Top View do Something

                        showHideLayout.startAnimation(slide_down);

                        showHideLayout.setVisibility(View.VISIBLE);


                    }
            }
        });


    }


    private void getCategories(String v1 , String v2 , String v3)
    {
        spotsDialog.show();
        CallApi.getAPI().getCategories(v1, v2, v3, new Callback<ArrayList<CategoryModel>>() {
            @Override
            public void success(ArrayList<CategoryModel> categoryModels, Response response) {

                spotsDialog.dismiss();

                categoryModelArrayList.addAll(categoryModels);

                //setup RecyclerView ..,
                catRecyclerView.setItemAnimator(new DefaultItemAnimator());

                StaggeredGridLayoutManager lom = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

                catRecyclerView.setLayoutManager(lom);

                //setAdapter ..,

                catAdapter = new CatModelRecyclerViewAdapter(context, categoryModelArrayList,

                        new CatModelRecyclerViewAdapter.onClick() {

                            @Override
                            public void OnClick(CategoryModel item) {
                                Log.e("itemID : ",item.getId().toString());
                                Bundle bundle = new Bundle();

                                bundle.putInt("Cat_id", item.getId());

                                bundle.putString("Cat_name", item.getCatNameAR());

                                setupSubCategoriesFragment(bundle);



                            }
                        });

                catRecyclerView.setAdapter(catAdapter);

                catAdapter.notifyDataSetChanged();

                catAdapter.notifyItemChanged(categoryModelArrayList.size());

//                catAdapter.notifyItemChanged(cateAdArrayList.size());
            }

            @Override
            public void failure(RetrofitError error) {

                spotsDialog.dismiss();

                Log.e("errorGetCategories",error.getMessage());

            }
        });
    }


    public void setupSubCategoriesFragment(Bundle bundle)
    {

        SubCategoryResultFrafment fragment = new SubCategoryResultFrafment();

        FragmentTransaction tr = getActivity().getSupportFragmentManager().beginTransaction();

        tr.replace(R.id.fragmentsContainer,fragment);

        tr.addToBackStack(fragment.getClass().getName());

        fragment.setArguments(bundle);

        tr.commit();

    }


}
