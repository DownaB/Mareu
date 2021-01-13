package com.downa.maru.Controller;

import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.downa.maru.Model.Meeting;
import com.downa.maru.Model.Room;
import com.downa.maru.R;
import com.downa.maru.Service.ApiService;


import java.util.List;

import Events.DeleteMeetingEvent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.MeetingViewHolder> {


    private ApiService mApiService;

    List<Meeting> mMeetingList;
    List<Room> mRoomList;


    public static class MeetingViewHolder extends RecyclerView.ViewHolder {

        ImageView avatar;
        TextView meeting;
        ImageButton delete;

        public MeetingViewHolder(@NonNull View itemView) {
            super(itemView);

            avatar = itemView.findViewById(R.id.avatar);
            meeting = itemView.findViewById(R.id.text_meeting);
            delete = itemView.findViewById(R.id.delete);
        }
    }

    public MeetingAdapter(List<Meeting> meetingList) {
        mMeetingList = meetingList;
    }

    public RoomAdapter(List<Room> roomList){mRoomList = roomList;}


    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_meeting, parent,false);
        MeetingViewHolder meetingViewHolder = new MeetingViewHolder(view);
        return meetingViewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        Meeting meeting = mMeetingList.get(position);
        Room room = mRoomList.get(position);
        holder.meeting.setText(meeting.getSubject());
        holder.avatar.getContext().load(room.getAvatar());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event.getDefault().post(new DeleteMeetingEvent(meeting));

            }
        });


    }

    @Override
    public int getItemCount() {
        return mMeetingList.size();
    }



}
