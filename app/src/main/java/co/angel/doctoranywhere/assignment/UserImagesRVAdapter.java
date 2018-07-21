package co.angel.doctoranywhere.assignment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import co.angel.doctoranywhere.assignment.models.User;

public class UserImagesRVAdapter extends RecyclerView.Adapter<UserImagesRVAdapter.ViewHolder> {
    public final static String TAG = UserImagesRVAdapter.class.getSimpleName();

    private final List<String> mValues;

    public UserImagesRVAdapter(List<String> urls, Context context) {
        mValues = urls;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_image_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.setData(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final SimpleDraweeView image;
        public String mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            image = view.findViewById(R.id.image);
        }
        public void setData(String imageUrl) {
//            image.setImageURI(imageUrl);
            // 170 MB

            Glide.with(getContext())
                    .load(imageUrl)
                    .apply(new RequestOptions().centerCrop())
                    .into(image);

            //max 140 MB with Glide
        }

        public void resizeImage() {
        }
    }

    private Context context;
    private Context getContext() {
        return this.context;
    }
}
