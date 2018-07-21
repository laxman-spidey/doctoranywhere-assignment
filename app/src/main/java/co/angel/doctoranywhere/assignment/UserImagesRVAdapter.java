package co.angel.doctoranywhere.assignment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

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
        if (getSpanSize(position) == 2) {
            holder.setFullImage(mValues.get(position));
        } else {
            holder.setHalfImage(mValues.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    private int getSpanSize(int position) {
        if (mValues.size() % 2 != 0) {
            return position == 0 ? 2 : 1;
        } else {
            return 1;
        }
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

        public void setHalfImage(String imageUrl) {
            int width = context.getResources().getDisplayMetrics().widthPixels;
            image.setLayoutParams(new RelativeLayout.LayoutParams(width / 2, width / 2));
            setImageUrl(imageUrl);
        }

        public void setFullImage(String imageUrl) {
            int width = context.getResources().getDisplayMetrics().widthPixels;
            image.setLayoutParams(new RelativeLayout.LayoutParams(width, width));
            setImageUrl(imageUrl);
        }

        private void setImageUrl(String imageUrl) {
//            image.setImageURI(imageUrl);
            // with fresco  -- 170 MB
            Glide.with(getContext())
                    .load(imageUrl)
                    .apply(new RequestOptions().centerCrop())
                    .into(image);
            //max 140 MB with Glide
        }

    }

    private Context context;

    private Context getContext() {
        return this.context;
    }
}
