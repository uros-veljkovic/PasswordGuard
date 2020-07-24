package project.passwordguard.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import project.passwordguard.dao.CredentialsDao;
import project.passwordguard.entity.CredentialsEntity;

@Database(entities = CredentialsEntity.class, version = 1)
public abstract class CredentialsDatabase extends RoomDatabase {

    private static CredentialsDatabase instance;

    public abstract CredentialsDao credentialsDao();

    public static synchronized CredentialsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    CredentialsDatabase.class,
                    "credentials_database")
                    .fallbackToDestructiveMigrationFrom()
                    .build();
        }
        return instance;
    }
}
