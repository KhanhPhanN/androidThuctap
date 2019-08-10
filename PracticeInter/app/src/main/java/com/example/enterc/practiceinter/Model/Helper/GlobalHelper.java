package com.example.enterc.practiceinter.Model.Helper;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;


import com.example.enterc.practiceinter.R;

import java.util.Calendar;

public class GlobalHelper {
    public static final String PREFERENCE_NAME_PIKA = "eup.mobi.pikasmart";
    public static final String isPremium = "isPremium";
    public static final String existYoutube = "existYoutube";
    public static final String showLyric = "showLyric";
    public static final String replayVideo = "replayVideo";
    public static final String nextVideo = "nextVideo";
    public static final String adsInterval = "adsInterval";
    public static final String showFurigana = "showFurigana";
    public static final String showLyricJa = "showLyricJa";
    public static final String showRomaji = "showRomaji";
    public static final String showLyricSub = "showLyricSub";
    public static final String signin = "signin";
    public static final String profile = "profileUser";
    public static final String infoSignin = "infoSignin";
    public static final String USER_TRANSLATE = "user_translate";
    public static final String USER_SINGER = "user_singer";
    public static final String SEARCH_HISTORY = "search_history";
    public static final String languageCode = "languageCode";
    public static final String countryCode = "countryCode";
    public static final String notiTrans = "notiTrans";
    public static final String TAGS = "TAGS";
    public static final String RATE = "RATE";
    public static final String PLAYED_VIDEO = "PLAYED_VIDEO";
    public static final String new_version = "new_version";
    public static final String list_trans = "list_trans";
    public static final String APP_LANGUAGE_CODE = "app_language_code";
    public static final String APP_THEME = "app_theme";
    public static final String IS_PLAY_WATCH_LATER = "is_watch_later";

    //backup data
    public static final String jpop_indicator = "jpop_indicator";
    public static final String jpop_listSinger = "jpop_listSinger";
    public static final String jpop_listAlbum = "jpop_listAlbum";
    public static final String jpop_chart = "jpop_chart";
    public static final String jpop_listContinue = "jpop_listContinue";
    public static final String jpop_listNew = "jpop_listNew";
    public static final String jpop_listHot = "jpop_listHot";
    public static final String anime_indicator = "anime_indicator";
    public static final String anime_listContinue = "anime_listContinue";
    public static final String anime_listAlbum = "anime_listAlbum";
    public static final String anime_listHot = "anime_listHot";
    public static final String anime_listNew = "anime_listNew";
    public static final String video_saved = "video_saved";
    public static final String video_translated = "video_translated";
    public static final String video_contrib = "video_contrib";
    public static final String video_votes = "video_votes";
    public static final String list_Follow = "list_Follow";
    public static final String list_Follower = "list_Follower";

    public static final String CONFIG_ADS_URL = "http://eup.mobi/apps/pikasmart/config_android.json";
    public static final String banner = "banner";
    public static final String interstitial = "interstitial";
    public static final String lastTimeClickAds = "time";
    public static final String lastTimeShowAdsInter = "lastTimeShowAdsInter";
    public static final String adpress = "adpress";
    public static final int DEFAULT_ADPRESS = 3600000;
    public static final String intervalAdsInter = "intervalAdsInter";
    public static final int DEFAULT_INTERVALADSINTER = 300000;
    public static final String idBanner = "idBanner";
    public static final String DEFAULT_ID_BANNER = "ca-app-pub-8268370626959195/2315571469";
    public static final String idInterstitial = "idInterstitial";
    public static final String DEFAULT_ID_INTER = "ca-app-pub-8268370626959195/6745771062";

    public static final String SKU_PREMIUM = "premium";
    public static final String ITEM_SKU = "android.test.purchased";
    public static final String SKU_SIXMONTHS = "pre6months";
    public static final String SKU_FOREVER = "pipop_premiumforever";
    public static final String type_premium = "type_premium";

    //follow
    public static final String URL_GET_FOLLOW = "http://pikasmart.com/api/folows/listfolow?id=%d&type=%s";
    public static final String URL_POST_FOLLOW = "http://pikasmart.com/api/folows/folow?access_token=%s";
    public static final String URL_POST_UNFOLLOW = "http://pikasmart.com/api/folows/unfolow?access_token=%s";
    public static final String URL_REPORT = "http://pikasmart.com/api/folows/report?access_token=%s";

    //singer
    public static final String URL_GET_INFO_SINGER = "http://pikasmart.com/api/Singers/infor?id_singer=%d";
    public static final String URL_GET_TOP_VIDEO = "http://pikasmart.com/api/Singers/topvideo?limit=%d&skip=%d&id_singer=%s";
    public static final String URL_GET_VIDEO = "http://pikasmart.com/api/Singers/video?limit=%d&skip=%d&id_singer=%s";
    public static final String URL_GET_LIST_SINGER = "http://pikasmart.com/api/Singers/listsinger?limit=%d";

