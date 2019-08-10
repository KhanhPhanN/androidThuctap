package com.example.enterc.practiceinter.Model;

import java.util.ArrayList;
import java.util.List;

public class LyricSong {

    private Object Err;

    private ArrayList<Datum> Data = null;

    public Object getErr() {
        return Err;
    }

    public void setErr(Object err) {
        this.Err = err;
    }

    public ArrayList<Datum> getData() {
        return Data;
    }

    public void setData(ArrayList<Datum> data) {
        this.Data = data;
    }

   public static class Datum {
        public Datum(Integer id, Integer song_id, String start_time, String end_time, Integer period_order, Integer sentence_id, String language_code, String sentence_value, String sentence_hira, String sentence_ro, String sentence_vn, List<Object> listword) {
            this.id = id;
            this.song_id = song_id;
            this.start_time = start_time;
            this.end_time = end_time;
            this.period_order = period_order;
            this.sentence_id = sentence_id;
            this.language_code = language_code;
            this.sentence_value = sentence_value;
            this.sentence_hira = sentence_hira;
            this.sentence_ro = sentence_ro;
            this.sentence_vn = sentence_vn;
            this.listword = listword;
        }

        private Integer id;

        private Integer song_id;

        private String start_time;

        private String end_time;

        private Integer period_order;

        private Integer sentence_id;

        private String language_code;

        private String sentence_value;

        private String sentence_hira;

        private String sentence_ro;

        private String sentence_vn;

        private List<Object> listword = null;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getSongId() {
            return song_id;
        }

        public void setSongId(Integer songId) {
            this.song_id = songId;
        }

        public String getStartTime() {
            return start_time;
        }

        public void setStartTime(String startTime) {
            this.start_time = startTime;
        }

        public String getEndTime() {
            return end_time;
        }

        public void setEndTime(String endTime) {
            this.end_time = endTime;
        }

        public Integer getPeriodOrder() {
            return period_order;
        }

        public void setPeriodOrder(Integer periodOrder) {
            this.period_order = periodOrder;
        }

        public Integer getSentenceId() {
            return sentence_id;
        }

        public void setSentenceId(Integer sentenceId) {
            this.sentence_id = sentenceId;
        }

        public String getLanguageCode() {
            return language_code;
        }

        public void setLanguageCode(String languageCode) {
            this.language_code = languageCode;
        }

        public String getSentenceValue() {
            return sentence_value;
        }

        public void setSentenceValue(String sentenceValue) {
            this.sentence_value = sentenceValue;
        }

        public String getSentenceHira() {
            return sentence_hira;
        }

        public void setSentenceHira(String sentenceHira) {
            this.sentence_hira = sentenceHira;
        }

        public String getSentenceRo() {
            return sentence_ro;
        }

        public void setSentenceRo(String sentenceRo) {
            this.sentence_ro = sentenceRo;
        }

        public String getSentenceVn() {
            return sentence_vn;
        }

        public void setSentenceVn(String sentenceVn) {
            this.sentence_vn = sentenceVn;
        }

        public List<Object> getListword() {
            return listword;
        }

        public void setListword(List<Object> listword) {
            this.listword = listword;
        }

        private boolean isSelected = false;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

    }
}