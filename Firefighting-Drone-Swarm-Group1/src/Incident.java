/**
 * Incident represents an incident in the Firefighting Drone Swarm system.
 * @author Lucas Warburton, 101276823
 */

 import java.sql.Time;

public abstract class Incident {
    public static final int LOW = 0;
    public static final int MODERATE = 1;
    public static final int HIGH = 2;
    public static final boolean FIRE_DETECTED = true;
    public static final boolean DRONE_REQUEST = false;

    private Time time;
    private int id;
    private int severity;
    private boolean type;
    
    public Incident(int hour, int minute, int second, int id, int severity, boolean type){
        time = new Time(((hour* 60 + minute)* 60 + second) * 1000);
        this.id = id;
        this.severity = severity;
        this.type = type;
    }

    /**
     * Gets the ID of the zone in which the incident happened.
     * @return the ID
     */
    public int getID(){
        return id;
    }

    /**
     * Gets the severity of the incident.
     * @return the severity
     */
    public int getSeverity(){
        return severity;
    }

    /**
     * Gets the time at which the incident happened.
     * @return the time
     */
    public Time getTime(){
        return time;
    }
}
