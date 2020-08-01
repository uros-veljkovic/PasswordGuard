package project.passwordguard.repository.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import project.passwordguard.model.CredentialsEntity;
import project.passwordguard.model.CreditCardEntity;

@Dao
public interface CreditCardDao {

    @Insert
    void insert(CreditCardEntity creditCardEntity);

    @Update
    void update(CreditCardEntity credentialsEntity);

    @Delete
    void delete(CreditCardEntity credentialsEntity);

    @Query("SELECT * FROM credit_card ORDER BY card_name ASC")
    LiveData<List<CreditCardEntity>> getAllCreditCards();

}
