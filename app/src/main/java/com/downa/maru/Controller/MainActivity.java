package com.downa.maru.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.date:
                mApiService.filterByDate();
                return true;
            case R.id.meeting_rooms:
                RoomGenerator.generateRoom();
                return true;
            case R.id.no_filter:
                mApiService.getMeeting();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }


}


