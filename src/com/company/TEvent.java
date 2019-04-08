package com.company;

//queue Event
public class TEvent {
    //Arrival time
    private double time;
    private EventTypes.Type type;

    public TEvent(){
        time = 0;
        type = EventTypes.Type.CHUNK;
    }

    public TEvent(double time, EventTypes.Type type){
        this.time = time;
        this.type = type;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }


    public EventTypes.Type getType() {
        return type;
    }

    public void setType(EventTypes.Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TEvent{" +
                "time=" + time +
                ", type=" + type +
                '}';
    }
}
