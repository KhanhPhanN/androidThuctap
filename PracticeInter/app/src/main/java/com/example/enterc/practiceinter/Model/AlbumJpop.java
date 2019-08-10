package com.example.enterc.practiceinter.Model;

import java.util.ArrayList;
import java.util.List;

public class AlbumJpop {

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

    public class Datum {


        private Integer id;

        private String title;

        private String name;

        private String name_en;

        private Integer type;

        private String image;

        private Integer status;

        private String created_at;

        private String updated_at;

        private Integer count_video;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNameEn() {
            return name_en;
        }

        public void setNameEn(String nameEn) {
            this.name_en = nameEn;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
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

        public Integer getCountVideo() {
            return count_video;
        }

        public void setCountVideo(Integer countVideo) {
            this.count_video = countVideo;
        }

    }
}
