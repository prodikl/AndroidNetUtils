package prodikl.com.teachers.util.async;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import prodikl.com.teachers.util.JsonCallback;

/**
 * Created by Keith on 12/9/2014.
 */
public class PostToUrlTask extends AsyncTask<BasicNameValuePair, Void, Void> {

    public String url;
    public JsonCallback callback;

    protected Void doInBackground(BasicNameValuePair... inputNameValuePairs){
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(inputNameValuePairs.length);
            for(BasicNameValuePair pair : inputNameValuePairs){
                nameValuePairs.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            String resultString = EntityUtils.toString(entity);
            callback.jsonReady(new JSONObject(resultString));
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }
}