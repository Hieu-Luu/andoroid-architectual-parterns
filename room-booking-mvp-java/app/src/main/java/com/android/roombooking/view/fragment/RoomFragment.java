package com.android.roombooking.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.roombooking.R;
import com.android.roombooking.model.Event;
import com.android.roombooking.presenter.EventsCollectionPresenter;
import com.android.roombooking.utils.injector.component.CalendarsComponent;
import com.android.roombooking.view.EventsCollectionView;
import com.android.roombooking.view.activity.RoomsActivity;
import com.android.roombooking.view.adapter.RoomEventsAdapter;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RoomFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RoomFragment extends Fragment implements EventsCollectionView {
    @Inject
    EventsCollectionPresenter eventsCollectionPresenter;
    @Bind(R.id.fc_list)
    RecyclerView eventsList;
    @Bind(R.id.fc_refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.fc_loading)
    View loadingView;
    @Bind(R.id.fc_empty_list)
    View emptyListView;

    private RoomEventsAdapter roomEventsAdapter;
    private static final String KEY_ROOM_ID = "key_room_id";
    private int roomId = -1;

    public RoomFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param roomId Room ID.
     * @return A new instance of fragment RoomFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RoomFragment newInstance(int roomId) {
        RoomFragment roomFragment = new RoomFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_ROOM_ID, roomId);
        roomFragment.setArguments(args);
        return roomFragment;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(KEY_ROOM_ID, roomId);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        eventsCollectionPresenter.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);
        ButterKnife.bind(this, root);
        initAdapter();
        initList();
        if (savedInstanceState != null) {
            roomId = savedInstanceState.getInt(KEY_ROOM_ID);
        } else {
            roomId = getArguments().getInt(KEY_ROOM_ID);
        }
        initEvents();
        setupRefreshLayout();
        return root;
    }

    private void setupRefreshLayout() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                eventsCollectionPresenter.onRefresh();
            }
        });
    }

    private void injectDependencies() {
        if (getActivity() instanceof RoomsActivity) {
            RoomsActivity activity = (RoomsActivity) getActivity();
            CalendarsComponent registrationComponent = activity.getCalendarsComponent();
            registrationComponent.inject(this);
        }
    }

    private void initList() {
        eventsList.setLayoutManager(new LinearLayoutManager(getContext()));
        eventsList.setAdapter(roomEventsAdapter);
    }

    private void initEvents() {
        eventsCollectionPresenter.attachView(this);
        eventsCollectionPresenter.setCalendarId(roomId);
    }

    private void initAdapter() {
        if (roomEventsAdapter == null) {
            roomEventsAdapter = new RoomEventsAdapter(getActivity());
        }
    }

    public void addEvents(Collection<Event> eventCollection) {
        roomEventsAdapter.replaceEvents(eventCollection);
        Objects.requireNonNull(eventsList.getAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        eventsCollectionPresenter.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        eventsCollectionPresenter.onStop();
    }

    @Override
    public void showLoading() {
        Runnable mRunnable = new Runnable() {
            @Override
            public void run() {
                emptyListView.setVisibility(View.GONE);
                loadingView.setVisibility(View.VISIBLE);
                refreshLayout.setRefreshing(true);
                eventsList.setVisibility(View.INVISIBLE);
            }
        };
        Objects.requireNonNull(getActivity()).runOnUiThread(mRunnable);
    }

    @Override
    public void showEvents(final List<Event> events) {
        Runnable mRunnable = new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
                loadingView.setVisibility(View.GONE);
                eventsList.setVisibility(View.VISIBLE);
                addEvents(events);
            }
        };
        Objects.requireNonNull(getActivity()).runOnUiThread(mRunnable);
    }

    @Override
    public void showError() {
        showEmpty();
    }

    @Override
    public void showEmpty() {
        Runnable mRunnable = new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
                loadingView.setVisibility(View.GONE);
                eventsList.setVisibility(View.GONE);
                emptyListView.setVisibility(View.VISIBLE);
            }
        };
        Objects.requireNonNull(getActivity()).runOnUiThread(mRunnable);
    }
}
