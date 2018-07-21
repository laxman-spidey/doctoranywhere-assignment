package co.angel.doctoranywhere.assignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

import co.angel.doctoranywhere.assignment.dataRepository.UserListRepo;
import co.angel.doctoranywhere.assignment.models.User;
import co.angel.doctoranywhere.assignment.usersList.UserListFragment;
import co.angel.doctoranywhere.assignment.usersList.UserListPresenter;
import co.angel.doctoranywhere.assignment.usersList.UsersListContract;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);
//        UserListFragment fragment = (UserListFragment) getSupportFragmentManager().findFragmentById(R.id.favoritesListFragment);
    }
}
