package co.angel.doctoranywhere.assignment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import co.angel.doctoranywhere.assignment.RESTServices.HerokuService;
import co.angel.doctoranywhere.assignment.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserListFragment extends Fragment {

    public final static String TAG = UserListFragment.class.getSimpleName();


    private List<User> users = new ArrayList<>();
    private UserListRecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    private ProgressBar progressBar;

    public UserListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        Context context = view.getContext();
        recyclerView = view.findViewById(R.id.userList);
        progressBar = view.findViewById(R.id.listLoadingProgressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new UserListRecyclerViewAdapter(users);
        recyclerView.setAdapter(adapter);
        setupScrollToLoad();

        return view;
    }

    public void addUsersToList(List<User> list) {
        users.addAll(list);
        adapter.notifyDataSetChanged();

    }

    public void resetUsersList(List<User> newList) {
        users.clear();
        addUsersToList(newList);
    }

    private boolean isLoading = false;

    private void setupScrollToLoad() {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (!isLoading) {
                    if (dy > 0) //check for scroll down
                    {
                        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                        int visibleItemCount = layoutManager.getChildCount();
                        int totalItemCount = layoutManager.getItemCount();
                        int pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();
                        Log.i(TAG, "onscrolled()" + pastVisiblesItems);
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            isLoading = true;
                            Log.v("...", " Reached Last Item");
                            loadMore();
                        }

                    }
                }
            }
        });
    }

    private void loadMore() {
        progressBar.setVisibility(View.VISIBLE);
        Toast.makeText(getContext(), "Loading more", Toast.LENGTH_SHORT).show();
        HerokuService.getUsers(adapter.getItemCount(), 20, (response) -> {
            if (response.isOkay) {
                Toast.makeText(getContext(), "data received", Toast.LENGTH_SHORT).show();
                addUsersToList((List<User>) response.data);
            } else {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
            Log.i(TAG, response.toString());
            isLoading = false;
            progressBar.setVisibility(View.GONE);
        });
    }
}
