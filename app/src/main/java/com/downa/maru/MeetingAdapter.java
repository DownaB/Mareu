package com.downa.maru;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.MeetingViewHolder> {

    List<Meeting> ListMeeting;

    public static class MeetingViewHolder extends RecyclerView.ViewHolder{

        ImageView mAvatar;
        TextView mText;
        ImageButton mDelete;

        public MeetingViewHolder(@NonNull View itemView) {
            super(itemView);
            mAvatar = itemView.findViewById(R.id.avatar);
            mText = itemView.findViewById(R.id.text_meeting);
            mDelete = itemView.findViewById(R.id.imageButton);
        }
    }

    public MeetingAdapter(List<Meeting> listMeeting) {
        ListMeeting = listMeeting;
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_meeting,parent,false);
        MeetingViewHolder meetingViewHolder = new MeetingViewHolder(view);
        return meetingViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        Meeting meeting = ListMeeting.get(position);
        holder.mText.setText(meeting.getText());
        holder.mAvatar.setImageIcon(meeting.getAvatar());
        holder.mDelete.setImageDrawable(meeting.getDrawable());



    }

    @Override
    public int getItemCount() {
        return ListMeeting.size();
    }


}
