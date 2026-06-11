package Database;

import Models.DoctorAppointment;
import java.sql.*;

public class DoctorAppointmentSQL {
    
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

    public static int saveReport(DoctorAppointment report) {
        String sql = "INSERT INTO doctor_appointment_history (nurse_report_id, patient_name, patient_id, report_data, status, sent_by, sent_to, sent_date) VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, report.getNurseReportId());
            pstmt.setString(2, report.getPatientName());
            pstmt.setString(3, report.getPatientId());
            pstmt.setString(4, report.getReportData());
            pstmt.setString(5, report.getStatus());
            pstmt.setString(6, report.getSentBy());
            pstmt.setString(7, report.getSentTo());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }

    public static boolean sendToAdmin(int id) {
        String sql = "UPDATE doctor_appointment_history SET status='Sent to Admin', sent_date=NOW() WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
    
    public static boolean updateReportData(int id, String reportData, String doctorNotes) {
        String sql = "UPDATE doctor_appointment_history SET report_data=?, doctor_notes=? WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, reportData);
            pstmt.setString(2, doctorNotes);
            pstmt.setInt(3, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
}