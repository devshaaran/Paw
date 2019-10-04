package com.disrupt.paws.ui.social;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.disrupt.paws.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import dagger.android.support.DaggerFragment;

public class SocialFragment extends DaggerFragment {

    private SocialViewModel socialViewModel;
    private ArrayAdapter<String> adapter;
    private List<String> values = new ArrayList<>();
    private List<String> pawsUsers = new ArrayList<>();

    private boolean popularClicked = false;
    private boolean nearestClicked = false;
    private boolean ratingClicked = false;
    private boolean distanceClicked = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        socialViewModel =
                ViewModelProviders.of(this).get(SocialViewModel.class);
        View root = inflater.inflate(R.layout.fragment_social, container, false);
        initFiltersListView(root);
        initSearchBar(root);
        initHorizontalButtons(root);
        initEventsListView(root);
        return root;
    }

    private void initHorizontalButtons(View root) {
        final Button popular = root.findViewById(R.id.popular_button);
        popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(popularClicked) {
                    popular.setBackgroundResource(R.drawable.horizontal_menu_button_bg);
                    popular.setTextColor(getContext().getColor(R.color.white));
                    popularClicked = !popularClicked;
                } else {
                    popular.setBackgroundResource(R.drawable.horizontal_menu_button_clicked_bg);
                    popular.setTextColor(getContext().getColor(R.color.black));
                    popularClicked = !popularClicked;
                }
            }
        });
        final Button nearest = root.findViewById(R.id.nearest_button);
        nearest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nearestClicked) {
                    nearest.setBackgroundResource(R.drawable.horizontal_menu_button_bg);
                    nearest.setTextColor(getContext().getColor(R.color.white));
                    nearestClicked = !nearestClicked;
                } else {
                    nearest.setBackgroundResource(R.drawable.horizontal_menu_button_clicked_bg);
                    nearest.setTextColor(getContext().getColor(R.color.black));
                    nearestClicked = !nearestClicked;
                }
            }
        });
        final Button rating = root.findViewById(R.id.rating_button);
        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ratingClicked) {
                    rating.setBackgroundResource(R.drawable.horizontal_menu_button_bg);
                    rating.setTextColor(getContext().getColor(R.color.white));
                    ratingClicked = !ratingClicked;
                } else {
                    rating.setBackgroundResource(R.drawable.horizontal_menu_button_clicked_bg);
                    rating.setTextColor(getContext().getColor(R.color.black));
                    ratingClicked = !ratingClicked;
                }
            }
        });
        final Button distance = root.findViewById(R.id.distance_button);
        distance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(distanceClicked) {
                    distance.setBackgroundResource(R.drawable.horizontal_menu_button_bg);
                    distance.setTextColor(getContext().getColor(R.color.white));
                    distanceClicked = !distanceClicked;
                } else {
                    distance.setBackgroundResource(R.drawable.horizontal_menu_button_clicked_bg);
                    distance.setTextColor(getContext().getColor(R.color.black));
                    distanceClicked = !distanceClicked;
                }
            }
        });
    }

    private void initFiltersListView(View root) {
        ListView listView = root.findViewById(R.id.names_list_view);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);
    }

    private void initSearchBar(View root) {
        initPawsUsers();
        EditText editText = root.findViewById(R.id.editText);
        editText.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    private Timer timer = new Timer();
                    private final long DELAY = 400;

                    @Override
                    public void afterTextChanged(final Editable s) {
                        timer.cancel();
                        timer = new Timer();
                        timer.schedule(
                                new TimerTask() {
                                    @Override
                                    public void run() {
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                refreshList(s.toString());
                                            }
                                        });
                                    }
                                },
                                DELAY
                        );
                    }
                }
        );
    }

    void initEventsListView(View root) {
        ListView listView = root.findViewById(R.id.events_list_view);
        EventsListAdapter adapter = new EventsListAdapter(getContext());
        listView.setAdapter(adapter);
    }

    private void initPawsUsers() {
        pawsUsers = Arrays.asList(getContext().getResources().getStringArray(R.array.social_network));
    }

    private void refreshList(String search) {
        values.clear();
        adapter.notifyDataSetChanged();
        for (String s : pawsUsers) {
            if (s.toLowerCase().startsWith(search.toLowerCase())) {
                values.add(s);
            }
        }
        adapter.notifyDataSetChanged();
    }
}