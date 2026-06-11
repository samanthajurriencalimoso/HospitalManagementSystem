package Database;

import Models.Patient;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientManagementSQL {
    
    private static final String URL = "jdbc:mysql://localhost:3306/hospitalmanagement";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static String getNextPatientId() {
        String sql = "SELECT id FROM patients WHERE id LIKE 'P-%' ORDER BY id DESC LIMIT 1";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                String lastId = rs.getString("id"); 
                String numericPart = lastId.substring(2); 
                try {
                    int nextNum = Integer.parseInt(numericPart) + 1;
                    return "P-" + nextNum;
                } catch (NumberFormatException e) {
                    return "P-10001";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "P-10001"; 
    }

    public static boolean insertPatient(Patient patient) {
        String sql = "INSERT INTO patients (id, name, room, doctor, nurse, status, admission_date, " +
                     "address, contact_number, allergies, conditions, medications, " +
                     "surgical_history, family_history, " +
                     "lifestyle_diet, medical_management, diet_breakfast, diet_lunch, diet_dinner, " +
                     "emergency_name, emergency_rel, emergency_num, blood_type, age, notes, " +
                     "bp, hr, temp, spo2, general_appearance, cardiovascular, respiratory, neurological) " +
                     "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            int idx = 1;
            stmt.setString(idx++, patient.getId().toUpperCase());
            stmt.setString(idx++, patient.getName());
            stmt.setString(idx++, patient.getRoom());
            stmt.setString(idx++, patient.getDoctor());
            stmt.setString(idx++, patient.getNurse());
            stmt.setString(idx++, patient.getStatus());
            stmt.setString(idx++, patient.getAdmissionDate());
            stmt.setString(idx++, patient.getAddress());
            stmt.setString(idx++, patient.getContactNumber());
            stmt.setString(idx++, patient.getAllergies());
            stmt.setString(idx++, patient.getConditions());
            stmt.setString(idx++, patient.getMedications());
            stmt.setString(idx++, patient.getSurgicalHistory());
            stmt.setString(idx++, patient.getFamilyHistory());
            stmt.setString(idx++, patient.getLifestyleDiet());
            stmt.setString(idx++, patient.getMedicalManagement());
            stmt.setString(idx++, patient.getDietBreakfast());
            stmt.setString(idx++, patient.getDietLunch());
            stmt.setString(idx++, patient.getDietDinner());
            stmt.setString(idx++, patient.getEmergencyName());
            stmt.setString(idx++, patient.getEmergencyRel());
            stmt.setString(idx++, patient.getEmergencyNum());
            stmt.setString(idx++, patient.getBloodType());
            stmt.setInt(idx++, patient.getAge());
            stmt.setString(idx++, patient.getNotes());
            stmt.setString(idx++, patient.getBp());
            stmt.setString(idx++, patient.getHr());
            stmt.setString(idx++, patient.getTemp());
            stmt.setString(idx++, patient.getSpo2());
            stmt.setString(idx++, patient.getGeneralAppearance());
            stmt.setString(idx++, patient.getCardiovascular());
            stmt.setString(idx++, patient.getRespiratory());
            stmt.setString(idx++, patient.getNeurological());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public static boolean updatePatientProfile(Patient patient) {
        String sql = "UPDATE patients SET address = ?, contact_number = ?, allergies = ?, " +
                     "conditions = ?, medications = ?, surgical_history = ?, family_history = ?, " +
                     "lifestyle_diet = ?, medical_management = ?, " +
                     "diet_breakfast = ?, diet_lunch = ?, diet_dinner = ?, " +
                     "emergency_name = ?, emergency_rel = ?, emergency_num = ?, blood_type = ?, " +
                     "age = ?, notes = ?, status = ?, " +
                     "bp = ?, hr = ?, temp = ?, spo2 = ?, " +
                     "general_appearance = ?, cardiovascular = ?, respiratory = ?, neurological = ? " +
                     "WHERE UPPER(id) = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            int idx = 1;
            stmt.setString(idx++, patient.getAddress());
            stmt.setString(idx++, patient.getContactNumber());
            stmt.setString(idx++, patient.getAllergies());
            stmt.setString(idx++, patient.getConditions());
            stmt.setString(idx++, patient.getMedications());
            stmt.setString(idx++, patient.getSurgicalHistory());
            stmt.setString(idx++, patient.getFamilyHistory());
            stmt.setString(idx++, patient.getLifestyleDiet());
            stmt.setString(idx++, patient.getMedicalManagement());
            stmt.setString(idx++, patient.getDietBreakfast());
            stmt.setString(idx++, patient.getDietLunch());
            stmt.setString(idx++, patient.getDietDinner());
            stmt.setString(idx++, patient.getEmergencyName());
            stmt.setString(idx++, patient.getEmergencyRel());
            stmt.setString(idx++, patient.getEmergencyNum());
            stmt.setString(idx++, patient.getBloodType());
            stmt.setInt(idx++, patient.getAge());
            stmt.setString(idx++, patient.getNotes());
            stmt.setString(idx++, patient.getStatus());
            stmt.setString(idx++, patient.getBp());
            stmt.setString(idx++, patient.getHr());
            stmt.setString(idx++, patient.getTemp());
            stmt.setString(idx++, patient.getSpo2());
            stmt.setString(idx++, patient.getGeneralAppearance());
            stmt.setString(idx++, patient.getCardiovascular());
            stmt.setString(idx++, patient.getRespiratory());
            stmt.setString(idx++, patient.getNeurological());
            stmt.setString(idx++, patient.getId().toUpperCase());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public static List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients ORDER BY id ASC";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                patients.add(new Patient(
                    rs.getString("id"), rs.getString("name"), rs.getString("room"),
                    rs.getString("doctor"), rs.getString("nurse"), rs.getString("status"),
                    rs.getString("admission_date"), rs.getString("address"), rs.getString("contact_number"),
                    rs.getString("allergies"), rs.getString("conditions"), rs.getString("medications"),
                    rs.getString("surgical_history"), rs.getString("family_history"),
                    rs.getString("lifestyle_diet"), rs.getString("medical_management"),
                    rs.getString("diet_breakfast"), rs.getString("diet_lunch"), rs.getString("diet_dinner"),
                    rs.getString("emergency_name"), rs.getString("emergency_rel"), rs.getString("emergency_num"),
                    rs.getString("blood_type"), rs.getInt("age"), rs.getString("notes"),
                    rs.getString("bp"), rs.getString("hr"), rs.getString("temp"), rs.getString("spo2"),
                    rs.getString("general_appearance"), rs.getString("cardiovascular"),
                    rs.getString("respiratory"), rs.getString("neurological")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return patients;
    }

    public static boolean updatePatientStatus(String id, String newStatus) {
        String sql = "UPDATE patients SET status = ?, admission_date = ? WHERE UPPER(id) = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            String currentTimestamp = java.time.LocalDateTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            stmt.setString(1, newStatus);
            stmt.setString(2, currentTimestamp);
            stmt.setString(3, id.toUpperCase());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public static boolean updatePatientRoomAndStatus(String id, String newRoom, String newStatus) {
        String sql = "UPDATE patients SET room = ?, status = ?, admission_date = ? WHERE UPPER(id) = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            String currentTimestamp = java.time.LocalDateTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            stmt.setString(1, newRoom);
            stmt.setString(2, newStatus);
            stmt.setString(3, currentTimestamp);
            stmt.setString(4, id.toUpperCase());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public static boolean deletePatient(String id) {
        String sql = "DELETE FROM patients WHERE UPPER(id) = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id.toUpperCase());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
    
    public static Patient getPatientById(String id) {
        String sql = "SELECT * FROM patients WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Patient(
                        rs.getString("id"), rs.getString("name"), rs.getString("room"),
                        rs.getString("doctor"), rs.getString("nurse"), rs.getString("status"),
                        rs.getString("admission_date"), rs.getString("address"), rs.getString("contact_number"),
                        rs.getString("allergies"), rs.getString("conditions"), rs.getString("medications"),
                        rs.getString("surgical_history"), rs.getString("family_history"),
                        rs.getString("lifestyle_diet"), rs.getString("medical_management"),
                        rs.getString("diet_breakfast"), rs.getString("diet_lunch"), rs.getString("diet_dinner"),
                        rs.getString("emergency_name"), rs.getString("emergency_rel"), rs.getString("emergency_num"),
                        rs.getString("blood_type"), rs.getInt("age"), rs.getString("notes"),
                        rs.getString("bp"), rs.getString("hr"), rs.getString("temp"), rs.getString("spo2"),
                        rs.getString("general_appearance"), rs.getString("cardiovascular"),
                        rs.getString("respiratory"), rs.getString("neurological")
                    );
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
    
    public static List<Patient> getPatientsByNurse(String nurseName) {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT * FROM patients WHERE UPPER(nurse) = ? ORDER BY id ASC";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, nurseName.toUpperCase().trim());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Patient(
                        rs.getString("id"), rs.getString("name"), rs.getString("room"),
                        rs.getString("doctor"), rs.getString("nurse"), rs.getString("status"),
                        rs.getString("admission_date"), rs.getString("address"), rs.getString("contact_number"),
                        rs.getString("allergies"), rs.getString("conditions"), rs.getString("medications"),
                        rs.getString("surgical_history"), rs.getString("family_history"),
                        rs.getString("lifestyle_diet"), rs.getString("medical_management"),
                        rs.getString("diet_breakfast"), rs.getString("diet_lunch"), rs.getString("diet_dinner"),
                        rs.getString("emergency_name"), rs.getString("emergency_rel"), rs.getString("emergency_num"),
                        rs.getString("blood_type"), rs.getInt("age"), rs.getString("notes"),
                        rs.getString("bp"), rs.getString("hr"), rs.getString("temp"), rs.getString("spo2"),
                        rs.getString("general_appearance"), rs.getString("cardiovascular"),
                        rs.getString("respiratory"), rs.getString("neurological")
                    ));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public static List<Patient> getPatientsByDoctor(String doctorName) {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT * FROM patients WHERE UPPER(doctor) = ? ORDER BY id ASC";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, doctorName.toUpperCase().trim());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Patient(
                        rs.getString("id"), rs.getString("name"), rs.getString("room"),
                        rs.getString("doctor"), rs.getString("nurse"), rs.getString("status"),
                        rs.getString("admission_date"), rs.getString("address"), rs.getString("contact_number"),
                        rs.getString("allergies"), rs.getString("conditions"), rs.getString("medications"),
                        rs.getString("surgical_history"), rs.getString("family_history"),
                        rs.getString("lifestyle_diet"), rs.getString("medical_management"),
                        rs.getString("diet_breakfast"), rs.getString("diet_lunch"), rs.getString("diet_dinner"),
                        rs.getString("emergency_name"), rs.getString("emergency_rel"), rs.getString("emergency_num"),
                        rs.getString("blood_type"), rs.getInt("age"), rs.getString("notes"),
                        rs.getString("bp"), rs.getString("hr"), rs.getString("temp"), rs.getString("spo2"),
                        rs.getString("general_appearance"), rs.getString("cardiovascular"),
                        rs.getString("respiratory"), rs.getString("neurological")
                    ));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public static boolean updatePatientVitals(Patient patient) {
        String sql = "UPDATE patients SET bp = ?, hr = ?, temp = ?, spo2 = ? WHERE UPPER(id) = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, patient.getBp());
            stmt.setString(2, patient.getHr());
            stmt.setString(3, patient.getTemp());
            stmt.setString(4, patient.getSpo2());
            stmt.setString(5, patient.getId().toUpperCase());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
    
    private static String currentPatientName = null;
    private static String currentPatientId = null;

    public static String getCurrentPatientName() {
        return currentPatientName;
    }

    public static String getCurrentPatientId() {
        return currentPatientId;
    }

    public static void setCurrentPatient(String name, String id) {
        currentPatientName = name;
        currentPatientId = id;
    }

    public static void clearCurrentPatient() {
        currentPatientName = null;
        currentPatientId = null;
    }
}