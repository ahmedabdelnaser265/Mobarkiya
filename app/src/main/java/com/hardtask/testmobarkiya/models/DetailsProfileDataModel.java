package com.hardtask.testmobarkiya.models;

/**
 * Created by it_ah on 28/03/2019.
 */

import java.util.List;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailsProfileDataModel {

    @SerializedName("isfavorit")
    @Expose
    private Boolean isfavorit;
    @SerializedName("shopId")
    @Expose
    private Integer shopId;
    @SerializedName("ShopNameAR")
    @Expose
    private String shopNameAR;
    @SerializedName("ShopNameEN")
    @Expose
    private String shopNameEN;
    @SerializedName("SellType")
    @Expose
    private Integer sellType;
    @SerializedName("Logo")
    @Expose
    private String logo;
    @SerializedName("AboutShopAR")
    @Expose
    private String aboutShopAR;
    @SerializedName("AbotuShopEN")
    @Expose
    private String abotuShopEN;
    @SerializedName("Mobil")
    @Expose
    private String mobil;
    @SerializedName("Telep1")
    @Expose
    private String telep1;
    @SerializedName("Telep2")
    @Expose
    private String telep2;
    @SerializedName("Telep3")
    @Expose
    private String telep3;
    @SerializedName("WebLink")
    @Expose
    private String webLink;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("AddressAR")
    @Expose
    private String addressAR;
    @SerializedName("AddressEN")
    @Expose
    private String addressEN;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("instagram")
    @Expose
    private String instagram;
    @SerializedName("Twitter")
    @Expose
    private String twitter;
    @SerializedName("snapchat")
    @Expose
    private String snapchat;
    @SerializedName("youtube")
    @Expose
    private String youtube;
    @SerializedName("Longitude")
    @Expose
    private String longitude;
    @SerializedName("Latitude")
    @Expose
    private String latitude;
    @SerializedName("shopphoto")
    @Expose
    private List<Shopphoto> shopphoto = null;

    public Boolean getIsfavorit() {
        return isfavorit;
    }

    public void setIsfavorit(Boolean isfavorit) {
        this.isfavorit = isfavorit;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopNameAR() {
        return shopNameAR;
    }

    public void setShopNameAR(String shopNameAR) {
        this.shopNameAR = shopNameAR;
    }

    public String getShopNameEN() {
        return shopNameEN;
    }

    public void setShopNameEN(String shopNameEN) {
        this.shopNameEN = shopNameEN;
    }

    public Integer getSellType() {
        return sellType;
    }

    public void setSellType(Integer sellType) {
        this.sellType = sellType;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAboutShopAR() {
        return aboutShopAR;
    }

    public void setAboutShopAR(String aboutShopAR) {
        this.aboutShopAR = aboutShopAR;
    }

    public String getAbotuShopEN() {
        return abotuShopEN;
    }

    public void setAbotuShopEN(String abotuShopEN) {
        this.abotuShopEN = abotuShopEN;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public String getTelep1() {
        return telep1;
    }

    public void setTelep1(String telep1) {
        this.telep1 = telep1;
    }

    public String getTelep2() {
        return telep2;
    }

    public void setTelep2(String telep2) {
        this.telep2 = telep2;
    }

    public String getTelep3() {
        return telep3;
    }

    public void setTelep3(String telep3) {
        this.telep3 = telep3;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddressAR() {
        return addressAR;
    }

    public void setAddressAR(String addressAR) {
        this.addressAR = addressAR;
    }

    public String getAddressEN() {
        return addressEN;
    }

    public void setAddressEN(String addressEN) {
        this.addressEN = addressEN;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getSnapchat() {
        return snapchat;
    }

    public void setSnapchat(String snapchat) {
        this.snapchat = snapchat;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public List<Shopphoto> getShopphoto() {
        return shopphoto;
    }

    public void setShopphoto(List<Shopphoto> shopphoto) {
        this.shopphoto = shopphoto;
    }


}