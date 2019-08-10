package com.example.enterc.practiceinter.Model;

import java.util.List;

public class Singer {

    public Singer(Integer id_singer, String name, String avatar, String type) {
            this.id_singer = id_singer;
            this.name = name;
            this.avatar = avatar;
            this.type = type;
        }

        private Integer id_singer;

        private String name;

        private String avatar;

        private String type;

        public Integer getIdSinger() {
            return id_singer;
        }

        public void setIdSinger(Integer idSinger) {
            this.id_singer = idSinger;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

    }

