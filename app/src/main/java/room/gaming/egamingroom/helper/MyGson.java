package room.gaming.egamingroom.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class MyGson {
    public  static Gson build() {return builder().create(); }

    public  static GsonBuilder builder() {
        GsonBuilder builder = new GsonBuilder();

            builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return  builder;
    }
}
