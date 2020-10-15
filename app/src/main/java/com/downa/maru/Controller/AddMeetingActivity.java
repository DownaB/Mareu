package com.downa.maru.Controller;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.downa.maru.Service.ApiService;
import com.downa.maru.Model.Meeting;
import com.downa.maru.Model.Room;
import com.downa.maru.R;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMeetingActivity extends AppCompatActivity {

    @Bind(R.id.OrganisateurLyt) TextInputLayout Organisateur;
    DatePicker mDatePicker = (DatePicker) this.findViewById(R.id.Date);
    TimePicker mTimePicker = (TimePicker) this.findViewById(R.id.Hours);
    @Bind(R.id.SubjectLyt) TextInputLayout Subject;
    ChipGroup mParticipants = (ChipGroup) this.findViewById(R.id.Participant);

    
    Room mRoom;

    private ApiService mApiService;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_add_meeting);
        ButterKnife.bind(this);

    }


@OnClick(R.id.Create)
    void addMeeting() {
        Meeting meeting = new Meeting (
                System.currentTimeMillis(),
                mRoom,
                Organisateur.getEditText().getText().toString(),
                List <mParticipants> = Arrays.asList();
                mDatePicker.getDayOfMonth(),mDatePicker.getMonth(),mDatePicker.getYear(),
                mTimePicker.setIs24HourView(true),
                Subject.getEditText().getText().toString());

        mApiService.createMeeting(meeting);
}

    public static class MainActivity extends AppCompatActivity {

        @Bind(R.id.meeting_recyclerview)
        private RecyclerView mRecyclerView;
        private ApiService.MeetingAdapter mMeetingAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            ButterKnife.bind(this);

            List<Meeting> ListMeeting = new ArrayList<>();


            mAdapter = new ApiService.MeetingAdapter(ListMeeting);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}
