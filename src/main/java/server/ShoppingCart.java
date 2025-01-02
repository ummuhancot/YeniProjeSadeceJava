package server;

import domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> products;

    // Constructor
    public ShoppingCart() {
        this.products = new ArrayList<>();
    }

    // Sepete ürün ekleme
    public void addProduct(Product product, int quantity) {
        if (product.getStock() >= quantity) {
            for (int i = 0; i < quantity; i++) {
                products.add(product);
            }
            product.decreaseStock(quantity); // Stoktan düşme işlemi
        } else {
            System.out.println("Not enough stock for " + product.getName());
        }
    }

    // Sepetten ürün çıkarma
    public void removeProduct(Product product, int quantity) {
        int count = 0;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(product.getName()) && count < quantity) {
                products.remove(i);
                count++;
                i--; // Listedeki öğeyi kaldırdığınızda indeks değişeceği için i'yi azaltıyoruz
            }
        }
    }

    // Sepetin toplam tutarını hesaplama
    public double getTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    // Sepetteki ürünleri listeleme
    public void displayCart() {
        System.out.println("Shopping Cart:");
        for (Product product : products) {
            System.out.println(product.getName());
        }
        System.out.println("Total Price: " + getTotal());
    }
}
