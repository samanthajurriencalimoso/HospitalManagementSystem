package Database;

import Models.AdminAppointment;
import Models.DoctorAppointment;
import java.sql.*;
import java.time.LocalDateTime;
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
                DoctorAppointment app = new DoctorAppointment(
                    rs.getInt("id"),
                    rs.getInt("nurse_report_id"),
                    rs.getString("patient_name"),
                    rs.getString("patient_id"),
                    rs.getString("report_data"),
                    rs.getString("doctor_notes"),
                    rs.getString("status"),
                    rs.getString("sent_by"),
                    rs.getString("sent_to"),
                    rs.getTimestamp("sent_date") != null ? rs.getTimestamp("sent_date").toLocalDateTime() : null,
                    rs.getString("approved_by"),
                    rs.getString("approved_role"),
                    rs.getTimestamp("approved_date") != null ? rs.getTimestamp("approved_date").toLocalDateTime() : null
                );
                list.add(app);
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
                    rs.getString("doctor_notes"),
                    rs.getString("status"),
                    rs.getString("sent_by"),
                    rs.getString("sent_to"),
                    rs.getTimestamp("sent_date") != null ? rs.getTimestamp("sent_date").toLocalDateTime() : null,
                    rs.getString("approved_by"),
                    rs.getString("approved_role"),
                    rs.getTimestamp("approved_date") != null ? rs.getTimestamp("approved_date").toLocalDateTime() : null
                );
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static boolean approveReport(int doctorReportId, String approvedBy) {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            
            String selectSql = "SELECT * FROM doctor_appointment_history WHERE id = ?";
            PreparedStatement selectStmt = conn.prepareStatement(selectSql);
            selectStmt.setInt(1, doctorReportId);
            ResultSet rs = selectStmt.executeQuery();
            
            if (!rs.next()) {
                return false;
            }
            
            String insertSql = "INSERT INTO admin_appointment_history (doctor_report_id, patient_name, patient_id, report_data, doctor_notes, status, sent_by, sent_to, sent_date, approved_by, approved_role, approved_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, ?, NOW())";
            pstmt1 = conn.prepareStatement(insertSql);
            pstmt1.setInt(1, doctorReportId);
            pstmt1.setString(2, rs.getString("patient_name"));
            pstmt1.setString(3, rs.getString("patient_id"));
            pstmt1.setString(4, rs.getString("report_data"));
            pstmt1.setString(5, rs.getString("doctor_notes"));
            pstmt1.setString(6, "Approved by Admin");
            pstmt1.setString(7, "Admin");
            pstmt1.setString(8, "System");
            pstmt1.setString(9, approvedBy);
            pstmt1.setString(10, "Admin");
            pstmt1.executeUpdate();
            
            String updateSql = "UPDATE doctor_appointment_history SET status='Approved by Admin', approved_by=?, approved_role='Admin', approved_date=NOW() WHERE id=?";
            pstmt2 = conn.prepareStatement(updateSql);
            pstmt2.setString(1, approvedBy);
            pstmt2.setInt(2, doctorReportId);
            pstmt2.executeUpdate();
            
            conn.commit();
            return true;
            
        } catch (SQLException e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            e.printStackTrace();
            return false;
        } finally {
            try { if (pstmt1 != null) pstmt1.close(); } catch (SQLException e) {}
            try { if (pstmt2 != null) pstmt2.close(); } catch (SQLException e) {}
            try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
    }

    public static boolean rejectReport(int doctorReportId, String reason) {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            
            String selectSql = "SELECT * FROM doctor_appointment_history WHERE id = ?";
            PreparedStatement selectStmt = conn.prepareStatement(selectSql);
            selectStmt.setInt(1, doctorReportId);
            ResultSet rs = selectStmt.executeQuery();
            
            if (!rs.next()) {
                return false;
            }
            
            String insertSql = "INSERT INTO admin_appointment_history (doctor_report_id, patient_name, patient_id, report_data, doctor_notes, status, sent_by, sent_to, sent_date, approved_by, approved_role, approved_date, return_reason) VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, ?, NOW(), ?)";
            pstmt1 = conn.prepareStatement(insertSql);
            pstmt1.setInt(1, doctorReportId);
            pstmt1.setString(2, rs.getString("patient_name"));
            pstmt1.setString(3, rs.getString("patient_id"));
            pstmt1.setString(4, rs.getString("report_data"));
            pstmt1.setString(5, rs.getString("doctor_notes"));
            pstmt1.setString(6, "Returned by Admin");
            pstmt1.setString(7, "Admin");
            pstmt1.setString(8, "Doctor");
            pstmt1.setString(9, "Admin");
            pstmt1.setString(10, "Admin");
            pstmt1.setString(11, reason);
            pstmt1.executeUpdate();
            
            String updateSql = "UPDATE doctor_appointment_history SET status='Returned by Admin', return_reason=?, returned_by='Admin', returned_date=NOW() WHERE id=?";
            pstmt2 = conn.prepareStatement(updateSql);
            pstmt2.setString(1, reason);
            pstmt2.setInt(2, doctorReportId);
            pstmt2.executeUpdate();
            
            conn.commit();
            return true;
            
        } catch (SQLException e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            e.printStackTrace();
            return false;
        } finally {
            try { if (pstmt1 != null) pstmt1.close(); } catch (SQLException e) {}
            try { if (pstmt2 != null) pstmt2.close(); } catch (SQLException e) {}
            try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
    }

    public static List<DoctorAppointment> getAllReports() {
        List<DoctorAppointment> list = new ArrayList<>();
        String sql = "SELECT * FROM doctor_appointment_history ORDER BY sent_date DESC";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                DoctorAppointment app = new DoctorAppointment(
                    rs.getInt("id"),
                    rs.getInt("nurse_report_id"),
                    rs.getString("patient_name"),
                    rs.getString("patient_id"),
                    rs.getString("report_data"),
                    rs.getString("doctor_notes"),
                    rs.getString("status"),
                    rs.getString("sent_by"),
                    rs.getString("sent_to"),
                    rs.getTimestamp("sent_date") != null ? rs.getTimestamp("sent_date").toLocalDateTime() : null,
                    rs.getString("approved_by"),
                    rs.getString("approved_role"),
                    rs.getTimestamp("approved_date") != null ? rs.getTimestamp("approved_date").toLocalDateTime() : null
                );
                list.add(app);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
    
    public static List<AdminAppointment> getAdminHistoryForReport(int doctorReportId) {
        List<AdminAppointment> list = new ArrayList<>();
        String sql = "SELECT * FROM admin_appointment_history WHERE doctor_report_id = ? ORDER BY sent_date DESC";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, doctorReportId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                AdminAppointment app = new AdminAppointment(
                    rs.getInt("id"),
                    rs.getInt("doctor_report_id"),
                    rs.getString("patient_name"),
                    rs.getString("patient_id"),
                    rs.getString("report_data"),
                    rs.getString("doctor_notes"),
                    rs.getString("status"),
                    rs.getString("sent_by"),
                    rs.getString("sent_to"),
                    rs.getTimestamp("sent_date") != null ? rs.getTimestamp("sent_date").toLocalDateTime() : null,
                    rs.getString("approved_by"),
                    rs.getString("approved_role"),
                    rs.getTimestamp("approved_date") != null ? rs.getTimestamp("approved_date").toLocalDateTime() : null
                );
                list.add(app);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}
