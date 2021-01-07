package projects;

import java.util.ArrayList;

public class MeetingLibrary {
    private ArrayList<Meeting> meetingList = new ArrayList<>();

    public void addMeeting(Meeting newMeeting){
        meetingList.add(newMeeting);
    }


}
