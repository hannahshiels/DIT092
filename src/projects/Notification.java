package projects;

import tools.CurrentDate;
import tools.RandomID;
import users.User;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class Notification {

    private String notificationID;
    private String projectID;
    private ArrayList<User> receivers;
    private User receiver;
    private String notificationTitle;
    private String notificationBody;
    private LocalDate date;

    //Constructor for notifications that have 2 or more receivers
    public Notification(ArrayList<User> receivers, String notificationTitle, String notificationBody){
        this.notificationID = RandomID.getRandomID();
        this.receivers = receivers;
        this.notificationTitle = notificationTitle;
        this.notificationBody = notificationBody;
        this.date = CurrentDate.getCurrentDate();
    }

    //Constructor for notifications that have a single receiver (e.g - Task Assigned)
    public Notification(User receiver, String notificationTitle, String notificationBody){
        this.notificationID = RandomID.getRandomID();
        this.receiver = receiver;
        this.notificationTitle = notificationTitle;
        this.notificationBody = notificationBody;
        this.date = CurrentDate.getCurrentDate();
    }

    public String getProjectID(){
        return projectID;
    }
    public ArrayList<User> getReceivers(){
        return receivers;
    }
    public User getIndividualReceiver(User user){
        ArrayList<User> receivers = new ArrayList<>(getReceivers());
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
