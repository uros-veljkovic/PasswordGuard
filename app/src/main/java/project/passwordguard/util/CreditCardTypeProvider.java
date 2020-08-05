package project.passwordguard.util;

import android.content.Context;
import android.widget.ArrayAdapter;

import static java.util.Arrays.sort;

public class CreditCardTypeProvider {

    static ArrayAdapter<String> creditCardTypeAdapter;
    static String[] creditCards = new String[]{
            "MasterCard",
            "VISA"
    };

    static {
        sort(creditCards);
    }

    public String[] getCreditCardTypeAdapter() {
        return creditCards;
    }

    public static ArrayAdapter<String> getWebsiteAdapter(Context context) {
        creditCardTypeAdapter = new ArrayAdapter<>(context, android.R.layout.select_dialog_item, creditCards);
        return creditCardTypeAdapter;
    }
}
