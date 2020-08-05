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

@Entity(tableName = "credentials")
public class CredentialsEntity extends BaseObservable implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "website_url")
    private String websiteUrl;
    @ColumnInfo(name = "username")
    private String username;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "password")
    private String password;

    public CredentialsEntity(String websiteUrl, String username, String email, String password) {
        this.websiteUrl = websiteUrl;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Ignore
    public CredentialsEntity() {
    }

    // ===== PARCELABLE IMPLEMENTATION =====
    protected CredentialsEntity(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        websiteUrl = in.readString();
        username = in.readString();
        email = in.readString();
        password = in.readString();
    }

    public static final Creator<CredentialsEntity> CREATOR = new Creator<CredentialsEntity>() {
        @Override
        public CredentialsEntity createFromParcel(Parcel in) {
            return new CredentialsEntity(in);
        }

        @Override
        public CredentialsEntity[] newArray(int size) {
            return new CredentialsEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(websiteUrl);
        dest.writeString(username);
        dest.writeString(email);
        dest.writeString(password);
    }

    // ======================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Bindable
    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String url) {
        this.websiteUrl = url;
        notifyPropertyChanged(BR.websiteUrl);
    }

    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

}
