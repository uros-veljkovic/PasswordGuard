package project.passwordguard.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import project.passwordguard.BR;

@Entity(tableName = "credit_card")
public class CreditCardEntity extends BaseObservable implements Parcelable {

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

    @Ignore
    public CreditCardEntity() {
    }

    protected CreditCardEntity(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        bankName = in.readString();
        cardName = in.readString();
        cardNumber = in.readString();
        expirationDate = in.readString();
        securityCode = in.readString();
        pin = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(bankName);
        dest.writeString(cardName);
        dest.writeString(cardNumber);
        dest.writeString(expirationDate);
        dest.writeString(securityCode);
        dest.writeString(pin);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CreditCardEntity> CREATOR = new Creator<CreditCardEntity>() {
        @Override
        public CreditCardEntity createFromParcel(Parcel in) {
            return new CreditCardEntity(in);
        }

        @Override
        public CreditCardEntity[] newArray(int size) {
            return new CreditCardEntity[size];
        }
    };

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
