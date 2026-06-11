package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalRequestSQL {

    private static Connection getConnection() throws SQLException {
        String url  = "jdbc:mysql://localhost:3306/hospitalmanagement";
        String user = "root";
        String pass = "";
        return DriverManager.getConnection(url, user, pass);
    }

    public static void ensureRequestedByColumn() {
        String sql = "ALTER TABLE medical_requests ADD COLUMN IF NOT EXISTS " +
                     "requested_by VARCHAR(20) NOT NULL DEFAULT ''";
        try (Connection conn = getConnection();
             Statement st = conn.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean addRequest(String category, String item,
                                     int qty, String status, String requestedBy) {
        String query = "INSERT INTO medical_requests " +
                       "(category, item_name, quantity, status, requested_by) " +
                       "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, category);
            ps.setString(2, item);
            ps.setInt(3, qty);
            ps.setString(4, status);
            ps.setString(5, requestedBy);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateQuantity(int id, int newQty, String newStatus) {
        String query = "UPDATE medical_requests SET quantity = ?, status = ? " + "WHERE request_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, newQty);
            ps.setString(2, newStatus);
            ps.setInt(3, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteRequest(int id) {
        String query = "DELETE FROM medical_requests WHERE request_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Object[]> getRequestsByUser(String employeeId) {
        List<Object[]> list = new ArrayList<>();
        String query = "SELECT request_id, category, item_name, quantity, " +
                       "billing_amount, status " +
                       "FROM medical_requests " +
                       "WHERE requested_by = ? " +
                       "ORDER BY request_id ASC";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, employeeId);
            try (ResultSet rs = ps.executeQuery()) {
                int counter = 1;
                while (rs.next()) {
                    list.add(new Object[]{
                        counter++,
                        rs.getString("category"),
                        rs.getString("item_name"),
                        rs.getInt("quantity"),
                        String.format("$%.2f", rs.getDouble("billing_amount")),
                        rs.getString("status"),
                        rs.getInt("request_id")
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Object[]> getAllRequests() {
        List<Object[]> list = new ArrayList<>();
        String query = "SELECT request_id, category, item_name, quantity, " +
                       "billing_amount, status FROM medical_requests " +
                       "ORDER BY request_id ASC";
        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            int counter = 1;
            while (rs.next()) {
                list.add(new Object[]{
                    counter++,
                    rs.getString("category"),
                    rs.getString("item_name"),
                    rs.getInt("quantity"),
                    String.format("$%.2f", rs.getDouble("billing_amount")),
                    rs.getString("status"),
                    rs.getInt("request_id")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}