package com.prodikl.androidNetUtils.async;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.prodikl.androidNetUtils.JsonCallback;

/**
 * Created by Keith on 12/23/2014.
 */
public class GetJsonTask extends AsyncTask<String, Void, Void> {
    public JsonCallback callback;

    protected Void doInBackground(String... urls){
        try {
            String url = urls[0];
            JSONObject json = new JSONObject(getJsonString(url));
            callback.jsonReady(json);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static String getJsonString(String stringUrl){
        try {
            URL url = new URL(stringUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream content = new BufferedInputStream(httpURLConnection.getInputStream());
            int BUFFER_SIZE = 2000;

            InputStreamReader isr = new InputStreamReader(content);
            int charRead;
            String str = "";
            char[] inputBuffer = new char[BUFFER_SIZE];
            while ((charRead = isr.read(inputBuffer)) > 0){
                // convert chars to string
                String readString = String.copyValueOf(inputBuffer, 0, charRead);
                str += readString;
                inputBuffer = new char[BUFFER_SIZE];
            }
            content.close();
            return str;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
