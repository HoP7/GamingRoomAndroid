package room.gaming.egamingroom.helper;

import android.content.Context;
import android.content.SharedPreferences;

import room.gaming.egamingroom.models.User;

public class MySession {
    private static final String PREFS_NAME = "user_key";
  private  static String user_key = "user_key";
    public  static User getUser(){

        SharedPreferences sharedPreferences = MyApp.getAppContext().getSharedPreferences(PREFS_NAME, MyApp.getAppContext().MODE_PRIVATE);
        String strJson = sharedPreferences.getString(user_key, "");
        if (strJson.length() == 0)
           return  null;

        User x= MyGson.build().fromJson(strJson, User.class);
         return x;
    }

    public  static void setUser( User user){
         String strJson = MyGson.build().toJson(user);
        SharedPreferences sharedPreferences = MyApp.getAppContext().getSharedPreferences(PREFS_NAME, MyApp.getAppContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(user_key, strJson); // should be strJson
        editor.apply();
    }
}
