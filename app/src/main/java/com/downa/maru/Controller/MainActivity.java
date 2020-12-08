package com.downa.maru.Controller;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.DatePicker;

import com.downa.maru.Model.Meeting;
import com.downa.maru.Model.Room;
import com.downa.maru.R;
import com.downa.maru.Service.ApiService;
import com.downa.maru.Service.MeetingApiService;
import com.downa.maru.databinding.ActivityMainBinding;

import java.util.List;

import DI.DI;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {


    private RecyclerView.LayoutManager mLayoutManager;
    private MeetingAdapter mMeetingAdapter;
    private ActivityMainBinding binding;
    private ApiService mApiService = DI.getMeeting();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar((Toolbar) binding.toolbar.getRoot());

    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Meeting> meetingList = mApiService.getMeeting();

        mMeetingAdapter = new MeetingAdapter(meetingList);
        binding.meetingRecyclerview.setAdapter(mMeetingAdapter);

        binding.addMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddMeetingActivity.class);
                startActivity(intent);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);

        final SubMenu mSubMenu = menu.findItem (R.id.room_list).getSubMenu();

        for (Room room : RoomGenerator.generateRoom()){
            mSubMenu.add(Menu.NONE, Menu.NONE,Menu.NONE,room.getName());
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.date:
                datePicker();
                return true;
            case R.id.room_list:
                return true;
            case R.id.no_filter:
                clearFilter();
                return true;

            default:
                filterByRoom(item.getTitle());
                return super.onOptionsItemSelected(item);
        }

    }

    private void filterByRoom(CharSequence title)
    {
        final List<Meeting> meetings = mApiService.filterByRoom(title.toString());
        mMeetingAdapter = new MeetingAdapter(meetings);
        binding.meetingRecyclerview.setAdapter(mMeetingAdapter);
    }

    private void datePicker() {

        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
            {
                final List<Meeting> meetings = mApiService.filterByDate(year, month, dayOfMonth);
                mMeetingAdapter = new MeetingAdapter(meetings);
                binding.meetingRecyclerview.setAdapter(mMeetingAdapter);
            }
        }, 2020, 11, 12).show();
    }

    private void clearFilter()
    {
        final List<Meeting> meetings = mApiService.getMeeting();
        mMeetingAdapter = new MeetingAdapter(meetings);
        binding.meetingRecyclerview.setAdapter(mMeetingAdapter);
    }

    }




