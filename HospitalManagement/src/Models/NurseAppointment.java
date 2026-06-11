package Models;

import java.time.LocalDateTime;

public class NurseAppointment {
    private int id;
    private String patientName;
    private String patientId;
    private String reportData;
    private String status;
    private String sentBy;
    private String sentTo;
    private LocalDateTime sentDate;

    public NurseAppointment(int id, String patientName, String patientId, String reportData, 
                            String status, String sentBy, String sentTo, LocalDateTime sentDate) {
        this.id = id;
        this.patientName = patientName;
        this.patientId = patientId;
        this.reportData = reportData;
        this.status = status;
        this.sentBy = sentBy;
        this.sentTo = sentTo;
        this.sentDate = sentDate;
    }

    public NurseAppointment(String patientName, String patientId, String reportData, 
                            String sentBy, String sentTo) {
        this(0, patientName, patientId, reportData, "Draft", sentBy, sentTo, null);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
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

}