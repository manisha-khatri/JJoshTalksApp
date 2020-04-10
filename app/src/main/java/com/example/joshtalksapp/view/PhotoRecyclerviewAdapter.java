package com.example.joshtalksapp.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.joshtalksapp.R;
import com.example.joshtalksapp.model.Photo;
import com.example.joshtalksapp.model.PhotoRecords;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoRecyclerviewAdapter extends RecyclerView.Adapter<PhotoRecyclerviewAdapter.ViewHolder> {
    Context context;
    PhotoRecords photoRecords;
    List<Photo> photoList;

    public PhotoRecyclerviewAdapter(PhotoRecords photoRecords, Context context) {
        this.photoRecords = photoRecords;
        this.context = context;
        this.photoList = photoRecords.getPhotos().getPhoto();
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.render(viewHolder, position);
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder holder;
        int position;
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgView);
            textView = itemView.findViewById(R.id.txtView);
        }

        public void render(ViewHolder viewHolder, int position) {
            this.holder = viewHolder;
            this.position = position;
            setImage();
            setTitle();
        }

        private void setTitle() {
            Photo photo = photoRecords.getPhotos().getPhoto().get(position);
            textView.setText(photo.getTitle());
            textView.setVisibility(View.INVISIBLE);
        }

        private void setImage() {
            Photo photo = photoRecords.getPhotos().getPhoto().get(position);
            String imageUrl = "https://farm"+photo.getFarm()+".staticflickr.com/"+photo.getServer()+"/"
                    +photo.getId()+"_"+photo.getSecret()+".jpg";

           // Picasso.with(context).setLoggingEnabled(true);
            Picasso.with(context)
                    .load(imageUrl)
                    .into(imageView);

            Log.i("url", imageUrl);
        }
    }

}
