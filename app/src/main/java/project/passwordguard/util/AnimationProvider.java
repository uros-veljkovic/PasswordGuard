package project.passwordguard.util;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import project.passwordguard.R;
import project.passwordguard.application.MyApplication;

public class AnimationProvider {

    public static Animation animRotateOpen = AnimationUtils.loadAnimation(MyApplication.applicationContext(), R.anim.rotate_open_anim);
    public static Animation animRotateClose = AnimationUtils.loadAnimation(MyApplication.applicationContext(), R.anim.rotate_close_anim);
    public static Animation animFromBottom = AnimationUtils.loadAnimation(MyApplication.applicationContext(), R.anim.from_bottom_anim);
    public static Animation animToBottom = AnimationUtils.loadAnimation(MyApplication.applicationContext(), R.anim.to_bottom_anim);

}
