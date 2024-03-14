package Riccardo;

public class Product {
    // ATTRIBUTI
    private long id;
    private String name;
    private String category;
    private double price;

    // COSTRUTTORE
    public Product(long id, String name, String category, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }
}
