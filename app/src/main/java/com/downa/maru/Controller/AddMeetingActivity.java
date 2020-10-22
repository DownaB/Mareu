package com.downa.maru.Controller;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.downa.maru.Service.ApiService;
import com.downa.maru.Model.Meeting;
import com.downa.maru.Model.Room;
import com.downa.maru.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMeetingActivity extends AppCompatActivity {

    @Bind(R.id.OrganisateurLyt) TextInputLayout Organisateur;
    @Bind(R.id.SubjectLyt) TextInputLayout Subject;
    ChipGroup mChipGroup = (ChipGroup) this.findViewById(R.id.Participant);
    Button mAdd_Participant = (Button)this.findViewById(R.id.Btn_add);
    TextView mDate = (TextView)this.findViewById(R.id.Date);
    Button mOkDate= (Button)this.findViewById(R.id.Ok_Date);
    TextView mHour = (TextView)this.findViewById(R.id.Hour);
    Button mOkHour = (Button)this.findViewById(R.id.Ok_Hour);

    Calendar c;
    DatePickerDialog mDatePickerDialog;
    TimePickerDialog mTimePickerDialog;
    Context mContext = this;

    Room mRoom;

    Chip chip;
    int chipNumber;

    private ApiService mApiService;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_add_meeting);
        ButterKnife.bind(this);

        mOkDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);


                mDatePickerDialog = new DatePickerDialog(AddMeetingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mDate.setText(dayOfMonth + "/" + month +"/" + year);
                    }
                }, day,month,year);
                mDatePickerDialog.show();
            }
        });

        mOkHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                mTimePickerDialog = new TimePickerDialog(AddMeetingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mHour.setText(hourOfDay + ":" + minute);
                    }
                },hour,minute,android.text.format.DateFormat.is24HourFormat(mContext));
                mTimePickerDialog.show();

            }
        });

      mAdd_Participant.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              Chip chip = new Chip(AddMeetingActivity.this);
              chip.setText("chip" + chipNumber ++);
              chip.setCheckable(true);
              chip.setCloseIcon(ContextCompat.getDrawable(AddMeetingActivity.this,R.drawable.ic_close_black_24dp));
              chip.setCloseIconVisible(true);

          }
      });

      chip.setOnCloseIconClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              mChipGroup.removeView(v);

          }
      });

      mChipGroup.addView(chip,0);

    }


@OnClick(R.id.Create)
    void addMeeting() {
        Meeting meeting = new Meeting (
                mRoom,
                Organisateur.getEditText().getText().toString(),
                mChipGroup,
                mDatePickerDialog,
                mTimePickerDialog,
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
