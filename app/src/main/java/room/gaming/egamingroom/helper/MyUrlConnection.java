package room.gaming.egamingroom.helper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

public class MyUrlConnection {
    private static final String TAG = "MyUrlConnection";

    public  static String Get(String urlString) {
        HttpURLConnection urlConnection = null;

        String result = null;

        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(false);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

              int  statusCode = urlConnection.getResponseCode();

            if (statusCode == 200) {
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

                String response = convertToString(inputStream);
                return  response;
            } else {

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
           if (urlConnection != null) {
               urlConnection.disconnect();
           }
        }
        return result;
    }

    private static String convertToString(InputStream in) {
        BufferedReader reader = null;
        StringBuilder response = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try{
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
}
