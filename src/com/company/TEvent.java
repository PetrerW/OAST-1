package com.company;

//queue Event
public class TEvent {
    //Arrival time
    private int time;
    private String type;

    public int get_time(){
        return this.time;
    }
    public void set_sime(int _time){
        this.time = _time;
    }

    public String get_type(){
        return this.type;
    }

    public void set_type(String _type){
        this.type = _type;
    }
}
