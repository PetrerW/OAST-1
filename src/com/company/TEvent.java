package com.company;

//queue Event
public class TEvent {
    //Arrival time
    private double time;
    private String type;

    public TEvent(){
        time = 0;
        type = "A";
    }

    public TEvent(double time, String type){
        this.time = time;
        this.type = type;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TEvent{" +
                "time=" + time +
                ", type='" + type + '\'' +
                '}';
    }
}