    //song
    public static final String URL_ADD_CONTRIBUTE = "http://pikasmart.com/api/Songs/addContributes?access_token=%s";
    public static final String URL_GET_LIST_NEW_SONG = "http://pikasmart.com/api/Songs/listnew?limit=%d&skip=%d&video_type=%s";
    public static final String URL_GET_LIST_POPULAR_SONG = "http://pikasmart.com/api/Songs/listpopular?limit=%d&skip=%d&video_type=%s";
    public static final String URL_GET_LIST_SONGS = "http://pikasmart.com/api/Songs/listsongs?limit=%d&skip=%d";
    public static final String URL_GET_LIST_CONTINUE_SONGS = "http://pikasmart.com/api/Songs/continue?limit=%d&skip=%d&video_type=%s&access_token=%s";
    public static final String URL_GET_LIST_RECOMMEND = "http://pikasmart.com/api/Songs/recommend?limit=%d&video_type=%s";
    public static final String URL_GET_LYRIC_SENTENCE = "http://pikasmart.com/api/SongSentences/getListLyricsBylanguageCode?song_id=%d&language_code=%s";
    public static final String URL_GET_LYRIC_BY_USER = "http://pikasmart.com/api/SongSentences/getListLyricsByUserIdTrangslate_Language?song_id=%d&user_id=%d&language_code=%s";
    public static final String URL_GET_LIST_WORD = "http://pikasmart.com/api/words/listWord?song_id=%d&language_code=%s";
    public static final String URL_GET_RELATE_SONG_SIGNIN = "http://pikasmart.com/api/Songs/related?id_singer=%d&limit=%d&skip=%d&video_type=%s&access_token=%s";
    public static final String URL_GET_RELATE_SONG = "http://pikasmart.com/api/Songs/related?id_singer=%d&limit=%d&skip=%d&video_type=%s";
    public static final String URL_GET_SONGS_SONG_SIGNIN = "http://pikasmart.com/api/Songs/song?song_id=%d&access_token=%s";
    public static final String URL_GET_SONGS_SONG = "http://pikasmart.com/api/Songs/song?song_id=%d";
    public static final String URL_GET_SONGS_SLUG = "http://pikasmart.com/api/Songs/get_song_slug?slug=%s";
    public static final String URL_VOTE_CONTRIBUTE = "http://pikasmart.com/api/Songs/voteContribute?access_token=%s";
    public static final String URL_TOP_WEEK = "http://pikasmart.com/api/Songs/topWeekTrandding?limit=%d&skip=%d";
    public static final String URL_TOP_WEEK_SIGNIN = "http://pikasmart.com/api/Songs/topWeekTrandding?limit=%d&skip=%d&access_token=%s";
    public static final String URL_ADD_TRANSLATION = "http://pikasmart.com/api/SongSentences/addSentenceTranslate?access_token=%s";
    public static final String URL_LIST_USER_TRANSLATE = "http://pikasmart.com/api/SongSentences/getlist_user_trangslate_language_code?song_id=%d&language_code=%s";
    public static final String URL_GET_TAGS = "http://pikasmart.com/api/Songs/getTag?limit=%d";
    public static final String URL_GET_TOP_VIDEO_VIEW = "http://pikasmart.com/api/Songs/getTopViewVideoPerNew?video_type=%s&limit=%d&type=%d&skip=%d";
    public static final String URL_GET_VIDEO_CHART = "http://pikasmart.com/api/Songs/topViewVideo?video_type=%s&limit=%d&type=%d&skip=%d";

    //users
    public static final String URl_GET_PROFILE = "http://pikasmart.com/api/Users/profile?user_id=%d";
    public static final String URL_SAVED_VIDEO = "http://pikasmart.com/api/Users/myvideo?limit=%d&skip=%d&access_token=%s";
    public static final String URL_USER_FOLLOW = "http://pikasmart.com/api/Users/listfolow?user_id=%d";
    public static final String URL_USER_TRANSLATE = "http://pikasmart.com/api/Users/listTranslate?user_id=%d&limit=%d&skip=%d";
    public static final String REQUEST_SIGNIN_EMAIL_URL = "http://pikasmart.com/api/Users/signin";
    public static final String UPDATE_PASSWORD = "http://pikasmart.com/api/Users/updatePassword?access_token=%s";
    public static final String VERIFY_PURCHASE = "http://pikasmart.com/api/Users/verifyPurchase?access_token=%s";

    public static final String URL_SHARE = "https://pipop.net/videos/%s";
    public static final String URL_MY_VIDEO = "http://pikasmart.com/api/Songs/repository?access_token=%s"; // save video
    public static final String URL_REMOVE_MY_VIDEO = "http://pikasmart.com/api/Users/removeMyVideo?access_token=%s"; // remove video

