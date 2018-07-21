package co.angel.doctoranywhere.assignment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import co.angel.doctoranywhere.assignment.dummy.DummyContent.DummyItem;
import co.angel.doctoranywhere.assignment.models.User;

import java.util.List;


public class UserListRecyclerViewAdapter extends RecyclerView.Adapter<UserListRecyclerViewAdapter.ViewHolder> {

    private final List<User> mValues;

    public UserListRecyclerViewAdapter(List<User> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.setData(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final SimpleDraweeView avatar;
        public final TextView name;
        public User mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            avatar = view.findViewById(R.id.avatar);
            name = view.findViewById(R.id.name);
        }
        public void setData(User user) {
            avatar.setImageURI(user.image);
            name.setText(user.name);
        }
    }
}
