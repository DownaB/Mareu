package com.downa.maru;

import android.os.Bundle;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.TaskStackBuilder;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMeeting extends AppCompatActivity {

    @Bind(R.id.OrganisateurLyt)
    TextInputLayout Organisateur;
    @Bind(R.id.DateLyt) TextInputLayout Date;
    @Bind(R.id.HoursLyt) TextInputLayout Hours;
    @Bind(R.id.ParticipantsLyt1) TextInputLayout Participant1;
    @Bind(R.id.ParticipantsLyt2) TextInputLayout Participant2;
    @Bind(R.id.ParticipantsLyt3)TextInputLayout Participant3;
    @Bind(R.id.ParticipantsLyt4) TextInputLayout Participant4;
    @Bind(R.id.ParticipantsLyt5) TextInputLayout Participant5;
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
                Participant4.getEditText().getText().toString(),
                Participant5.getEditText().getText().toString(),
                Subject.getEditText().getText().toString()
    );

        mApiService.createMeeting(meeting);
}
}
