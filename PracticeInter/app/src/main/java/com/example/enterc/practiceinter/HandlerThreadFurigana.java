package com.example.enterc.practiceinter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;


import com.example.enterc.practiceinter.Model.Helper.GlobalHelper;
import com.example.enterc.practiceinter.Model.Helper.PreferenceHelper;
import com.example.enterc.practiceinter.Model.Helper.StringHelper;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class HandlerThreadFurigana<T> extends HandlerThread {
    private static final String TAG = "HandlerThreadFurigana";

    private static final int MESSAGE_FURIGANA = 1;
    private Handler mRequestHandler;
    private ConcurrentMap<T, String> mRequestMap = new ConcurrentHashMap<>();

    private Handler mResponseHandler;
    private HandlerFuriganaListener<T> mHanderListener;

    private PreferenceHelper preferenceHelper;

    public interface HandlerFuriganaListener<T> {
        void onFuriganaConverted(T target, String converted);
    }

    public void setHandlerFuriganaListener(HandlerFuriganaListener<T> listener) {
        mHanderListener = listener;
    }

    public HandlerThreadFurigana(Handler responseHandler, Context context) {
        super(TAG);
        mResponseHandler = responseHandler;
        preferenceHelper = new PreferenceHelper(context, GlobalHelper.PREFERENCE_NAME_PIKA);
    }


    @SuppressLint("HandlerLeak")
    @SuppressWarnings("unchecked")
    @Override
    protected void onLooperPrepared() {
        mRequestHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == MESSAGE_FURIGANA) {
                    T target = (T) msg.obj;
                    handleRequest(target);
                }
                super.handleMessage(msg);
            }
        };
    }

    public void queueConvertFurigana(T target, String value) {
        if (value == null) {
            mRequestMap.remove(target);
        } else {
            mRequestMap.put(target, value);
            if (mRequestHandler != null)
                mRequestHandler.obtainMessage(MESSAGE_FURIGANA, target)
                        .sendToTarget();
        }
    }

    public void clearQueue() {
        mRequestHandler.removeMessages(MESSAGE_FURIGANA);
    }

    private void handleRequest(final T target) {

        // convert furigana
        if (target == null) return;

        final String value = mRequestMap.get(target);

        if (value == null) {
            return;
        }

        final String converted;

        if (value.contains("|")) {
            List<String> list = Arrays.asList(value.split("\\|", 2));
            if (list.isEmpty() || list.size() < 2) return;
            String html = StringHelper.stringToFurigana(list.get(0), list.get(1));

            converted = getSentence(html);
        } else converted = getExampleContent(value);

        mResponseHandler.post(new Runnable() {
            public void run() {
                if (mRequestMap.get(target) != null && !mRequestMap.get(target).equals(value)) {
                    return;
                }
                mRequestMap.remove(target);
                mHanderListener.onFuriganaConverted(target, converted);
            }
        });
    }

    // MARK: check show || disable furigana text
    private String getSentence(String html) {
        return this.preferenceHelper.isShowFurigana() ? StringHelper.htlm2Furigana(html) : StringHelper.html2String(html);
    }

    private String getExampleContent(String jap) {
        return StringHelper.enableFurigana(jap);
    }

}

