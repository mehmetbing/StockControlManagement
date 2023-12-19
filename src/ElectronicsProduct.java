public class ElectronicsProduct extends Product {
    private String brand;

    public ElectronicsProduct(String name, int quantity, String brand) {
        super(name, quantity);
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "ElectronicsProduct{" +
                "name='" + getName() + '\'' +
                ", quantity=" + getQuantity() +
                ", brand='" + brand + '\'' +
                '}';
    }
}
