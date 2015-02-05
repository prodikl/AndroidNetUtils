package com.prodikl.androidNetUtils;

import android.content.Context;
import android.util.Log;

import org.apache.http.message.BasicNameValuePair;

import com.prodikl.androidNetUtils.async.GetJsonTask;
import com.prodikl.androidNetUtils.async.PostToUrlTask;

/**
 * Created by Keith on 12/9/2014.
 */
public class NetUtils {

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
