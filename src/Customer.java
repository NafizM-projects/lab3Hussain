public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String state;
    private double purchaseTotal;

    public Customer(int customerId, String firstName, String lastName, String state, double purchaseTotal) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.state = state;
        this.purchaseTotal = purchaseTotal;
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

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", state='" + state + '\'' +
                ", purchaseTotal=" + purchaseTotal +
                '}';
    }
}