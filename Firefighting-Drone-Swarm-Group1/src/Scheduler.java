
/***
 * This is a class where data is exchanged between Fire Incident System and the Drones
 * @author ahmedbabar
 */
public class Scheduler implements Runnable {

    private Box fireIncidentSendBox;
    private Box fireIncidentRecieveBox;
    private Box droneSendBox;
    private Box droneRecieveBox;



    /***
     * Constructor for Scheduler.
     */
    public Scheduler(Box fireIncidentSendBox, Box droneSendBox, Box fireIncidentRecieveBox, Box droneRecieveBox) {
        this.fireIncidentSendBox = fireIncidentSendBox;
        this.droneSendBox = droneSendBox;
        this.fireIncidentRecieveBox = fireIncidentRecieveBox;
        this.droneRecieveBox = droneRecieveBox;
    }

    @Override
    public void run() {
        while (true) {
            Object iMessage = fireIncidentRecieveBox.get();
            if (iMessage instanceof IncidentMessage){
                droneSendBox.put((IncidentMessage)iMessage);
                droneRecieveBox.get();
                fireIncidentSendBox.put(true);
            }
            else{
                droneSendBox.put(null);
                break;
            }
        }
    }
}

