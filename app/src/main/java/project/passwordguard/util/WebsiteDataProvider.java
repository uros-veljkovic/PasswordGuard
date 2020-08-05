package project.passwordguard.util;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Collections;

import static java.util.Arrays.sort;

public class WebsiteDataProvider {

    static ArrayAdapter<String> websiteAdapter;
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

    static {
        sort(websites);
    }

    public String[] getWebsites() {
        return websites;
    }

    public static ArrayAdapter<String> getWebsiteAdapter(Context context) {
        websiteAdapter = new ArrayAdapter<>(context, android.R.layout.select_dialog_item, websites);
        return websiteAdapter;
    }
}
