package room.gaming.egamingroom.helper;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

public class MyApp extends Application {
    private static Activity currentActivity = null;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getAppContext() {
        return context;
    }
    public static  Activity getCurrentActivity() {
        return  currentActivity;
    }
    public static   void setCurrentActivity(Activity activity) {
        currentActivity = activity;
    }
}