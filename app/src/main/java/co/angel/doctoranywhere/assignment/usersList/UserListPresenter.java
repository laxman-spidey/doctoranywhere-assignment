package co.angel.doctoranywhere.assignment.usersList;

import android.support.annotation.NonNull;

import java.util.List;

import co.angel.doctoranywhere.assignment.dataRepository.Constants;
import co.angel.doctoranywhere.assignment.dataRepository.UserListRepo;
import co.angel.doctoranywhere.assignment.models.User;

public class UserListPresenter implements UsersListContract.Presenter {

    public final static String TAG = UserListPresenter.class.getSimpleName();

    private final UserListRepo mUserListRepo;

    private final UsersListContract.View mUsersListView;


    public UserListPresenter(@NonNull UserListRepo mUserListRepo, @NonNull UsersListContract.View mUsersListView) {
        this.mUserListRepo = mUserListRepo;
        this.mUsersListView = mUsersListView;
        mUsersListView.setPresenter(this);
    }

    @Override
    public void loadUserList() {
        mUsersListView.showProgress();
        mUserListRepo.getUsers(Constants.ZERO, Constants.LIMIT, (response) -> {
            if (response.isOkay) {
                List<User> users = (List<User>) response.data;
                if (users != null && users.size() > 0) {
                    mUsersListView.showUserList(users);
                }
                else {
                    mUsersListView.showNoDataFound();
                }
            } else {
                mUsersListView.showError();
            }
        });
    }

    @Override
    public void loadMoreUsers(int offset) {
        mUsersListView.moreItemsLoadingStarted();
        mUserListRepo.getUsers(offset, Constants.LIMIT, (response) -> {
            if (response.isOkay) {
                List<User> users = (List<User>) response.data;
                if (users != null && users.size() > 0) {
                    mUsersListView.moreItemsLoaded((List<User>) response.data);
                }
                else {
                    mUsersListView.noMoreItemsToLoad();
                }
            } else {
                mUsersListView.noMoreItemsToLoad();
            }
        });
    }

    @Override
    public void start() {

    }
}
