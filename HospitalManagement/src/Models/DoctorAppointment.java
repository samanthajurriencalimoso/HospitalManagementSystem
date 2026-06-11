package Models;

import java.time.LocalDateTime;

public class DoctorAppointment {
    private int id;
    private int nurseReportId;
    private String patientName;
    private String patientId;
    private String reportData;
    private String status;
    private String sentBy;
    private String sentTo;
    private LocalDateTime sentDate;
    private String doctorNotes;

    public DoctorAppointment(int id, int nurseReportId, String patientName, String patientId, 
                             String reportData, String status, String sentBy, String sentTo, 
                             LocalDateTime sentDate) {
        this.id = id;
        this.nurseReportId = nurseReportId;
        this.patientName = patientName;
        this.patientId = patientId;
        this.reportData = reportData;
        this.status = status;
        this.sentBy = sentBy;
        this.sentTo = sentTo;
        this.sentDate = sentDate;
    }

    public DoctorAppointment(int nurseReportId, String patientName, String patientId, 
                             String reportData, String sentBy, String sentTo) {
        this(0, nurseReportId, patientName, patientId, reportData, "Received from Nurse", sentBy, sentTo, null);
    }
    
    public DoctorAppointment(int id, int nurseReportId, String patientName, String patientId, 
                             String reportData, String status, String sentBy, String sentTo) {
        this(id, nurseReportId, patientName, patientId, reportData, status, sentBy, sentTo, null);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getNurseReportId() { return nurseReportId; }
    public void setNurseReportId(int nurseReportId) { this.nurseReportId = nurseReportId; }
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }
    public String getReportData() { return reportData; }
    public void setReportData(String reportData) { this.reportData = reportData; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getSentBy() { return sentBy; }
    public void setSentBy(String sentBy) { this.sentBy = sentBy; }
    public String getSentTo() { return sentTo; }
    public void setSentTo(String sentTo) { this.sentTo = sentTo; }
    public LocalDateTime getSentDate() { return sentDate; }
    public void setSentDate(LocalDateTime sentDate) { this.sentDate = sentDate; }
    public String getDoctorNotes() { return doctorNotes; }
    public void setDoctorNotes(String doctorNotes) { this.doctorNotes = doctorNotes; }
}