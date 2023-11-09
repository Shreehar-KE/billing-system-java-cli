public class Product {
    private String prodName, prodId;
    private int tax;
    private double unitPrice;

    public Product(String prodName, String prodId, int tax, double unitPrice) {
        this.prodName = prodName;
        this.prodId = prodId;
        this.tax = tax;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return this.prodName;
    }

    public String getId() {
        return this.prodId;
    }

    public int getTax() {
        return this.tax;
    }

    public double getPrice() {
        return this.unitPrice;
    }

    public String print() {
        return this.prodName + "\t" + this.prodId + "\t" + this.tax + "\t" + this.unitPrice;
    }
}
