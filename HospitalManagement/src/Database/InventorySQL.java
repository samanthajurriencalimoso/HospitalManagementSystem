package Database;

import Models.Item;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class InventorySQL {
    
    private static final String URL = "jdbc:mysql://localhost:3306/hospitalmanagement";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static boolean insertItem(Item item) {
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

    public static List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM inventory ORDER BY id DESC";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                items.add(new Item(
                    rs.getInt("id"),
                    rs.getString("category"),
                    rs.getString("item"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"),
                    rs.getString("status"),
                    rs.getString("expiry")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
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

    public static boolean decreaseStockById(int itemId, int requestedQty) {
        String selectSql = "SELECT quantity FROM inventory WHERE id = ?";
        String updateSql = "UPDATE inventory SET quantity = ? WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            con.setAutoCommit(false);
            int currentQty = 0;
            try (PreparedStatement stmt = con.prepareStatement(selectSql)) {
                stmt.setInt(1, itemId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    currentQty = rs.getInt("quantity");
                } else {
                    JOptionPane.showMessageDialog(null, "Item not found!");
                    return false;
                }
            }
            if (currentQty < requestedQty) {
                JOptionPane.showMessageDialog(null, "Insufficient stock! Available: " + currentQty);
                return false;
            }
            int newQty = currentQty - requestedQty;
            String newStatus = newQty == 0 ? "Out of Stock" : (newQty < 50 ? "Low Stock" : "Good");
            try (PreparedStatement stmt = con.prepareStatement(updateSql)) {
                stmt.setInt(1, newQty);
                stmt.setInt(2, itemId);
                stmt.executeUpdate();
            }
            con.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int getCurrentStockById(int itemId) {
        String sql = "SELECT quantity FROM inventory WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("quantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static double getPriceById(int itemId) {
        String sql = "SELECT price FROM inventory WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public static List<Object[]> getAvailableItems() {
        List<Object[]> items = new ArrayList<>();
        String sql = "SELECT id, item, quantity, expiry FROM inventory WHERE quantity > 0 ORDER BY expiry ASC";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                items.add(new Object[]{
                    rs.getInt("id"),
                    rs.getString("item"),
                    rs.getInt("quantity"),
                    rs.getString("expiry")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
}

