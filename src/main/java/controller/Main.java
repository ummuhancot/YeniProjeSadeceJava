package controller;

import domain.*;
import server.Store;

import java.time.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Mağaza ve kullanıcılar oluşturuluyor
        Store store = new Store();
        AdminUser admin = new AdminUser("Admin", "admin@example.com");
        User user = new User("JohnDoe", "john.doe@example.com");

        // Admin kullanıcısı ürün ekliyor
        Product phone = new ElectronicProduct("Phone", 1000.0, 10, "2 years");
        Product laptop = new ClothingProduct("Laptop", 1500.0, 5, "L");
        admin.addProductToStore(store, phone);
        admin.addProductToStore(store, laptop);

        // Scanner nesnesi, kullanıcıdan input almak için
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Ana menü
        do {
            System.out.println("\nÇevrimiçi Mağaza Uygulaması");
            System.out.println("1. Mağaza Envanterini Görüntüle");
            System.out.println("2. Ürün Sepete Ekle");
            System.out.println("3. Sepeti Görüntüle");
            System.out.println("4. Sepeti Onayla (Ödeme)");
            System.out.println("5. Admin İşlemleri");
            System.out.println("6. Çıkış");
            System.out.print("Seçiminizi yapın (1-6): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Mağaza envanterini görüntüle
                    store.displayInventory();
                    break;

                case 2:
                    // Ürün sepete ekleme
                    System.out.print("Sepete eklemek istediğiniz ürünün adını girin: ");
                    String productName = scanner.next();
                    Product product = store.getProduct(productName);
                    if (product != null) {
                        System.out.print("Kaç adet eklemek istersiniz? ");
                        int quantity = scanner.nextInt();
                        user.addToCart(product, quantity);
                    } else {
                        System.out.println("Ürün bulunamadı.");
                    }
                    break;

                case 3:
                    // Sepeti görüntüle
                    user.viewCart();
                    break;

                case 4:
                    // Sepeti onayla (Ödeme işlemi)
                    user.checkout();
                    break;

                case 5:
                    // Admin işlemleri
                    System.out.println("\nAdmin Menüsü");
                    System.out.println("1. Ürün Ekle");
                    System.out.println("2. Ürün Sil");
                    System.out.print("Admin işlemi seçin (1-2): ");
                    int adminChoice = scanner.nextInt();

                    switch (adminChoice) {
                        case 1:
                            // Ürün ekleme
                            System.out.print("Eklenecek ürünün adını girin: ");
                            String newProductName = scanner.next();
                            System.out.print("Ürün fiyatını girin: ");
                            double price = scanner.nextDouble();
                            System.out.print("Ürün stoğunu girin: ");
                            int stock = scanner.nextInt();
                            System.out.print("Ürün türünü (Electronic/Clothing) girin: ");
                            String type = scanner.next();
                            if (type.equalsIgnoreCase("Electronic")) {
                                System.out.print("Garanti süresini girin: ");
                                String warranty = scanner.next();
                                Product newProduct = new ElectronicProduct(newProductName, price, stock, warranty);
                                admin.addProductToStore(store, newProduct);
                            } else if (type.equalsIgnoreCase("Clothing")) {
                                System.out.print("Beden bilgisini girin: ");
                                String size = scanner.next();
                                Product newClothing = new ClothingProduct(newProductName, price, stock, size);
                                admin.addProductToStore(store, newClothing);
                            } else {
                                System.out.println("Geçersiz ürün türü.");
                            }
                            break;

                        case 2:
                            // Ürün silme
                            System.out.print("Silmek istediğiniz ürünün adını girin: ");
                            String removeProductName = scanner.next();
                            Product productToRemove = store.getProduct(removeProductName);
                            if (productToRemove != null) {
                                admin.removeProductFromStore(store, productToRemove);
                                System.out.println("Ürün başarıyla silindi.");
                            } else {
                                System.out.println("Ürün bulunamadı.");
                            }
                            break;

                        default:
                            System.out.println("Geçersiz işlem.");
                            break;
                    }
                    break;

                case 6:
                    // Çıkış
                    System.out.println("Çıkılıyor...");
                    break;

                default:
                    System.out.println("Geçersiz seçim, tekrar deneyin.");
                    break;
            }
        } while (choice != 6);  // Çıkış yapılana kadar döngü devam eder.

        scanner.close();
    }
}
