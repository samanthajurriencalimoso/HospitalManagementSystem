package Database;

import Models.NurseAppointment;
import java.sql.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NurseAppointmentSQL {
    
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

    public static int saveReport(NurseAppointment report) {
        String sql = "INSERT INTO nurse_appointment_history (patient_name, patient_id, report_data, status, sent_by, sent_to, sent_date) VALUES (?, ?, ?, ?, ?, ?, NOW())";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, report.getPatientName());
            pstmt.setString(2, report.getPatientId());
            pstmt.setString(3, report.getReportData());
            pstmt.setString(4, report.getStatus());
            pstmt.setString(5, report.getSentBy());
            pstmt.setString(6, report.getSentTo());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }

    public static boolean updateReport(NurseAppointment report) {
        String sql = "UPDATE nurse_appointment_history SET patient_name=?, patient_id=?, report_data=? WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, report.getPatientName());
            pstmt.setString(2, report.getPatientId());
            pstmt.setString(3, report.getReportData());
            pstmt.setInt(4, report.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public static boolean sendToDoctor(int id) {
        String sql = "UPDATE nurse_appointment_history SET status='Sent to Doctor', sent_date=NOW() WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public static List<NurseAppointment> getReportsForDoctor() {
        List<NurseAppointment> reports = new ArrayList<>();
        String sql = "SELECT * FROM nurse_appointment_history WHERE status='Sent to Doctor' ORDER BY sent_date DESC";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("sent_date");
                LocalDateTime sentDate = timestamp != null ? timestamp.toLocalDateTime() : null;

                reports.add(new NurseAppointment(
                    rs.getInt("id"),
                    rs.getString("patient_name"),
                    rs.getString("patient_id"),
                    rs.getString("report_data"),
                    rs.getString("status"),
                    rs.getString("sent_by"),
                    rs.getString("sent_to"),
                    sentDate
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return reports;
    }

    public static NurseAppointment getReportById(int id) {
        String sql = "SELECT * FROM nurse_appointment_history WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("sent_date");
                LocalDateTime sentDate = timestamp != null ? timestamp.toLocalDateTime() : null;

                return new NurseAppointment(
                    rs.getInt("id"),
                    rs.getString("patient_name"),
                    rs.getString("patient_id"),
                    rs.getString("report_data"),
                    rs.getString("status"),
                    rs.getString("sent_by"),
                    rs.getString("sent_to"),
                    sentDate
                );
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
    
    public static List<NurseAppointment> getReportsByPatient(String patientName) {
        List<NurseAppointment> reports = new ArrayList<>();
        String sql = "SELECT * FROM nurse_appointment_history WHERE patient_name = ? ORDER BY sent_date DESC";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, patientName);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Timestamp timestamp = rs.getTimestamp("sent_date");
                    LocalDateTime sentDate = timestamp != null ? timestamp.toLocalDateTime() : null;

                    reports.add(new NurseAppointment(
                        rs.getInt("id"),
                        rs.getString("patient_name"),
                        rs.getString("patient_id"),
                        rs.getString("report_data"),
                        rs.getString("status"),
                        rs.getString("sent_by"),
                        rs.getString("sent_to"),
                        sentDate
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

}
