package com.hardtask.testmobarkiya.fragments;


import android.Manifest;
import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.hardtask.testmobarkiya.R;
import com.hardtask.testmobarkiya.adapters.SubCatModelRecyclerViewAdapter;
import com.hardtask.testmobarkiya.models.SubCategoryDataModel;
import com.hardtask.testmobarkiya.networks.CallApi;
import com.hardtask.testmobarkiya.sharedPreference.SharedPrefManager;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import static com.hardtask.testmobarkiya.HomeActivity.homeText;
import static com.hardtask.testmobarkiya.HomeActivity.likeImageHome;
import static com.hardtask.testmobarkiya.HomeActivity.shareImageHome;
import static com.hardtask.testmobarkiya.HomeActivity.showHideLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubCategoryResultFrafment extends Fragment {

    private Context  context ;

    public int catId;

    public String catName;

    RecyclerView recyclerView ;

    private ArrayList<SubCategoryDataModel>subCategoryDataModelArrayList;

    private SubCatModelRecyclerViewAdapter adapter ;

    Typeface typeface ;

    SpotsDialog spotsDialog ;

    int REQUEST_CALL_PHONE = 1 ;

    FrameLayout containerLayout ;

    FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categories_result, container, false);

        context = container.getContext();

        fragmentManager = getFragmentManager();

        //initView ..,
        initViews(view);

        containerLayout.setVisibility(View.GONE);

        //get Bundle Data ..,
        getBundle();

        //getSubCat
        getSubCategories(String.valueOf(catId));

        shareImageHome.setVisibility(View.GONE);

        likeImageHome.setVisibility(View.GONE);


        return view ;
    }

    private void getBundle() {
        {
            Bundle bundle = this.getArguments();
            if (bundle != null) {

                catId = bundle.getInt("Cat_id");

                catName = bundle.getString("Cat_name");

                homeText.setText(catName);

                homeText.setTypeface(typeface);

            }
        }
    }

    private void initViews(View view) {

        //Load animation
        final Animation slide_down = AnimationUtils.loadAnimation(context,
                R.anim.slide_down);

        final Animation slide_up = AnimationUtils.loadAnimation(context,
                R.anim.slide_up);

        recyclerView=  (RecyclerView) view.findViewById(R.id.resultCatRecyclerView);

        containerLayout = (FrameLayout)view.findViewById(R.id.detailsContainer);

        subCategoryDataModelArrayList = new ArrayList<>();

        //setTypeFace..,
        typeface = ResourcesCompat.getFont(context,R.font.ge_dinar);

        spotsDialog = new SpotsDialog(context,R.style.Custom);

        likeImageHome.setVisibility(View.GONE);

        shareImageHome.setVisibility(View.GONE);

        showHideLayout.setVisibility(View.VISIBLE);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

    private void getSubCategories(String Cat_ID)
    {
        spotsDialog.show();

        CallApi.getAPI().getSubCategories(Cat_ID, String.valueOf(0), String.valueOf(0), String.valueOf(1),"",

                "YRW76GXj3f63ElMx", String.valueOf(0), new Callback<ArrayList<SubCategoryDataModel>>() {
                    @Override

                    public void success(ArrayList<SubCategoryDataModel> subCategoryDataModels, Response response) {

                        spotsDialog.dismiss();

                        subCategoryDataModelArrayList.addAll(subCategoryDataModels);

                        recyclerView.setItemAnimator(new DefaultItemAnimator());

                        recyclerView.setLayoutManager(new LinearLayoutManager(context));

                        adapter = new SubCatModelRecyclerViewAdapter(context, subCategoryDataModelArrayList,

                                new SubCatModelRecyclerViewAdapter.onClick() {
                                    @Override
                                    public void OnClick(SubCategoryDataModel item) {

                                        Bundle b = new Bundle();

                                        b.putString("shopName",item.getNameAR());

                                        b.putInt("shopID",item.getShopId());

                                        setupProfileFragment(b);

                                    }
                                }, new SubCatModelRecyclerViewAdapter.callonClick() {
                            @Override
                            public void MakeCall(SubCategoryDataModel item2) {

                                if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)

                                        != PackageManager.PERMISSION_GRANTED) {

                                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE},

                                            REQUEST_CALL_PHONE);
                                }
                                else
                                {
                                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + item2.getTele()));

                                    startActivity(intent);
                                }



                            }
                        });

                        recyclerView.setAdapter(adapter);

                        adapter.notifyDataSetChanged();

                        adapter.notifyItemChanged(subCategoryDataModelArrayList.size());

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        spotsDialog.dismiss();
                    }
                });
    }

    private void setupProfileFragment(Bundle b) {

        containerLayout.setVisibility(View.VISIBLE);

        ProfileFragment fragment = new ProfileFragment();

        FragmentTransaction tr = getActivity().getSupportFragmentManager().beginTransaction();

        tr.replace(R.id.fragmentsContainer,fragment);

        fragment.setArguments(b);

        tr.addToBackStack(fragment.getClass().getName());

        tr.commit();

    }
}
