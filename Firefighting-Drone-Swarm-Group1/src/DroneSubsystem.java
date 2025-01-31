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
        if (ON_JOB) {
            droneReport();
        } else {

        }
    }

    public void jobDetails() {
        currentJobdetails = scheduler.assignIncident();

    }


    public void droneReport() {
       //Tell the scheduler the job is done.
        System.out.println("Job severity: " + currentJobdetails.getSeverity());
        System.out.println("Job Time Details: " + currentJobdetails.getTime());
    }
}
