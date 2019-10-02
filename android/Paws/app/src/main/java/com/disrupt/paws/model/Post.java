package com.disrupt.paws.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class Post {

    public static DiffUtil.ItemCallback<Post> DIFF_CALLBACK = new DiffUtil.ItemCallback<Post>() {
        @Override
        public boolean areItemsTheSame(@NonNull Post oldItem, @NonNull Post newItem) {
            //return oldItem.id.equals(newItem.id);
            return true;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Post oldItem, @NonNull Post newItem) {
            //return oldItem.equals(newItem);
            return true;
        }
    };
}
