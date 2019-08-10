package com.example.enterc.practiceinter.View;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.enterc.practiceinter.Model.ListWordJSONObject;
import com.example.enterc.practiceinter.adapter.VocabularyAdapter;

public class HeaderVocabulary extends AbstractExpandableItem<ListWordJSONObject.NLevel> implements MultiItemEntity {
    public String title;

    public HeaderVocabulary(String title) {
        this.title = title;
    }

    @Override
    public int getItemType() {
        return VocabularyAdapter.TYPE_HEADER;
    }

    @Override
    public int getLevel() {
        return 0;
    }

}