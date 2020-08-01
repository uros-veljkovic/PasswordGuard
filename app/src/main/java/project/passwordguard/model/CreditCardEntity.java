package project.passwordguard.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import project.passwordguard.BR;

@Entity(tableName = "credit_card")
public class CreditCardEntity extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "bank_name")
    private String bankName;
    @ColumnInfo(name = "card_name")
    private String cardName;
    @ColumnInfo(name = "card_number")
    private String cardNumber;
    @ColumnInfo(name = "expiration_date")
    private String expirationDate;
    @ColumnInfo(name = "security_code")
    private String securityCode;
    @ColumnInfo(name = "pin")
    private String pin;

    public CreditCardEntity(String bankName, String cardName, String cardNumber, String expirationDate, String securityCode, String pin) {
        this.bankName = bankName;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
        this.pin = pin;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Bindable
    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
        notifyPropertyChanged(BR.bankName);
    }

    @Bindable
    public String getCardName() {
        return cardName;
    }
    public void setCardName(String cardName) {
        this.cardName = cardName;
        notifyPropertyChanged(BR.cardNumber);
    }

    @Bindable
    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        notifyPropertyChanged(BR.cardNumber);
    }

    @Bindable
    public String getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
        notifyPropertyChanged(BR.expirationDate);
    }

    @Bindable
    public String getSecurityCode() {
        return securityCode;
    }
    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
        notifyPropertyChanged(BR.securityCode);
    }

    @Bindable
    public String getPin() {
        return pin;
    }
    public void setPin(String pin) {
        this.pin = pin;
        notifyPropertyChanged(BR.pin);
    }
}
