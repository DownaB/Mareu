package com.downa.maru.Controller;

import android.os.Bundle;
import android.view.View;

import com.downa.maru.Model.Meeting;
import com.downa.maru.R;
import com.downa.maru.Service.ApiService;
import com.downa.maru.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/*
public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MeetingAdapter mMeetingAdapter;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        List<Meeting> meetingList = new ArrayList<>();

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mMeetingAdapter = new MeetingAdapter(meetingList);
        binding.meetingRecyclerview.setAdapter(mMeetingAdapter);
    }


}
*/

