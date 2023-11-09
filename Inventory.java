import java.util.HashMap;

public class Inventory {
    private HashMap<String, Product> productsList;
    private HashMap<String, Integer> quantities;

    public Inventory() {
        this.quantities = new HashMap<>();
        this.productsList = new HashMap<>();
    }

    public boolean isEmpty() {
        return this.productsList.isEmpty();
    }

    public void addProduct(String prodName, String prodId, int tax, double unitPrice, int quantity) {
        Product prod = new Product(prodName, prodId, tax, unitPrice);
        this.productsList.put(prodId, prod);
        this.quantities.put(prodId, quantity);
    }

    public Product getProduct(String prodId) {
        return this.productsList.get(prodId);
    }

    public int getQuantity(String prodId) {
        return this.quantities.get(prodId);
    }

    public void updateStock(String prodId, int quantity) {
        this.quantities.put(prodId, quantity);
    }

    public void deleteProduct(String prodId) {
        this.productsList.remove(prodId);
        this.quantities.remove(prodId);
    }

    public String displayStock(String prodId) {
        return "Name: " + this.getProduct(prodId).getName()
                + "\nQuantity: " + this.getQuantity(prodId)
                + "\n";
    }

    public void displayInventory() {
        System.out.println("----------------------------------------");
        System.out.println("Name\tID\tTax\tPrice\tQuantity");
        System.out.println("----\t--\t---\t-----\t--------");
        for (Product p : this.productsList.values()) {
            int q = this.quantities.get(p.getId());
            String op = p.getName() + "\t";
            op += p.getId() + "\t";
            op += p.getTax() + "%\t" + String.format("%.2f", p.getPrice()) + "\t";
            op += q;
            System.out.println(op);
            op = "";
        }
        System.out.println("----------------------------------------\n");
    }

    public boolean containsProduct(String prodId) {
        return this.productsList.containsKey(prodId);
    }

    public boolean checkQuantity(String prodID, int q) {
        int temp = this.getQuantity(prodID);
        if (temp < q) {
            System.out.println("Requested quantity not available, only " + temp + " left...!\n");
            return false;
        } else {
            return true;
        }
    }
}
