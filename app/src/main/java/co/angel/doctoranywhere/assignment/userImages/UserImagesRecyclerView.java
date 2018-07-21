package co.angel.doctoranywhere.assignment.userImages;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

import co.angel.doctoranywhere.assignment.R;
import co.angel.doctoranywhere.assignment.util.ItemOffsetDecoration;

public class UserImagesRecyclerView extends RecyclerView {

    public final static String TAG = UserImagesRecyclerView.class.getSimpleName();

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
    private GridLayoutManager layoutManager;

    private void setUpAdapter() {

        layoutManager = new GridLayoutManager(getContext(), 2);
        setLayoutManager(layoutManager);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.item_offset);
        addItemDecoration(itemDecoration);
        this.setAdapter(adapter);

    }

    public void setItems(List<String> images) {
        items.clear();
        items.addAll(images);
        setSpan(items.size());
        adapter.notifyDataSetChanged();
    }

    private void setSpan(int itemsSize) {
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (itemsSize % 2 != 0) {
                    return position == 0 ? 2 : 1;
                }
                return 1;
            }
        });
    }
}
