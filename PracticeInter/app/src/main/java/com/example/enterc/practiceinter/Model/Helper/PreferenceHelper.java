package com.example.enterc.practiceinter.Model.Helper;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.enterc.practiceinter.Model.Helper.GlobalHelper;
import com.example.enterc.practiceinter.R;


public class PreferenceHelper {

    private SharedPreferences sharedPreferences;
    private Context context;

    public PreferenceHelper(Context context, String name) {
        if (context == null) {
            return;
        }

        sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        this.context = context;
    }

    /**
     * MARK: settings
     *
     * @return
     */
    // MARK: premium
    public boolean isPremium() {
        return sharedPreferences != null && sharedPreferences.getBoolean(GlobalHelper.isPremium, false);

    }

    public void setPremium(Boolean newValue) {
        if (sharedPreferences == null) return;
        sharedPreferences.edit().putBoolean(GlobalHelper.isPremium, newValue).apply();
    }

    // MARK: replay video
    public boolean isReplayVideo() {
        return sharedPreferences == null || sharedPreferences.getBoolean(GlobalHelper.replayVideo, false);
    }

    public void setReplayVideo(Boolean newValue) {
        if (sharedPreferences == null) return;
        sharedPreferences.edit().putBoolean(GlobalHelper.replayVideo, newValue).apply();
    }

    // MARK: furigana
    public boolean isShowFurigana() {
        return sharedPreferences == null || sharedPreferences.getBoolean(GlobalHelper.showFurigana, true);
    }

    public void setShowFurigana(Boolean newValue) {
        if (sharedPreferences == null) return;
        sharedPreferences.edit().putBoolean(GlobalHelper.showFurigana, newValue).apply();
    }

    // MARK: lyric ja
    public boolean isShowLyricJa() {
        return sharedPreferences == null || sharedPreferences.getBoolean(GlobalHelper.showLyricJa, true);
    }

    public void setShowLyricJa(Boolean newValue) {
        if (sharedPreferences == null) return;
        sharedPreferences.edit().putBoolean(GlobalHelper.showLyricJa, newValue).apply();
    }

    // MARK: Romaji
    public boolean isShowRomaji() {
        return sharedPreferences == null || sharedPreferences.getBoolean(GlobalHelper.showRomaji, true);
    }

    public void setShowRomaji(Boolean newValue) {
        if (sharedPreferences == null) return;
        sharedPreferences.edit().putBoolean(GlobalHelper.showRomaji, newValue).apply();
    }

    // MARK: lyric Vi
    public boolean isShowLyricSub() {
        return sharedPreferences == null || sharedPreferences.getBoolean(GlobalHelper.showLyricSub, true);
    }

    public void setShowLyricSub(Boolean newValue) {
        if (sharedPreferences == null) return;
        sharedPreferences.edit().putBoolean(GlobalHelper.showLyricSub, newValue).apply();
    }


    public float getInterstitial() {
        if (sharedPreferences == null) return 1f;

        return sharedPreferences.getFloat(GlobalHelper.interstitial, 1f);
    }

    public void setInterstitial(float newValue) {
        if (sharedPreferences == null) return;

        sharedPreferences.edit().putFloat(GlobalHelper.interstitial, newValue).apply();
    }

    public void setLastTimeClickAds(long time) {
        if (sharedPreferences == null) return;

        sharedPreferences.edit().putLong(GlobalHelper.lastTimeClickAds, time).apply();
    }

    public long getLastTimeShowAdsInter() {
        if (sharedPreferences == null) return 0;

        return sharedPreferences.getLong(GlobalHelper.lastTimeShowAdsInter, 0);
    }

    public void setLastTimeShowAdsInter(long time) {
        if (sharedPreferences == null) return;

        sharedPreferences.edit().putLong(GlobalHelper.lastTimeShowAdsInter, time).apply();
    }

    public long getLastTimeClickAds() {
        if (sharedPreferences == null) return 0;

        return sharedPreferences.getLong(GlobalHelper.lastTimeClickAds, 0);
    }

    public int getAdpress() {
        if (sharedPreferences == null) return GlobalHelper.DEFAULT_ADPRESS;

        return sharedPreferences.getInt(GlobalHelper.adpress, GlobalHelper.DEFAULT_ADPRESS);
    }

    public void setAdPress(int newValue) {
        if (sharedPreferences == null) return;

        sharedPreferences.edit().putInt(GlobalHelper.adpress, newValue).apply();
    }

    public int getIntervalAdsInter() {
        if (sharedPreferences == null) return GlobalHelper.DEFAULT_INTERVALADSINTER;

        return sharedPreferences.getInt(GlobalHelper.intervalAdsInter, GlobalHelper.DEFAULT_INTERVALADSINTER);
    }

    public void setIntervalAdsInter(int newValue) {
        if (sharedPreferences == null) return;

        sharedPreferences.edit().putInt(GlobalHelper.intervalAdsInter, newValue).apply();
    }

