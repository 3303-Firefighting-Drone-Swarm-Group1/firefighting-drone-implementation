import java.sql.Time;
import java.util.ArrayList;
import java.util.Set;

import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;

/***
 * @author ahmedbabar
 */
public class DroneSubsystem implements Runnable {
    private Boolean ON_JOB = false;
    Scheduler scheduler;
    IncidentMessage currentJobdetails;


    private final double SIZE_OF_TANK = 12;
    double requiredLiquid;
    //Time taken to empty tank = 2.4 seconds
    long timeToEmptyTank = (long) 2.4;
    long openCloseNozzle = 1;
    double speed = 25; //25 m/s


    //Drone Calculation Variables
    double timeTaken = 0;
    double distance;
    double numReturnTrips;

    Box sendBox, recieveBox;

    public DroneSubsystem(Scheduler Scheduler, Box sendBox, Box recieveBox) {
        this.scheduler = Scheduler;
        this.sendBox = sendBox;
        this.recieveBox = recieveBox;
    }

    /***
     * Should only check for available work.
     */
    @Override
    public void run() {
        while(true){
            //Look for Job
            jobDetails();
            //Complete Job
            if (currentJobdetails != null) {
                ON_JOB = true;
            } else break;
            extinguishFire();
        }
    }

    private void extinguishFire() {
        //How much water to extinguish
        if (currentJobdetails.getSeverity() == Incident.Severity.LOW) {
            //How long the drone takes to get there from truck



            //How much water the drone uses
            requiredLiquid = 10;
            numReturnTrips = ceil(requiredLiquid/SIZE_OF_TANK);
        } else if (currentJobdetails.getSeverity() == Incident.Severity.MODERATE) {
            //How long the drone takes
            //How much water the drone uses
            requiredLiquid = 20;
            numReturnTrips = ceil(requiredLiquid/SIZE_OF_TANK);
        } else if (currentJobdetails.getSeverity() == Incident.Severity.HIGH) {
            //How long the drone takes
            //How much water the drone uses
            requiredLiquid = 30;
            numReturnTrips = ceil(requiredLiquid/SIZE_OF_TANK);
        }
        droneCalculations();
        notifyJobCompletion();
    }

    public void jobDetails() {
        System.out.println("Drone Requests Job");
        currentJobdetails = (IncidentMessage) recieveBox.get();
    }

    public void notifyJobCompletion() {
        //Send a Packet back to the Scheduler
        sendBox.put(true);
    }

    public void droneCalculations() {
        //distance to travel
        distance = sqrt((currentJobdetails.getEndX())^2 + (currentJobdetails.getEndY())^2);
        //Time to open Nozzle and empty tank
        //Elapsed Time taken for drone to return to base.
        timeTaken = 2*(distance/speed)*numReturnTrips + openCloseNozzle + timeToEmptyTank*(requiredLiquid/SIZE_OF_TANK);
        droneReport();
    }

    public void droneReport() {
        //Tell the scheduler the job is done.
        System.out.println("Job severity: " + currentJobdetails.getSeverity());
        System.out.println("Job Completion Time: " + timeTaken);
        ON_JOB = false;
    }
}
