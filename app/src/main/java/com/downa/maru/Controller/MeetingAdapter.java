package com.downa.maru.Controller;


import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.downa.maru.Model.Meeting;

import com.downa.maru.R;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import Events.DeleteMeetingEvent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.MeetingViewHolder> {


    List<Meeting> mMeetingList;


    public static class MeetingViewHolder extends RecyclerView.ViewHolder {

        ImageView avatar;
        TextView meeting;
        ImageButton delete;
        TextView participants;

        public MeetingViewHolder(@NonNull View itemView) {
            super(itemView);

            avatar = itemView.findViewById(R.id.avatar);
            meeting = itemView.findViewById(R.id.text_meeting);
            Typeface boldTypeFace = Typeface.defaultFromStyle(Typeface.BOLD);
            meeting.setTypeface(boldTypeFace);
            delete = itemView.findViewById(R.id.delete);
            participants = itemView.findViewById(R.id.participant);
        }
    }

    public MeetingAdapter(List<Meeting> meetingList) {
        mMeetingList = meetingList;
    }


    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_meeting, parent, false);
        MeetingViewHolder meetingViewHolder = new MeetingViewHolder(view);
        return meetingViewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        Meeting meeting = mMeetingList.get(position);
        StringBuilder builder = new StringBuilder();
        builder.append(meeting.getSubject());
        builder.append(" - ");
        builder.append(meeting.getHour());
        builder.append(" - ");
        builder.append(meeting.getRoom().getName());

        StringBuilder builderParticipants = new StringBuilder();

        for (int i = 0; i<meeting.getParticipants().size(); i++) {
            builderParticipants.append(meeting.getParticipants().get(i));

            if (i < meeting.getParticipants().size() - 1) {
                builderParticipants.append(" , ");
            }
        }

        holder.meeting.setText(builder.toString());
        holder.participants.setText(builderParticipants.toString());
        holder.avatar.setImageResource(meeting.getRoom().getAvatar());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));

            }
        });


    }

    @Override
    public int getItemCount() {
        return mMeetingList.size();
    }


}
