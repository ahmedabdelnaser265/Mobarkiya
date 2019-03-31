package com.hardtask.testmobarkiya.models;

/**
 * Created by it_ah on 27/03/2019.
 */

import java.util.List;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryModel {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("CatParentId")
    @Expose
    private Integer catParentId;
    @SerializedName("CatNameAR")
    @Expose
    private String catNameAR;
    @SerializedName("CatNameEN")
    @Expose
    private String catNameEN;
    @SerializedName("IsSpecialCat")
    @Expose
    private Boolean isSpecialCat;
    @SerializedName("cateAds")
    @Expose
    private List<CateAd> cateAds = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCatParentId() {
        return catParentId;
    }

    public void setCatParentId(Integer catParentId) {
        this.catParentId = catParentId;
    }

    public String getCatNameAR() {
        return catNameAR;
    }

    public void setCatNameAR(String catNameAR) {
        this.catNameAR = catNameAR;
    }

    public String getCatNameEN() {
        return catNameEN;
    }

    public void setCatNameEN(String catNameEN) {
        this.catNameEN = catNameEN;
    }

    public Boolean getIsSpecialCat() {
        return isSpecialCat;
    }

    public void setIsSpecialCat(Boolean isSpecialCat) {
        this.isSpecialCat = isSpecialCat;
    }

    public List<CateAd> getCateAds() {
        return cateAds;
    }

    public void setCateAds(List<CateAd> cateAds) {
        this.cateAds = cateAds;
    }


    public class CateAd {

        @SerializedName("AdvId")
        @Expose
        private Integer advId;
        @SerializedName("AdvertisePlace")
        @Expose
        private String advertisePlace;
        @SerializedName("CategoryId")
        @Expose
        private String categoryId;
        @SerializedName("order")
        @Expose
        private Integer order;
        @SerializedName("Type")
        @Expose
        private Integer type;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("whatsup")
        @Expose
        private String whatsup;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("photo")
        @Expose
        private String photo;
        @SerializedName("photos")
        @Expose
        private List<String> photos = null;

        public Integer getAdvId() {
            return advId;
        }

        public void setAdvId(Integer advId) {
            this.advId = advId;
        }

        public String getAdvertisePlace() {
            return advertisePlace;
        }

        public void setAdvertisePlace(String advertisePlace) {
            this.advertisePlace = advertisePlace;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public Integer getOrder() {
            return order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getWhatsup() {
            return whatsup;
        }

        public void setWhatsup(String whatsup) {
            this.whatsup = whatsup;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public List<String> getPhotos() {
            return photos;
        }

        public void setPhotos(List<String> photos) {
            this.photos = photos;
        }

    }
}
