package co.angel.doctoranywhere.assignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

import co.angel.doctoranywhere.assignment.RESTServices.HerokuService;
import co.angel.doctoranywhere.assignment.RESTServices.HerokuVolleyService;
import co.angel.doctoranywhere.assignment.RESTServices.ResponseListener;
import co.angel.doctoranywhere.assignment.models.User;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);
        UserListFragment fragment = (UserListFragment) getSupportFragmentManager().findFragmentById(R.id.favoritesListFragment);
        HerokuService.getUsers(0,50, (response) -> {
            if (response.isOkay) {
                Toast.makeText(this, "data received", Toast.LENGTH_SHORT).show();
                List<User> users = (List<User>) response.data;
                fragment.addUsersToList(users);
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
            Log.i(TAG, response.toString());
        });

    }
}
