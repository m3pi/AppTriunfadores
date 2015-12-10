package utils;

import android.util.Base64;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mompi3p on 10/12/2015.
 */
public class SolicitudPrestamoUtils {

    public static final String TAG = "SolicitudPrestamoUtils";

    public static String getTriunfadoresForSearchTerm(String searchTerm){

        HttpURLConnection httpConnection = null;
        BufferedReader bufferedReader = null;
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(ConstantsUtils.URL_ROOT_TRIUNFADORES_API+searchTerm);
            httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("GET");

            //String jsonString = appAuthentication();
            //JSONObject jsonObjectDocument = new JSONObject(jsonString);
            //String token = jsonObjectDocument.getString("token_type") + " " +
            //        jsonObjectDocument.getString("access_token");

            //httpConnection.setRequestProperty("Authorization", token);
            httpConnection.setRequestProperty("Content-Type", "application/json");
            httpConnection.connect();

            bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));

            String line;
            while ((line = bufferedReader.readLine()) != null){
                response.append(line);
            }

            Log.d(TAG, "GET response code: " + String.valueOf(httpConnection.getResponseCode()));
            Log.d(TAG, "JSON response: " + response.toString());

            return response.toString();

        } catch (Exception e) {
            Log.e(TAG, "GET error: " + Log.getStackTraceString(e));
            return null;

        }finally {
            if(httpConnection != null){
                httpConnection.disconnect();
            }
        }
    }

}
