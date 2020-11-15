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
import android.widget.Toast;

import com.downa.maru.Service.ApiService;
import com.downa.maru.Model.Meeting;
import com.downa.maru.Model.Room;
import com.downa.maru.R;
import com.downa.maru.databinding.ActivityAddMeetingBinding;
import com.downa.maru.databinding.ActivityMainBinding;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.validation.Validator;

import androidx.annotation.CheckResult;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;




public class AddMeetingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener implements ValidationListener {

private int day = -1;
private int month = -1;
private int year = -1;
private int hour = -1;
private int minute = -1;

private ActivityAddMeetingBinding binding;


private List <Room> RoomList = RoomGenerator.generateRoom();

private Validator mValidator;

private ApiService mApiService;






    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        initDatePicker();
        initTimePicker();
        initAddChip();
        initSpinnerRoom();
        initSubject();
        initValidator();
        initAddMeeting();
    }


    private void initDatePicker() {

        final DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selectedDay, int selectedMonth, int selectedYear) {

                day=selectedDay;
                month=selectedMonth;
                year= selectedYear;

                binding.Date.setText(selectedDay + "/" + selectedMonth++ + "/" + selectedYear);
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

                binding.Hour.setText(selectedHour + ":" + selectedMinute);

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
                    Toast.makeText(AddMeetingActivity.this,"l'email saisie n'est pas valide",Toast.LENGTH_LONG).show();
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

        final String Subject = binding.SubjectLyt.getText().toString();
        System.out.println(Subject);
        }

        private void initValidator(){

        mValidator = new Validator(this);
        mValidator.setValidationListner(this);

        public void onValidationSucceeded(){

            initAddMeeting();

            }

            public void onValidationFailed(){

                Toast.makeText(AddMeetingActivity.this, "Le formulaire n'est pas complet", Toast.LENGTH_SHORT).show();
            }

        }



    private void initAddMeeting(){
    binding.Create.setOnClickListener(new View.OnClickListener() {

        @Checked
        binding.RoomList;

        @Order (value = 1)
        @NotEmpty
        binding.Participant;

        @Checked
        binding.select_date;

        @Checked
        binding.select_hour;

        @NotEmpty
        binding.SubjectLyt;


        @Override
        public void onClick(View view) {
            Meeting meeting = new Meeting(RoomList,RoomList,day,month,year,hour,minute, );

                    mApiService.createMeeting(meeting);
        }
    });

}



}
