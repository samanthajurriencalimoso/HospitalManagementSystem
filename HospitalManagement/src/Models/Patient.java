package Models;

public class Patient {
    private String id, name, room, doctor, nurse, status, admissionDate, address, contactNumber,
                   allergies, conditions, medications, surgicalHistory, familyHistory,
                   lifestyleDiet, medicalManagement, dietBreakfast, dietLunch, dietDinner,
                   emergencyName, emergencyRel, emergencyNum, bloodType, notes,
                   bp, hr, temp, spo2,
                   generalAppearance, cardiovascular, respiratory, neurological;
    private int age;

    public Patient(String id, String name, String room, String doctor, String nurse,
                   String status, String admissionDate, String address, String contactNumber,
                   String allergies, String conditions, String medications,
                   String surgicalHistory, String familyHistory,
                   String lifestyleDiet, String medicalManagement,
                   String dietBreakfast, String dietLunch, String dietDinner,
                   String emergencyName, String emergencyRel, String emergencyNum,
                   String bloodType, int age, String notes,
                   String bp, String hr, String temp, String spo2,
                   String generalAppearance, String cardiovascular, String respiratory, String neurological) {
        this.id = id; this.name = name; this.room = room;
        this.doctor = doctor; this.nurse = nurse; this.status = status;
        this.admissionDate = admissionDate; this.address = address;
        this.contactNumber = contactNumber; this.allergies = allergies;
        this.conditions = conditions; this.medications = medications;
        this.surgicalHistory = surgicalHistory; this.familyHistory = familyHistory;
        this.lifestyleDiet = lifestyleDiet; this.medicalManagement = medicalManagement;
        this.dietBreakfast = dietBreakfast; this.dietLunch = dietLunch; this.dietDinner = dietDinner;
        this.emergencyName = emergencyName; this.emergencyRel = emergencyRel;
        this.emergencyNum = emergencyNum; this.bloodType = bloodType;
        this.age = age; this.notes = notes;
        this.bp = bp; this.hr = hr; this.temp = temp; this.spo2 = spo2;
        this.generalAppearance = generalAppearance;
        this.cardiovascular = cardiovascular;
        this.respiratory = respiratory;
        this.neurological = neurological;
    }

    public Patient(String id, String name, String room, String doctor,
               String nurse, String status, String admissionDate) {

    this(id, name, room, doctor, nurse, status, admissionDate,"", "", "", "","", "",
        "", "","","", "","","", "","",
        "Pending",0, "","","","","","", "","", "");
    }

    public String getId()            { return id; }
    public String getName()          { return name; }
    public String getRoom()          { return room; }
    public String getDoctor()        { return doctor; }
    public String getNurse()         { return nurse; }
    public String getStatus()        { return status; }
    public String getAdmissionDate() { return admissionDate; }
    public String getAddress()       { return address; }
    public String getContactNumber() { return contactNumber; }
    public String getAllergies()     { return allergies; }
    public String getConditions()    { return conditions; }
    public String getMedications()   { return medications; }
    public String getEmergencyName() { return emergencyName; }
    public String getEmergencyRel()  { return emergencyRel; }
    public String getEmergencyNum()  { return emergencyNum; }
    public String getBloodType()     { return bloodType; }
    public int    getAge()           { return age; }
    public String getNotes()         { return notes; }
    public String getSurgicalHistory() { return surgicalHistory; }
    public String getFamilyHistory()   { return familyHistory; }
    public String getLifestyleDiet()   { return lifestyleDiet; }
    public String getMedicalManagement() { return medicalManagement; }
    public String getDietBreakfast()   { return dietBreakfast; }
    public String getDietLunch()       { return dietLunch; }
    public String getDietDinner()      { return dietDinner; }
    public String getBp()    { return bp; }
    public String getHr()    { return hr; }
    public String getTemp()  { return temp; }
    public String getSpo2()  { return spo2; }
    public String getGeneralAppearance() { return generalAppearance; }
    public String getCardiovascular()    { return cardiovascular; }
    public String getRespiratory()       { return respiratory; }
    public String getNeurological()      { return neurological; }

    public void setId(String id)                   { this.id = id; }
    public void setName(String name)               { this.name = name; }
    public void setRoom(String room)               { this.room = room; }
    public void setDoctor(String doctor)           { this.doctor = doctor; }
    public void setNurse(String nurse)             { this.nurse = nurse; }
    public void setStatus(String status)           { this.status = status; }
    public void setAdmissionDate(String d)         { this.admissionDate = d; }
    public void setAddress(String address)         { this.address = address; }
    public void setContactNumber(String c)         { this.contactNumber = c; }
    public void setAllergies(String allergies)     { this.allergies = allergies; }
    public void setConditions(String conditions)   { this.conditions = conditions; }
    public void setMedications(String medications) { this.medications = medications; }
    public void setEmergencyName(String n)         { this.emergencyName = n; }
    public void setEmergencyRel(String r)          { this.emergencyRel = r; }
    public void setEmergencyNum(String n)          { this.emergencyNum = n; }
    public void setBloodType(String b)             { this.bloodType = b; }
    public void setAge(int age)                    { this.age = age; }
    public void setNotes(String notes)             { this.notes = notes; }
    public void setSurgicalHistory(String s)       { this.surgicalHistory = s; }
    public void setFamilyHistory(String f)         { this.familyHistory = f; }
    public void setLifestyleDiet(String s)         { this.lifestyleDiet = s; }
    public void setMedicalManagement(String s)     { this.medicalManagement = s; }
    public void setDietBreakfast(String s)         { this.dietBreakfast = s; }
    public void setDietLunch(String s)             { this.dietLunch = s; }
    public void setDietDinner(String s)            { this.dietDinner = s; }
    public void setBp(String bp)                   { this.bp = bp; }
    public void setHr(String hr)                   { this.hr = hr; }
    public void setTemp(String temp)               { this.temp = temp; }
    public void setSpo2(String spo2)               { this.spo2 = spo2; }
    public void setGeneralAppearance(String ga)    { this.generalAppearance = ga; }
    public void setCardiovascular(String cv)       { this.cardiovascular = cv; }
    public void setRespiratory(String resp)        { this.respiratory = resp; }
    public void setNeurological(String neuro)      { this.neurological = neuro; }
}