package Database;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MedicalRequestSQL {

    private static final String URL = "jdbc:mysql://localhost:3306/hospitalmanagement";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static boolean addRequestWithBilling(String category, String item, int qty, String status, String requestedBy, double billingAmount) {
        String query = "INSERT INTO medical_requests (category, item_name, quantity, status, requested_by, billing_amount, requested_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, category);
            ps.setString(2, item);
            ps.setInt(3, qty);
            ps.setString(4, status);
            ps.setString(5, requestedBy);
            ps.setDouble(6, billingAmount);
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Object[]> getAllPendingRequests() {
        List<Object[]> list = new ArrayList<>();
        String query = "SELECT request_id, category, item_name, quantity, requested_by, billing_amount, status, requested_at " +
                       "FROM medical_requests WHERE status = 'Pending' ORDER BY requested_at ASC";
        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            int counter = 1;
            while (rs.next()) {
                list.add(new Object[]{
                    counter++,
                    rs.getInt("request_id"),
                    rs.getString("category"),
                    rs.getString("item_name"),
                    rs.getInt("quantity"),
                    rs.getString("requested_by"),
                    String.format("₱%.2f", rs.getDouble("billing_amount")),
                    rs.getString("status"),
                    rs.getTimestamp("requested_at")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public static List<Object[]> getUserRequests(String userId) {
        List<Object[]> list = new ArrayList<>();
        String query = "SELECT request_id, category, item_name, quantity, billing_amount, status, requested_at FROM medical_requests WHERE requested_by = ? ORDER BY requested_at DESC";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            int counter = 1;
            while (rs.next()) {
                list.add(new Object[]{
                    counter++,
                    rs.getInt("request_id"),
                    rs.getString("category"),
                    rs.getString("item_name"),
                    rs.getInt("quantity"),
                    String.format("₱%.2f", rs.getDouble("billing_amount")),
                    rs.getString("status"),
                    rs.getTimestamp("requested_at")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Object[]> getRequestsByUser(String userId) {
        return getUserRequests(userId);
    }

    public static boolean markRequestAsReceived(int requestId, double billingAmount, String adminNotes) {
        String updateMedical = "UPDATE medical_requests SET status = 'Received', billing_amount = ? WHERE request_id = ?";
        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(updateMedical)) {
                ps.setDouble(1, billingAmount);
                ps.setInt(2, requestId);
                ps.executeUpdate();
            }
            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

