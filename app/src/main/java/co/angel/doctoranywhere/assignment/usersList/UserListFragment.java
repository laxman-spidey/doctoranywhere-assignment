package co.angel.doctoranywhere.assignment.usersList;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.angel.doctoranywhere.assignment.BaseFragment;
import co.angel.doctoranywhere.assignment.R;
import co.angel.doctoranywhere.assignment.dataRepository.UserListRepo;
import co.angel.doctoranywhere.assignment.models.User;

public class UserListFragment extends BaseFragment implements UsersListContract.View {

    public final static String TAG = UserListFragment.class.getSimpleName();

    private UsersListContract.Presenter mPresenter;
    private List<User> users = new ArrayList<>();
    private UserListRecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    private ProgressBar progressBar;

    public UserListFragment() {
        mPresenter = new UserListPresenter(UserListRepo.getInstance(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        setSuccessView(view);
        setInitViewText("Search for your Favorite Songs");
        Context context = view.getContext();
        recyclerView = view.findViewById(R.id.userList);
        progressBar = view.findViewById(R.id.listLoadingProgressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new UserListRecyclerViewAdapter(users, getContext());
        recyclerView.setAdapter(adapter);
        setupScrollToLoad();
        mPresenter.loadUserList();
        return rootView;
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

    @Override
    public void showProgress() {
        inProgress();
    }

    @Override
    public void showError() {
        onError();
    }

    @Override
    public void showNoDataFound() {
        showNoDataFound();
    }

    @Override
    public void showUserList(List<User> userList) {
        onSuccess();
        users.addAll(userList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void moreItemsLoadingStarted() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void moreItemsLoaded(List<User> userList) {
        isLoading = false;
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), "more users loaded", Toast.LENGTH_SHORT).show();
        users.addAll(userList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void noMoreItemsToLoad() {
        isLoading = false;
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), "No more Items to load", Toast.LENGTH_SHORT).show();
    }


    private void loadMore() {
        mPresenter.loadMoreUsers(adapter.getItemCount());
    }

    @Override
    public void setPresenter(UsersListContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
