package co.angel.doctoranywhere.assignment.dataRepository.RESTServices;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.angel.doctoranywhere.assignment.dataRepository.Constants;
import co.angel.doctoranywhere.assignment.dataRepository.ResponseListener;
import co.angel.doctoranywhere.assignment.dataRepository.UserListRepo;
import co.angel.doctoranywhere.assignment.models.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public class UserListRetrofitRepo extends UserListRepo{

    public final static String TAG = UserListRetrofitRepo.class.getSimpleName();

    public void getUsers(int offset, int limit, final ResponseListener listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API.DOMAIN)
                .build();
        HerokuRetrofitService service = retrofit.create(HerokuRetrofitService.class);

        //create GET parameters
        Map<String, String> data = new HashMap<>();
        data.put(Constants.PARAMS.OFFSET, String.valueOf(offset));
        data.put(Constants.PARAMS.LIMIT, String.valueOf(limit));

        //create get request
        Call<ResponseBody> users = service.getUsers(data);
        Log.i("Request", users.request().toString());

        users.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        List<User> users = new ArrayList<>();
                        String responseString = response.body().string().trim();
                        Log.i(TAG, responseString);
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
                } else {
                    listener.onResponseRecieved(new ResponseListener.Response(true, null));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.onResponseRecieved(new ResponseListener.Response(false, t.getMessage()));
                t.printStackTrace();

            }
        });
    }


    public interface HerokuRetrofitService {
        @GET(Constants.API.USERS)
        Call<ResponseBody> getUsers(@QueryMap Map<String, String> options);
    }
}
