package com.example.enterc.practiceinter.Database;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

@Table(name = "type_video", database = Database.class)
public class TypeVideoItem {
    @PrimaryKey
    String type;

    @Column
    String dataJson;

    public TypeVideoItem(String type, String dataJson) {
        this.type = type;
        this.dataJson = dataJson;
    }

    public TypeVideoItem() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDataJson() {
        return dataJson;
    }

    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }
}
