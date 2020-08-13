package project.passwordguard.repository.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import project.passwordguard.application.MyApplication;
import project.passwordguard.model.CreditCardEntity;
import project.passwordguard.repository.dao.CreditCardDao;

@Database(entities = CreditCardEntity.class, version = 1, exportSchema = false)
public abstract class CreditCardDatabase extends RoomDatabase {

    public abstract CreditCardDao creditCardDao();

    private static CreditCardDatabase instance;
//    private static final int NUMBER_OF_THREADS = 2;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newSingleThreadExecutor();

    public static synchronized CreditCardDatabase getInstance() {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    MyApplication.applicationContext(),
                    CreditCardDatabase.class,
                    "credit_card_database")
                    .fallbackToDestructiveMigrationFrom()
                    .build();
        }
        return instance;
    }
}
