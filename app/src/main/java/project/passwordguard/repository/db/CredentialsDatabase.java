package project.passwordguard.repository.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import project.passwordguard.application.MyApplication;
import project.passwordguard.repository.Repository;
import project.passwordguard.repository.dao.CredentialsDao;
import project.passwordguard.model.CredentialsEntity;

@Database(entities = CredentialsEntity.class, version = 2, exportSchema = false)
public abstract class CredentialsDatabase extends RoomDatabase {

    public abstract CredentialsDao credentialsDao();

    private static volatile CredentialsDatabase instance;
//    private static final int NUMBER_OF_THREADS = 2;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newSingleThreadExecutor();

    public static synchronized CredentialsDatabase getInstance() {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    MyApplication.applicationContext(),
                    CredentialsDatabase.class,
                    "credentials_database")
                    .fallbackToDestructiveMigrationFrom()
                    .build();
        }
        return instance;
    }
}
