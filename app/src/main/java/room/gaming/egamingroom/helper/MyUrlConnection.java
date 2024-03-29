package room.gaming.egamingroom.helper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

import room.gaming.egamingroom.models.MyApiResult;


public class MyUrlConnection {
    public  enum HttpMethod{
        GET,POST,HEAD,OPTIONS,PUT,DELETE,TRACE
    }
    private static final String TAG = "MyUrlConnection";

    public  static MyApiResult request(String urlString, HttpMethod httpMethod, String postData, String contentType) {
        HttpURLConnection urlConnection = null;

        String charset = "UTF-8";

        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(false);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept",  contentType);
            urlConnection.setRequestProperty("Accept-Charset", charset);
            urlConnection.setRequestProperty("authtoken", MySession.getUser() != null ? MySession.getUser().token : "" );
            urlConnection.setRequestMethod(httpMethod.toString());
            urlConnection.setUseCaches(false);
            urlConnection.setAllowUserInteraction(false);


              if (postData != null) {
                  urlConnection.setDoOutput(true);
                  byte[] outputBytes = postData.getBytes(charset);
                  OutputStream os = urlConnection.getOutputStream();
                  os.write(outputBytes);
                  os.flush();
                  os.close();
              }
            int  statusCode = urlConnection.getResponseCode();

            if (statusCode == 200) {
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

                String response = convertToString(inputStream);
                return MyApiResult.OK(response);
            } else {
      InputStream inputStream = new BufferedInputStream(urlConnection.getErrorStream());
      String response = convertToString(inputStream);

      return  MyApiResult.Error(statusCode, response);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return  MyApiResult.Error(0, e.getMessage());
        }
        finally {
           if (urlConnection != null) {
               urlConnection.disconnect();
           }
        }
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
