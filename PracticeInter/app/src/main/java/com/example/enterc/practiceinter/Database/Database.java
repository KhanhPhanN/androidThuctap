package com.example.enterc.practiceinter.Database;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

@com.raizlabs.android.dbflow.annotation.Database(name = Database.NAME, version = Database.VERSION)
public class Database {
    public static final String NAME = "CompanyDatabase";

    public static final int VERSION = 1;


    public static void deletePlaylist(int id) {
        PlaylistDetailItem playlistDetailItem = SQLite
                    .select()
                    .from(PlaylistDetailItem.class)
                    .where(PlaylistDetailItem_Table.id.eq(id))
                    .querySingle();
            if (playlistDetailItem != null) {
                FlowManager.getModelAdapter(PlaylistDetailItem.class).delete(playlistDetailItem);
            }

    }

}
