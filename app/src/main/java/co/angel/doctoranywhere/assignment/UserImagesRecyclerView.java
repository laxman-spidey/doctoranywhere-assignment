package co.angel.doctoranywhere.assignment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

public class UserImagesRecyclerView extends RecyclerView{

    public UserImagesRecyclerView(@NonNull Context context) {
        super(context);
        setUpAdapter();
    }

    public UserImagesRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setUpAdapter();
    }

    public UserImagesRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setUpAdapter();
    }

    private List<String> items = new ArrayList<>();
    private UserImagesRVAdapter adapter = new UserImagesRVAdapter(items, getContext());
    private void setUpAdapter() {
        this.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
//        setLayoutManager(new LinearLayoutManager(getContext()));
        this.setAdapter(adapter);
    }

    public void setItems(List<String> images) {
        items.clear();
        items.addAll(images);
        adapter.notifyDataSetChanged();
    }
}
