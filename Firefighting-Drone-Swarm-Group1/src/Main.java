public class Main {
    public static void main(String[] args) {
        /*
        Iteration 1: Fire system notifies Scheduler, Scheduler notifies Drones
        How it works. Scheduler stores available work for the Drones.
        The Drone is actively looking for work when there is work available.
         */

        Scheduler scheduler = new Scheduler();
        FireIncidentSubsystem fireSystem = new FireIncidentSubsystem(scheduler, "Firefighting-Drone-Swarm-Group1/input/sample_zone_file.csv","Firefighting-Drone-Swarm-Group1/input/Sample_event_file.csv");
        DroneSubsystem drone_specifications = new DroneSubsystem(scheduler);
        Thread drone1 = new Thread(drone_specifications);
        Thread fire_system1 = new Thread(fireSystem);
        fire_system1.start();
        drone1.start();
    }

}
