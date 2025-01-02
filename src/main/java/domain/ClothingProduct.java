package domain;

public class ClothingProduct extends Product {
    private String size;

    public ClothingProduct(String name, double price, int stock, String size) {
        super(name, price, stock);
        this.size = size;
    }

    @Override
    public void displayInfo() {
        System.out.println("Clothing Product: " + getName() + " - Price: " + getPrice() + " - Stock: " + getStock() + " - Size: " + size);
    }

    @Override
    public String toFileString() {
        return getName() + "," + getPrice() + "," + getStock() + ",Clothing," + size;
    }
}
