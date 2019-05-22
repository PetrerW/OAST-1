package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Report {

    private String fileName = "default.txt";

    public Report(String fileName) {
        this.fileName = fileName;
    }

    /*
        Generates report in format
        <lambda>, <list of averages delays>
     */
    public void generateReport(double lambda, List<LinkedList<TEvent>> eventsLists) {
        String report = lambda + ";";
        for (LinkedList<TEvent> eventsList : eventsLists) {
            report += calculateAverageDelay(eventsList) + ";";
        }
        report += "\n";

        printToFile(fileName, report);
    }

    /*
    Generates report in format
    <lambda>, <list of averages delays>
    Warm up parameter is used
    */
    public void generateReport(double lambda, List<LinkedList<TEvent>> eventsLists, int warpUpIndex) {
        String report = lambda + ";";
        for (LinkedList<TEvent> eventsList : eventsLists) {
            report += calculateAverageDelay(eventsList, warpUpIndex) + ";";
        }
        report += "\n";

        printToFile(fileName, report);
    }


    private void printToFile(String fileName, String text) {

        File file = new File(fileName);


        BufferedWriter bufferedWriter = null;
        try {
            if (file.exists()) {
                bufferedWriter = new BufferedWriter(new FileWriter(file, true));

            } else {
                bufferedWriter = new BufferedWriter(new FileWriter(file));

            }
            bufferedWriter.write(text);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private double calculateAverageDelay(LinkedList<TEvent> eventsList) {

        HashMap<Integer, Double> serviceTimes = new HashMap<>();

        for (TEvent t : eventsList
        ) {
            if (t.getType() == EventTypes.Type.EVENT_ARRIVAL) {
                serviceTimes.put(t.getClientId(), t.getTime());
            } else if (t.getType() == EventTypes.Type.EVENT_DEPARTURE) {
                serviceTimes.replace(t.getClientId(),
                        t.getTime() - serviceTimes.get(t.getClientId()));
            }
        }

        double sum = 0;
        for (Double d : serviceTimes.values()
        ) {
            sum += d;
        }
        double averageDelay = sum / serviceTimes.size();

        return averageDelay;
    }

    //Uses warm up parameter
    private double calculateAverageDelay(LinkedList<TEvent> eventsList, int warpUpIndex) {

        HashMap<Integer, Double> serviceTimes = new HashMap<>();

        for (TEvent t : eventsList
        ) {
            if (t.getClientId() > warpUpIndex) {
                if (t.getType() == EventTypes.Type.EVENT_ARRIVAL) {
                    serviceTimes.put(t.getClientId(), t.getTime());
                } else if (t.getType() == EventTypes.Type.EVENT_DEPARTURE) {
                    serviceTimes.replace(t.getClientId(),
                            t.getTime() - serviceTimes.get(t.getClientId()));
                }
            }
        }

        double sum = 0;
        for (Double d : serviceTimes.values()
        ) {
            sum += d;
        }
        double averageDelay = sum / serviceTimes.size();

        return averageDelay;
    }


}