    public static final String URL_POST_COUNT_PLAY_SIGNIN = "http://pikasmart.com/api/Songs/countplay?access_token=%s";
    public static final String URL_POST_COUNT_PLAY = "http://pikasmart.com/api/Songs/countplay";
    public static final String URL_POST_REPORT = "http://pikasmart.com/api/Songs/report?access_token=%s";

    public static final String URL_SEARCH_SINGIN = "http://pikasmart.com/api/Songs/search_v1?keyword=%s&limit=%d&skip=%d&access_token=%s";
    public static final String URL_SEARCH = "http://pikasmart.com/api/Songs/search_v1?keyword=%s&limit=%d&skip=%d";

    //account
    public static final String REQUEST_SIGNIN_FACEBOOK_URL = "http://pikasmart.com/api/Users/signinWithFacebook";
    public static final String REQUEST_SIGNIN_GOOGLE_URL = "http://pikasmart.com/api/Users/signinWithGmail";
    public static final String URL_GET_NOTIFICATION = "http://pikasmart.com/api/Users/notification?limit=%d&skip=%d&access_token=%s";

    //contribute
    public static final String URL_GET_CONTRIBUTE_SIGNIN = "http://pikasmart.com/api/Songs/contributes?limit=%d&skip=%d&type=%s&access_token=%s";
    public static final String URL_GET_CONTRIBUTE = "http://pikasmart.com/api/Songs/contributes?limit=%d&skip=%d&type=%s";

    //album
    public static final String URL_GET_LIST_ALBUM = "http://pikasmart.com/api/albums/listAlbum?limit=%d&skip=%d&type_album=%d";
    public static final String URL_GET_LIST_SONG_ALBUM = "http://pikasmart.com/api/albumSongs/listSongInAlbum?album_id=%d&limit=%d&skip=%d";

    public static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36";
    public static final String MAZII_URL_SVG_WORD = "http://data.mazii.net/kanji/%s.svg";
    public static final String MAZII_URL_KANJI_SEARCH = "http://api.mazii.net/api/kanji/%s/%d";
    public static final String MAZII_URL_KANJI_SEARCH_VI = "http://mazii.net/api/mazii/%s/%d";
    public static final String MAZII_URL_EXAMPLE = "https://api.mazii.net/api/example/%s";
    public static final String MAZII_URL_EXAMPLE_VI = "http://mazii.net/api/simle/%s";
    public static final String MAZII_URL_WORD_CN = "https://mazii.net/api/search_jacn/%s/20";
    public static final String MAZII_URL_WORD_VI = "http://mazii.net/api/search/%s/20";
    public static final String MAZII_URL_WORD = "https://api.mazii.net/api/word/%s";
    public static final String GOOGLE_URL_TRANSLATE = "https://translate.googleapis.com/translate_a/single?client=gtx&dt=t&dt=bd&dj=1&dt=ex&dt=ld&dt=md&dt=qca&dt=rw&dt=rm&dt=ss&dt=at&sl=%s&tl=%s&q=%s"; // from, to, query
    public static final String GOOGLE_URL_IMAGE = "https://www.google.com/search?source=lnms&tbm=isch&q=%s"; //query
    public static final String MAZII_URL_REQUEST = "http://mazii.net/api/search";

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

//    public static String getLanguage(Context context, String lang) {
//        try {
//            switch (lang.toLowerCase()) {
//                case "vn":
//                    return context.getString(R.string.vn);
//                case "jp":
//                    return context.getString(R.string.jp);
//                case "id":
//                    return context.getString(R.string.id);
//                default:
//                    return context.getString(R.string.en);
//            }
//        } catch (NullPointerException e) {
//            return "";
//        }
//    }

    public static void transparentStatusAndNavigation(Activity activity) {
        //make full transparent statusBar
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(activity, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            activity.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            );
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(activity, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, false);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            activity.getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    private static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * @param context      context
     * @param attr         attr resource
     * @param defaultColor default color
     * @return
     */
    public static int resolveColorByStyle(Context context, int attr, int defaultColor) {
        int colorResolved = defaultColor;
        TypedValue typedValue = new TypedValue();
        //Set color base on theme
        if (context.getTheme().resolveAttribute(attr, typedValue, true)) {
            colorResolved = typedValue.data;
        }

        return colorResolved;
    }

    public static void setLightDarkTheme(AppCompatActivity appCompatActivity, int theme) {
        if (theme == 0) {
            appCompatActivity.setTheme(R.style.AppTheme);
        } else if (theme == 1) {
            //appCompatActivity.setTheme(R.style.DarkTheme);
        } else {
            int hourOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            if (hourOfDay >= 18 || hourOfDay <= 5) {
               // appCompatActivity.setTheme(R.style.DarkTheme);
            } else {
                appCompatActivity.setTheme(R.style.AppTheme);
            }
        }
    }
}
