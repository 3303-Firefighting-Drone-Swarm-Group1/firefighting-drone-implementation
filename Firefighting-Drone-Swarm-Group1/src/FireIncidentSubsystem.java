/**
 * The FireIncidentSubsystem class represents a Fire Incident Subsystem. It reads input files containing information 
 * about the zones and events that occur, and then transmits them to the scheduler.
 * 
 * @author Lucas Warburton, 101276823
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;

public class FireIncidentSubsystem implements Runnable{
    private Scheduler scheduler;
    private String zoneInput, eventInput;
    private Box sendBox, recieveBox;

    public FireIncidentSubsystem(Scheduler scheduler, String zoneInput, String eventInput, Box sendBox, Box recieveBox){
        this.scheduler = scheduler;
        this.zoneInput = zoneInput;
        this.eventInput = eventInput;
        this.sendBox = sendBox;
        this.recieveBox = recieveBox;
    }

    /**
     * Reads the input files and transmits the information to the scheduler.
     */
    public void run(){
        HashMap<Integer, Zone> zones = null;
        try {
            zones = readZones();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Incident> incidents = null;
        try {
            incidents = readIncidents();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (Incident incident: incidents){
            sendBox.put(new IncidentMessage(incident.getSeverity(), zones.get(1).getStart(), zones.get(1).getEnd(), incident.getTime(), incident.getType()));
        }
        recieveBox.get(); // get acknowledgement
        System.out.println("Fire Incident Recieved Job Completion Token.");
    }

    /**
     * Reads the zone input file.
     * @return an HashMap containing the information about each zone
     */
    private HashMap<Integer, Zone> readZones() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(zoneInput));
        HashMap<Integer, Zone> zones = new HashMap<>();
        sc.nextLine();
        while (sc.hasNextLine()){
            String[] line = sc.nextLine().trim().split(",");
            String[] start = line[1].split("();");
            String[] end = line[2].split("();");
            zones.put(
                    Integer.parseInt(line[0]),
                    new Zone(Integer.parseInt(start[0].substring(1)),
                            Integer.parseInt(start[1].substring(0,1)),
                            Integer.parseInt(end[0].substring(1)),
                            Integer.parseInt(end[1].substring(0, (end[1].length())-1))));
        }
        sc.close();
        return zones;
    }

    /**
     * Reads the event input file.
     * @return an ArrayList containing the information about each event
     */
    private ArrayList<Incident> readIncidents() throws FileNotFoundException {
        Scanner sc = new Scanner(new File (eventInput));
        ArrayList<Incident> incidents = new ArrayList<>();
        sc.nextLine();
        while (sc.hasNextLine()){
            String[] line = sc.nextLine().trim().split(",");

            String[] time = line[0].split(":");

            Incident.Type type;
            if (line[2].equals("FIRE_DETECTED")) type = Incident.Type.FIRE_DETECTED;
            else type = Incident.Type.DRONE_REQUEST;

            Incident.Severity severity;
            if (line[3].equals("High")) severity = Incident.Severity.HIGH;
            else if (line[3].equals("Moderate")) severity = Incident.Severity.MODERATE;
            else severity = Incident.Severity.LOW;

            incidents.add(new Incident(Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2]), Integer.parseInt(line[1]), severity, type));
        }
        sc.close();
        return incidents;
    }

}
