package com.disrupt.paws.ui.feed.pagination;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.disrupt.paws.R;
import com.disrupt.paws.model.Post;

import static com.disrupt.paws.model.Post.DIFF_CALLBACK;

public class ItemAdapter extends PagedListAdapter<Post, ItemAdapter.ItemViewHolder> {

    private final RequestManager requestManager;

    public ItemAdapter(RequestManager requestManager) {
        super(DIFF_CALLBACK);
        this.requestManager = requestManager;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Post photo = getItem(position);
        if (photo != null) {
            //requestManager.load(photo.getUrl_s()).into(holder.image);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
