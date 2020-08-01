package project.passwordguard.repository.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import project.passwordguard.application.MyApplication;
import project.passwordguard.repository.dao.CredentialsDao;
import project.passwordguard.model.CredentialsEntity;

@Database(entities = CredentialsEntity.class, version = 1)
public abstract class CredentialsDatabase extends RoomDatabase {

    private static CredentialsDatabase instance;
    public abstract CredentialsDao credentialsDao();

    public static synchronized CredentialsDatabase getInstance() {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    MyApplication.applicationContext(),
//                    context.getApplicationContext(),
                    CredentialsDatabase.class,
                    "credentials_database")
                    .fallbackToDestructiveMigrationFrom()
                    .build();
        }
        return instance;
    }
}
