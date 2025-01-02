package domain;

public abstract class Product {
    private String name;
    private double price;
    private int stock;

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void decreaseStock(int quantity) {
        this.stock -= quantity;
    }

    public abstract void displayInfo();

    // Ürün bilgilerini dosyaya kaydetmek için
    public abstract String toFileString();
}

