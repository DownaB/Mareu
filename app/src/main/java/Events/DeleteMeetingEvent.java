package Events;

import com.downa.maru.Model.Meeting;

public class DeleteMeetingEvent {

    public Meeting mMeeting;

    public  DeleteMeetingEvent (Meeting meeting){
        this.mMeeting = meeting;
    }
}
