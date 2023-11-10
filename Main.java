import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    static ArrayList<Cashier> cashierList = new ArrayList<>();
    static Inventory inventory = new Inventory();
    static HashMap<String, Bill> billList = new HashMap<>();
    static int prodIDs = 1000;
    static int billNos = 100;
    static Scanner scan = new Scanner(System.in);

    public static void main(String args[]) {
        while (true) {
            System.out.println("1.Owner\n2.Cashier\n3.Exit\n");
            System.out.print("------> ");
            int ip = Integer.parseInt(scan.nextLine());
            System.out.println();
            switch (ip) {
                case 1: {
                    System.out.print("Enter Name: ");
                    String oName = scan.nextLine();
                    if (!(oName.equals("Owner"))) {
                        System.out.println("Incorrect Name, Try Again...!\n");
                        break;
                    }
                    System.out.print("Enter Password: ");
                    String oPwd = scan.nextLine();
                    System.out.println();
                    if (!(oPwd.equals("Password123"))) {
                        System.out.println("Incorrect Password, Try Again...!\n");
                        break;
                    }
                    System.out.println("Login Successfull..!\n");
                    startOwner();
                    break;
                }
                case 2: {
                    if (cashierList.isEmpty()) {
                        System.out.println("No cashiers are added yet...!\n");
                        break;
                    }
                    System.out.print("Enter Name: ");
                    String name = scan.nextLine();
                    System.out.print("Enter Password: ");
                    String pwd = scan.nextLine();
                    System.out.println();
                    boolean flag = false;
                    for (Cashier c : cashierList) {
                        if (c.getName().equals(name) && c.getPwd().equals(pwd)) {
                            System.out.println("Login Successfull...!\n");
                            flag = true;
                            startCashier(c.getName());
                            break;
                        }
                    }
                    if (!flag) {
                        System.out.println("Wrong Credentials, Try Again...!\n");
                    }
                    break;
                }
                case 3: {
                    System.exit(0);
                }
                default: {
                    System.out.println("Wrong Input, Try Again...!\n");
                }
            }
        }
    }

    private static void startOwner() {
        while (true) {
            System.out.println("1.Add Cashier\n2.Generate Report\n3.Logout\n");
            System.out.print("------> ");
            int ip = Integer.parseInt(scan.nextLine());
            System.out.println();
            if (ip == 1) {
                addCashier();
            } else if (ip == 2) {
                generateReport();
            } else if (ip == 3) {
                System.out.println("Logout Successfull..!\n");
                break;
            } else {
                System.out.println("Wrong Input, Try Again...!\n");
            }
        }
    }

    private static void addCashier() {
        System.out.println("Enter 'DONE' to quit.");
        System.out.println();
        while (true) {
            System.out.print("Cashier Name: ");
            String name = scan.nextLine();
            System.out.println();
            if (name.equals("DONE")) {
                break;
            }
            System.out.print("Enter Password: ");
            String pwd = scan.nextLine();
            System.out.println();
            Cashier c = new Cashier(name, pwd);

            if (!cashierList.isEmpty()) {
                if (cashierList.contains(c)) {
                    System.out.println("Cashier Name already exists, Try Again...!\n");
                    continue;
                }
            }
            cashierList.add(c);
            System.out.println("Cashier " + c.getName() + " Added Successfully...!\n");

        }
    }

    private static void generateReport() {

    }

    private static void startCashier(String cname) {
        while (true) {
            System.out.println("1.Stock Entry\n2.Bill Entry\n3.Logout\n");
            System.out.print("------> ");
            int ip = Integer.parseInt(scan.nextLine());
            System.out.println();
            if (ip == 1) {
                stockEntry();
            } else if (ip == 2) {
                if ((billList.size() == 0 && inventory.isEmpty())) {
                    System.out.println("Function not allowed at this moment...!\n");
                    continue;
                }
                billEntry(cname);
            } else if (ip == 3) {
                System.out.println("Logout Successfull..!\n");
                break;
            } else {
                System.out.println("Wrong Input, Try Again...\n!");
            }
        }
    }

    private static void stockEntry() {
        while (true) {
            System.out.println("1.Add Stock\n2.Update Stock\n3.Delete Stock\n4.Display Inventory\n5.Exit\n");
            System.out.print("------> ");
            int ip = Integer.parseInt(scan.nextLine());
            System.out.println();
            if (ip == 1) {
                addStock();
            } else if (ip == 2) {
                if (inventory.isEmpty()) {
                    System.out.println("Add some product(s) before updating..!\n");
                    continue;
                }
                updateStock();
            } else if (ip == 3) {
                if (inventory.isEmpty()) {
                    System.out.println("Add some product(s) before deleting..!\n");
                    continue;
                }
                deleteStock();
            } else if (ip == 4) {
                if (inventory.isEmpty()) {
                    System.out.println("Inventory is Empty..!\n");
                    continue;
                }
                inventory.displayInventory();
            } else if (ip == 5) {
                break;
            } else {
                System.out.println("Wrong Input, Try Again...!\n");
            }
        }
    }

    private static void addStock() {
        System.out.println("Enter the product details, 'DONE' to quit");
        System.out.println();
        while (true) {
            System.out.print("Enter Product's Name: ");
            String prodName = scan.nextLine();
            System.out.println();
            if (prodName.equals("DONE")) {
                break;
            }
            String prodId = prodIDs + "";
            ++prodIDs;
            System.out.print("Enter Product's Tax: ");
            int prodTax = Integer.parseInt(scan.nextLine());
            System.out.print("Enter Product's Price: ");
            double prodPrice = Double.parseDouble(scan.nextLine());
            System.out.print("Enter Initial Quantity: ");
            int q = Integer.parseInt(scan.nextLine());
            inventory.addProduct(prodName, prodId, prodTax, prodPrice, q);
            System.out.println();
            System.out.println(prodName + " (ID: " + prodId + ") Added Successfully...!\n");
        }
    }

    private static void updateStock() {
        System.out.println("Enter 'DONE' to quit.");
        System.out.println();
        while (true) {
            System.out.print("Enter Product's ID: ");
            String prodId = scan.nextLine();
            System.out.println();
            if (prodId.equals("DONE")) {
                break;
            }
            System.out.println();
            if (!inventory.containsProduct(prodId)) {
                System.out.println("Invalid Product ID, Try Again...!\n");
                continue;
            }
            System.out.println(inventory.displayStock(prodId));

            System.out.print("Enter New Quantity: ");
            int q = Integer.parseInt(scan.nextLine());
            System.out.println();
            inventory.updateStock(prodId, q);

            System.out.println("Updated Successfully, New Stock: \n" + inventory.displayStock(prodId));
        }
    }

    private static void deleteStock() {
        System.out.println("Enter 'DONE' to quit.");
        System.out.println();
        while (true) {
            System.out.print("Enter Product's ID: ");
            String prodId = scan.nextLine();
            System.out.println();
            if (prodId.equals("DONE")) {
                break;
            }
            System.out.println();
            if (!inventory.containsProduct(prodId)) {
                System.out.println("Invalid Product ID, Try Again...!\n");
                continue;
            }
            String temp = inventory.getProduct(prodId).getName();
            inventory.deleteProduct(prodId);

            System.out.println(temp + " Deleted Successfully...!\n");
        }
    }

    private static void billEntry(String cname) {
        while (true) {
            System.out.println("1.New Bill\n2.Update Bill\n3.Delete Bill\n4.View Bill\n5.Exit\n");

            System.out.print("------> ");
            int ip = Integer.parseInt(scan.nextLine());
            System.out.println();

            if (ip == 1) {
                newBill(cname);
            } else if (ip == 5) {
                break;
            } else {
                System.out.print("Enter Bill No.: ");
                String bNo = scan.nextLine();
                System.out.println();

                if (!billList.containsKey(bNo)) {
                    System.out.println("Invalid Bill No., Try Again...!\n");
                    continue;
                }
                if (ip == 2) {
                    updateBill(bNo);
                } else if (ip == 3) {
                    billList.get(bNo).displayBill();
                    System.out.print("Confirm deleting this Bill (y/n): ");
                    String ch = scan.nextLine();
                    System.out.println();
                    if (ch.equals("y")) {
                        billList.remove(bNo);
                        System.out.println("Bill (" + bNo + ") deleted...!\n");

                    } else {
                        System.out.println("Delete Operation Cancelled...!\n");
                    }
                } else if (ip == 4) {
                    billList.get(bNo).displayBill();
                } else {
                    System.out.println("Wrong Input, Try Again...!\n");
                }

            }
        }
    }

    private static void newBill(String cname) {
        Bill b = new Bill(billNos + "", cname);
        billNos++;
        addProductsToBill(b);
        if (b.isEmpty()) {
            System.out.println("New Bill Operation Failed...!\n");
            billNos--;
            return;
        }
        billList.put(b.getBillNo(), b);
    }

    private static void updateBill(String bNo) {
        Bill b = billList.get(bNo);
        while (true) {
            System.out.println("1.Add New Product/Update Quantity\n2.Delete Product\n3.Exit\n");
            System.out.print("------> ");
            int ip = Integer.parseInt(scan.nextLine());
            System.out.println();
            if (ip == 1) {
                System.out.println("Existing Bill:");
                b.displayBill();
                addProductsToBill(b);
            } else if (ip == 2) {
                System.out.println("Existing Bill:");
                b.displayBill();
                System.out.print("Enter Product's ID: ");
                String prodID = scan.nextLine();
                System.out.println();
                if (b.containsProduct(prodID)) {
                    int q = b.getQuantity(prodID);
                    int stock = inventory.getQuantity(prodID);
                    inventory.updateStock(prodID, stock + q);
                    b.deleteProduct(prodID);
                    System.out.println("Delete Successfull...!\n");
                } else {
                    System.out.println("Delete Unsuccessfull, Invalid Product ID\n");
                }
                if (!b.isEmpty()) {
                    b.displayBill();
                } else {
                    billList.remove(bNo);
                    System.out.println("No products left, Bill (" + bNo + ") deleted...!\n");
                }
            } else if (ip == 3) {
                break;
            } else {
                System.out.println("Wrong Input, Try Again...!\n");
            }
        }

    }

    private static void addProductsToBill(Bill b) {
        System.out.println("Enter 'DONE' to quit.\n");
        while (true) {
            System.out.print("Enter the Product's ID: ");
            String prodID = scan.nextLine();
            System.out.println();
            if (prodID.equals("DONE")) {
                break;
            }
            if (!inventory.containsProduct(prodID)) {
                System.out.println("Invalid Product ID, Try Again...!\n");
                continue;
            } else if (inventory.getQuantity(prodID) == 0) {
                System.out.println("Product is Out of Stock, Try Again...!\n");
                continue;
            } else if (b.containsProduct(prodID)) {
                System.out.print("Product already exists in Bill, Do you want to the update quantity (y/n)? ");
                String ch = scan.nextLine();
                System.out.println();
                if (ch.equals("y")) {
                    updateQuantityInBill(b, prodID);
                } else {
                    continue;
                }
            } else {
                insertProductInBill(b, prodID);
            }
        }
        if (!b.isEmpty()) {
            b.displayBill();
        }
    }

    private static void insertProductInBill(Bill b, String prodID) {
        int q = getUserQuantity(prodID);
        b.addProduct(inventory.getProduct(prodID), q);
        int stock = inventory.getQuantity(prodID);
        inventory.updateStock(prodID, stock - q);
    }

    private static void updateQuantityInBill(Bill b, String prodID) {
        int oldQ = b.getQuantity(prodID);
        int stock = inventory.getQuantity(prodID);
        inventory.updateStock(prodID, stock + oldQ);
        int q = getUserQuantity(prodID);
        b.addProduct(inventory.getProduct(prodID), q);
        stock = inventory.getQuantity(prodID);
        inventory.updateStock(prodID, stock - q);
    }

    private static int getUserQuantity(String prodId) {
        int q;
        do {
            System.out.print("Enter the Quantity: ");
            q = Integer.parseInt(scan.nextLine());
            System.out.println();
            if (q < 1) {
                System.out.println("Invalid Quantity, Try Again...!\n");
                continue;
            }
        } while (!inventory.checkQuantity(prodId, q));
        return q;
    }
}
