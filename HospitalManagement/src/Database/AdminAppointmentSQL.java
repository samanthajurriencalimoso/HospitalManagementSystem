package Database;

import Models.AdminAppointment;
import Models.DoctorAppointment;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AdminAppointmentSQL {
    
    private static final String URL = "jdbc:mysql://localhost:3306/hospitalmanagement";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static int saveReport(AdminAppointment report) {
        String sql = "INSERT INTO admin_appointment_history (doctor_report_id, patient_name, patient_id, report_data, doctor_notes, status, sent_by, sent_to, sent_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW())";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, report.getDoctorReportId());
            pstmt.setString(2, report.getPatientName());
            pstmt.setString(3, report.getPatientId());
            pstmt.setString(4, report.getReportData());
            pstmt.setString(5, report.getDoctorNotes());
            pstmt.setString(6, report.getStatus());
            pstmt.setString(7, report.getSentBy());
            pstmt.setString(8, report.getSentTo());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }

    public static List<DoctorAppointment> getReportsForAdmin() {
        List<DoctorAppointment> reports = new ArrayList<>();
        String sql = "SELECT * FROM doctor_appointment_history WHERE status='Sent to Admin' ORDER BY sent_date DESC";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("sent_date");
                LocalDateTime sentDate = timestamp != null ? timestamp.toLocalDateTime() : null;
                Timestamp approvedTimestamp = rs.getTimestamp("approved_date");
                LocalDateTime approvedDate = approvedTimestamp != null ? approvedTimestamp.toLocalDateTime() : null;

                DoctorAppointment report = new DoctorAppointment(
                    rs.getInt("id"),
                    rs.getInt("nurse_report_id"),
                    rs.getString("patient_name"),
                    rs.getString("patient_id"),
                    rs.getString("report_data"),
                    rs.getString("doctor_notes"),
                    rs.getString("status"),
                    rs.getString("sent_by"),
                    rs.getString("sent_to"),
                    sentDate,
                    rs.getString("approved_by"),
                    rs.getString("approved_role"),
                    approvedDate
                );
                reports.add(report);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return reports;
    }

    public static DoctorAppointment getDoctorReportById(int id) {
        String sql = "SELECT * FROM doctor_appointment_history WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("sent_date");
                LocalDateTime sentDate = timestamp != null ? timestamp.toLocalDateTime() : null;
                Timestamp approvedTimestamp = rs.getTimestamp("approved_date");
                LocalDateTime approvedDate = approvedTimestamp != null ? approvedTimestamp.toLocalDateTime() : null;

                DoctorAppointment report = new DoctorAppointment(
                    rs.getInt("id"),
                    rs.getInt("nurse_report_id"),
                    rs.getString("patient_name"),
                    rs.getString("patient_id"),
                    rs.getString("report_data"),
                    rs.getString("doctor_notes"),
                    rs.getString("status"),
                    rs.getString("sent_by"),
                    rs.getString("sent_to"),
                    sentDate,
                    rs.getString("approved_by"),
                    rs.getString("approved_role"),
                    approvedDate
                );
                return report;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static boolean updateAdminReport(int id, String reportData, String doctorNotes) {
        String sql = "UPDATE admin_appointment_history SET report_data=?, doctor_notes=? WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, reportData);
            pstmt.setString(2, doctorNotes);
            pstmt.setInt(3, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public static boolean finalApprove(int id, String approvedBy, String approvedRole) {
        String sql = "UPDATE admin_appointment_history SET status='Approved', approved_by=?, approved_role=?, approved_date=NOW() WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, approvedBy);
            pstmt.setString(2, approvedRole);
            pstmt.setInt(3, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
}