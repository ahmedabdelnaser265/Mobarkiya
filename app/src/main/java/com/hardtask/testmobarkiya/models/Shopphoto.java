package com.hardtask.testmobarkiya.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by it_ah on 28/03/2019.
 */
public class Shopphoto {

    @SerializedName("photoid")
    @Expose
    private Integer photoid;
    @SerializedName("photoname")
    @Expose
    private String photoname;

    public Integer getPhotoid() {
        return photoid;
    }

    public void setPhotoid(Integer photoid) {
        this.photoid = photoid;
    }

    public String getPhotoname() {
        return photoname;
    }

    public void setPhotoname(String photoname) {
        this.photoname = photoname;
    }

}
