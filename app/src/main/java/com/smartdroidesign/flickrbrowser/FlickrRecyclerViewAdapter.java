package com.smartdroidesign.flickrbrowser;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

class FlickrRecyclerViewAdapter extends RecyclerView.Adapter<FlickrRecyclerViewAdapter.FlickrImageViewHolder> {
    private static final String TAG = "FlickrRecyclerViewAdapt";
    private List<Photo> mPhotosList;
    private Context mContext;

    public FlickrRecyclerViewAdapter(List<Photo> photosList, Context context) {
        mPhotosList = photosList;
        mContext = context;
    }

    // Inflates and return the view
    @NonNull
    @Override
    public FlickrImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Called by the layout manager when it needs a view
        Log.d(TAG, "onCreateViewHolder: new view requested");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.browse, viewGroup, false);
        return new FlickrImageViewHolder(view);
    }

    // Called by the recycler view when it wants new data to be stored in a viewHolder
    // so it can display it in an existing row.
    @Override
    public void onBindViewHolder(@NonNull FlickrImageViewHolder holder, int position) {
        Photo photoItem = mPhotosList.get(position);
        Log.d(TAG, "onBindViewHolder: " + photoItem.getTitle() + " --> " + position);
        Picasso.get().load(photoItem.getImage())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.thumbnail);

        holder.title.setText(photoItem.getTitle());
        Log.d(TAG, "onBindViewHolder: " + photoItem.getTitle());
    }


    @Override
    public int getItemCount() {
//        Log.d(TAG, "getItemCount: called");
        return ((mPhotosList != null) && (mPhotosList.size() != 0) ? mPhotosList.size() : 0); // Ternary operator
    }

    void loadNewData(List<Photo> newPhotos) {
        mPhotosList = newPhotos;
        notifyDataSetChanged(); // -> Tells the recycler view the data has changed
    }

    // Display the picture in big size when thumbnail is tapped
    public Photo getPhoto(int position) {
        return ((mPhotosList != null) && (mPhotosList.size() != 0) ? mPhotosList.get(position) : null);
    }


    static class FlickrImageViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "FlickrImageViewHolder";
        ImageView thumbnail = null;
        TextView title = null;

        public FlickrImageViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "FlickrImageViewHolder: starts");
            this.thumbnail = itemView.findViewById(R.id.thumbnail);
            this.title = itemView.findViewById(R.id.title);
        }
    }

}
