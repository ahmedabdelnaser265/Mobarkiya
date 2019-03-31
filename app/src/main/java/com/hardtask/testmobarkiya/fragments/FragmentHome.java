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

        //setTypeFace..,
        typeface = ResourcesCompat.getFont(context,R.font.ge_dinar);

        catRecyclerView = (RecyclerView)view.findViewById(R.id.catRecyclerViewHome);

        categoryModelArrayList = new ArrayList<>();

        spotsDialog = new SpotsDialog(context,R.style.Custom);


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
