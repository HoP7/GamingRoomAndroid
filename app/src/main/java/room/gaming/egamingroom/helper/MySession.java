package room.gaming.egamingroom.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class MySession {
    private static final String PREFS_NAME = "user_key";
  private  static String user_key = "user_key";
    public  static String getUser(Context context){
       // SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, context.MODE_PRIVATE);
       // String strJson = sharedPreferences.getString(user_key, "");
       // if (strJson.length() == 0)
        //    return  null;

        // User x= MyGson.build().fromJson(strJson, User.class);
        // return x;
    return null;
    }

    public  static void setUser(Context context, String user){
        // String strJson = MyJson.build().toJson(user)
       // SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
       // SharedPreferences.Editor editor = sharedPreferences.edit();
       // editor.putString(user_key, user); // should be strJson
        // editor.apply();
    }
}
