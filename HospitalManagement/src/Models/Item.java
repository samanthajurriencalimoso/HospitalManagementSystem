package Models;

public class Item {
    private int id;
    private String category;
    private String item;
    private int quantity;
    private double price;
    private String status;
    private String expiry;

    public Item(String category, String item, int quantity, double price, String status, String expiry) {
        this.category = category;
        this.item = item;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.expiry = expiry;
    }
    
    public Item(int id, String category, String item, int quantity, double price, String status, String expiry) {
        this.id = id;
        this.category = category;
        this.item = item;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.expiry = expiry;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getItem() { return item; }
    public void setItem(String item) { this.item = item; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getExpiry() { return expiry; }
    public void setExpiry(String expiry) { this.expiry = expiry; }
}