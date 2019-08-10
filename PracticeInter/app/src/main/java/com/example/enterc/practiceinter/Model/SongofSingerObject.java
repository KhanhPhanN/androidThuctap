package com.example.enterc.practiceinter.Model;


import java.util.ArrayList;
import java.util.List;

public class SongofSingerObject {

    private Error Err;

    public Error getError() {
        return Err;
    }

    public void setError(Error error) {
        this.Err = error;
    }

    private List<Song> Song;

    public List<Song> getSong() {
        return Song;
    }

    public void setSong(ArrayList<Song> song) {
        this.Song = song;
    }

    public class Song {

        private Integer id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        private String thumbnail;

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        private Integer delete_flag;

        public Integer getDeleteFlag() {
            return delete_flag;
        }

        public void setDeleteFlag(Integer deleteFlag) {
            this.delete_flag = deleteFlag;
        }

        private String language_code;

        public String getLanguageCode() {
            return language_code;
        }

        public void setLanguageCode(String languageCode) {
            this.language_code = languageCode;
        }

        private Integer level_id;

        public Integer getLevelId() {
            return level_id;
        }

        public void setLevelId(Integer levelId) {
            this.level_id = levelId;
        }

        private String created_at;

        public String getCreatedAt() {
            return created_at;
        }

        public void setCreatedAt(String createdAt) {
            this.created_at = createdAt;
        }

        private String updated_at;

        public String getUpdatedAt() {
            return updated_at;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updated_at = updatedAt;
        }

        private Integer view;

        public Integer getView() {
            return view;
        }

        public void setView(Integer view) {
            this.view = view;
        }

        private String slug;

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        private String video_type;

        public String getVideoType() {
            return video_type;
        }

        public void setVideoType(String videoType) {
            this.video_type = videoType;
        }

        private String video_length;

        public String getVideoLength() {
            return video_length;
        }

        public void setVideoLength(String videoLength) {
            this.video_length = videoLength;
        }

        private Integer first_phase_flag;

        public Integer getFirstPhaseFlag() {
            return first_phase_flag;
        }

        public void setFirstPhaseFlag(Integer firstPhaseFlag) {
            this.first_phase_flag = firstPhaseFlag;
        }

        private Object admin_id;

        public Object getAdminId() {
            return admin_id;
        }

        public void setAdminId(Object adminId) {
            this.admin_id = adminId;
        }

        private Object last_updated_candidate;

        public Object getLastUpdatedCandidate() {
            return last_updated_candidate;
        }

        public void setLastUpdatedCandidate(Object lastUpdatedCandidate) {
            this.last_updated_candidate = lastUpdatedCandidate;
        }

        private Object user_translate_flag;

        public Object getUserTranslateFlag() {
            return user_translate_flag;
        }

        public void setUserTranslateFlag(Object userTranslateFlag) {
            this.user_translate_flag = userTranslateFlag;
        }

        private String name_ro;

        public String getNameRo() {
            return name_ro;
        }

        public void setNameRo(String nameRo) {
            this.name_ro = nameRo;
        }

        private String name_en;

        public String getNameEn() {
            return this.name_en;
        }

        public void setNameEn(String name_en) {
            this.name_en = name_en;
        }

        private String name_vn;

        public String getNameVn() {
            return name_vn;
        }

        public void setNameVn(String nameVn) {
            this.name_vn = nameVn;
        }

        private boolean isSelected = false;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }

    public class Error {

        private Integer statusCode;
        private String name;
        private String message;
        private String stack;

        public Integer getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(Integer statusCode) {
            this.statusCode = statusCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getStack() {
            return stack;
        }

        public void setStack(String stack) {
            this.stack = stack;
        }

    }
}
