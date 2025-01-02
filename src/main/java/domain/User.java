package domain;

import domain.Product;
import server.Store;

import java.io.*;
import java.util.*;

public class User {
    private String name;
    private String email;
    private Map<Product, Integer> cart;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.cart = new HashMap<>();
        loadCart();  // Sepeti dosyadan yükle
    }

    public void addToCart(Product product, int quantity) {
        cart.put(product, quantity);
        saveCart();  // Sepete ekleme işleminde dosyayı güncelle
    }

    public void removeFromCart(Product product) {
        cart.remove(product);
        saveCart();  // Sepetten ürün çıkarıldığında dosyayı güncelle
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Sepetiniz boş.");
        } else {
            System.out.println("Sepetiniz:");
            double total = 0;
            for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                System.out.println(product.getName() + " - " + quantity + " x " + product.getPrice() + " = " + product.getPrice() * quantity);
                total += product.getPrice() * quantity;
            }
            System.out.println("Toplam: " + total);
        }
    }

    public void checkout() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            total += product.getPrice() * quantity;
        }
        System.out.println("Ödeme yapılıyor... Toplam: " + total);
        cart.clear();
        saveCart();  // Ödeme yapıldığında sepeti sıfırla ve kaydet
    }

    // Sepeti dosyaya kaydet
    private void saveCart() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("cart.txt"))) {
            for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                writer.write(product.getName() + "," + quantity);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Sepet dosyaya kaydedilemedi: " + e.getMessage());
        }
    }

    // Sepeti dosyadan yükle
    private void loadCart() {
        try (BufferedReader reader = new BufferedReader(new FileReader("cart.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String productName = parts[0];
                int quantity = Integer.parseInt(parts[1]);
                Product product = new Store().getProduct(productName);  // Store üzerinden ürün al
                if (product != null) {
                    cart.put(product, quantity);
                }
            }
        } catch (IOException e) {
            System.out.println("Sepet dosyadan yüklenemedi: " + e.getMessage());
        }
    }
}
