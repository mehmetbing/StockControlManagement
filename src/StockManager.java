import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;



// StockManager.java
public class StockManager<T extends Product> {
    private List<T> products;
    private Map<String, T> productMap;
    private Scanner scanner;

    public StockManager() {
        this.products = new ArrayList<>();
        this.productMap = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    public void addProduct(T product) {
        products.add(product);
        productMap.put(product.getName(), product);
    }

    public void removeProduct(String productName) {
        T product = productMap.get(productName);
        if (product != null) {
            products.remove(product);
            productMap.remove(productName);
        }
    }

    public void updateStock(String productName, int newQuantity) {
        T product = productMap.get(productName);
        if (product != null) {
            product.setQuantity(newQuantity);
        }
    }

    public void displayStock() {
        products.forEach(System.out::println);
    }

    public void performOperationOnAll(StockOperation<T> operation) {
        products.forEach(operation::performOperation);
    }

    public void startStockManagement() {
        while (true) {
            System.out.println("\n1. Add Product ");
            System.out.println("2. Remove Product ");
            System.out.println("3. Update Stock ");
            System.out.println("4. Display Stock ");
            System.out.println("5. Exit");
            System.out.print(" Type one of the options above: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String productName = scanner.nextLine();
                    System.out.print("Enter initial quantity: ");
                    int initialQuantity = scanner.nextInt();
                    T newProduct = (T) new Product(productName, initialQuantity);
                    addProduct(newProduct);
                    System.out.println("Product added successfully.");
                    break;
                case 2:
                    System.out.print("Enter product name to remove: ");
                    String productToRemove = scanner.nextLine();
                    removeProduct(productToRemove);
                    System.out.println("Product removed successfully.");
                    break;
                case 3:
                    System.out.print("Enter product name to update stock: ");
                    String productToUpdate = scanner.nextLine();
                    System.out.print("Enter new quantity: ");
                    int newQuantity = scanner.nextInt();
                    updateStock(productToUpdate, newQuantity);
                    System.out.println("Stock updated successfully.");
                    break;
                case 4:
                    System.out.println("Current Stock:");
                    displayStock();
                    break;
                case 5:
                    System.out.println("Exiting the application. Thanks for using this app");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    public static void main(String[] args) {
        StockManager<Product> stockManager = new StockManager<>();

        // Lambda function for increasing stock by 5 units
        StockOperation<Product> increaseStockOperation = product -> {
            int currentQuantity = product.getQuantity();
            product.setQuantity(currentQuantity + 5);
        };

        // Lambda function for displaying product details
        StockOperation<Product> displayProductOperation = System.out::println;

        stockManager.addProduct(new Product("Shirt", 500));
        stockManager.addProduct(new Product("Trousers", 350));
        stockManager.addProduct(new Product("Socks", 195));
        Product laptop = new Product("Laptop", 1000);
        Product mouse = new Product("Headphones", 590);
        Product printer = new Product("USB", 960);

        stockManager.addProduct(laptop);
        stockManager.addProduct(mouse);
        stockManager.addProduct(printer);

        ElectronicsProduct smartphone = new ElectronicsProduct("Smartphone", 15, "Samsung");
        stockManager.addProduct(smartphone);

        System.out.println("Initial Stock:");
        stockManager.displayStock();

        // Increase stock by 5 units for all products
        stockManager.performOperationOnAll(increaseStockOperation);

        System.out.println("\nStock after increasing by 5 units:");
        stockManager.displayStock();

        // Display product details using lambda function
        System.out.println("\nProduct Details:");
        stockManager.performOperationOnAll(displayProductOperation);

        System.out.println("\nCurrent Stock including Electronics Products:");
        stockManager.displayStock();

        // Start stock management application with user input
        stockManager.startStockManagement();
    }
}