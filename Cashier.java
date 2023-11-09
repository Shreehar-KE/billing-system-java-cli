// import java.util.ArrayList;
// import java.util.HashMap;

public class Cashier {
    private String name, pwd;
    // private HashMap<String, Bill> billList;

    public Cashier(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
        // this.billList = new HashMap<>();
    }

    public String getName() {
        return this.name;
    }

    public String getPwd() {
        return this.pwd;
    }

    @Override
    public boolean equals(Object comparedObject) {
        if (this == comparedObject) {
            return true;
        }

        if (!(comparedObject instanceof Cashier)) {
            return false;
        }

        Cashier comparedCashier = (Cashier) comparedObject;

        if (this.name.equals(comparedCashier.getName())) {
            return true;
        }
        return false;
    }

    // public void addBill(String billNo, Bill bill) {
    // this.billList.put(billNo, bill);
    // }

    // public void updateBill(String billNo, Product prod, int quantity) {
    // this.billList.get(billNo).addProduct(prod, quantity);
    // }

    // public void deleteBill(String billNo) {
    // this.billList.remove(billNo);
    // }

    // public ArrayList<Bill> getBills() {
    // return new ArrayList<>(this.billList.values());
    // }

    // public boolean containsBill(String billNo) {
    // return this.billList.containsKey(billNo);
    // }
}