    public String getIdBanner() {
        if (sharedPreferences == null) return GlobalHelper.DEFAULT_ID_BANNER;

        return sharedPreferences.getString(GlobalHelper.idBanner, GlobalHelper.DEFAULT_ID_BANNER);
    }

    public void setIdBanner(String newValue) {
        if (sharedPreferences == null) return;

        sharedPreferences.edit().putString(GlobalHelper.idBanner, newValue).apply();
    }

    public String getIdInter() {
        if (sharedPreferences == null) return GlobalHelper.DEFAULT_ID_INTER;

        return sharedPreferences.getString(GlobalHelper.idInterstitial, GlobalHelper.DEFAULT_ID_INTER);
    }

    public void setIdInter(String newValue) {
        if (sharedPreferences == null) return;

        sharedPreferences.edit().putString(GlobalHelper.idInterstitial, newValue).apply();
    }

    /**
     * MARK: update config ads
     *
     * @return
     */
    public float getBanner() {
        if (sharedPreferences == null) return 1f;

        try {
            return sharedPreferences.getFloat(GlobalHelper.banner, 1f);
        } catch (ClassCastException e) {
            return 1f;
        }
    }

    public void setBanner(float newValue) {
        if (sharedPreferences == null) return;

        sharedPreferences.edit().putFloat(GlobalHelper.banner, newValue).apply();
    }

    // return 0 : signout
    // return 1 : signin email
    // return 2 : signin facebook
    // return 3 : signin google
    public int getSignin() {
        if (sharedPreferences == null) return 0;

        try {
            return sharedPreferences.getInt(GlobalHelper.signin, 0);
        } catch (ClassCastException e) {
            return 0;
        }
    }

    public void setSignin(int newValue) {
        if (sharedPreferences == null) return;
        sharedPreferences.edit().putInt(GlobalHelper.signin, newValue).apply();
        if (newValue == 0)
            setProfile("");
    }

    public String getProfile() {
        if (sharedPreferences == null) return "";

        try {
            return sharedPreferences.getString(GlobalHelper.profile, "");
        } catch (ClassCastException e) {
            return "";
        }
    }

    public void setProfile(String newValue) {
        if (sharedPreferences == null) return;

        sharedPreferences.edit().putString(GlobalHelper.profile, newValue).apply();
    }

    public String getInfoSigninUser() {
        if (sharedPreferences == null) return "";
        try {
            return sharedPreferences.getString(GlobalHelper.infoSignin, "");
        } catch (ClassCastException e) {
            return "";
        }
    }

    public void setInfoSigninUser(String newValue) {
        if (sharedPreferences == null) return;
        sharedPreferences.edit().putString(GlobalHelper.infoSignin, newValue).apply();
    }

    // MARK: show lyric on fullscreen video
    public boolean isShowLyricOnVideo() {
        return sharedPreferences == null || sharedPreferences.getBoolean(GlobalHelper.showLyric, true);
    }

    public void setShowLyricOnVideo(Boolean newValue) {
        if (sharedPreferences == null) return;
        sharedPreferences.edit().putBoolean(GlobalHelper.showLyric, newValue).apply();
    }

    //check app youtube exist
    public boolean isYoutubeAppExist() {
        return sharedPreferences == null || sharedPreferences.getBoolean(GlobalHelper.existYoutube, true);
    }

    public void setYoutubeAppExist(Boolean newValue) {
        if (sharedPreferences == null) return;
        sharedPreferences.edit().putBoolean(GlobalHelper.existYoutube, newValue).apply();
    }

    // MARK: auto play next video
    public boolean isAutoNextVideo() {
        return sharedPreferences == null || sharedPreferences.getBoolean(GlobalHelper.nextVideo, false);
    }

    public void setAutoNextVideo(Boolean newValue) {
        if (sharedPreferences == null) return;
        sharedPreferences.edit().putBoolean(GlobalHelper.nextVideo, newValue).apply();
    }

//    // MARK: playing AdsInterval
//    public boolean isPlayingAdsInterval() {
//        return sharedPreferences == null || sharedPreferences.getBoolean(GlobalHelper.adsInterval, false);
//    }
//
//    public void setPlayingAdsInterval(Boolean newValue) {
//        if (sharedPreferences == null) return;
//        sharedPreferences.edit().putBoolean(GlobalHelper.adsInterval, newValue).apply();
//    }

    //history search
    public String getSearchHistory() {
        if (sharedPreferences == null) return "";

        try {
            return sharedPreferences.getString(GlobalHelper.SEARCH_HISTORY, "");
        } catch (ClassCastException e) {
            return "";
        }
    }

    public void setSearchHistory(String newValue) {
        if (sharedPreferences == null) return;

        sharedPreferences.edit().putString(GlobalHelper.SEARCH_HISTORY, newValue).apply();
    }

    public String getLanguageCode() {
        if (sharedPreferences == null) return context.getString(R.string.language_code);

        return sharedPreferences.getString(GlobalHelper.languageCode, context.getString(R.string.language_code));
    }

    public void setLanguageCode(String newValue) {
        if (sharedPreferences == null) return;

        sharedPreferences.edit().putString(GlobalHelper.languageCode, newValue).apply();
    }

