Firefighting Drone Swarm System - Iteration 1

Team Members:
- Abdulaziz Al Sibakhi
- Ahmed Babar
- Marc Fernandes
- Sam Touma
- Lucas Warburton

Project Description:
This project implements a control system and simulator for a firefighting drone swarm. The system consists of three main subsystems:

1. Scheduler: Handles task coordination and assigns drones to incidents.
2. Drone Subsystem: Represents drones that extinguish fires.
3. Fire Incident Subsystem: Simulates fire zones and sends incident requests to the scheduler.

In this iteration, the focus is on establishing basic communication between subsystems using multithreading.

File Structure:
- DroneSubsystem.java: Represents the drone's behavior, including movement and firefighting operations.
- FireIncidentSubsystem.java: Reads incident and zone data and forwards it to the Scheduler.
- Incident.java: Defines the data structure for fire incidents, including severity and type.
- IncidentMessage.java: Encapsulates incident details for communication between subsystems.
- Main.java: Entry point of the system that initializes and runs all subsystems.
- Scheduler.java: Coordinates between the drones and fire incidents.
- Zone.java: Represents a geographical zone where fires may occur.
- Box.java: Represents a thread-safe box used to transfer objects between threads.

Input Files:
- Sample_event_file.csv: Event data for fire incidents.
- sample_zone_file.csv: Zone configuration data.

Setup Instructions:
1. Ensure you have Java JDK 17 and IntelliJ IDEA installed.
2. Place the input files (Sample_event_file.csv, sample_zone_file.csv) in the input directory.
3. Compile the project in IntelliJ.
4. Run the Main.java file to start the system.

Execution:
1. Scheduler: Acts as the central server to coordinate requests and assign tasks.
2. Fire Incident Subsystem: Reads the input files, generates incidents, and forwards them to the Scheduler.
3. Drone Subsystem: Continuously polls the Scheduler for tasks and performs firefighting operations.

Testing Instructions:
- Use the provided input files (Sample_event_file.csv, sample_zone_file.csv) to simulate incidents.
- Observe console logs for communication between subsystems and the completion of firefighting tasks.
- Run the provided JUnit test (SystemTest.java) file to verify system functionality.

Test File:
A JUnit test file is included to verify that the system can read input files and correctly pass incidents between components. The test performs the following checks:

1. It ensures that the necessary input files exist.
2. It verifies that the input files are not empty.
3. It checks whether incidents are successfully passed to the scheduler.
4. It starts both the fire incident and drone subsystems as threads and validates that at least one incident has been processed.

UML Class Diagram:
A UML class diagram is included to visualize the relationships between the main classes. It illustrates how the Scheduler, DroneSubsystem, FireIncidentSubsystem, Incident, and other classes interact within the system. The class diagram provides an overview of class attributes, methods, and associations.

Sequence Diagram:
A sequence diagram is included to depict the flow of communication between the subsystems. It shows the interaction between the FireIncidentSubsystem, Scheduler, and DroneSubsystem, demonstrating how incidents are generated, assigned, and processed by drones.