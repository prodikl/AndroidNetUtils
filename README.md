# AndroidNetUtils
Basic net utilities for 

1. GETing JSON from a url 
2. POSTing POST data to a URL and getting back JSON

# Download

Nab the zip or clone and move the `com.prodikl.androidNetUtils` folder into your project

# Usage

Implement the `JsonCallback` interface in your activity (or object) and create a `jsonReady(JSONObject jsonObject)` callback function.

- GET: Call `NetUtils.getJsonFromUrl()` and pass in your URL and activity that implements `JsonCallback`

  `NetUtils.getJsonFromUrl("http://www.mysite.com/user/info", this);`
  
- POST: Call `NetUtils.postToUrlAndReturnJson()` and pass in your URL, your activity, and an array of `BasicNameValuePair`s

```
  NetUtils.postToUrlAndReturnJson("http://www.mysite.com/user/info", this, 
    new BasicNameValuePair("first_name", "Tyrion"),
    new BasicNameValuePair("last_name", "Lannister"));
```

When the JSON is ready it will call `jsonReady()` in your activity.

# Caveats

This is done asynchronously, so if you're trying to modify your UI (in Android), you'll have to take an extra step to execute your code on the UI thread. 

# Future

I plan to add a Boolean to run on UI thread if necessary.
