package project.passwordguard.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import project.passwordguard.dao.CredentialsDao;
import project.passwordguard.db.CredentialsDatabase;
import project.passwordguard.entity.CredentialsEntity;

public class Repository {

    private CredentialsDao credentialsDao;
    private LiveData<List<CredentialsEntity>> credentials;

    public Repository(Application application) {
        CredentialsDatabase db = CredentialsDatabase.getInstance(application);
        credentialsDao = db.credentialsDao();
        credentials = credentialsDao.getAllCredentials();
    }

    public void insert(CredentialsEntity entity) {
        new InsertCredentialsAsyncTaks(credentialsDao).execute(entity);
    }
    public void update(CredentialsEntity entity) {
        new UpdateCredentialsAsyncTaks(credentialsDao).execute(entity);
    }
    public void delete(CredentialsEntity entity) {
        new DeleteCredentialsAsyncTaks(credentialsDao).execute(entity);
    }
    public LiveData<List<CredentialsEntity>> getCredentials() {
        return  credentials;
    }

    private static abstract class CredentialsAsyncTask extends AsyncTask<CredentialsEntity, Void, Void> {
        protected CredentialsDao dao;

        private CredentialsAsyncTask(CredentialsDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(CredentialsEntity... credentialsEntities) {
            execute(credentialsEntities[0]);
            return null;
        }

        protected abstract void execute(CredentialsEntity entity);

    }

    private static class InsertCredentialsAsyncTaks extends CredentialsAsyncTask {

        private InsertCredentialsAsyncTaks(CredentialsDao dao) {
            super(dao);
        }

        @Override
        protected void execute(CredentialsEntity entity) {
            dao.insert(entity);
        }

    }
    private static class UpdateCredentialsAsyncTaks extends CredentialsAsyncTask {

        private UpdateCredentialsAsyncTaks(CredentialsDao dao) {
            super(dao);
        }

        @Override
        protected void execute(CredentialsEntity entity) {
            dao.update(entity);
        }
    }
    private static class DeleteCredentialsAsyncTaks extends CredentialsAsyncTask {

        private DeleteCredentialsAsyncTaks(CredentialsDao dao) {
            super(dao);
        }

        @Override
        protected void execute(CredentialsEntity entity) {
            dao.delete(entity);
        }
    }


}
