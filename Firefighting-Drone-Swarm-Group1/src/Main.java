public class Main {
    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler();
        FireIncidentSubsystem fireSystem = new FireIncidentSubsystem(scheduler, "","");
        DroneSubsystem dronespecifications = new DroneSubsystem(scheduler);
        Thread drone1 = new Thread(dronespecifications);
        Thread firesystem1 = new Thread(fireSystem);
        firesystem1.start();
        drone1.start();
    }

}
