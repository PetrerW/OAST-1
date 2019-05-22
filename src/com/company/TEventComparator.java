package com.company;

import java.util.Comparator;

public class TEventComparator implements Comparator<TEvent> {
    @Override
    public int compare(TEvent T1, TEvent T2){
        if(T1.getTime() < T2.getTime()) {
            return 1;
        }
        //TODO check if it's correct and TEST
        else if(T1.getTime() == T2.getTime()){
            if((T1.getType()==EventTypes.Type.EVENT_ARRIVAL
                || T1.getType()==EventTypes.Type.EVENT_DEPARTURE)
                &&
                    (T2.getType() == EventTypes.Type.SYSTEM_OFF
                || T2.getType() == EventTypes.Type.SYSTEM_ON)
            )
                return 1;
            else
                return -1;
        }
        else
            return -1;
    }
}
