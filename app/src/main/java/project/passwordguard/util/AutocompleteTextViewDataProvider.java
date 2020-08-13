package project.passwordguard.util;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Collections;

import static java.util.Arrays.sort;

public class AutocompleteTextViewDataProvider {

    static ArrayAdapter<String> websiteAdapter;
    static ArrayAdapter<String> creditCardAdapter;

    static String[] websites = new String[]{
            "Facebook",
            "Youtube",
            "Google",
            "LinkedIn",
            "Skype",
            "Vk",
            "Snapchat",
            "Instagram",
            "Discord"
    };
    static String[] creditCards = new String[]{
            "MasterCard",
            "VISA"
    };


    static {
        sort(websites);
        sort(creditCards);
    }

    public String[] getWebsites() {
        return websites;
    }
    public String[] getCreditCards() {
        return creditCards;
    }

    public static ArrayAdapter<String> getWebsiteAdapter(Context context) {
        websiteAdapter = new ArrayAdapter<>(context, android.R.layout.select_dialog_item, websites);
        return websiteAdapter;
    }
    public static ArrayAdapter<String> getCreditCardAdapter(Context context) {
        creditCardAdapter = new ArrayAdapter<>(context, android.R.layout.select_dialog_item, creditCards);
        return creditCardAdapter;
    }
}
