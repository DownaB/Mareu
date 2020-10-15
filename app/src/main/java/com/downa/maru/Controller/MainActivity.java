package com.downa.maru.Controller;

import android.os.Bundle;

import com.downa.maru.Model.Meeting;
import com.downa.maru.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MeetingAdapter mMeetingAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Meeting> meetingList = new ArrayList<>();

        mRecyclerView = findViewById(R.id.meeting_recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mMeetingAdapter = new MeetingAdapter(meetingList);
        mRecyclerView.setAdapter(mMeetingAdapter);
    }


}
