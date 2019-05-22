package com.company;

/**
 * M/M/1/inf queue
 */
public class System {

    int numberOfClientsInSystem;
    /**
     * Number of all clients in system during simulation. Used to define client_id
     */
    int totalNumberOfClients;

    final int offAverageTime = 35;

    final int onAverageTime = 40;

    public System(){
        numberOfClientsInSystem = 0;
        totalNumberOfClients = 0;
        Log.info("System created.\n\tNumber of clients in system: " +
                numberOfClientsInSystem);

    }

    public int getNumberOfClientsInSystem() {
        return numberOfClientsInSystem;
    }

    public void setNumberOfClientsInSystem(int numberOfClientsInSystem) {
        this.numberOfClientsInSystem = numberOfClientsInSystem;
    }

    public int getTotalNumberOfClients() {
        return totalNumberOfClients;
    }

    public void setTotalNumberOfClients(int totalNumberOfClients) {
        this.totalNumberOfClients = totalNumberOfClients;
    }

    public int addClient(){
        numberOfClientsInSystem++;
        totalNumberOfClients++;
        Log.info("Client added.\n\tclientId: " + (totalNumberOfClients) +
                "\n\tNumber of clients in system: " + numberOfClientsInSystem);

        return totalNumberOfClients;
    }

    public void removeClient(){

        if(numberOfClientsInSystem > 0) {
            numberOfClientsInSystem--;
        }
        Log.info("Client removed.\n\tNumber of clients in system: " +
                numberOfClientsInSystem);
    }

    public int getOffAverageTime() {
        return offAverageTime;
    }

    public int getOnAverageTime() {
        return onAverageTime;
    }
}
