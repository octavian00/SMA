package com.example.tema1.pictures;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.example.tema1.R;

import java.util.List;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {
    public List<Uri> uriList;

    public PictureAdapter(List<Uri> uriList) {
        this.uriList = uriList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.row_upload_photos,parent,false);
        return new ViewHolder(v);
    }
    public void removeItemAtPosition(int position){
        uriList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,uriList.size());
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.photoUploadedIv.getContext())
                .load(uriList.get(position)).apply(new RequestOptions().centerCrop())
                .into(holder.photoUploadedIv);
    }


    @Override
    public int getItemCount() {
        return uriList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView closeIv;
        public ImageView photoUploadedIv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            closeIv = itemView.findViewById(R.id.iv_row_upload_close);
            photoUploadedIv = itemView.findViewById(R.id.iv_row_upload_image);
            closeIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        removeItemAtPosition(position);
                    }
                }
            });
        }
    }
}
