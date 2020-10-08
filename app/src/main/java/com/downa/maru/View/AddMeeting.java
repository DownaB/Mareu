package com.downa.maru.View;

import android.os.Bundle;

import com.downa.maru.Controller.ApiService;
import com.downa.maru.Model.Meeting;
import com.downa.maru.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMeeting extends AppCompatActivity {

    @Bind(R.id.OrganisateurLyt) TextInputLayout Organisateur;
    @Bind(R.id.DateLyt) TextInputLayout Date;
    @Bind(R.id.HoursLyt) TextInputLayout Hours;
    @Bind(R.id.ParticipantsLyt1) TextInputLayout Participant1;
    @Bind(R.id.ParticipantsLyt2) TextInputLayout Participant2;
    @Bind(R.id.ParticipantsLyt3)TextInputLayout Participant3;
    @Bind(R.id.SubjectLyt) TextInputLayout Subject;

    private ApiService mApiService;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_add_meeting);
        ButterKnife.bind(this);

    }

@OnClick(R.id.Create)
    void addMeeting() {
        Meeting meeting = new Meeting(
                System.currentTimeMillis(),
                Organisateur.getEditText().getText().toString(),
                Date.getEditText().getText().toString(),
                Hours.getEditText().getText().toString(),
                Participant1.getEditText().getText().toString(),
                Participant2.getEditText().getText().toString(),
                Participant3.getEditText().getText().toString(),
                Subject.getEditText().getText().toString()
    );

        mApiService.createMeeting(meeting);
}

    public static class MainActivity extends AppCompatActivity {

        @Bind(R.id.meeting_recyclerview)
        private RecyclerView mRecyclerView;
        private ApiService.MeetingAdapter mAdapter;

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
