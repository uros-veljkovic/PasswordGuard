package project.passwordguard.repository.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import project.passwordguard.application.MyApplication;
import project.passwordguard.model.CreditCardEntity;
import project.passwordguard.repository.dao.CreditCardDao;

@Database(entities = CreditCardEntity.class, version = 1)
public abstract class CreditCardDatabase extends RoomDatabase {

    private static CreditCardDatabase instance;
    public abstract CreditCardDao creditCardDao();

    public static synchronized CreditCardDatabase getInstance() {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    MyApplication.applicationContext(),
//                    context.getApplicationContext(),
                    CreditCardDatabase.class,
                    "credit_card_database")
                    .fallbackToDestructiveMigrationFrom()
                    .build();
        }
        return instance;
    }
}