    public String getPreForeverMoney() {
        if (sharedPreferences == null) return "₫89,000";

        return sharedPreferences.getString(GlobalHelper.SKU_FOREVER, "₫189,000");
    }

    public void setPreForeverMoney(String newValue) {
        if (sharedPreferences == null) return;

        sharedPreferences.edit().putString(GlobalHelper.SKU_FOREVER, newValue).apply();
    }

    public String getPreSixMoney() {
        if (sharedPreferences == null) return "₫129,000";

        return sharedPreferences.getString(GlobalHelper.SKU_SIXMONTHS, "₫129,000");
    }

    public void setPreSixMoney(String newValue) {
        if (sharedPreferences == null) return;

        sharedPreferences.edit().putString(GlobalHelper.SKU_SIXMONTHS, newValue).apply();
    }


    public String getTypePremium() {
        if (sharedPreferences == null) return "";

        return sharedPreferences.getString(GlobalHelper.type_premium, "");
    }

    public void setTypePremium(String newValue) {
        if (sharedPreferences == null) return;

        sharedPreferences.edit().putString(GlobalHelper.type_premium, newValue).apply();
    }

//    public String getCountryCode() {
//        if (sharedPreferences == null) return context.getString(R.string.language_code);
//
//        return sharedPreferences.getString(GlobalHelper.countryCode, context.getString(R.string.language_code));
//    }

    public void setCountryCode(String newValue) {
        if (sharedPreferences == null) return;

        sharedPreferences.edit().putString(GlobalHelper.countryCode, newValue).apply();
    }

    public int getNotiTrans() {
        if (sharedPreferences == null) return 0;

        try {
            return sharedPreferences.getInt(GlobalHelper.notiTrans, 3);
        } catch (ClassCastException e) {
            return 0;
        }
    }

    public void setNotiTrans(int newValue) {
        if (sharedPreferences == null) return;
        sharedPreferences.edit().putInt(GlobalHelper.notiTrans, newValue).apply();
    }

    public String getTags() {
        if (sharedPreferences == null) return "";

        return sharedPreferences.getString(GlobalHelper.TAGS, "");
    }

    public void setTags(String newValue) {
        if (sharedPreferences == null) return;

        sharedPreferences.edit().putString(GlobalHelper.TAGS, newValue).apply();
    }


    public int getShowRate() {
        if (sharedPreferences == null) return 5;

        try {
            return sharedPreferences.getInt(GlobalHelper.RATE, 5);
        } catch (ClassCastException e) {
            return 5;
        }
    }

    public void setShowRate(int newValue) {
        if (sharedPreferences == null) return;
        sharedPreferences.edit().putInt(GlobalHelper.RATE, newValue).apply();
    }

    // MARK: playing AdsInterval
    public boolean isPlayedVideo() {
        return sharedPreferences == null || sharedPreferences.getBoolean(GlobalHelper.PLAYED_VIDEO, false);
    }

    public void setPlayedVideo(Boolean newValue) {
        if (sharedPreferences == null) return;
        sharedPreferences.edit().putBoolean(GlobalHelper.PLAYED_VIDEO, newValue).apply();
    }

    // MARK: playing AdsInterval
    public boolean isNewVersion() {
        return sharedPreferences == null || sharedPreferences.getBoolean(GlobalHelper.new_version, true);
    }

    public void setNewVerison(Boolean newValue) {
        if (sharedPreferences == null) return;
        sharedPreferences.edit().putBoolean(GlobalHelper.new_version, newValue).apply();
    }

    public String getListTrans() {
        if (sharedPreferences == null) return "";

        return sharedPreferences.getString(GlobalHelper.list_trans, "");
    }

    public void setListTrans(String newValue) {
        if (sharedPreferences == null) return;

        sharedPreferences.edit().putString(GlobalHelper.list_trans, newValue).apply();
    }

    public String getApplicationLanguageCode() {
        if (sharedPreferences == null) return null;

        return sharedPreferences.getString(GlobalHelper.APP_LANGUAGE_CODE, null);
    }

    public void setapplicationLanguageCode(String newValue) {
        if (sharedPreferences == null) return;

        sharedPreferences.edit().putString(GlobalHelper.APP_LANGUAGE_CODE, newValue).apply();
    }

    public int getTheme() {
        if (sharedPreferences == null) return 0;

        return sharedPreferences.getInt(GlobalHelper.APP_THEME, 0);
    }

    public void setTheme(int newValue) {
        if (sharedPreferences == null) return;

        sharedPreferences.edit().putInt(GlobalHelper.APP_THEME, newValue).apply();
    }

    public boolean isPlayWatchLater() {
        if (sharedPreferences == null) return true;
        return sharedPreferences.getBoolean(GlobalHelper.IS_PLAY_WATCH_LATER, true);
    }

    public void setPlayWatchLater(boolean isWatchLater) {
        if (sharedPreferences == null) return;

        sharedPreferences.edit().putBoolean(GlobalHelper.IS_PLAY_WATCH_LATER, isWatchLater).apply();
    }


}

