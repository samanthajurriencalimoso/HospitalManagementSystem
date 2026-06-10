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
        String sql = "INSERT INTO patients (id, name, room, doctor, nurse, status, admission_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, patient.getId().toUpperCase());
            stmt.setString(2, patient.getName());
            stmt.setString(3, patient.getRoom());
            stmt.setString(4, patient.getDoctor());
            stmt.setString(5, patient.getNurse());
            stmt.setString(6, patient.getStatus());
            stmt.setString(7, patient.getAdmissionDate());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updatePatient(Patient patient) {
        String sql = "UPDATE patients SET name = ?, room = ?, doctor = ?, nurse = ?, status = ? WHERE UPPER(id) = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, patient.getName());
            stmt.setString(2, patient.getRoom());
            stmt.setString(3, patient.getDoctor());
            stmt.setString(4, patient.getNurse());
            stmt.setString(5, patient.getStatus());
            stmt.setString(6, patient.getId().toUpperCase());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients ORDER BY id ASC";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                patients.add(new Patient(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("room"),
                    rs.getString("doctor"),
                    rs.getString("nurse"),
                    rs.getString("status"),
                    rs.getString("admission_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deletePatient(String id) {
        String sql = "DELETE FROM patients WHERE UPPER(id) = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, id.toUpperCase());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}