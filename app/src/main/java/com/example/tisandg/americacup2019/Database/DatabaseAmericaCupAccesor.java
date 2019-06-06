package com.example.tisandg.americacup2019.Database;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseAmericaCupAccesor {

    private static DatabaseAmericaCup databaseIntance;
    //Constant about the name assigned to SQLite database
    private static final String TODO_DB_NAME = "todo_db";

    private DatabaseAmericaCupAccesor() {
    }

    public static DatabaseAmericaCup getInstance(Context context) {
        if (databaseIntance == null) {
            // Create or open a new SQLite database, and return it as a Room Database instance.
            databaseIntance = Room.databaseBuilder(context,
                    DatabaseAmericaCup.class, TODO_DB_NAME).build();
        }
        return databaseIntance;
    }

}
