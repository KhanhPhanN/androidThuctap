package com.example.enterc.practiceinter.Fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.enterc.practiceinter.Activity.PlayVideo;
import com.example.enterc.practiceinter.Listener.CallBack;
import com.example.enterc.practiceinter.Listener.ListWordCallback;
import com.example.enterc.practiceinter.Listener.OnShowOrHideFabListener;
import com.example.enterc.practiceinter.Listener.StringCallback;
import com.example.enterc.practiceinter.LoadData.GetListWordHelper;
import com.example.enterc.practiceinter.Model.Helper.GlobalHelper;
import com.example.enterc.practiceinter.Model.Helper.PreferenceHelper;
import com.example.enterc.practiceinter.R;
import com.example.enterc.practiceinter.adapter.VocabularyAdapter;

import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentVocabulary extends Fragment{
    PreferenceHelper preferenceHelper;
    int songId = -1;
    boolean isLoaded = false;
    @BindView(R.id.rv_vocabulary)
    RecyclerView recyclerView;

    @BindView(R.id.place_holder)
    RelativeLayout placeHolder;
    @BindView(R.id.place_holder_img)
    ImageView placeHolderImg;
    @BindView(R.id.place_holder_tv)
    TextView placeHolderTextView;

    @BindDrawable(R.drawable.hg_no_connection)
    Drawable drawableImgNoConnection;
    @BindDrawable(R.drawable.hg_loading)
    Drawable drawableImgLoading;
    @BindDrawable(R.drawable.hg_error)
    Drawable drawableImgError;
    @BindDrawable(R.drawable.hg_no_result)
    Drawable drawableImgNoResult;

    @BindString(R.string.loading)
    String loading;
    @BindString(R.string.loading_vocabulary_error)
    String loadingError;
    @BindString(R.string.no_vocabulary)
    String noVocabulary;
    @BindString(R.string.no_connect)
    String noConnection;
    @BindString(R.string.seen_more_detail_word)
    String seen_more_detail_word;
    @BindString(R.string.sign_in)
    String sign_in;

    OnShowOrHideFabListener fabListener;
    public static FragmentVocabulary newInstance(int songId) {

        Bundle args = new Bundle();
        args.putInt("ID", songId);
        FragmentVocabulary fragment = new FragmentVocabulary();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vocabulary_detail_video, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        preferenceHelper = new PreferenceHelper(getContext(), GlobalHelper.PREFERENCE_NAME_PIKA);

        Bundle bundle = getArguments();
        if (bundle != null) {
            songId = bundle.getInt("ID", -1);
        }
       //initUi();
        setupRecyclerView();
        loadVocabulary();
    }
    private void createAndSetAdapter(List<MultiItemEntity> nLevelMultiItemEntities) {
        VocabularyAdapter adapter = new VocabularyAdapter(nLevelMultiItemEntities, callback);

        recyclerView.setAdapter(adapter);
    }
    private StringCallback callback = new StringCallback() {
        @Override
        public void execute(String word) {
//            if (preferenceHelper.getSignin() != 0) {
//                Intent intent = new Intent(getContext(), DetailWordActivity.class);
//                intent.putExtra("word", word);
//                startActivity(intent);
//            } else
//                Snackbar.make(getActivity().findViewById(R.id.main_layout_id), seen_more_detail_word, Snackbar.LENGTH_SHORT)
//                        .setAction(sign_in, new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                startActivity(new Intent(getActivity(), SigninV2Activity.class));
//                            }
//                        }).show();
        }
    };

    private void setupRecyclerView() {
        if (getContext() != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setHasFixedSize(true);

        }
    }


    private void loadVocabulary() {

            new GetListWordHelper(getContext(), onPre, onPost).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                    String.format(GlobalHelper.URL_GET_LIST_WORD, songId, preferenceHelper.getLanguageCode()));

    }
    private ListWordCallback onPost = new ListWordCallback() {
        @Override
        public void execute(List<MultiItemEntity> nLevelMultiItemEntities) {
            if (getActivity() != null && !getActivity().isFinishing()) {
                if (nLevelMultiItemEntities != null) {
                    if (!nLevelMultiItemEntities.isEmpty()) {
                        isLoaded = true;
                        showHidePlaceholder(false);
                        createAndSetAdapter(nLevelMultiItemEntities);
                    } else {
                        showNoResultPlaceholder();
                    }
                } else showErrorPlaceholder();
            }
        }
    };

    private CallBack onPre = new CallBack() {
        @Override
        public void execute() {
            showLoadingPlaceholder();
        }
    };


    private void showHidePlaceholder(Boolean isShow) {
        recyclerView.setVisibility(isShow ? View.GONE : View.VISIBLE);
        placeHolder.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }


    private void showLoadingPlaceholder() {
        placeHolderImg.setVisibility(View.VISIBLE);
        placeHolderImg.setImageDrawable(drawableImgLoading);
        placeHolderTextView.setText(loading);
        showHidePlaceholder(true);
    }
    private void showErrorPlaceholder() {
        placeHolderImg.setVisibility(View.VISIBLE);
        placeHolderImg.setImageDrawable(drawableImgError);
        placeHolderTextView.setText(loadingError);
        showHidePlaceholder(true);
    }

    private void showNoResultPlaceholder() {
        placeHolderImg.setVisibility(View.VISIBLE);
        placeHolderImg.setImageDrawable(drawableImgNoResult);
        placeHolderTextView.setText(noVocabulary);
        showHidePlaceholder(true);
    }

//    @Override
//    public void onSettingsEvent(EventSettingsHelper event) {
//        switch (event.getStateChange()) {
//            case FURIGANA:
//                break;
//            case LYRIC_JA:
//                break;
//            case ROMAJI:
//                break;
//            case LYRIC_SUB:
//                break;
//            case CHANGE_LANGUAGE:
//                loadVocabulary();
//                break;
//            case LOAD_CONTENT:
//                if (!isLoaded)
//                    loadVocabulary();
//                break;
//        }
//        super.onSettingsEvent(event);
//    }
}
