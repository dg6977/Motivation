package com.example.sexyguy.motivation;

public class ScheduleItem {

    private int scheduleItemId;
    private String scheduleName;
    private String scheduleStartTime;
    private String scheduleEndTime;
    private String scheduleImg;

    public void setScheduleItemId(int scheduleItemId){
        this.scheduleItemId=scheduleItemId;
    }

    public void setScheduleName(String scheduleName){
        this.scheduleName=scheduleName;
    }

    public void setScheduleStartTime(String scheduleStartTime){
        this.scheduleStartTime=scheduleStartTime;
    }

    public void setScheduleEndTime(String scheduleEndTime){
        this.scheduleEndTime=scheduleEndTime;
    }

    public void setScheduleImg(String scheduleImg){
        this.scheduleImg=scheduleImg;
    }

    public int getScheduleItemId(){
        return scheduleItemId;
    }

    public String getScheduleName(){
        return scheduleName;
    }

    public String getScheduleStartTime(){
        return scheduleStartTime;
    }

    public String getScheduleEndTime(){
        return scheduleEndTime;
    }

    public String getScheduleImg(){
        return scheduleImg;
    }

}
