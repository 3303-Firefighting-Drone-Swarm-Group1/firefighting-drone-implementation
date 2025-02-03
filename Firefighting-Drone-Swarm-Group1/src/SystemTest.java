import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.util.Scanner;

/***
 * @author Sam Touma
 * Iteration 1 Feb 1, 2025
 */

class SystemTest {

    private static final String ZONE_FILE = "input/sample_zone_file.csv";
    private static final String EVENT_FILE = "input/Sample_event_file.csv";;

    @Test
    void testFileReading() {
        // Check if files exist
        File zoneFile = new File(ZONE_FILE);
        File eventFile = new File(EVENT_FILE);

        assertTrue(zoneFile.exists(), "Zone input file is missing!");
        assertTrue(eventFile.exists(), "Event input file is missing!");

        // Check if files can be read
        try (Scanner zoneScanner = new Scanner(zoneFile)) {
            assertTrue(zoneScanner.hasNextLine(), "Zone file is empty!");
        } catch (Exception e) {
            fail("Failed to read zone file: " + e.getMessage());
        }

        try (Scanner eventScanner = new Scanner(eventFile)) {
            assertTrue(eventScanner.hasNextLine(), "Event file is empty!");
        } catch (Exception e) {
            fail("Failed to read event file: " + e.getMessage());
        }
    }

    @Test
    void testIncidentPassing() throws InterruptedException {
        // Create components
        Scheduler scheduler = new Scheduler();
        FireIncidentSubsystem fireSystem = new FireIncidentSubsystem(scheduler, ZONE_FILE, EVENT_FILE);
        DroneSubsystem drone = new DroneSubsystem(scheduler);

        // Start threads
        Thread fireThread = new Thread(fireSystem);
        Thread droneThread = new Thread(drone);
        fireThread.start();
        droneThread.start();

        // thread delay
        Thread.sleep(5000);

        // scheduler processed at least one incident
        assertNotNull(scheduler.assignIncident(), "Incident was not passed to Scheduler correctly!");

        // terminate threads
        fireThread.interrupt();
        droneThread.interrupt();
        fireThread.join();
        droneThread.join();
    }
}
