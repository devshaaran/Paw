package com.disrupt.paws.ui.feed.pagination;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item_view, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Post post = getItem(position);
        if (post != null) {
            holder.titleText.setText(post.getTitle());
            holder.detailsText.setText(post.getDescription());
            holder.viewsCountText.setText(String.valueOf(post.getWatchersCount()));
            holder.likesCountText.setText(String.valueOf(post.getLikesCount()));
            holder.commentsCountText.setText(String.valueOf(post.getComments().size()));
            holder.dateText.setText(String.valueOf(post.getCreatedDate()));

            requestManager.load(post.getImagePath()).into(holder.postImage);
            requestManager.load(post.getAuthorImage()).into(holder.authorImage);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleText;
        private final TextView detailsText;
        private final TextView viewsCountText;
        private final TextView likesCountText;
        private final TextView commentsCountText;
        private final TextView dateText;

        private final ImageView postImage;
        private final ImageView authorImage ;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.titleTextView);
            detailsText = itemView.findViewById(R.id.detailsTextView);
            postImage = itemView.findViewById(R.id.postImageView);
            authorImage = itemView.findViewById(R.id.authorImageView);
            viewsCountText = itemView.findViewById(R.id.watcherCounterTextView);
            likesCountText = itemView.findViewById(R.id.likeCounterTextView);
            commentsCountText = itemView.findViewById(R.id.commentsCountTextView);
            dateText = itemView.findViewById(R.id.dateTextView);
        }
    }
}
