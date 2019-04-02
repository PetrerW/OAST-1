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

    public double get_time(){
        return this.time;
    }

    public void set_sime(double _time){
        this.time = _time;
    }

    public String get_type(){
        return this.type;
    }

    public void set_type(String _type){
        this.type = _type;
    }

    @Override
    public String toString(){
        final String s = this.get_type() + " " + this.get_time();
        return s;
    }
}
