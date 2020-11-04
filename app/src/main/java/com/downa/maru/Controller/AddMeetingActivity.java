package com.downa.maru.Controller;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.downa.maru.Service.ApiService;
import com.downa.maru.Model.Meeting;
import com.downa.maru.Model.Room;
import com.downa.maru.R;
import com.downa.maru.databinding.ActivityMainBinding;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputLayout;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMeetingActivity extends AppCompatActivity {

private int day = -1;
private int month = -1;
private int year = -1;
private int hour = -1;
private int minute = -1;

private ActivityAddMeetingBinding binding;

    Room mRoom;
    List <Room> RoomList = RoomGenerator.generateRoom();

    int chipNumber;


    private ApiService mApiService;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        val binding = ActivityAddMeetingBinding.inflate(LayoutInflater);
        setContentView(binding.root);


        binding.SubjectLyt.text = "sujet";
        ChipGroup mChipGroup = (ChipGroup) this.findViewById(R.id.Participant);

        Spinner mSpinner = (Spinner) findViewById(R.id.RoomMeeting);

        initDatePicker();
        initTimePicker();


      mAdd_Participant.setOnClickListener(new View.OnClickListener() {
          binding.button.setOnClickListener(new View.OnClickListener(){
              Btn_add.userClicked()});

          @Override
          public void onClick(View v) {
              binding.Input.editText;

              String mEmail = this.editText.getText().toString();

              Chip chip = new Chip(AddMeetingActivity.this);
              chip.setText(mEmail + chipNumber ++);
              chip.setCloseIcon(ContextCompat.getDrawable(AddMeetingActivity.this,R.drawable.ic_close_black_24dp));
              chip.setCloseIconVisible(true);
              mChipGroup.addView(chip,0);

              String email = "^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\.[A_Za-z]{2,4}$";
              if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                  Log.i(getString(R.string.Email));
              }

              chip.setOnCloseIconClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                      mChipGroup.removeView(v);


                  }
              });


          }

          });
      ArrayAdapter<Room> arrayAdapter = new ArrayAdapter<Room>(this,android.R.layout.simple_spinner_item,RoomList);
      mSpinner.setAdapter(arrayAdapter);

      }

    private void initDatePicker(){
        final TextView mDate = findViewById(R.id.Date);
        final Button mSelect_date = findViewById(R.id.select_date);
        final DatePickerDialog mDatePickerDialog = new DatePickerDialog(this);
        mDatePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selectedDay, int selectedMonth, int selectedYear) {

                day= selectedDay;
                month= selectedMonth;
                year= selectedYear;

                mDate.setText(selectedDay + "/" + selectedMonth + "/" + selectedYear);

            }
        });

        mSelect_date.setOnClickListener(new View.OnClickListener() {
            binding.button.setOnClickListener(new.View.OnClickListener){mSelect_date.userClicked()});
            @Override
            public void onClick(View view) {
                mDatePickerDialog.show();
            }
        });

    }

    private void initTimePicker(){
        final TextView mHour = findViewById(R.id.Hour);
        final Button mSelect_hour = findViewById(R.id.select_hour);

        final TimePickerDialog mTimePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {

                hour = selectedHour;
                minute = selectedMinute;

                mHour.setText(selectedHour + "/" + selectedMinute);

            }
        },9,0, true);

        mSelect_hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimePickerDialog.show();
            }
        });
    }




binding.button.setOnClickListener(new.View.OnClickListener){Create.userClicked()});
@OnClick(R.id.Create)
    void addMeeting() {
        Meeting meeting = new Meeting (
                mRoom,
                mChipGroup,
                mDatePickerDialog,
                mTimePickerDialog,
                Subject);

        mApiService.createMeeting(meeting);
}

    public static class MainActivity extends AppCompatActivity {

        binding = MeetingRecyclerviewBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        @Bind(R.id.meeting_recyclerview)
        private RecyclerView mRecyclerView;
        private ApiService.MeetingAdapter mMeetingAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            binding = ActivityMainBinding.inflate(getLayoutInflater());
            View view = binding.getRoot();
            setContentView(R.layout.activity_main);

            List<Meeting> ListMeeting = new ArrayList<>();


            mAdapter = new ApiService.MeetingAdapter(ListMeeting);
            mRecyclerView.setAdapter(mAdapter);
        }

    }


}
