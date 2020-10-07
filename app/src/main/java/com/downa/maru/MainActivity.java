package com.downa.maru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.meeting_recyclerview)
    private RecyclerView mRecyclerView;
    private MeetingAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        List <Meeting> ListMeeting = new ArrayList<>();


        mAdapter = new MeetingAdapter(ListMeeting);
        mRecyclerView.setAdapter(mAdapter);
    }
}
