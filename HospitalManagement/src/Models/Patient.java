package Models;

public class Patient {
    private String id;
    private String name;
    private String room;
    private String doctor;
    private String nurse;
    private String status;
    private String admissionDate; // Added property tracking

    // Updated Constructor
    public Patient(String id, String name, String room, String doctor, String nurse, String status, String admissionDate) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.doctor = doctor;
        this.nurse = nurse;
        this.status = status;
        this.admissionDate = admissionDate;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRoom() { return room; }
    public void setRoom(String room) { this.room = room; }

    public String getDoctor() { return doctor; }
    public void setDoctor(String doctor) { this.doctor = doctor; }

    public String getNurse() { return nurse; }
    public void setNurse(String nurse) { this.nurse = nurse; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAdmissionDate() { return admissionDate; }
    public void setAdmissionDate(String admissionDate) { this.admissionDate = admissionDate; }
}