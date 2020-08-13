package project.passwordguard.util;

import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;

import project.passwordguard.R;
import project.passwordguard.application.MyApplication;

public class PasswordIconProvider {

    public static Drawable locked = ResourcesCompat.getDrawable(
            MyApplication.applicationContext().getResources(),
            R.drawable.ic_lock,
            null
    );
    public static Drawable unlocked = ResourcesCompat.getDrawable(
            MyApplication.applicationContext().getResources(),
            R.drawable.ic_lock_unlocked,
            null
    );

}
