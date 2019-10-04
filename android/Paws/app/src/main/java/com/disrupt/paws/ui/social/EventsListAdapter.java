package com.disrupt.paws.ui.social;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.disrupt.paws.R;

import java.util.ArrayList;
import java.util.List;

public class EventsListAdapter extends BaseAdapter {

    private List<Integer> images;
    private Context context;

    public EventsListAdapter(Context context) {
        this.context = context;
        images = new ArrayList<>();
        images.add(R.drawable.event1);
        images.add(R.drawable.event2);
        images.add(R.drawable.event3);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int i) {
        return images.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ListViewViewHolder holder;
        if (convertView == null) {
            holder = new ListViewViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.event_list_item, null, true);
            holder.iv = convertView.findViewById(R.id.event_list_image);

            convertView.setTag(holder);
        } else {
            holder = (ListViewViewHolder) convertView.getTag();
        }
        holder.iv.setImageResource(images.get(i));

        return convertView;
    }

    private class ListViewViewHolder {
        private ImageView iv;
    }
}
