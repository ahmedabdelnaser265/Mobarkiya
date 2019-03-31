package com.hardtask.testmobarkiya.models;

/**
 * Created by it_ah on 27/03/2019.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCategoryDataModel {

    @SerializedName("shopId")
    @Expose
    private Integer shopId;
    @SerializedName("NameAR")
    @Expose
    private String nameAR;
    @SerializedName("NameEn")
    @Expose
    private String nameEn;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("Tele")
    @Expose
    private String tele;
    @SerializedName("SellType")
    @Expose
    private Integer sellType;
    @SerializedName("CateAds")
    @Expose
    private List<Object> cateAds = null;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getNameAR() {
        return nameAR;
    }

    public void setNameAR(String nameAR) {
        this.nameAR = nameAR;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public Integer getSellType() {
        return sellType;
    }

    public void setSellType(Integer sellType) {
        this.sellType = sellType;
    }

    public List<Object> getCateAds() {
        return cateAds;
    }

    public void setCateAds(List<Object> cateAds) {
        this.cateAds = cateAds;
    }

}