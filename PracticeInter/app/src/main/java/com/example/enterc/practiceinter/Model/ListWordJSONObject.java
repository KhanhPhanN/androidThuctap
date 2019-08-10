package com.example.enterc.practiceinter.Model;

import java.util.ArrayList;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.enterc.practiceinter.adapter.VocabularyAdapter;

public class ListWordJSONObject {

    private Error Err;

    public Error getErr() { return this.Err; }

    public void setErr(Error Err) { this.Err = Err; }

    private Data Data;

    public Data getData() { return this.Data; }

    public void setData(Data Data) { this.Data = Data; }

    public class NLevel implements MultiItemEntity {
        private int id;

        public int getId() { return this.id; }

        public void setId(int id) { this.id = id; }

        private String slug;

        public String getSlug() { return this.slug; }

        public void setSlug(String slug) { this.slug = slug; }

        private String word_value;

        public String getWordValue() { return this.word_value; }

        public void setWordValue(String word_value) { this.word_value = word_value; }

        private String reading;

        public String getReading() { return this.reading; }

        public void setReading(String reading) { this.reading = reading; }

        private String meaning;

        public String getMeaning() { return this.meaning; }

        public void setMeaning(String meaning) { this.meaning = meaning; }

        private String level;

        public String getLevel() { return this.level; }

        public void setLevel(String level) { this.level = level; }

        private int level_id;

        public int getLevelId() { return this.level_id; }

        public void setLevelId(int level_id) { this.level_id = level_id; }

        private String mini_kanji;

        public String getMiniKanji() { return this.mini_kanji; }

        public void setMiniKanji(String mini_kanji) { this.mini_kanji = mini_kanji; }

        @Override
        public int getItemType() {
            return VocabularyAdapter.TYPE_ITEM_VOCABULARY;
        }
    }

    public class Data
    {
        private ArrayList<NLevel> N5;

        public ArrayList<NLevel> getN5() { return this.N5; }

        public void setN5(ArrayList<NLevel> N5) { this.N5 = N5; }

        private ArrayList<NLevel> N4;

        public ArrayList<NLevel> getN4() { return this.N4; }

        public void setN4(ArrayList<NLevel> N4) { this.N4 = N4; }

        private ArrayList<NLevel> N3;

        public ArrayList<NLevel> getN3() { return this.N3; }

        public void setN3(ArrayList<NLevel> N3) { this.N3 = N3; }

        private ArrayList<NLevel> N2;

        public ArrayList<NLevel> getN2() { return this.N2; }

        public void setN2(ArrayList<NLevel> N2) { this.N2 = N2; }

        private ArrayList<NLevel> N1;

        public ArrayList<NLevel> getN1() { return this.N1; }

        public void setN1(ArrayList<NLevel> N1) { this.N1 = N1; }
    }

    public class Error
    {
        private int statusCode;

        public int getStatusCode() { return this.statusCode; }

        public void setStatusCode(int statusCode) { this.statusCode = statusCode; }

        private String name;

        public String getName() { return this.name; }

        public void setName(String name) { this.name = name; }

        private String message;

        public String getMessage() { return this.message; }

        public void setMessage(String message) { this.message = message; }

        private String stack;

        public String getStack() { return this.stack; }

        public void setStack(String stack) { this.stack = stack; }
    }

}
