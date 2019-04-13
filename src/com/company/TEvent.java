package com.company;

//queue Event
public class TEvent {

    private double time;
    private EventTypes.Type type;
    /*
     * To identify arrival and departure events for the same client
     */
    private int clientId;

    public TEvent(double time, EventTypes.Type type){
        this.time = time;
        this.type = type;
        clientId = -1;
    }

    public TEvent(double time, EventTypes.Type type, int clientId){
        this.time = time;
        this.type = type;
        this.clientId = clientId;
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

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "TEvent{" +
                "time=" + time +
                ", type=" + type +
                '}';
    }
}
