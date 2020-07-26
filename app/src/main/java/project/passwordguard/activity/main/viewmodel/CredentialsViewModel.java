package project.passwordguard.activity.main.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import project.passwordguard.entity.CredentialsEntity;
import project.passwordguard.repository.Repository;

public class CredentialsViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<CredentialsEntity>> credentials;

    public CredentialsViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        credentials = new MutableLiveData<>();
        credentials = repository.getCredentials();
    }

    public void insert(CredentialsEntity entity) {
        repository.insert(entity);
    }

    public void update(CredentialsEntity entity) {
        repository.update(entity);
    }

    public void delete(CredentialsEntity entity) {
        repository.delete(entity);
    }

    public LiveData<List<CredentialsEntity>> getCredentials() {
        return credentials;
    }

}
