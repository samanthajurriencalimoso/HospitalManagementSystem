package Models;

public class Employee {
    private String name;
    private String id;
    private int age;
    private String role;
    private String status;
    private String department;
    private String email;
    private String phone;
    private String username;
    private String password;
    private String profileImage; // New Field

    // Constructor
    public Employee(String name, String id, int age, String role, String status, String department, String email, String phone, String username, String password, String profileImage) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.role = role;
        this.status = status;
        this.department = department;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.profileImage = profileImage;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getProfileImage() { return profileImage; }
    public void setProfileImage(String profileImage) { this.profileImage = profileImage; }
}