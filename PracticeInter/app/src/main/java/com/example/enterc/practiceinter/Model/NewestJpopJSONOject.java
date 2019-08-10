package com.example.enterc.practiceinter.Model;

import java.util.ArrayList;
import java.util.List;

public class NewestJpopJSONOject {

    private Object Err;

    private ArrayList<Song> Song = null;

    public Object getErr() {
        return Err;
    }

    public void setErr(Object err) {
        this.Err = err;
    }

    public ArrayList<Song> getSong() {
        return Song;
    }

    public void setSong(ArrayList<Song> song) {
        this.Song = song;
    }
    public class Song {


        private Integer id;

        private String name;

        private String slug;

        private String url;

        private String url_vimeo;

        private Integer check_download;

        private String thumbnail;

        private String created_at;

        private String updated_at;

        private String video_type;

        private String video_length;

        private Integer level_id;

        private String name_vn;

        private String name_ro;

        private String name_en;

        private Integer view;

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

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrlVimeo() {
            return url_vimeo;
        }

        public void setUrlVimeo(String urlVimeo) {
            this.url_vimeo = urlVimeo;
        }

        public Integer getCheckDownload() {
            return check_download;
        }

        public void setCheckDownload(Integer checkDownload) {
            this.check_download = checkDownload;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getCreatedAt() {
            return created_at;
        }

        public void setCreatedAt(String createdAt) {
            this.created_at = createdAt;
        }

        public String getUpdatedAt() {
            return updated_at;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updated_at = updatedAt;
        }

        public String getVideoType() {
            return video_type;
        }

        public void setVideoType(String videoType) {
            this.video_type = videoType;
        }

        public String getVideoLength() {
            return video_length;
        }

        public void setVideoLength(String videoLength) {
            this.video_length = videoLength;
        }

        public Integer getLevelId() {
            return level_id;
        }

        public void setLevelId(Integer levelId) {
            this.level_id = levelId;
        }

        public String getNameVn() {
            return name_vn;
        }

        public void setNameVn(String nameVn) {
            this.name_vn = nameVn;
        }

        public String getNameRo() {
            return name_ro;
        }

        public void setNameRo(String nameRo) {
            this.name_ro = nameRo;
        }

        public String getNameEn() {
            return name_en;
        }

        public void setNameEn(String nameEn) {
            this.name_en = nameEn;
        }

        public Integer getView() {
            return view;
        }

        public void setView(Integer view) {
            this.view = view;
        }

    }
}
