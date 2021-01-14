package com.downa.maru.Controller;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.downa.maru.Service.ApiService;
import com.downa.maru.Model.Meeting;
import com.downa.maru.Model.Room;
import com.downa.maru.R;
import com.downa.maru.Service.MeetingApiService;
import com.downa.maru.databinding.ActivityAddMeetingBinding;
import com.downa.maru.databinding.ToolbarBinding;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import DI.DI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;


public class AddMeetingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

private int day = -1;
private int month = -1;
private int year = -1;
private int hour = -1;
private int minute = -1;


private ActivityAddMeetingBinding binding;

private List <Room> RoomList = RoomGenerator.generateRoom();

private ApiService mApiService = DI.getMeeting();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar((Toolbar) binding.toolbar.getRoot());

        initDatePicker();
        initTimePicker();
        initAddChip();
        initSpinnerRoom();
        initSubject();
        initAddMeeting();
        setupActionBar();

    }



    private void initDatePicker() {

        final DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selectedDay, int selectedMonth, int selectedYear) {

                day=selectedDay;
                month=selectedMonth;
                year= selectedYear;

                selectedMonth ++;
                binding.Date.setText(String.format("%02d/%02d/%04d",selectedDay ,selectedMonth ,selectedYear));
            }
        };

        final Calendar c = Calendar.getInstance();
        final DatePickerDialog mDatePickerDialog = new DatePickerDialog(this, listener, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

        binding.selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatePickerDialog.show();
            }
        });

    }


    private void initTimePicker(){

        final TimePickerDialog mTimePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {

                hour = selectedHour;
                minute = selectedMinute;

                binding.Hour.setText(String.format("%02d:%02d",selectedHour,selectedMinute));

            }
        },9,0, true);

        binding.selectHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimePickerDialog.show();
            }
        });
    }

    private void initAddChip(){

        binding.BtnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String mEmail = binding.Input.getText().toString();

                if (Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()) {
                    Chip chip = new Chip(AddMeetingActivity.this);
                    chip.setText(mEmail);
                    chip.setCloseIcon(ContextCompat.getDrawable(AddMeetingActivity.this, R.drawable.ic_close_black_24dp));
                    chip.setCloseIconVisible(true);
                    chip.setOnCloseIconClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            binding.Participant.removeView(v);
                        }
                    });
                    binding.Participant.addView(chip, 0);

                } else {
                    Toast.makeText(AddMeetingActivity.this, R.string.mail_non_valide,Toast.LENGTH_LONG).show();
                }
            }

        });


        }


    private void initSpinnerRoom() {

        ArrayAdapter<Room> arrayAdapter = new ArrayAdapter<Room>(this, android.R.layout.simple_spinner_item, RoomList);
        binding.RoomMeeting.setAdapter(arrayAdapter);

        binding.RoomMeeting.setOnItemSelectedListener(this);
    }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

        String text = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }

        public void initSubject(){

        final String Subject = binding.Subject.getText().toString();
        System.out.println(Subject);
        }

        private void initValidation(){

            if (binding.Participant.getChildCount() <1){
                Toast.makeText(AddMeetingActivity.this,R.string.Merci_d_entrer_un_participant, Toast.LENGTH_SHORT).show();
            }

            else if (TextUtils.isEmpty(binding.Date.getText()) == true){
                Toast.makeText(AddMeetingActivity.this, R.string.Merci_d_entrer_une_date, Toast.LENGTH_SHORT).show();
            }

            else if (TextUtils.isEmpty(binding.Hour.getText()) == true){
                Toast.makeText(AddMeetingActivity.this, R.string.Merci_d_entrer_une_heure, Toast.LENGTH_SHORT).show();
            }

            else if(TextUtils.isEmpty(binding.Subject.getText()) == true){
            Toast.makeText(AddMeetingActivity.this,R.string.Merci_d_entrer_le_sujet_de_la_réunion,Toast.LENGTH_SHORT).show();

        }else{
                ArrayList<String> emails = new ArrayList<>();
                for (int i =0; i<binding.Participant.getChildCount();i++){
                final Chip chip = (Chip) binding.Participant.getChildAt(i);
                final String email = chip.getText().toString();
                emails.add(email);}

                final Room room = (Room) binding.RoomMeeting.getSelectedItem();

                Meeting meeting = new Meeting(room,emails,day,month,year,hour,minute, binding.Subject.getText().toString());

                mApiService.createMeeting(meeting);

                navigate();

                finish();
            }


        }

    private void initAddMeeting(){
    binding.Create.setOnClickListener(new View.OnClickListener() {


        @Override
        public void onClick(View view) {
           initValidation();
        }
    });

}
    public void navigate (){
    Intent intent = new Intent(AddMeetingActivity.this, MainActivity.class);
    startActivity(intent);

    }

    private void setupActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


}


