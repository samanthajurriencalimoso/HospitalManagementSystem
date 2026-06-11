package Models;

import java.time.LocalDateTime;

public class AdminAppointment {
    private int id;
    private int doctorReportId;
    private String patientName;
    private String patientId;
    private String reportData;
    private String doctorNotes;
    private String status;
    private String sentBy;
    private String sentTo;
    private LocalDateTime sentDate;
    private String approvedBy;
    private String approvedRole;
    private LocalDateTime approvedDate;

    public AdminAppointment(int id, int doctorReportId, String patientName, String patientId, 
                            String reportData, String doctorNotes, String status, 
                            String sentBy, String sentTo, LocalDateTime sentDate,
                            String approvedBy, String approvedRole, LocalDateTime approvedDate) {
        this.id = id;
        this.doctorReportId = doctorReportId;
        this.patientName = patientName;
        this.patientId = patientId;
        this.reportData = reportData;
        this.doctorNotes = doctorNotes;
        this.status = status;
        this.sentBy = sentBy;
        this.sentTo = sentTo;
        this.sentDate = sentDate;
        this.approvedBy = approvedBy;
        this.approvedRole = approvedRole;
        this.approvedDate = approvedDate;
    }

    public AdminAppointment(int doctorReportId, String patientName, String patientId, 
                            String reportData, String sentBy, String sentTo) {
        this(0, doctorReportId, patientName, patientId, reportData, "", 
             "Received from Doctor", sentBy, sentTo, null, "", "", null);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getDoctorReportId() { return doctorReportId; }
    public void setDoctorReportId(int doctorReportId) { this.doctorReportId = doctorReportId; }
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }
    public String getReportData() { return reportData; }
    public void setReportData(String reportData) { this.reportData = reportData; }
    public String getDoctorNotes() { return doctorNotes; }
    public void setDoctorNotes(String doctorNotes) { this.doctorNotes = doctorNotes; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getSentBy() { return sentBy; }
    public void setSentBy(String sentBy) { this.sentBy = sentBy; }
    public String getSentTo() { return sentTo; }
    public void setSentTo(String sentTo) { this.sentTo = sentTo; }
    public LocalDateTime getSentDate() { return sentDate; }
    public void setSentDate(LocalDateTime sentDate) { this.sentDate = sentDate; }
    public String getApprovedBy() { return approvedBy; }
    public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy; }
    public String getApprovedRole() { return approvedRole; }
    public void setApprovedRole(String approvedRole) { this.approvedRole = approvedRole; }
    public LocalDateTime getApprovedDate() { return approvedDate; }
    public void setApprovedDate(LocalDateTime approvedDate) { this.approvedDate = approvedDate; }
}

