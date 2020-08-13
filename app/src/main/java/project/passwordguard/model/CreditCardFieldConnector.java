package project.passwordguard.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import project.passwordguard.BR;
import project.passwordguard.util.constants.Constants;

import static project.passwordguard.util.constants.Constants.MONTH_INDEX;
import static project.passwordguard.util.constants.Constants.YEAR_INDEX;

public class CreditCardFieldConnector extends BaseObservable {


    private String bankName;
    private String cardName;
    private String cardNumbers1;
    private String cardNumbers2;
    private String cardNumbers3;
    private String cardNumbers4;
    private String expMonth;
    private String expYear;
    private String securityCode;
    private String pin;

    public CreditCardFieldConnector() {
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
        notifyPropertyChanged(BR.cardName);
    }

    @Bindable

    public String getCardNumbers1() {
        return cardNumbers1;
    }

    public void setCardNumbers1(String cardNumbers1) {
        this.cardNumbers1 = cardNumbers1;
        notifyPropertyChanged(BR.cardNumbers1);
    }

    @Bindable
    public String getCardNumbers2() {
        return cardNumbers2;
    }

    public void setCardNumbers2(String cardNumbers2) {
        this.cardNumbers2 = cardNumbers2;
        notifyPropertyChanged(BR.cardNumbers2);
    }

    @Bindable
    public String getCardNumbers3() {
        return cardNumbers3;
    }

    public void setCardNumbers3(String cardNumbers3) {
        this.cardNumbers3 = cardNumbers3;
        notifyPropertyChanged(BR.cardNumbers3);
    }

    @Bindable
    public String getCardNumbers4() {
        return cardNumbers4;
    }

    public void setCardNumbers4(String cardNumbers4) {
        this.cardNumbers4 = cardNumbers4;
        notifyPropertyChanged(BR.cardNumbers4);
    }

    @Bindable
    public String getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
        notifyPropertyChanged(BR.expMonth);
    }

    @Bindable
    public String getExpYear() {
        return expYear;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
        notifyPropertyChanged(BR.expYear);
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

    public void createConnectorFrom(CreditCardEntity entity) {
        bankName = entity.getBankName();
        cardName = entity.getCardName();

        String[] cardNumbers = entity.getCardNumber().split(" ");

        cardNumbers1 = cardNumbers[0];
        cardNumbers2 = cardNumbers[1];
        cardNumbers3 = cardNumbers[2];
        cardNumbers4 = cardNumbers[3];

        String[] expirationDate = entity.getExpirationDate().split("/");
        expMonth = expirationDate[MONTH_INDEX];
        expYear = expirationDate[YEAR_INDEX];

        securityCode = entity.getSecurityCode();
        pin = entity.getPin();

        notifyChange();
    }

    public CreditCardEntity buildEntity() {
        String cardNumber = cardNumbers1 + " " + cardNumbers2 + " " + cardNumbers3 + " " + cardNumbers4;
        String expDate = expMonth + "/" + expYear;

        CreditCardEntity entity = new CreditCardEntity();
        entity.setBankName(bankName);
        entity.setCardName(cardName);
        entity.setCardNumber(cardNumber);
        entity.setExpirationDate(expDate);
        entity.setPin(pin);
        entity.setSecurityCode(securityCode);

        return entity;
    }
}
