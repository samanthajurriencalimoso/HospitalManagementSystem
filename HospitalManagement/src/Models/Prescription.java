package Models;

public class Prescription {
    private int prescriptionId;
    private String patientId;
    private String patientName;
    private String drugName;
    private String dosage;
    private int frequency;
    private int duration;
    private String status;

    public Prescription(int prescriptionId, String patientId, String patientName, String drugName, String dosage, int frequency, int duration, String status) {
        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.drugName = drugName;
        this.dosage = dosage;
        this.frequency = frequency;
        this.duration = duration;
        this.status = status;
    }

    public int getPrescriptionId() { return prescriptionId; }
    public void setPrescriptionId(int prescriptionId) { this.prescriptionId = prescriptionId; }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getDrugName() { return drugName; }
    public void setDrugName(String drugName) { this.drugName = drugName; }

    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }

    public int getFrequency() { return frequency; }
    public void setFrequency(int frequency) { this.frequency = frequency; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}