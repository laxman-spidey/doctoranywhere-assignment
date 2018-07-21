package co.angel.doctoranywhere.assignment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.drawee.view.SimpleDraweeView;

import co.angel.doctoranywhere.assignment.models.User;

import java.util.List;


public class UserListRecyclerViewAdapter extends RecyclerView.Adapter<UserListRecyclerViewAdapter.ViewHolder> {

    private final List<User> mValues;

    public UserListRecyclerViewAdapter(List<User> items, Context context) {
        mValues = items;
        this.context = context;
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
        public final UserImagesRecyclerView imagesView;
        public User mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            avatar = view.findViewById(R.id.avatar);
            name = view.findViewById(R.id.name);
            imagesView = view.findViewById(R.id.images_list_view);
        }
        public void setData(User user) {
//            Glide.with(getContext())
//                    .load(user.image)
//                    .apply(new RequestOptions().centerCrop())
//                    .into(avatar);
            avatar.setImageURI(user.image);
            name.setText(user.name);
            imagesView.setItems(user.items);

        }
    }
    private Context context;

    private Context getContext() {
        return this.context;
    }
}
