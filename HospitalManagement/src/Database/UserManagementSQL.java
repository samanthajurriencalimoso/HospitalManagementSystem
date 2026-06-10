package Database;

import Models.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserManagementSQL {
    
    public static Employee currentEmployee;

    private static final String URL = "jdbc:mysql://localhost:3306/hospitalmanagement";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static String getNextIdByRole(String rolePrefix) {
        String sql = "SELECT id FROM employees WHERE id LIKE ? ORDER BY id DESC LIMIT 1";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, rolePrefix + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String lastId = rs.getString("id");
                    String numericPart = lastId.substring(rolePrefix.length());
                    try {
                        int nextNum = Integer.parseInt(numericPart) + 1;
                        return rolePrefix + String.format("%03d", nextNum);
                    } catch (NumberFormatException nfe) {
                        return rolePrefix + "001";
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rolePrefix + "001";
    }

    public static boolean insertEmployee(Employee emp) {
        String sql = "INSERT INTO employees (name, id, age, role, status, department, email, phone, username, password, profile_image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, emp.getName());
            stmt.setString(2, emp.getId().toUpperCase()); 
            stmt.setInt(3, emp.getAge());
            stmt.setString(4, emp.getRole());
            stmt.setString(5, emp.getStatus());
            stmt.setString(6, emp.getDepartment());
            stmt.setString(7, emp.getEmail());
            stmt.setString(8, emp.getPhone());
            stmt.setString(9, emp.getUsername());
            stmt.setString(10, emp.getPassword());
            stmt.setString(11, emp.getProfileImage());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees ORDER BY id ASC"; 
        
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Employee emp = new Employee(
                    rs.getString("name"),
                    rs.getString("id"),
                    rs.getInt("age"),
                    rs.getString("role"),
                    rs.getString("status"),
                    rs.getString("department"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("profile_image")
                );
                employees.add(emp);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public static boolean updateEmployee(Employee emp) {
        String sql = "UPDATE employees SET name = ?, age = ?, role = ?, status = ?, department = ?, email = ?, phone = ?, profile_image = ? WHERE UPPER(id) = ?";
        
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, emp.getName());
            stmt.setInt(2, emp.getAge());
            stmt.setString(3, emp.getRole());
            stmt.setString(4, emp.getStatus());
            stmt.setString(5, emp.getDepartment());
            stmt.setString(6, emp.getEmail());
            stmt.setString(7, emp.getPhone());
            stmt.setString(8, emp.getProfileImage());
            stmt.setString(9, emp.getId().toUpperCase()); 
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateStatus(String id, String newStatus) {
        String sql = "UPDATE employees SET status = ? WHERE UPPER(id) = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, newStatus);
            stmt.setString(2, id.toUpperCase());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteEmployee(String id) {
        String sql = "DELETE FROM employees WHERE UPPER(id) = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id.toUpperCase());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static java.util.List<String> getActiveEmployeeNamesByRole(String role) {
        java.util.List<String> names = new java.util.ArrayList<>();
        String sql = "SELECT name FROM employees WHERE UPPER(role) = ? AND UPPER(status) = 'ACTIVE' ORDER BY name ASC";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, role.toUpperCase());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    names.add(rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return names;
    }

    public static boolean resetPassword(String username, String email, String newPassword) {
        String checkSql = "SELECT COUNT(*) FROM employees WHERE username = ? AND email = ? AND UPPER(status) = 'ACTIVE'";
        String updateSql = "UPDATE employees SET password = ? WHERE username = ? AND email = ? AND UPPER(status) = 'ACTIVE'";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement checkStmt = con.prepareStatement(checkSql)) {
                checkStmt.setString(1, username);
                checkStmt.setString(2, email);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) == 0) return false;
                }
            }
            try (PreparedStatement updateStmt = con.prepareStatement(updateSql)) {
                updateStmt.setString(1, newPassword);
                updateStmt.setString(2, username);
                updateStmt.setString(3, email);
                return updateStmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}