package server;

import domain.ClothingProduct;
import domain.ElectronicProduct;
import domain.Product;

import java.io.*;
import java.util.*;

public class Store {
    private List<Product> products;

    public Store() {
        products = new ArrayList<>();
        loadProducts();  // Başlangıçta ürünleri dosyadan yükle
    }

    public void addProduct(Product product) {
        products.add(product);
        saveProducts();  // Ürün eklendiğinde dosyayı güncelle
    }

    public void removeProduct(Product product) {
        products.remove(product);
        saveProducts();  // Ürün silindiğinde dosyayı güncelle
    }

    public Product getProduct(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    public void displayInventory() {
        if (products.isEmpty()) {
            System.out.println("Mağazada ürün yok.");
        } else {
            System.out.println("Store Inventory:");
            for (Product product : products) {
                product.displayInfo();
            }
        }
    }

    // Ürünleri dosyaya kaydet
    private void saveProducts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("products.txt"))) {
            for (Product product : products) {
                writer.write(product.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ürünler dosyaya kaydedilemedi: " + e.getMessage());
        }
    }

    // Ürünleri dosyadan yükle
    private void loadProducts() {
        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                double price = Double.parseDouble(parts[1]);
                int stock = Integer.parseInt(parts[2]);
                String type = parts[3];

                Product product = null;
                if (type.equals("Electronic")) {
                    String warranty = parts[4];
                    product = new ElectronicProduct(name, price, stock, warranty);
                } else if (type.equals("Clothing")) {
                    String size = parts[4];
                    product = new ClothingProduct(name, price, stock, size);
                }

                if (product != null) {
                    products.add(product);
                }
            }
        } catch (IOException e) {
            System.out.println("Ürünler dosyadan yüklenemedi: " + e.getMessage());
        }
    }
}
