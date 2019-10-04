package com.disrupt.paws.ui.social;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        socialViewModel =
                ViewModelProviders.of(this).get(SocialViewModel.class);
        View root = inflater.inflate(R.layout.fragment_social, container, false);
        initFiltersListView(root);
        initSearchBar(root);
        initEventsListView(root);
        return root;
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