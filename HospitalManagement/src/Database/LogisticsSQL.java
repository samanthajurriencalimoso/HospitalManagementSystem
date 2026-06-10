package Database;

import Models.LogisticsOrder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LogisticsSQL {
    
    private static final String URL = "jdbc:mysql://localhost:3306/hospitalmanagement";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static boolean insertOrder(LogisticsOrder order) {
        String sql = "INSERT INTO logistics (order_id, item, amount, status) VALUES (?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, order.getOrderId());
            stmt.setString(2, order.getItem());
            stmt.setDouble(3, order.getAmount());
            stmt.setString(4, order.getStatus());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<LogisticsOrder> getAllOrders() {
        List<LogisticsOrder> orders = new ArrayList<>();
        String sql = "SELECT * FROM logistics ORDER BY order_id DESC";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                LogisticsOrder order = new LogisticsOrder(
                    rs.getString("order_id"),
                    rs.getString("item"),
                    rs.getDouble("amount"),
                    rs.getString("status")
                );
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public static boolean updateOrder(LogisticsOrder order) {
        String sql = "UPDATE logistics SET item = ?, amount = ? WHERE order_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, order.getItem());
            stmt.setDouble(2, order.getAmount());
            stmt.setString(3, order.getOrderId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateStatus(String orderId, String newStatus) {
        String sql = "UPDATE logistics SET status = ? WHERE order_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, newStatus);
            stmt.setString(2, orderId);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteOrder(String orderId) {
        String sql = "DELETE FROM logistics WHERE order_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, orderId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static int getLastOrderSequenceNum() {
        String sql = "SELECT order_id FROM logistics WHERE order_id LIKE 'ORD-%' ORDER BY order_id DESC LIMIT 1";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                String rawId = rs.getString("order_id");
                return Integer.parseInt(rawId.replace("ORD-", ""));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 10000; 
    }
}