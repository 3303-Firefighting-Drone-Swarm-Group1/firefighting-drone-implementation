import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;

public class FireIncidentSubsystem implements Runnable{
    private Scheduler scheduler;
    private String zoneInput, eventInput;

    public FireIncidentSubsystem(Scheduler scheduler, String zoneInput, String eventInput){
        this.scheduler = scheduler;
        this.zoneInput = zoneInput;
        this.eventInput = eventInput;
    }

    public void start(){
        HashMap<Integer, Zone> zones = readZones();
        ArrayList<Incident> incidents = readIncidents();
        for (Incident incident: incidents){
            scheduler.put(new IncidentMessage(incident.getSeverity(), zones.get(incident.getID()).getStart(), zones.get(incident.getID()).getEnd(), incident.getTime(), incident.getType()));
        }
    }

    private HashMap<Integer, Zone> readZones(){
        Scanner sc = new Scanner(new File(zoneInput));
        HashMap<Integer, Zone> zones = new HashMap<>();
        sc.nextLine();
        while (sc.hasNextLine()){
            String[] line = sc.nextLine().trim().split(",");
            String[] start = line[1].split("();");
            String[] end = line[2].split("();");
            zones.put(Integer.parseInt(line[0]), new Zone(Integer.parseInt(start[0]), Integer.parseInt(start[1]), Integer.parseInt(end[0]), Integer.parseInt(end[1])));
        }
        return zones;
    }

    private ArrayList<Incident> readIncidents(){
        Scanner sc = new Scanner(new File (eventInput));
        ArrayList<Incident> incidents = new ArrayList<>();
        sc.nextLine();
        while (sc.hasNextLine()){
            String[] line = sc.nextLine().trim().split(",");

            String[] time = line[0].split(":");

            boolean type;
            if (line[2].equals("FIRE_DETECTED")) type = Incident.FIRE_DETECTED;
            else type = Incident.DRONE_REQUEST;

            int severity;
            if (line[3].equals("High")) severity = Incident.HIGH;
            else if (line[3].equals("Moderate")) severity = Incident.MODERATE;
            else severity = Incident.LOW;

            incidents.add(new Incident(Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2]), Integer.parseInt(line[1]), severity, type));
        }
        return incidents;
    }
    
}
