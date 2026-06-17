package Database;

import Models.Appointment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentSQL {

    private static final String URL = "jdbc:mysql://localhost:3306/hospitalmanagement";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static int getLastSequenceNum() {
        String sql = "SELECT appointment_id FROM appointments ORDER BY appointment_id DESC LIMIT 1";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                String rawId = rs.getString("appointment_id");
                return Integer.parseInt(rawId.replace("AP-", ""));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static List<Appointment> getAllAppointments() {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT * FROM appointments ORDER BY appointment_id ASC";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Appointment(
                    rs.getString("appointment_id"),
                    rs.getString("patient_name"),
                    rs.getString("doctor_name"),
                    rs.getString("treatment"),
                    rs.getString("status"),
                    rs.getString("appointment_date") 
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean insertAppointment(Appointment app) {
        String sql = "INSERT INTO appointments (appointment_id, patient_name, doctor_name, treatment, status, appointment_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, app.getAppointmentId());
            stmt.setString(2, app.getPatientName());
            stmt.setString(3, app.getDoctorName());
            stmt.setString(4, app.getTreatment());
            stmt.setString(5, app.getStatus());
            stmt.setString(6, app.getAppointmentDate());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateAppointment(Appointment app) {
        String sql = "UPDATE appointments SET patient_name = ?, doctor_name = ?, treatment = ?, status = ?, appointment_date = ? WHERE appointment_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, app.getPatientName());
            stmt.setString(2, app.getDoctorName());
            stmt.setString(3, app.getTreatment());
            stmt.setString(4, app.getStatus());
            stmt.setString(5, app.getAppointmentDate());
            stmt.setString(6, app.getAppointmentId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateStatus(String id, String newStatus) {
        String sql = "UPDATE appointments SET status = ? WHERE appointment_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, newStatus);
            stmt.setString(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteAppointment(String id) {
        String sql = "DELETE FROM appointments WHERE appointment_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}