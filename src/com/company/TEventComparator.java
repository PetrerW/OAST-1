package com.company;

import java.util.Comparator;

public class TEventComparator implements Comparator<TEvent> {
    @Override
    public int compare(TEvent T1, TEvent T2){
        if(T1.getTime() < T2.getTime()) {
            return 1;
        }
        else
            return -1;
    }
}
