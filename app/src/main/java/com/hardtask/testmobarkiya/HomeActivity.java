package com.hardtask.testmobarkiya;

import android.content.Context;
import android.media.Image;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hardtask.testmobarkiya.adapters.CatModelRecyclerViewAdapter;
import com.hardtask.testmobarkiya.fragments.FragmentHome;
import com.hardtask.testmobarkiya.fragments.SubCategoryResultFrafment;
import com.hardtask.testmobarkiya.models.CategoryModel;
import com.hardtask.testmobarkiya.networks.CallApi;
import com.hardtask.testmobarkiya.sharedPreference.SharedPrefManager;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView catRecyclerView ;

    private ArrayList<CategoryModel> categoryModelArrayList;

    private ArrayList<CategoryModel.CateAd>cateAdArrayList ;

    CatModelRecyclerViewAdapter catAdapter ;

    public static TextView homeText ;

    android.support.v4.app.FragmentManager mfragmentmanager;

    static FrameLayout container ;

    SpotsDialog spotsDialog;

    public Context context ;

    FloatingActionButton fab;

    DrawerLayout drawer;

    NavigationView navigationView ;

    public static LinearLayout showHideLayout ;


    public static ImageView shareImageHome ,searchImageHome,likeImageHome ;

    Typeface typeface ;

    boolean isUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initViews ..,
        initViews();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        mfragmentmanager = getSupportFragmentManager();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(

                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);

        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);


        setupFragment();
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(GravityCompat.START);}

        else if (mfragmentmanager.getBackStackEntryCount() > 0)
        {
            mfragmentmanager.popBackStackImmediate();

                homeText.setText("الرئيسية");
                homeText.setTypeface(typeface);
        }

        else {

            //close app when back pressed twice ..,
            Intent a = new Intent(Intent.ACTION_MAIN);

            a.addCategory(Intent.CATEGORY_HOME);

            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(a);

            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private void initViews()

    {

        //setTypeFace..,
        typeface = ResourcesCompat.getFont(HomeActivity.this,R.font.ge_dinar);

         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

         fab = (FloatingActionButton) findViewById(R.id.fab);

         navigationView = (NavigationView) findViewById(R.id.nav_view);

        homeText = (TextView)findViewById(R.id.homeTextHomeActivity);

        homeText.setText("الرئيسية");

        homeText.setTypeface(typeface);

        container = (FrameLayout)findViewById(R.id.fragmentsContainer);

        container.setVisibility(View.GONE);

        categoryModelArrayList = new ArrayList<>();

        cateAdArrayList = new ArrayList<>();

        cateAdArrayList = new ArrayList<>();

        spotsDialog = new SpotsDialog(HomeActivity.this,R.style.Custom);

        shareImageHome = (ImageView)findViewById(R.id.shareImage);

        searchImageHome = (ImageView)findViewById(R.id.sarchImage);

        likeImageHome = (ImageView)findViewById(R.id.favIn);

//        disLikeHome = (ImageView)findViewById(R.id.favOut);

        shareImageHome.setVisibility(View.GONE);

        likeImageHome .setVisibility(View.GONE);

//        disLikeHome.setVisibility(View.GONE);

        showHideLayout = (LinearLayout)findViewById(R.id.hideShowLayout);


    }

    public void setupFragment()
    {
        container.setVisibility(View.VISIBLE);

        FragmentHome fragment = new FragmentHome();

        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();

        tr.replace(R.id.fragmentsContainer,fragment);

        tr.addToBackStack(fragment.getClass().getName());

        tr.commit();

    }

    // slide the view from below itself to the current position
    public static void slideUpLayout(View view){
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                view.getHeight(),  // fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);

    }

    // slide the view from its current position to below itself
    public static void slideDownLayout(View view){
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                view.getHeight()); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.VISIBLE);

    }

}
