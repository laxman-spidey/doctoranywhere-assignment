package co.angel.doctoranywhere.assignment.usersList;

import java.util.List;

import co.angel.doctoranywhere.assignment.BasePresenter;
import co.angel.doctoranywhere.assignment.BaseView;
import co.angel.doctoranywhere.assignment.models.User;

public interface UsersListContract {
    interface View extends BaseView<Presenter> {

        void showProgress();
        void showError();
        void showNoDataFound();
        void showUserList(List<User> userList);
        void moreItemsLoadingStarted();
        void moreItemsLoaded(List<User> userList);
        void noMoreItemsToLoad();
    }

    interface Presenter extends BasePresenter {
        void loadUserList();
        void loadMoreUsers(int offset);
    }
}
