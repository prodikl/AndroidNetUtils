package prodikl.com.teachers.util.async;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import prodikl.com.teachers.util.JsonCallback;

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

    private static String getJsonString(String url){
        int BUFFER_SIZE = 2000;
        InputStream content = null;
        try {
            content  = OpenHttpGETConnection(url);
        } catch (Exception e){
            Log.d("DownloadImages", e.getLocalizedMessage());
            return "";
        }

        InputStreamReader isr = new InputStreamReader(content);
        int charRead;
        String str = "";
        char[] inputBuffer = new char[BUFFER_SIZE];
        try {
            while ((charRead = isr.read(inputBuffer)) > 0){
                // convert chars to string
                String readString = String.copyValueOf(inputBuffer, 0, charRead);
                str += readString;
                inputBuffer = new char[BUFFER_SIZE];
            }
            content.close();
        } catch (IOException e){
            Log.d("DownloadImages", e.getLocalizedMessage());
            return "";
        }
        return str;
    }
    private static InputStream OpenHttpGETConnection(String url){
        InputStream inputStream = null;
        HttpClient httpClient = null;
        HttpResponse httpResponse = null;


        try {
            httpClient = new DefaultHttpClient();
        } catch (Exception e){ Log.d("HttpClient", e.getMessage());}

        try {
            httpResponse = httpClient.execute(new HttpGet(url));
        } catch (Exception e){Log.d("HttpResponse", e.getMessage());}

        try {
            inputStream = httpResponse.getEntity().getContent();
        } catch (Exception e){
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return inputStream;
    }
}
