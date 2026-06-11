package Database;

import Models.NurseReport;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NurseReportsSQL {

    private static Connection getConnection() throws SQLException {
        String url  = "jdbc:mysql://localhost:3306/hospitalmanagement";
        String user = "root";
        String pass = "";
        return DriverManager.getConnection(url, user, pass);
    }

    public static int getLastSequenceNum() {
        String sql = "SELECT MAX(CAST(SUBSTRING(report_id, 4) AS UNSIGNED)) "
                   + "FROM nurse_reports WHERE report_id LIKE 'NR-%'";
        try (Connection conn = getConnection();
             Statement  stmt = conn.createStatement();
             ResultSet  rs   = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static NurseReport getById(String reportId) {
        String sql = "SELECT * FROM nurse_reports WHERE report_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, reportId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static NurseReport getLatestByType(String reportType) {
        String sql = "SELECT * FROM nurse_reports "
                   + "WHERE report_type = ? "
                   + "ORDER BY report_date DESC, created_at DESC LIMIT 1";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, reportType);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean insertReport(NurseReport report) {
        String sql = "INSERT INTO nurse_reports "
                   + "(report_id, report_type, patient_name, nurse_name, status, report_date, notes) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, report.getReportId());
            ps.setString(2, report.getReportType());
            ps.setString(3, report.getPatientName());
            ps.setString(4, report.getNurseName());
            ps.setString(5, report.getStatus());
            ps.setString(6, report.getReportDate());
            ps.setString(7, report.getNotes());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<NurseReport> getAllReports() {
        List<NurseReport> list = new ArrayList<>();
        String sql = "SELECT * FROM nurse_reports ORDER BY report_date DESC, created_at DESC";
        try (Connection conn = getConnection();
             Statement  stmt = conn.createStatement();
             ResultSet  rs   = stmt.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<NurseReport> getReportsByType(String reportType) {
        List<NurseReport> list = new ArrayList<>();
        String sql = "SELECT * FROM nurse_reports WHERE report_type = ? ORDER BY report_date DESC";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, reportType);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean updateReport(NurseReport report) {
        String sql = "UPDATE nurse_reports "
                   + "SET report_type = ?, patient_name = ?, nurse_name = ?, "
                   + "    status = ?, report_date = ?, notes = ? "
                   + "WHERE report_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, report.getReportType());
            ps.setString(2, report.getPatientName());
            ps.setString(3, report.getNurseName());
            ps.setString(4, report.getStatus());
            ps.setString(5, report.getReportDate());
            ps.setString(6, report.getNotes());
            ps.setString(7, report.getReportId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateStatus(String reportId, String newStatus) {
        String sql = "UPDATE nurse_reports SET status = ? WHERE report_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setString(2, reportId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteReport(String reportId) {
        String sql = "DELETE FROM nurse_reports WHERE report_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, reportId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static NurseReport mapRow(ResultSet rs) throws SQLException {
        return new NurseReport(
            rs.getString("report_id"),
            rs.getString("report_type"),
            rs.getString("patient_name"),
            rs.getString("nurse_name"),
            rs.getString("status"),
            rs.getString("report_date"),
            rs.getString("notes")
        );
    }
}