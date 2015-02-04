package prodikl.com.teachers.util;

import android.content.Context;
import android.util.Log;

import org.apache.http.message.BasicNameValuePair;

import prodikl.com.teachers.util.async.GetJsonTask;
import prodikl.com.teachers.util.async.PostToUrlTask;

/**
 * Created by Keith on 12/9/2014.
 */
public class NetUtil {

    public static void getJsonFromUrl(String url, JsonCallback callback){
        GetJsonTask jsonTask = new GetJsonTask();
        jsonTask.callback = callback;
        jsonTask.execute(url);
    }

    public static void postToUrlAndReturnJson(String url, JsonCallback callback, BasicNameValuePair... inputPairs){
        PostToUrlTask task = new PostToUrlTask();
            task.url = url;
            task.callback = callback;
        task.execute(inputPairs);
    }
}
