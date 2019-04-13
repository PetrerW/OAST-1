package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class Report {

    public void generateReport(LinkedList<TEvent> eventsList){

        HashMap<Integer, Double> serviceTimes = new HashMap<>();

        for (TEvent t: eventsList
        ) {
            if(t.getType() == EventTypes.Type.EVENT_ARRIVAL){
                serviceTimes.put(t.getClientId(),t.getTime());
            }
            else if(t.getType() == EventTypes.Type.EVENT_DEPARTURE){
                serviceTimes.replace(t.getClientId(),
                        t.getTime() - serviceTimes.get(t.getClientId()));
            }
        }

        double sum = 0;
        for (Double d: serviceTimes.values()
             ) {
            sum +=d;
        }
        double averageDelay = sum/serviceTimes.size();

        String report = "Average delay: " + averageDelay + "\n" + serviceTimes.toString();
        printToFile("Report_" + new SimpleDateFormat("yyyy-MM-dd hh-mm-ss'.txt'").format(new Date()),
                report);
    }

    private void printToFile(String fileName, String text){
        File file = new File(fileName);
        BufferedWriter bufferedWriter = null;
        try{
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(text);

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


