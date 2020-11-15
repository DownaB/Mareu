package com.downa.maru.Controller;

import android.os.Bundle;
import android.view.View;

import com.downa.maru.Model.Meeting;
import com.downa.maru.R;
import com.downa.maru.Service.ApiService;
import com.downa.maru.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends AppCompatActivity {

    private ActivityMainBinding binding;

    private MeetingAdapter mMeetingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        List<Meeting> ListMeeting = new ArrayList<>();

        mMeetingAdapter = new MeetingAdapter(ListMeeting);
        binding.meetingRecyclerview.setAdapter(mMeetingAdapter);
    }
}
