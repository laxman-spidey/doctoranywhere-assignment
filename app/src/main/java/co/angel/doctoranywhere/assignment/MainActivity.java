package co.angel.doctoranywhere.assignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import co.angel.doctoranywhere.assignment.RESTServices.HerokuService;
import co.angel.doctoranywhere.assignment.RESTServices.HerokuVolleyService;
import co.angel.doctoranywhere.assignment.RESTServices.ResponseListener;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HerokuService.getUsers(10,10, (response) -> {
            Log.i(TAG, response.toString());
        });

    }
}
