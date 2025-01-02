package domain;

public class ElectronicProduct extends Product {
    private String warrantyPeriod;

    public ElectronicProduct(String name, double price, int stock, String warrantyPeriod) {
        super(name, price, stock);
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public void displayInfo() {
        System.out.println("Electronic Product: " + getName() + " - Price: " + getPrice() + " - Stock: " + getStock() + " - Warranty: " + warrantyPeriod);
    }

    @Override
    public String toFileString() {
        return getName() + "," + getPrice() + "," + getStock() + ",Electronic," + warrantyPeriod;
    }
}
