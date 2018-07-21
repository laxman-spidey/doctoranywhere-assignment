package co.angel.doctoranywhere.assignment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.angel.doctoranywhere.assignment.dummy.DummyContent;
import co.angel.doctoranywhere.assignment.dummy.DummyContent.DummyItem;
import co.angel.doctoranywhere.assignment.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserListFragment extends Fragment {


    private List<User> users = new ArrayList<>();
    private UserListRecyclerViewAdapter adapter;
    public UserListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
//            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
//            } else {
//                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
//            }
            adapter = new UserListRecyclerViewAdapter(users);
            recyclerView.setAdapter(adapter);
        }
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
}
