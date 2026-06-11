package Models;

public class LogisticsOrder {
    private String orderId;
    private String item;
    private double amount;
    private String status;
    private boolean isCheckedIn;

    public LogisticsOrder(String orderId, String item, double amount, String status, boolean isCheckedIn) {
        this.orderId = orderId;
        this.item = item;
        this.amount = amount;
        this.status = status;
        this.isCheckedIn = isCheckedIn;
    }

    public LogisticsOrder(String orderId, String item, double amount, String status) {
        this(orderId, item, amount, status, false);
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getItem() { return item; }
    public void setItem(String item) { this.item = item; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public boolean isCheckedIn() { return isCheckedIn; }
    public void setCheckedIn(boolean isCheckedIn) { this.isCheckedIn = isCheckedIn; }
}