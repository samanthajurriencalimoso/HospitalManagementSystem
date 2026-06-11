package Database;

import Models.DoctorAppointment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminReportSQL {

    private static final String URL = "jdbc:mysql://localhost:3306/hospitalmanagement";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection getConnection() throws SQLException {
        try { Class.forName("com.mysql.cj.jdbc.Driver"); }
        catch (ClassNotFoundException e) { e.printStackTrace(); }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static List<DoctorAppointment> getReportsForAdmin() {
        List<DoctorAppointment> list = new ArrayList<>();
        String sql = "SELECT * FROM doctor_appointment_history WHERE status = 'Sent to Admin' ORDER BY sent_date DESC";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(new DoctorAppointment(
                    rs.getInt("id"),
                    rs.getInt("nurse_report_id"),
                    rs.getString("patient_name"),
                    rs.getString("patient_id"),
                    rs.getString("report_data"),
                    rs.getString("status"),
                    rs.getString("sent_by"),
                    rs.getString("sent_to")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public static DoctorAppointment getReportById(int id) {
        String sql = "SELECT * FROM doctor_appointment_history WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new DoctorAppointment(
                    rs.getInt("id"),
                    rs.getInt("nurse_report_id"),
                    rs.getString("patient_name"),
                    rs.getString("patient_id"),
                    rs.getString("report_data"),
                    rs.getString("status"),
                    rs.getString("sent_by"),
                    rs.getString("sent_to")
                );
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static boolean approveReport(int id, String approvedBy) {
        String sql = "UPDATE doctor_appointment_history SET status='Approved by Admin', sent_to=?, sent_date=NOW() WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, approvedBy);
            pstmt.setInt(2, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public static boolean rejectReport(int id, String reason) {
        String sql = "UPDATE doctor_appointment_history SET status='Returned to Doctor', doctor_notes=? WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, reason);
            pstmt.setInt(2, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public static List<DoctorAppointment> getAllReports() {
        List<DoctorAppointment> list = new ArrayList<>();
        String sql = "SELECT * FROM doctor_appointment_history ORDER BY sent_date DESC";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(new DoctorAppointment(
                    rs.getInt("id"),
                    rs.getInt("nurse_report_id"),
                    rs.getString("patient_name"),
                    rs.getString("patient_id"),
                    rs.getString("report_data"),
                    rs.getString("status"),
                    rs.getString("sent_by"),
                    rs.getString("sent_to")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}