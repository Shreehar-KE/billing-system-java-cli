import java.util.ArrayList;
import java.util.HashMap;

public class Bill {
    private String billNo;
    private HashMap<String, ArrayList<Object>> productList;

    public Bill(String billNo) {
        this.billNo = billNo;
        this.productList = new HashMap<>();
    }

    public String getBillNo() {
        return this.billNo;
    }

    public void addProduct(Product product, int quantity) {
        ArrayList<Object> temp = new ArrayList<>();
        temp.add(product);
        temp.add(quantity);
        this.productList.put(product.getId(), temp);
    }

    public void updateProduct(Product product, int quantity) {
        if (this.productList.containsKey(product.getId())) {
            addProduct(product, quantity);
        } else {
            System.out.println("Update Unsuccessfull, product doesn't exist...!");
        }
    }

    public void deleteProduct(Product product) {
        if (this.productList.containsKey(product.getId())) {
            this.productList.remove(product.getId());
        } else {
            System.out.println("Delete Unsuccessfull, product doesn't exist...!");
        }
    }

    public double getTotalAmount() {
        if (this.productList.isEmpty()) {
            System.out.println("No products are added yet...!");
            return (-1);
        }
        double amount = 0.0;
        for (ArrayList<Object> arr : this.productList.values()) {
            amount += this.calcTotalPrice(arr);
        }
        return amount;
    }

    private double calcTotalPrice(ArrayList<Object> arr) {
        int q = (int) arr.get(1);
        Product p = (Product) arr.get(0);
        double amount = (p.getPrice() + (p.getPrice() * (p.getTax() / 100))) * q;
        return amount;
    }

    public void displayBill() {
        System.out.println();
        System.out.println("Bill No:\t" + this.billNo);
        System.out.println("------------------------------------------------");
        System.out.println("Name\tID\tTax\tUnit Price\tQuantity\tTotal Price");
        System.out.println("----\t--\t---\t----------\t--------\t-----------");
        for (String k : this.productList.keySet()) {
            Product p = (Product) this.productList.get(k).get(0);
            int q = (int) this.productList.get(k).get(1);
            String res = p.getName() + "\t"
                    + k + "\t"
                    + p.getTax() + "\t"
                    + String.format("%.2f", p.getPrice()) + "\t"
                    + q + "\t"
                    + String.format("%.2f", this.calcTotalPrice(this.productList.get(k)));
            System.out.println(res);
        }
        System.out.println("------------------------------------------------");
        System.out.println("Total Amount:\t" + this.getTotalAmount());
        System.out.println();
    }
}
