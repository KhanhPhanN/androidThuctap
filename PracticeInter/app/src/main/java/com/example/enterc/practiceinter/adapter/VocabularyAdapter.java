package com.example.enterc.practiceinter.adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.enterc.practiceinter.Listener.CallBack;
import com.example.enterc.practiceinter.Listener.StringCallback;
import com.example.enterc.practiceinter.Model.Helper.AnimationHelper;
import com.example.enterc.practiceinter.Model.Helper.SpeakTextHelper;
import com.example.enterc.practiceinter.Model.ListWordJSONObject;
import com.example.enterc.practiceinter.R;
import com.example.enterc.practiceinter.View.HeaderVocabulary;


import java.util.List;


public class VocabularyAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_ITEM_VOCABULARY = 1;

    private StringCallback callback;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public VocabularyAdapter(List<MultiItemEntity> data, StringCallback callback) {
        super(data);
        this.callback = callback;
        addItemType(TYPE_HEADER, R.layout.item_header_vocabulary);
        addItemType(TYPE_ITEM_VOCABULARY, R.layout.item_vocabulary_dv);
    }

    @Override
    protected void convert(final BaseViewHolder holder, MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case TYPE_HEADER:
                final HeaderVocabulary header = (HeaderVocabulary) item;
                holder.setText(R.id.title, header.title)
                        .setImageResource(R.id.iv, header.isExpanded() ? R.drawable.ic_arrow_right : R.drawable.ic_expand_more);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        if (header.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }
                    }
                });
                break;
            case TYPE_ITEM_VOCABULARY:
                final ListWordJSONObject.NLevel nLevel = (ListWordJSONObject.NLevel) item;
                if (nLevel != null) {
                    if (nLevel.getWordValue() == null)
                        nLevel.setWordValue("");
                    holder.setText(R.id.word_tv, nLevel.getWordValue());
                    if (nLevel.getReading() != null)
                        holder.setText(R.id.reading_word_tv, nLevel.getReading());
                    if (nLevel.getMeaning() != null)
                        holder.setText(R.id.meaning_tv, nLevel.getMeaning().contains("\n") ?
                                nLevel.getMeaning().replace("\n", "") : nLevel.getMeaning());
                    if (nLevel.getMiniKanji() != null && nLevel.getMiniKanji().length() > 0) {
                        holder.getView(R.id.layout_minikanji).setVisibility(View.VISIBLE);
                        holder.setText(R.id.tv_minikanji, nLevel.getMiniKanji());
                    } else
                        holder.getView(R.id.layout_minikanji).setVisibility(View.GONE);

                    holder.getView(R.id.frame_speak).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AnimationHelper.ScaleAnimation(holder.getView(R.id.frame_speak), new CallBack() {
                                @Override
                                public void execute() {
                                    SpeakTextHelper.SpeakText(nLevel.getWordValue());
                                }
                            }, 0.94f);
                        }
                    });

                    holder.getView(R.id.layout_item_vocabb).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AnimationHelper.ScaleAnimation(holder.getView(R.id.layout_item_vocabb), new CallBack() {
                                @Override
                                public void execute() {
                                    callback.execute(nLevel.getWordValue());
                                }
                            }, 0.96f);
                        }
                    });

                }
                break;
        }
    }
}
