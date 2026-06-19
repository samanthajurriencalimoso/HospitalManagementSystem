package Database;

import Models.DoctorAppointment;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        String sql = "INSERT INTO doctor_appointment_history (nurse_report_id, patient_name, patient_id, report_data, doctor_notes, status, sent_by, sent_to, sent_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW())";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, report.getNurseReportId());
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

    public static boolean sendToAdmin(int id) {
        String sql = "UPDATE doctor_appointment_history SET status='Sent to Admin', sent_date=NOW() WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public static DoctorAppointment getReportById(int id) {
        String sql = "SELECT * FROM doctor_appointment_history WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Timestamp sentTimestamp = rs.getTimestamp("sent_date");
                LocalDateTime sentDate = sentTimestamp != null ? sentTimestamp.toLocalDateTime() : null;
                Timestamp approvedTimestamp = rs.getTimestamp("approved_date");
                LocalDateTime approvedDate = approvedTimestamp != null ? approvedTimestamp.toLocalDateTime() : null;
                
                return new DoctorAppointment(
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
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static List<DoctorAppointment> getReportsForAdmin() {
        List<DoctorAppointment> reports = new ArrayList<>();
        String sql = "SELECT * FROM doctor_appointment_history WHERE status='Sent to Admin' ORDER BY sent_date DESC";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Timestamp sentTimestamp = rs.getTimestamp("sent_date");
                LocalDateTime sentDate = sentTimestamp != null ? sentTimestamp.toLocalDateTime() : null;
                Timestamp approvedTimestamp = rs.getTimestamp("approved_date");
                LocalDateTime approvedDate = approvedTimestamp != null ? approvedTimestamp.toLocalDateTime() : null;
                
                reports.add(new DoctorAppointment(
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
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return reports;
    }
    
    public static List<DoctorAppointment> getReturnedReportsForDoctor() {
        List<DoctorAppointment> reports = new ArrayList<>();
        String sql = "SELECT * FROM doctor_appointment_history WHERE status='Returned by Admin' ORDER BY returned_date DESC";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Timestamp sentTimestamp = rs.getTimestamp("sent_date");
                LocalDateTime sentDate = sentTimestamp != null ? sentTimestamp.toLocalDateTime() : null;
                Timestamp approvedTimestamp = rs.getTimestamp("approved_date");
                LocalDateTime approvedDate = approvedTimestamp != null ? approvedTimestamp.toLocalDateTime() : null;

                reports.add(new DoctorAppointment(
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
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return reports;
    }

        public static String getReturnReason(int id) {
            String sql = "SELECT return_reason FROM doctor_appointment_history WHERE id=?";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return rs.getString("return_reason");
                }
            } catch (SQLException e) { e.printStackTrace(); }
            return null;
        }
}


