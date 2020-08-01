package project.passwordguard.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import project.passwordguard.model.CreditCardEntity;
import project.passwordguard.repository.dao.CredentialsDao;
import project.passwordguard.repository.dao.CreditCardDao;
import project.passwordguard.repository.db.CredentialsDatabase;
import project.passwordguard.model.CredentialsEntity;
import project.passwordguard.repository.db.CreditCardDatabase;

public class Repository {

    private static Repository instance;

    private CredentialsDao credentialsDao;
    private CreditCardDao creditCardDao;
    private LiveData<List<CredentialsEntity>> credentials;
    private LiveData<List<CreditCardEntity>> creditCards;

    private Repository() {
        CredentialsDatabase databaseCredentials = CredentialsDatabase.getInstance();
        CreditCardDatabase databaseCreditCard = CreditCardDatabase.getInstance();

        credentialsDao = databaseCredentials.credentialsDao();
        creditCardDao = databaseCreditCard.creditCardDao();

        credentials = credentialsDao.getAllCredentials();
        creditCards = creditCardDao.getAllCreditCards();
    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public void insert(CredentialsEntity entity) {
        new InsertCredentialsAsyncTask(credentialsDao).execute(entity);
    }

    public void insert(CreditCardEntity entity) {
        new InsertCreditCardAsyncTask(creditCardDao).execute(entity);
    }

    public void update(CredentialsEntity entity) {
        new UpdateCredentialsAsyncTask(credentialsDao).execute(entity);
    }

    public void update(CreditCardEntity entity) {
        new UpdateCreditCardAsyncTask(creditCardDao).execute(entity);
    }

    public void delete(CredentialsEntity entity) {
        new DeleteCredentialsAsyncTask(credentialsDao).execute(entity);
    }

    public void delete(CreditCardEntity entity) {
        new DeleteCreditCardAsyncTask(creditCardDao).execute(entity);
    }

    public LiveData<List<CredentialsEntity>> getCredentials() {
        return credentials;
    }

    public LiveData<List<CreditCardEntity>> getCreditCards() {
        return creditCards;
    }

    private static abstract class CredentialsAsyncTask extends AsyncTask<CredentialsEntity, Void, Void> {
        protected CredentialsDao dao;

        private CredentialsAsyncTask(CredentialsDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(CredentialsEntity... entities) {
            execute(entities[0]);
            return null;
        }

        protected abstract void execute(CredentialsEntity entity);

    }

    private static abstract class CreditCardAsyncTask extends AsyncTask<CreditCardEntity, Void, Void> {
        protected CreditCardDao dao;

        private CreditCardAsyncTask(CreditCardDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(CreditCardEntity... entities) {
            execute(entities[0]);
            return null;
        }

        protected abstract void execute(CreditCardEntity entity);

    }

    private static class InsertCredentialsAsyncTask extends CredentialsAsyncTask {

        private InsertCredentialsAsyncTask(CredentialsDao dao) {
            super(dao);
        }

        @Override
        protected void execute(CredentialsEntity entity) {
            dao.insert(entity);
        }

    }

    private static class InsertCreditCardAsyncTask extends CreditCardAsyncTask {

        private InsertCreditCardAsyncTask(CreditCardDao dao) {
            super(dao);
        }

        @Override
        protected void execute(CreditCardEntity entity) {
            dao.insert(entity);
        }

    }

    private static class UpdateCredentialsAsyncTask extends CredentialsAsyncTask {

        private UpdateCredentialsAsyncTask(CredentialsDao dao) {
            super(dao);
        }

        @Override
        protected void execute(CredentialsEntity entity) {
            dao.update(entity);
        }
    }

    private static class UpdateCreditCardAsyncTask extends CreditCardAsyncTask {

        private UpdateCreditCardAsyncTask(CreditCardDao dao) {
            super(dao);
        }

        @Override
        protected void execute(CreditCardEntity entity) {
            dao.update(entity);
        }
    }

    private static class DeleteCredentialsAsyncTask extends CredentialsAsyncTask {

        private DeleteCredentialsAsyncTask(CredentialsDao dao) {
            super(dao);
        }

        @Override
        protected void execute(CredentialsEntity entity) {
            dao.delete(entity);
        }
    }

    private static class DeleteCreditCardAsyncTask extends CreditCardAsyncTask {

        private DeleteCreditCardAsyncTask(CreditCardDao dao) {
            super(dao);
        }

        @Override
        protected void execute(CreditCardEntity entity) {
            dao.delete(entity);
        }
    }


}
