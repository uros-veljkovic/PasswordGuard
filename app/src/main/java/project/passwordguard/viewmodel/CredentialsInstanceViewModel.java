package project.passwordguard.viewmodel;

import androidx.lifecycle.ViewModel;

import project.passwordguard.model.CredentialsEntity;

public class CredentialsInstanceViewModel extends ViewModel {

    private CredentialsEntity entity;

    public CredentialsInstanceViewModel(){
        super();
        entity = new CredentialsEntity();
    }

    public CredentialsEntity getEntity() {
        return entity;
    }
    public void setEntity(CredentialsEntity entity) {
        this.entity = entity;
    }
}
