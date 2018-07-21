package co.angel.doctoranywhere.assignment.RESTServices;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.angel.doctoranywhere.assignment.models.User;

public class HerokuVolleyService  extends VolleyService {
    private static HerokuVolleyService singleton = null;


    public void getUsers(int offset, int limit, final ResponseListener listener) {
        Map<String, String> data = new HashMap<>();
        data.put(Constants.PARAMS.OFFSET, String.valueOf(offset));
        data.put(Constants.PARAMS.LIMIT, String.valueOf(limit));

        String url = Constants.API.DOMAIN + Constants.API.USERS + "?" + urlEncodeUTF8(data);
        StringRequest jsonObjectRequest = new StringRequest
                (Request.Method.GET, url, response -> {
                    try {
                        List<User> users = new ArrayList<>();
                        String responseString = response.trim();
                        JSONObject object = new JSONObject(responseString);

                        //Extract users array from the response
                        JSONArray array = object.getJSONObject("data").getJSONArray("users");
                        for (int i = 0; i < array.length(); i++) {
                            User user = new Gson().fromJson(array.getString(i), User.class);
                            users.add(user);
                        }
                        listener.onResponseRecieved(new ResponseListener.Response(true, users));
                    } catch (Exception e) {
                        listener.onResponseRecieved(new ResponseListener.Response(true, null));
                        e.printStackTrace();
                    }
                }, error ->
                {
                    listener.onResponseRecieved(new ResponseListener.Response(false, error.getMessage()));
                    error.printStackTrace();
                });

        Log.i(TAG, jsonObjectRequest.toString());
        cancelPreviousAndAddRequestToQueue(getContext(), jsonObjectRequest, SEARCH);
    }


    public static HerokuVolleyService getInstance(Context context) {
        if (singleton == null) {
            singleton = new HerokuVolleyService(context);
        }
        return singleton;
    }

    public static String TAG = HerokuVolleyService.class.getSimpleName();

    public HerokuVolleyService(Context context) {
        setContext(context);
    }
}
