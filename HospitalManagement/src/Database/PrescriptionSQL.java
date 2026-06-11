package Database;

import Models.Prescription;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionSQL {
    private static final String URL = "jdbc:mysql://localhost:3306/hospitalmanagement";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver missing: " + e.getMessage());
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static List<Prescription> getAllPrescriptions() {
        List<Prescription> list = new ArrayList<>();
        String sql = "SELECT * FROM prescriptions ORDER BY prescription_id DESC";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Prescription(
                    rs.getInt("prescription_id"),
                    rs.getString("patient_id"),
                    rs.getString("patient_name"),
                    rs.getString("drug_name"),
                    rs.getString("dosage"),
                    rs.getInt("frequency"),
                    rs.getInt("duration"),
                    rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Fetch Failure: " + e.getMessage());
        }
        return list;
    }

    public static boolean addPrescription(Prescription p) {
        String sql = "INSERT INTO prescriptions (patient_id, patient_name, drug_name, dosage, frequency, duration) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getPatientId());
            ps.setString(2, p.getPatientName());
            ps.setString(3, p.getDrugName());
            ps.setString(4, p.getDosage());
            ps.setInt(5, p.getFrequency());
            ps.setInt(6, p.getDuration());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Insert Failure: " + e.getMessage());
            return false;
        }
    }

    public static boolean updatePrescription(Prescription p) {
        String sql = "UPDATE prescriptions SET patient_id = ?, patient_name = ?, drug_name = ?, dosage = ?, frequency = ?, duration = ? WHERE prescription_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getPatientId());
            ps.setString(2, p.getPatientName());
            ps.setString(3, p.getDrugName());
            ps.setString(4, p.getDosage());
            ps.setInt(5, p.getFrequency());
            ps.setInt(6, p.getDuration());
            ps.setInt(7, p.getPrescriptionId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Update Failure: " + e.getMessage());
            return false;
        }
    }

    public static boolean deletePrescription(int id) {
        String sql = "DELETE FROM prescriptions WHERE prescription_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Delete Failure: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean updatePrescriptionStatus(int prescriptionId, String newStatus) {
        String sql = "UPDATE prescriptions SET status = ? WHERE prescription_id = ?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setString(1, newStatus);
            stmt.setInt(2, prescriptionId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Update Failure: " + e.getMessage());
            return false;
        }
    }
}