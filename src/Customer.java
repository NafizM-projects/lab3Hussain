/** Project: Solo Lab3 Database
 * Purpose Details: makes customer a separate entity accessible to both the cruds so i didn't hve to write it again
 * Course: IST 242
 * Author: Nafiz Hussain
 * Date Developed: 2/22/2026
 * Last Date Changed: 3/1/2026
 * Rev: 3

 */


public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String state;
    private double purchaseTotal;
    private String purchaseType;  // New field: e.g., "Electronics", "Clothing", "Groceries", etc.
    private String paymentMethod; // New field: "Cash" or "Card"

    public Customer(int customerId, String firstName, String lastName, String state,
                    double purchaseTotal, String purchaseType, String paymentMethod) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.state = state;
        this.purchaseTotal = purchaseTotal;
        this.purchaseType = purchaseType;
        this.paymentMethod = paymentMethod;
    }

    // Getters
    public int getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getState() {
        return state;
    }

    public double getPurchaseTotal() {
        return purchaseTotal;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    // Setters
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPurchaseTotal(double purchaseTotal) {
        this.purchaseTotal = purchaseTotal;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", state='" + state + '\'' +
                ", purchaseTotal=" + purchaseTotal +
                ", purchaseType='" + purchaseType + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }
}
