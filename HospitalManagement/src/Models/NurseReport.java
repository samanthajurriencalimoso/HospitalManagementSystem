package Models;

public class NurseReport {

    private String reportId;
    private String reportType;
    private String patientName;
    private String nurseName;
    private String status;
    private String reportDate;
    private String notes;

    public NurseReport() {}

    public NurseReport(String reportId, String reportType, String patientName,
                       String nurseName, String status, String reportDate, String notes) {
        this.reportId    = reportId;
        this.reportType  = reportType;
        this.patientName = patientName;
        this.nurseName   = nurseName;
        this.status      = status;
        this.reportDate  = reportDate;
        this.notes       = notes;
    }

    public String getReportId()    { return reportId; }
    public String getReportType()  { return reportType; }
    public String getPatientName() { return patientName; }
    public String getNurseName()   { return nurseName; }
    public String getStatus()      { return status; }
    public String getReportDate()  { return reportDate; }
    public String getNotes()       { return notes; }

    public void setReportId(String reportId)       { this.reportId    = reportId; }
    public void setReportType(String reportType)   { this.reportType  = reportType; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public void setNurseName(String nurseName)     { this.nurseName   = nurseName; }
    public void setStatus(String status)           { this.status      = status; }
    public void setReportDate(String reportDate)   { this.reportDate  = reportDate; }
    public void setNotes(String notes)             { this.notes       = notes; }

    @Override
    public String toString() {
        return "NurseReport{" +
               "reportId='"   + reportId    + '\'' +
               ", reportType='" + reportType + '\'' +
               ", patient='"  + patientName + '\'' +
               ", nurse='"    + nurseName   + '\'' +
               ", status='"   + status      + '\'' +
               ", date='"     + reportDate  + '\'' +
               '}';
    }
}