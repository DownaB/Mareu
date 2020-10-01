package com.downa.maru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MeetingAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List <Meeting> ListMeeting = new ArrayList<>();

        mRecyclerView = findViewById(R.id.meeting_recyclerview);

        mAdapter = new MeetingAdapter(ListMeeting);
        mRecyclerView.setAdapter(mAdapter);
    }
}
