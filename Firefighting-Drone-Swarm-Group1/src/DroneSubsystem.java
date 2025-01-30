public class DroneSubsystem implements Runnable {
    private final Boolean ON_JOB = false;


    String fireIncident;

    public DroneSubsystem(String fireIncident) {
        this.fireIncident = fireIncident;
    }



    @Override
    public void run() {
        try {
            while (!ON_JOB) {
                //CheckScheduler for information
                checkScheduler();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void checkScheduler() {
        if (!ON_JOB) {
            //scheduler.availableWork();
        }
    }
}
