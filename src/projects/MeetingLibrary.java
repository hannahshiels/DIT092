package projects;

import users.User;

import java.time.LocalDate;
import java.util.ArrayList;

public class MeetingLibrary {
    private ArrayList<Meeting> meetingList = new ArrayList<>();

    public void addMeeting(Meeting newMeeting){
        meetingList.add(newMeeting);
    }

    public ArrayList<Meeting> getUpcomingMeetings(String projectID){
        ArrayList<Meeting> upcomingMeetings = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (int i = 0; i < meetingList.size(); i++){
            Meeting meeting = meetingList.get(i);
            if(meeting.getProjectID().equals(projectID) && meeting.getMeetingDate().compareTo(today) > 0){
                upcomingMeetings.add(meeting);
            }
        }
        return upcomingMeetings;
    }

    public ArrayList<Meeting> getPastMeetings(String projectID){
        ArrayList<Meeting> pastMeetings = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (int i = 0; i < meetingList.size(); i++){
            Meeting meeting = meetingList.get(i);
            if(meeting.getProjectID().equals(projectID) && meeting.getMeetingDate().compareTo(today) < 0){
                pastMeetings.add(meeting);
            }
        }
        return pastMeetings;
    }

}
