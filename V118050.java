import java.util.Scanner;

// Patient.java
class Patient {
    int patientID;
    String name;
    int age;
    String disease;

    // Constructor to initialize patient details
    public Patient(int patientID, String name, int age, String disease) {
        this.patientID = patientID;
        this.name = name;
        this.age = age;
        this.disease = disease;
    }

    // Method to display patient details
    public void displayPatientDetails() {
        System.out.println("Patient ID: " + patientID);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Disease: " + disease);
    }
}

public class V118050{
    static Patient[] patients = new Patient[10]; // Array to store patients (max 10 patients)
    static int patientCount = 0; // To keep track of the number of patients

    // Method to add a patient
    public static void addPatient() {
        Scanner scanner = new Scanner(System.in);

        // Validate patient ID (should be integer)
        int patientID = 0;
        boolean validID = false;
        while (!validID) {
            System.out.print("Enter Patient ID: ");
            if (scanner.hasNextInt()) {
                patientID = scanner.nextInt();
                scanner.nextLine(); // Consume the newline
                validID = true;
            } else {
                System.out.println("Invalid input. Please enter a valid integer for Patient ID.");
                scanner.nextLine(); // Consume the invalid input
            }
        }

        // Validate patient name (should be string)
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        while (name.matches(".\\d.")) { // If name contains any numbers, prompt again
            System.out.println("Invalid input. Patient name should not contain numbers. Please enter again:");
            name = scanner.nextLine();
        }

        // Validate age (should be positive integer and within a reasonable range)
        int age = 0;
        boolean validAge = false;
        while (!validAge) {
            System.out.print("Enter Patient Age: ");
            if (scanner.hasNextInt()) {
                age = scanner.nextInt();
                scanner.nextLine(); // Consume the newline
                if (age > 0 && age <= 120) {
                    validAge = true;
                } else {
                    System.out.println("Invalid input. Age must be between 1 and 120. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer for age.");
                scanner.nextLine(); // Consume the invalid input
            }
        }

        // Validate disease (should be string)
        System.out.print("Enter Disease: ");
        String disease = scanner.nextLine();
        while (disease.matches(".\\d.")) { // If disease contains numbers, prompt again
            System.out.println("Disease name should not contain numbers. Please enter again:");
            disease = scanner.nextLine();
        }

        // Adding the patient to the array
        if (patientCount < patients.length) {
            patients[patientCount] = new Patient(patientID, name, age, disease);
            patientCount++;
            System.out.println("Patient added successfully!");
        } else {
            System.out.println("Hospital is at full capacity. Cannot add more patients.");
        }
    }

    // Method to display all patients
    public static void displayAllPatients() {
        if (patientCount == 0) {
            System.out.println("No patients available.");
        } else {
            for (int i = 0; i < patientCount; i++) {
                System.out.println("\n--- Patient " + (i + 1) + " ---");
                patients[i].displayPatientDetails();
            }
        }
    }

    // Method to search a patient by ID
    public static void searchPatientByID(int patientID) {
        boolean found = false;
        for (int i = 0; i < patientCount; i++) {
            if (patients[i].patientID == patientID) {
                System.out.println("\nPatient found:");
                patients[i].displayPatientDetails();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No patient found with ID " + patientID);
        }
    }

    // Main method to interact with the user
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n--- Hospital Management System ---");
            System.out.println("1. Add Patient");
            System.out.println("2. Display All Patients");
            System.out.println("3. Search Patient by ID");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    // Add Patient
                    addPatient();
                    break;

                case 2:
                    // Display all patients
                    displayAllPatients();
                    break;

                case 3:
                    // Search Patient by ID
                    System.out.print("Enter Patient ID to search: ");
                    int searchID = scanner.nextInt();
                    searchPatientByID(searchID);
                    break;

                case 4:
                    // Exit
                    System.out.println("Exiting Hospital Management System.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}