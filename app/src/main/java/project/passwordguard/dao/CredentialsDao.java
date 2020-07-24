package project.passwordguard.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import project.passwordguard.entity.CredentialsEntity;

@Dao
public interface CredentialsDao {

    @Insert
    void insert(CredentialsEntity credentialsEntity);

    @Update
    void update(CredentialsEntity credentialsEntity);

    @Delete
    void delete(CredentialsEntity credentialsEntity);

    @Query("SELECT * FROM credentials ORDER BY website_url ASC")
    LiveData<List<CredentialsEntity>> getAllCredentials();

}
