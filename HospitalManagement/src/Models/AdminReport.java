package Models;

import java.time.LocalDateTime;

public class AdminReport {
    private int id;
    private int doctorReportId;
    private String patientName;
    private String patientId;
    private String reportData;
    private String status;
    private String sentBy;
    private String approvedBy;
    private LocalDateTime receivedDate;
    private LocalDateTime approvedDate;
    private String adminNotes;

    public AdminReport(int id, int doctorReportId, String patientName, String patientId,
                       String reportData, String status, String sentBy, String approvedBy,
                       LocalDateTime receivedDate, LocalDateTime approvedDate, String adminNotes) {
        this.id = id;
        this.doctorReportId = doctorReportId;
        this.patientName = patientName;
        this.patientId = patientId;
        this.reportData = reportData;
        this.status = status;
        this.sentBy = sentBy;
        this.approvedBy = approvedBy;
        this.receivedDate = receivedDate;
        this.approvedDate = approvedDate;
        this.adminNotes = adminNotes;
    }

    public int getId() { return id; }
    public int getDoctorReportId() { return doctorReportId; }
    public String getPatientName() { return patientName; }
    public String getPatientId() { return patientId; }
    public String getReportData() { return reportData; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getSentBy() { return sentBy; }
    public String getApprovedBy() { return approvedBy; }
    public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy; }
    public LocalDateTime getReceivedDate() { return receivedDate; }
    public LocalDateTime getApprovedDate() { return approvedDate; }
    public void setApprovedDate(LocalDateTime approvedDate) { this.approvedDate = approvedDate; }
    public String getAdminNotes() { return adminNotes; }
    public void setAdminNotes(String adminNotes) { this.adminNotes = adminNotes; }
}