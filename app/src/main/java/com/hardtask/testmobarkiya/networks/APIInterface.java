package com.hardtask.testmobarkiya.networks;

import com.hardtask.testmobarkiya.models.CategoryModel;
import com.hardtask.testmobarkiya.models.DetailsProfileDataModel;
import com.hardtask.testmobarkiya.models.Shopphoto;
import com.hardtask.testmobarkiya.models.SubCategoryDataModel;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit2.Call;

/**
 * Created by it_ah on 27/03/2019.
 */

public interface APIInterface
{

    @GET("/GetCategories")

    void getCategories(@Query("PageIndex")String pageIndex,
                       @Query("key")String key,
                       @Query("Type")String type,
                       Callback<ArrayList<CategoryModel>>dataModellistCallback);


    @GET("/GetShop")

    void getShopDetails(@Query("shop_id")String shopID ,
                        @Query("userid")String userID,
                        @Query("key")String key,
                   Callback<ArrayList<DetailsProfileDataModel>> getData);

    @GET("/GetShop")
    void getShopDetailsPhoto(@Query("shop_id")String shopID ,
                        @Query("userid")String userID,
                        @Query("key")String key,
                        Callback<ArrayList<DetailsProfileDataModel>> getPhotos);




    @GET("/GetShopsUnderCategory")

    void getSubCategories(@Query("_CategoryId")String categoryID,
                       @Query("pageIndex")String pageIndex,
                       @Query("UserId")String user_id,
                       @Query("type")String type,
                       @Query("keyword")String keyWord,
                       @Query("key")String key ,
                       @Query("SellType")String sellType,
                       Callback<ArrayList<SubCategoryDataModel>>SubCatdataModellistCallback);

    @GET("/MakeShopFavorite")
    void setFavorite(@Query("userid") String userId,
                         @Query("shopId") String shopId,
                         @Query("key") String key,
                         Callback<Response> response);

}
