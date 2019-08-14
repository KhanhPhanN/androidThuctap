package com.example.enterc.practiceinter.Model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfile {

    @SerializedName("Err")
    @Expose
    private Err err;
    @SerializedName("User")
    @Expose
    private User user;

    public Err getErr() {
        return err;
    }

    public void setErr(Err err) {
        this.err = err;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public class User {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("activate_flag")
        @Expose
        private Integer activateFlag;
        @SerializedName("avatar")
        @Expose
        private String avatar;
        @SerializedName("sex")
        @Expose
        private Integer sex;
        @SerializedName("job")
        @Expose
        private Object job;
        @SerializedName("day_of_birth")
        @Expose
        private Integer dayOfBirth;
        @SerializedName("month_of_birth")
        @Expose
        private Integer monthOfBirth;
        @SerializedName("year_of_birth")
        @Expose
        private Integer yearOfBirth;
        @SerializedName("level_jap")
        @Expose
        private String levelJap;
        @SerializedName("interest_jap")
        @Expose
        private Object interestJap;
        @SerializedName("singer_favorite")
        @Expose
        private String singerFavorite;
        @SerializedName("song_favorite")
        @Expose
        private String songFavorite;
        @SerializedName("more_profile")
        @Expose
        private String moreProfile;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("accessToken")
        @Expose
        private String accessToken;
        @SerializedName("badges")
        @Expose
        private String badges;
        @SerializedName("is_premium")
        @Expose
        private Boolean isPremium = false;
        @SerializedName("premium")
        @Expose
        private Premium premium;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Integer getActivateFlag() {
            return activateFlag;
        }

        public void setActivateFlag(Integer activateFlag) {
            this.activateFlag = activateFlag;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public Integer getSex() {
            return sex;
        }

        public void setSex(Integer sex) {
            this.sex = sex;
        }

        public Object getJob() {
            return job;
        }

        public void setJob(Object job) {
            this.job = job;
        }

        public Integer getDayOfBirth() {
            return dayOfBirth;
        }

        public void setDayOfBirth(Integer dayOfBirth) {
            this.dayOfBirth = dayOfBirth;
        }

        public Integer getMonthOfBirth() {
            return monthOfBirth;
        }

        public void setMonthOfBirth(Integer monthOfBirth) {
            this.monthOfBirth = monthOfBirth;
        }

        public Integer getYearOfBirth() {
            return yearOfBirth;
        }

        public void setYearOfBirth(Integer yearOfBirth) {
            this.yearOfBirth = yearOfBirth;
        }

        public String getLevelJap() {
            return levelJap;
        }

        public void setLevelJap(String levelJap) {
            this.levelJap = levelJap;
        }

        public Object getInterestJap() {
            return interestJap;
        }

        public void setInterestJap(Object interestJap) {
            this.interestJap = interestJap;
        }

        public String getSingerFavorite() {
            return singerFavorite;
        }

        public void setSingerFavorite(String singerFavorite) {
            this.singerFavorite = singerFavorite;
        }

        public String getSongFavorite() {
            return songFavorite;
        }

        public void setSongFavorite(String songFavorite) {
            this.songFavorite = songFavorite;
        }

        public String getMoreProfile() {
            return moreProfile;
        }

        public void setMoreProfile(String moreProfile) {
            this.moreProfile = moreProfile;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getBadges() {
            return badges;
        }

        public void setBadges(String badges) {
            this.badges = badges;
        }

        public Boolean getIsPremium() {
            return isPremium;
        }

        public void setIsPremium(Boolean isPremium) {
            this.isPremium = isPremium;
        }

        public Premium getPremium() {
            return premium;
        }

        public void setPremium(Premium premium) {
            this.premium = premium;
        }
    }

    public class Premium {

        @SerializedName("product_id")
        @Expose
        private String productId;
        @SerializedName("purchase_date")
        @Expose
        private String purchaseDate;
        @SerializedName("time_expired")
        @Expose
        private Double timeExpired;

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getPurchaseDate() {
            return purchaseDate;
        }

        public void setPurchaseDate(String purchaseDate) {
            this.purchaseDate = purchaseDate;
        }

        public Double getTimeExpired() {
            return timeExpired;
        }

        public void setTimeExpired(Double timeExpired) {
            this.timeExpired = timeExpired;
        }

    }

    public class Err {

        @SerializedName("statusCode")
        @Expose
        private Integer statusCode;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("data")
        @Expose
        private Object data;

        public Integer getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(Integer statusCode) {
            this.statusCode = statusCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

    }
}