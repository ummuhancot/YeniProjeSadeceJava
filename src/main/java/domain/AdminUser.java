package domain;

import server.Store;

public class AdminUser extends Online_store.User {

    // Constructor
    public AdminUser(String username, String email) {
        super(username, email); // Parent class'ın constructor'ını çağırıyoruz
    }

    // Admin'e özel ürün ekleme
    public void addProductToStore(Store store, Product product) {
        store.addProduct(product);
    }

    // Admin'e özel ürün çıkarma
    public void removeProductFromStore(Store store, Product product) {
        store.removeProduct(product);
    }
}
