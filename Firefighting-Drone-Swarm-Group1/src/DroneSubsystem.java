/***
 * @author ahmedbabar
 */
public class DroneSubsystem implements Runnable {
    private final Boolean ON_JOB = false;
    Scheduler scheduler;
    IncidentMessage currentJobdetails;

    public DroneSubsystem(Scheduler Scheduler) {
        this.scheduler = Scheduler;
    }

    /***
     * Should only check for available work.
     */
    @Override
    public void run() {
        //Look for Job
        jobDetails();
        //Print out the Information from
        droneReport();
        //Send a message back.

    }

    public void jobDetails() {
        System.out.println("Drone Requests Job");
        currentJobdetails = scheduler.assignIncident();
    }

    public void notifyJobCompletion() {
        //Send a Packet back to the Scheduler
        scheduler.setJob_Complete(true);
    }

    public void droneReport() {
       //Tell the scheduler the job is done.
        System.out.println("Job severity: " + currentJobdetails.getSeverity());
        System.out.println("Job Time Details: " + currentJobdetails.getTime());
    }
}
