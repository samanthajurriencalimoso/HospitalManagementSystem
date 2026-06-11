package Database;

import Models.LogisticsOrder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class LogisticsSQL {
    
    private static final String URL = "jdbc:mysql://localhost:3306/hospitalmanagement";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static boolean insertOrder(LogisticsOrder order) {
        String sql = "INSERT INTO logistics (order_id, item, amount, status, is_checked_in) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, order.getOrderId());
            stmt.setString(2, order.getItem());
            stmt.setDouble(3, order.getAmount());
            stmt.setString(4, order.getStatus());
            stmt.setInt(5, order.isCheckedIn() ? 1 : 0);
            
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
                boolean checkedIn = rs.getInt("is_checked_in") == 1;

                LogisticsOrder order = new LogisticsOrder(
                    rs.getString("order_id"),
                    rs.getString("item"),
                    rs.getDouble("amount"),
                    rs.getString("status"),
                    checkedIn
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
    String checkAmountSql = "SELECT amount, item FROM logistics WHERE order_id = ?";
    String updateLogisticsSql = "UPDATE logistics SET status = ? WHERE order_id = ?";
    
    try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
        con.setAutoCommit(false);
        double finalAmount = 0.00;
        String fullItemName = "";

        try (PreparedStatement stmt = con.prepareStatement(checkAmountSql)) {
            stmt.setString(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    finalAmount = rs.getDouble("amount");
                    fullItemName = rs.getString("item");
                }
            }
        }

        if (newStatus.equalsIgnoreCase("Delivered") && finalAmount <= 0.00) {
            con.rollback();
            JOptionPane.showMessageDialog(null, 
                "Validation Error: You cannot mark this request as Delivered while the amount is 0.00!\n" +
                "Please use 'Edit Order' to input the correct billing amount first.", 
                "Missing Invoice Billing", JOptionPane.ERROR_MESSAGE);
            return false; 
        }

        try (PreparedStatement stmt = con.prepareStatement(updateLogisticsSql)) {
            stmt.setString(1, newStatus);
            stmt.setString(2, orderId);
            stmt.executeUpdate();
        }

        if (newStatus.equalsIgnoreCase("Delivered")) {
            if (!fullItemName.isEmpty() && fullItemName.contains(" (Qty:")) {
                String pureItemName = fullItemName.substring(0, fullItemName.indexOf(" (Qty:"));
                
                String updateDocSql = "UPDATE medical_requests SET status = 'Received', billing_amount = ? " +
                                      "WHERE item_name = ? AND status = 'Pending'";
                try (PreparedStatement stmt = con.prepareStatement(updateDocSql)) {
                    stmt.setDouble(1, finalAmount);
                    stmt.setString(2, pureItemName);
                    stmt.executeUpdate();
                }
            }
        }

        con.commit();
        return true;
        
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
    
    public static boolean markAsCheckedIn(String orderId) {
        String query = "UPDATE logistics SET is_checked_in = 1 WHERE order_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, orderId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean insertOrderFromDoctorRequest(String item, int quantity) {
        int nextSeq = getLastOrderSequenceNum() + 1;
        String generatedOrderId = "ORD-" + nextSeq;
        
        String formattedItemName = item + " (Qty: " + quantity + ")";
        
        double defaultAmount = 0.0;
        String defaultStatus = "Pending";
        int isCheckedInBit = 0;

        String sql = "INSERT INTO logistics (order_id, item, amount, status, is_checked_in) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, generatedOrderId);
            stmt.setString(2, formattedItemName);
            stmt.setDouble(3, defaultAmount);
            stmt.setString(4, defaultStatus);
            stmt.setInt(5, isCheckedInBit);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}