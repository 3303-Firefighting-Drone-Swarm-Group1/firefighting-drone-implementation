import java.util.LinkedList;
import java.util.Queue;

/***
 * This is a class where data is exchanged between Fire Incident System and the Drones
 */
public class Scheduler {
    private final Boolean availableTasks = false;
    Queue<IncidentMessage> availableWork;

    private boolean readable = false;
    private boolean writable = true;

    /***
     * Constructor for Scheduler.
     */
    public Scheduler() {
        this.availableWork = new LinkedList<IncidentMessage>();
    }

    /***
     * Add new incident to the queue of available jobs
     * @param incident
     */
    public synchronized void addNewIncident(IncidentMessage incident) {
        while (!readable) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        availableWork.add(incident);
        readable = false;
        writable = true;
        notifyAll();

    }

    /***
     * Assign a drone a new job.
     * @return
     */
    public synchronized IncidentMessage assignIncident() {
        while (!writable) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        readable = true;
        writable = false;
        notifyAll();
        return availableWork.remove();

    }

    //method to get information from Fire incident

}

