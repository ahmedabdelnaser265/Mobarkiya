package com.hardtask.testmobarkiya.fragments;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.hardtask.testmobarkiya.R;
import com.hardtask.testmobarkiya.adapters.DetailsDataModelRecyclerViewAdapter;
import com.hardtask.testmobarkiya.models.DetailsProfileDataModel;
import com.hardtask.testmobarkiya.models.Shopphoto;
import com.hardtask.testmobarkiya.networks.CallApi;
import com.hardtask.testmobarkiya.networks.UrlsApi;
import com.hardtask.testmobarkiya.sharedPreference.SharedPrefManager;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedInput;

import static android.content.ContentValues.TAG;
import static android.content.Context.CLIPBOARD_SERVICE;
import static com.hardtask.testmobarkiya.HomeActivity.homeText;
import static com.hardtask.testmobarkiya.HomeActivity.likeImageHome;
import static com.hardtask.testmobarkiya.HomeActivity.searchImageHome;
import static com.hardtask.testmobarkiya.HomeActivity.shareImageHome;
import static com.hardtask.testmobarkiya.HomeActivity.showHideLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    TextView companyName ;

    TextView companyDescription ;

    TextView companyPhone ;

    TextView compnyAddress ;

    TextView companyWebsite ;

    TextView companyEmail ;

    LinearLayout companyCallPhone ;

    LinearLayout compnyGoMaps ;

    CircleImageView companyImage ;

    RecyclerView subCatRecyclerView ;

    public Context context ;

    SharedPreferences sharedPreferences ;

    SharedPreferences.Editor editor ;

    SpotsDialog spotsDialog ;

    Typeface typeface ;

    Integer catID ;

    ArrayList<Shopphoto> shopphotoArrayList;

    ArrayList<DetailsProfileDataModel>detailsProfileDataModelArrayList;

    DetailsProfileDataModel dataModelDetailsProfile =null;

    LinearLayoutManager linearLayoutManager = null ;

    int position =0 ;

    ArrayList<Integer>photosList ;

    ImageView faceBookImg, twitterImg,instagramImg , snapChtImg ,youTubeImg ;

    int i =0;

    static android.support.v4.app.FragmentManager fragmentManager ;

    public String shopName ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_profile, container, false);

        context = container.getContext();

        sharedPreferences = context.getSharedPreferences(SharedPrefManager.SHARED_PREF_NAME,Context.MODE_PRIVATE);

        editor = sharedPreferences.edit();

        fragmentManager =getFragmentManager();

        //setTypeFace..,
        typeface = ResourcesCompat.getFont(context,R.font.ge_dinar);

        homeText.setTypeface(typeface);

        spotsDialog = new SpotsDialog(context,R.style.Custom);

        initViews(v);

        checkLikedStatus();

        checkSavedUser();

        getBundle();

        homeText.setText(shopName);

        detailsProfileDataModelArrayList = new ArrayList<>();

        getDetails(catID);

        shopphotoArrayList = new ArrayList<>();

        photosList = new ArrayList<>();

        likeImageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFavourite(catID);
            }
        });



        return v ;
    }

    private void initViews(View view)

    {

        //Load animation
        final Animation slide_down = AnimationUtils.loadAnimation(context,
                R.anim.slide_down);

        final Animation slide_up = AnimationUtils.loadAnimation(context,
                R.anim.slide_up);

        showHideLayout.setVisibility(View.GONE);

        companyName = (TextView)view.findViewById(R.id.txtNameCompanySubCat);

        companyDescription = (TextView)view.findViewById(R.id.txtSubscribeCompanySubCat);

        companyPhone = (TextView)view.findViewById(R.id.txtCallPhoneCompanySubCat);

        companyPhone.setPaintFlags(companyPhone.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        compnyAddress = (TextView)view.findViewById(R.id.txtAddressCompanySubCat);

        companyWebsite = (TextView)view.findViewById(R.id.txtWebSiteCompanySubCat);

        companyEmail = (TextView)view.findViewById(R.id.txtEmailCompanySubCat);

        companyCallPhone = (LinearLayout) view.findViewById(R.id.layoutGetCallSubCat);

        compnyGoMaps =(LinearLayout)view.findViewById(R.id.layoutGetMapsSubCat);

        companyImage=(CircleImageView)view.findViewById(R.id.imageCompanySubCat);

        subCatRecyclerView =(RecyclerView)view.findViewById(R.id.recyclerViewProfileSubCat);

        faceBookImg = (ImageView)view.findViewById(R.id.img_facebook);

        instagramImg = (ImageView)view.findViewById(R.id.img_instagram);

        snapChtImg = (ImageView)view.findViewById(R.id.img_snapchat);

        twitterImg = (ImageView)view.findViewById(R.id.img_twitter);

        youTubeImg = (ImageView)view.findViewById(R.id.img_youtube);

        shareImageHome.setVisibility(View.VISIBLE);

        searchImageHome.setVisibility(View.GONE);

        likeImageHome.setVisibility(View.VISIBLE);


    }

    private void autoGetUser(String key)
    {

        spotsDialog.show();

        StringRequest request = new StringRequest(Request.Method.GET, UrlsApi.BASEURL+"/Register?key="+key,

                new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                spotsDialog.dismiss();

                Log.e("ResponseGetID",response.toString());


                SharedPrefManager.getInstance(context).saveUserID(String.valueOf(response));



            }
        }, new com.android.volley.Response.ErrorListener() {

            @Override

            public void onErrorResponse(VolleyError error) {
                Log.e("responseGetIDError",error.getMessage());
            }
        });

        Volley.newRequestQueue(context).add(request);


    }

    private void checkSavedUser()
    {


        String userID = sharedPreferences.getString(SharedPrefManager.USER_ID,"");

        if (userID.isEmpty()||userID==null)
        {
            autoGetUser("YRW76GXj3f63ElMx");
        }
        else
        {
            Log.e("UserNow?",userID);
        }



    }

    private void getDetails(Integer catID){
        spotsDialog.show();

        String userID = sharedPreferences.getString(SharedPrefManager.USER_ID,"");

        CallApi.getAPI().getShopDetails(String.valueOf(catID), userID, "YRW76GXj3f63ElMx",

                new Callback<ArrayList<DetailsProfileDataModel>>() {
            @Override
            public void success(ArrayList<DetailsProfileDataModel> detailsProfileDataModels, retrofit.client.Response response) {

               if (detailsProfileDataModels !=null)
               {
                   if (detailsProfileDataModels.size()>0)
                   {
                       dataModelDetailsProfile = detailsProfileDataModels.get(0);

                       if (dataModelDetailsProfile.getShopNameAR().isEmpty()||dataModelDetailsProfile.getShopNameAR()==null)
                       {
                           companyName.setText("");
                       }
                       else
                       {
                           companyName.setText(dataModelDetailsProfile.getShopNameAR());

                       }

                       Glide.with(context).load(dataModelDetailsProfile.getLogo()).into(companyImage);

                       if (dataModelDetailsProfile.getAddressAR().isEmpty()||dataModelDetailsProfile.getAddressAR()==null)
                       {
                           compnyAddress.setText("");
                       }
                       else
                       {

                           compnyAddress.setText(dataModelDetailsProfile.getAddressAR());
                       }

                       if (dataModelDetailsProfile.getMobil().isEmpty()||dataModelDetailsProfile.getMobil()==null)
                       {
                           companyPhone.setText("");
                       }

                       else {companyPhone.setText(dataModelDetailsProfile.getMobil());

                       companyPhone.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               intentCall(context,1,dataModelDetailsProfile.getMobil());
                           }
                       });}

                       if (dataModelDetailsProfile.getEmail().isEmpty()||dataModelDetailsProfile.getEmail()==null)
                       {
                           companyEmail.setText("");
                       }
                       else {companyEmail.setText(dataModelDetailsProfile.getEmail());}


                       if (dataModelDetailsProfile.getWebLink().isEmpty()||dataModelDetailsProfile.getWebLink()=="")
                       {
                           companyWebsite.setText("");
                       }
                       companyWebsite.setText(dataModelDetailsProfile.getWebLink());

                       companyWebsite.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               goWebSite(context,dataModelDetailsProfile.getWebLink());
                           }
                       });

                       if (dataModelDetailsProfile.getAboutShopAR().isEmpty()||dataModelDetailsProfile.getAboutShopAR()==null)
                       {
                           companyDescription.setText("");
                       }
                       else
                       {
                           companyDescription.setText(dataModelDetailsProfile.getAboutShopAR());
                       }

                       //shareData
                       shareImageHome.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               copyTextForShare(dataModelDetailsProfile.getLogo(),dataModelDetailsProfile.getMobil(),

                                       dataModelDetailsProfile.getShopNameAR(),dataModelDetailsProfile.getMobil());
                           }
                       });

                       //visitSocialMedia..,

                       faceBookImg.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {goSocialMedia(context,"https://www.facebook.com/",dataModelDetailsProfile.getFacebook());}});

                       twitterImg.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {goSocialMedia(context,"https://twitter.com/",dataModelDetailsProfile.getTwitter());}});

                       instagramImg.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {goSocialMedia(context,"https://www.instagram.com/",dataModelDetailsProfile.getInstagram());}});

                       youTubeImg.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {goSocialMedia(context,"https://www.youtube.com/",dataModelDetailsProfile.getYoutube());}});

                       snapChtImg.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {goSocialMedia(context,"https://accounts.snapchat.com/",dataModelDetailsProfile.getSnapchat());}});

                       //intent Call ..,
                       companyCallPhone.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {intentCall(context,1,dataModelDetailsProfile.getMobil());}});

                   }
               }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

    private void getPhotos(Integer catID)
    {
        spotsDialog.show();
        String userID = sharedPreferences.getString(SharedPrefManager.USER_ID,"");
        CallApi.getAPI().getShopDetailsPhoto(String.valueOf(catID), userID, "YRW76GXj3f63ElMx",

                new Callback<ArrayList<DetailsProfileDataModel>>() {
            @Override
            public void success(final ArrayList<DetailsProfileDataModel> shopphotos, retrofit.client.Response response) {
                spotsDialog.dismiss();
                Log.e("responseGetPhotos",response.getBody().toString());

                Log.d("shopphotos", "size -> " + shopphotos.size());

                Log.d("shopphotos", "size -> " + shopphotos.get(0).getShopphoto().size());

                for (i = 0; i<shopphotos.size(); i++)

                {
                    shopphotoArrayList= (ArrayList<Shopphoto>) shopphotos.get(i).getShopphoto();


                //setup recyclerView ..,
                subCatRecyclerView.setItemAnimator(new DefaultItemAnimator());

                linearLayoutManager = new LinearLayoutManager(context);

                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

                subCatRecyclerView.setLayoutManager
                        (linearLayoutManager);

                DetailsDataModelRecyclerViewAdapter adapter =

                        new DetailsDataModelRecyclerViewAdapter(context, shopphotoArrayList, new

                                DetailsDataModelRecyclerViewAdapter.onClick() {
                            @Override
                            public void OnClick(Shopphoto item) {




                            }
                        });

                subCatRecyclerView.setAdapter(adapter);

                adapter.notifyDataSetChanged();

                adapter.notifyItemChanged(shopphotoArrayList.size());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                spotsDialog.dismiss();
            }
        });
    }

    private void getBundle() {
        {
            Bundle bundle = this.getArguments();
            if (bundle != null) {

                catID = bundle.getInt("shopID");

                shopName = bundle.getString("shopName");
                Log.e("catID", String.valueOf(catID));

                getPhotos(catID);

            }
        }
    }

    private void setFavourite(Integer catID) {
        spotsDialog.show();

        String userId = sharedPreferences.getString(SharedPrefManager.USER_ID,"");

        CallApi.getAPI().setFavorite(userId, String.valueOf(catID), "YRW76GXj3f63ElMx", new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {

                spotsDialog.dismiss();

                TypedInput body = response.getBody();
                String outResponse="";

                try {

                    BufferedReader reader = new BufferedReader(new InputStreamReader(body.in()));

                    StringBuilder out_response = new StringBuilder();

                    String newLine = System.getProperty("line.separator");

                    String line;

                    while ((line = reader.readLine()) != null) {
                        out_response.append(line);
                        out_response.append(newLine);
                    }

                    outResponse = out_response.toString();
                    Log.d("outResponse", ""+outResponse);

                    if (outResponse.contains("1"))
                    {
                        likeImageHome.setImageResource(R.mipmap.fav_in);

                        SharedPrefManager.getInstance(context).likeStatus(true);
                    }

                    else if (outResponse.contains("0"))

                    {
                        likeImageHome.setImageResource(R.mipmap.fav_out);

                        SharedPrefManager.getInstance(context).likeStatus(false);
                    }


                } catch (Exception ex) {

                    ex.printStackTrace();


                }

            }

            @Override
            public void failure(RetrofitError error) {
                spotsDialog.dismiss();
                Log.e("errorGetFavourite",error.getMessage());
            }
        });

    }

    private void checkLikedStatus()
    {
        Boolean liked = sharedPreferences.getBoolean(SharedPrefManager.ISLIKED,false);

        if (liked.equals(true))

        {
            likeImageHome.setImageResource(R.mipmap.fav_in);

        }
        else
        {
            likeImageHome.setImageResource(R.mipmap.fav_out);
        }
    }

    private String copyTextForShare(String photoData, String mobileData,String shopName,String shopMobile){

        String textCopied =
                context.getString(R.string.shareAdDetails) + "\n\n" + photoData
                        + "\n\n" + mobileData +  context.getString(R.string.shareAdDetails)
                        + "\n\n" + shopName
                + "\n\n" +shopMobile;

        ClipboardManager myClipboard;
        myClipboard = (ClipboardManager)context.getSystemService(CLIPBOARD_SERVICE);
        ClipData myClip;
        myClip = ClipData.newPlainText("text",textCopied);
        myClipboard.setPrimaryClip(myClip);

        Log.e("TextCopied",textCopied);

        shareMethod(textCopied);

        return textCopied;
    }

    private void shareMethod(String copiedClipboard)
    {
    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
    sharingIntent.setType("text/plain");
    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, copiedClipboard);
    startActivity(Intent.createChooser(sharingIntent,"Share using"));
    }

    private static void goSocialMedia(Context context,String url, String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(url + id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }

    private static void intentCall(Context context ,Integer requestCall,String phoneNumber)
    {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)

                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE},

                    requestCall);
        }
        else
        {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));

            context.startActivity(intent);
        }
    }

    private static void goWebSite(Context context,String url){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(url));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }

}
