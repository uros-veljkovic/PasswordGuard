package project.passwordguard.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import project.passwordguard.model.CredentialsEntity;
import project.passwordguard.model.CreditCardEntity;
import project.passwordguard.model.CreditCardFieldConnector;

public class CreditCardInstanceViewModel extends ViewModel  {

    private CreditCardEntity entity;
    private CreditCardFieldConnector connector;

    public CreditCardInstanceViewModel(){
        super();
        entity = new CreditCardEntity();
        connector = new CreditCardFieldConnector();
    }

    public CreditCardEntity getEntity() {
        return connector.buildEntity();
    }
    public void setEntity(CreditCardEntity entity) {
        this.entity = entity;
        this.connector.createConnectorFrom(entity);
    }

    public CreditCardFieldConnector getConnector() {
        return connector;
    }
    public void setConnector(CreditCardFieldConnector connector) {
        this.connector = connector;
    }


}
