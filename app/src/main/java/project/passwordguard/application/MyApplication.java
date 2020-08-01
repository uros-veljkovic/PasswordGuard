package project.passwordguard.application;

import android.app.Application;
import android.content.Context;

public class MyApplication extends android.app.Application {

    private static MyApplication application = null;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Context applicationContext() {
        return application.getApplicationContext();
    }
}
