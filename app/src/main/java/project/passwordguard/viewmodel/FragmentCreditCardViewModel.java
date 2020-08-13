package project.passwordguard.viewmodel;

import android.app.Presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import project.passwordguard.model.CreditCardEntity;
import project.passwordguard.repository.Repository;

public class FragmentCreditCardViewModel extends ViewModel {

    private Repository repository;
    private LiveData<List<CreditCardEntity>> creditCards;

    public FragmentCreditCardViewModel() {
        super();
        repository = Repository.getInstance();
        creditCards = repository.getCreditCards();
    }

    public void insert(CreditCardEntity entity) {
        repository.insert(entity);
    }

    public void update(CreditCardEntity entity) {
        repository.update(entity);
    }

    public void delete(CreditCardEntity entity) {
        repository.delete(entity);
    }

    public LiveData<List<CreditCardEntity>> getCreditCards() {
        if(creditCards == null){
            creditCards = new MutableLiveData<>();
            loadCreditCards();
        }
        return creditCards;
    }

    private void loadCreditCards() {
        creditCards = repository.getCreditCards();
    }
}