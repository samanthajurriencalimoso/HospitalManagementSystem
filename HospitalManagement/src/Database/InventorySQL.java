package Database;

import Models.InventoryItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventorySQL {
    
    private static final String URL = "jdbc:mysql://localhost:3306/hospitalmanagement";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static boolean insertItem(InventoryItem item) {
        String sql = "INSERT INTO inventory (category, item, quantity, price, status, expiry) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, item.getCategory());
            stmt.setString(2, item.getItem());
            stmt.setInt(3, item.getQuantity());
            stmt.setDouble(4, item.getPrice());
            stmt.setString(5, item.getStatus());
            stmt.setString(6, item.getExpiry());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<InventoryItem> getAllItems() {
        List<InventoryItem> items = new ArrayList<>();
        String sql = "SELECT * FROM inventory ORDER BY id DESC";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                InventoryItem item = new InventoryItem(
                    rs.getInt("id"),
                    rs.getString("category"),
                    rs.getString("item"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"),
                    rs.getString("status"),
                    rs.getString("expiry")
                );
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public static boolean updateItem(InventoryItem item) {
        String sql = "UPDATE inventory SET category = ?, item = ?, quantity = ?, price = ?, status = ?, expiry = ? WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, item.getCategory());
            stmt.setString(2, item.getItem());
            stmt.setInt(3, item.getQuantity());
            stmt.setDouble(4, item.getPrice());
            stmt.setString(5, item.getStatus());
            stmt.setString(6, item.getExpiry());
            stmt.setInt(7, item.getId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateQuantityAndStatus(int id, int newQty, String newStatus) {
        String sql = "UPDATE inventory SET quantity = ?, status = ? WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, newQty);
            stmt.setString(2, newStatus);
            stmt.setInt(3, id);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteItem(int id) {
        String sql = "DELETE FROM inventory WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}