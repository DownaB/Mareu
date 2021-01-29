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
import com.downa.maru.databinding.ActivityMainBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;
import java.util.List;

import DI.DI;
import Events.DeleteMeetingEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    private MeetingAdapter mMeetingAdapter;
    private ActivityMainBinding binding;
    private ApiService mApiService = DI.getMeeting();

    private CharSequence room = null;
    private int year = -1;
    private int month = -1;
    private int dayOfMonth = -1;

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
        initList();

        binding.addMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddMeetingActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event) {
        mApiService.deleteMeeting(event.mMeeting);
        initList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        final SubMenu mSubMenu = menu.findItem(R.id.room_list).getSubMenu();
        for (Room room : RoomGenerator.generateRoom()) {
            mSubMenu.add(Menu.NONE, Menu.NONE, Menu.NONE, room.getName());
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
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

    private void filterByRoom(CharSequence title) {
        year = -1;
        month = -1;
        dayOfMonth = -1;
        room = title;
        updateList(mApiService.filterByRoom(title.toString()));
    }

    private void datePicker() {
        final DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                MainActivity.this.dayOfMonth = dayOfMonth;
                MainActivity.this.month = month;
                MainActivity.this.year = year;
                filterByDate(year, month, dayOfMonth);
            }
        };
        final Calendar c = Calendar.getInstance();
        final DatePickerDialog mDatePickerDialog = new DatePickerDialog(this, listener, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        mDatePickerDialog.show();
    }

    private void filterByDate(int year, int month, int dayOfMonth) {
        room = null;
        updateList(mApiService.filterByDate(year,month,dayOfMonth));
    }

    private void clearFilter() {
        room = null;
        year = -1;
        month = -1;
        dayOfMonth = -1;
        updateList(mApiService.getMeeting());
    }

    private void initList(){
        if (room != null) {
            filterByRoom(room);
        } else if (year != -1 && month != -1 && dayOfMonth != -1) {
            filterByDate(year, month, dayOfMonth);
        } else {
            clearFilter();
        }
    }

    private void updateList(List<Meeting> meetings){
        mMeetingAdapter = new MeetingAdapter(meetings);
        binding.meetingRecyclerview.setAdapter(mMeetingAdapter);
    }
}




