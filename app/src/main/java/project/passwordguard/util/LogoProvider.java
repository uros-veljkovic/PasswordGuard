package project.passwordguard.util;

import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;

import java.lang.reflect.Field;

import project.passwordguard.R;
import project.passwordguard.application.MyApplication;
import project.passwordguard.model.CredentialsEntity;
import project.passwordguard.model.CreditCardEntity;

public class LogoProvider {

    static Drawable defaultCreditCardLogo = ResourcesCompat.getDrawable(MyApplication.applicationContext().getResources(),
            R.drawable.ic_credit_card,
            null);
    static Drawable defaultCredentialsLogo = ResourcesCompat.getDrawable(MyApplication.applicationContext().getResources(),
            R.drawable.ic_password,
            null);
    static Drawable drawable = null;
    static Field[] drawableFileds = R.mipmap.class.getFields();

    public static Drawable getLogoResourceFor(CredentialsEntity credentialsEntity) {
        drawable = null; // Reset drawable
        filterDrawables(credentialsEntity.getWebsiteUrl());

        return (drawable == null) ? defaultCredentialsLogo : drawable;
    }

    public static Drawable getLogoResourceFor(CreditCardEntity creditCardEntity) {
        drawable = null; // Reset drawable
        filterDrawables(creditCardEntity.getCardName()); // Master card | VISA | default...

        return (drawable == null) ? defaultCreditCardLogo : drawable;
    }


    private static void filterDrawables(String demandedString) {
        for (Field field : drawableFileds) {
            if (field.getName().contains(demandedString.toLowerCase())) {
                try {
                    drawable = MyApplication.applicationContext().getDrawable(field.getInt(null));
                    return;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
