import java.awt.*;
import java.sql.Time;
import java.util.LinkedList;
import java.util.Queue;

/***
 * This is a class where data is exchanged between Fire Incident System and the Drones
 * @author ahmedbabar
 */
public class Scheduler {

    private Boolean Job_Complete = false;
    Queue<IncidentMessage> availableWork;
    private boolean readable = true;
    private boolean writable = false;



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
                System.out.println("Waiting for incident to be read");
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        availableWork.add(incident);
        ///System.out.println(incident);
        notifyIncidentCleared();

        //Method Synchronization
        readable = false;
        writable = true;
        notifyAll();

    }

    private void notifyIncidentCleared() {
        if (Job_Complete) {
            System.out.println("Job completed");
        }
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
        System.out.println("Assigning incident");
        readable = true;
        writable = false;
        notifyAll();
        return availableWork.remove();
    }

    //Get information back to the fire system.
    public void setJob_Complete(Boolean job_Complete) {
        Job_Complete = job_Complete;
    }

    public Boolean getJob_Complete() {
        return Job_Complete;
    }
}

