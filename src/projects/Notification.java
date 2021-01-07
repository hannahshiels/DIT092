package projects;

import tools.CurrentDate;
import tools.RandomID;
import users.User;

import java.time.LocalDate;
import java.util.ArrayList;

public class Notification {

    private String notificationID;
    private String projectID;
    private ArrayList<User> receivers;
    private String notificationTitle;
    private String notificationBody;
    private LocalDate date;

    public Notification(ArrayList<User> receivers, String notificationTitle, String notificationBody){
        this.notificationID = RandomID.getRandomID();
        this.receivers = receivers;
        this.notificationTitle = notificationTitle;
        this.notificationBody = notificationBody;
        this.date = CurrentDate.getCurrentDate();
    }


    // Added setters, idk if we will need them
    public void setReceiver(ArrayList<User> receivers){
        this.receivers = receivers;
    }
    public void setNotificationTitle(String notificationTitle){
        this.notificationTitle = notificationTitle;
    }
    public void setNotificationBody(String notificationBody){
        this.notificationBody = notificationBody;
    }

    public String getProjectID(){
        return projectID;
    }
    public ArrayList<User> getReceiver(){
        return receivers;
    }
    public User getIndividualReceiver(User user){
        for(User newUser : receivers){
            if(newUser == user){
                return user;
            }
        }
        return null;
    }
    public String getNotificationTitle(){
        return notificationTitle;
    }
    public String getNotificationBody(){
        return notificationBody;
    }
    public String getNotificationID(){
        return notificationID;
    }
    public boolean isAfter(Notification anotherNotification){
        return date.isAfter(anotherNotification.date);
    }
}
