import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    static ArrayList<Cashier> cashierList;
    static Inventory inventory = new Inventory();
    static HashMap<String, Bill> billList;
    static int prodIDs;
    static int billNos;

    Main() {
        cashierList = new ArrayList<>();
        inventory = new Inventory();
        billList = new HashMap<>();
        prodIDs = 1000;
        billNos = 0001;
    }

    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("1.Owner\n2.Cashier\n3.Exit\n");
            System.out.print("------>");
            int ip = s.nextInt();
            System.out.println();
            switch (ip) {
                case 1: {
                    System.out.print("Enter Name: ");
                    if (!(s.nextLine().equals("Owner"))) {
                        System.out.println("Incorrect Name, Try Again...!\n");
                        break;
                    }
                    System.out.print("Enter Password: ");
                    if (!(s.nextLine().equals("Password123"))) {
                        System.out.println("Incorrect Password, Try Again...!\n");
                        break;
                    }
                    System.out.println("Login Successfull..!\n");
                    startOwner();
                    break;
                }
                case 2: {
                    System.out.print("Enter Name: ");
                    String name = s.nextLine();
                    System.out.print("Enter Password: ");
                    String pwd = s.nextLine();
                    boolean flag = false;
                    for (Cashier c : cashierList) {
                        if (c.getName().equals(name) && c.getPwd().equals(pwd)) {
                            System.out.println("Login Successfull...!\n");
                            flag = true;
                            startCashier(c);
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
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("1.Add Cashier\n2.Generate Report\n3.Logout\n");
            System.out.print("------>");
            int ip = s.nextInt();
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
        Scanner s = new Scanner(System.in);
        System.out.print("No. of cashiers to be added? ");
        int num = s.nextInt();
        while (num > 0) {
            System.out.print("Cashier Name: ");
            String name = s.nextLine();
            System.out.print("Enter Password: ");
            String pwd = s.nextLine();
            Cashier c = new Cashier(name, pwd);

            if (!cashierList.contains(c)) {
                cashierList.add(c);
                System.out.println("Cashier Added Successfully...!\n");
                --num;
            } else {
                System.out.println("Cashier Name already exists, Try Again...!\n");
            }
        }
    }

    private static void generateReport() {

    }

    private static void startCashier(Cashier c) {
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("1.Stock Entry\n2.Bill Entry\n3.Logout\n");
            System.out.print("------>");
            int ip = s.nextInt();
            System.out.println();
            if (ip == 1) {
                stockEntry();
            } else if (ip == 2) {
                billEntry(c);
            } else if (ip == 3) {
                System.out.println("Logout Successfull..!\n");
                break;
            } else {
                System.out.println("Wrong Input, Try Again...\n!");
            }
        }
    }

    private static void stockEntry() {
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("1.Add Stock\n2.Update Stock\n3.Delete Stock\n4.Display Inventory\n5.Exit\n");
            System.out.print("------>");
            int ip = s.nextInt();
            System.out.println();
            if (ip == 1) {
                addStock();
            } else if (ip == 2) {
                updateStock();
            } else if (ip == 3) {
                deleteStock();
            } else if (ip == 4) {
                inventory.displayInventory();
            } else if (ip == 5) {
                break;
            } else {
                System.out.println("Wrong Input, Try Again...!\n");
            }
        }
    }

    private static void addStock() {
        Scanner s = new Scanner(System.in);
        // System.out.print("No. of products to be added? ");
        // int num = s.nextInt();
        // System.out.println();
        while (true) {
            System.out.println("Enter the product details, 'DONE' to quit");
            System.out.print("Enter Product's Name: ");
            String pName = s.nextLine();
            if (pName.equals("DONE")) {
                break;
            }
            String pID = prodIDs + "";
            ++prodIDs;
            System.out.print("Enter Product's Tax: ");
            int pTax = s.nextInt();
            System.out.print("Enter Product's Price: ");
            double pPrice = s.nextDouble();
            System.out.print("Enter Initial Quantity: ");
            int q = s.nextInt();
            inventory.addProduct(pName, pID, pTax, pPrice, q);
            System.out.println(pName + " (" + pID + ") Added Successfully...!\n");
        }
    }

    private static void updateStock() {
        Scanner s = new Scanner(System.in);
        // System.out.print("No. of products to be updated? ");
        // int num = s.nextInt();
        // System.out.println();
        while (true) {
            System.out.println("Enter 'DONE' to quit");
            System.out.print("Enter Product's ID: ");
            String pID = s.nextLine();
            if (pID.equals("DONE")) {
                break;
            }
            System.out.println();
            if (!inventory.containsProduct(pID)) {
                System.out.println("Product doesn't exists, Try Again...!\n");
                continue;
            }
            System.out.println(inventory.displayStock(pID));

            System.out.print("Enter New Quantity: ");
            int q = s.nextInt();
            System.out.println();
            inventory.updateStock(pID, q);

            System.out.println("Updated Successfully, New Stock: \n" + inventory.displayStock(pID));
        }
    }

    private static void deleteStock() {
        Scanner s = new Scanner(System.in);
        // System.out.print("No. of products to be deleted? ");
        // int num = s.nextInt();
        // System.out.println();
        while (true) {
            System.out.println("Enter 'DONE' to quit.");
            System.out.print("Enter Product's ID: ");
            String pID = s.nextLine();
            if (pID.equals("DONE")) {
                break;
            }
            System.out.println();
            if (!inventory.containsProduct(pID)) {
                System.out.println("Product doesn't exists, Try Again...!\n");
                continue;
            }
            String temp = inventory.getProduct(pID).getName();
            inventory.deleteProduct(pID);

            System.out.println(temp + " Deleted Successfully...!\n");
        }
    }

    private static void billEntry(Cashier c) {
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("1.New Bill\n2.Update Bill\n3.Delete Bill\n4.Exit\n");
            System.out.print("------>");
            int ip = s.nextInt();
            System.out.println();
            if (ip == 1) {
                Bill bill = newBill(c);
                billList.put(bill.getBillNo(), bill);
            } else if (ip == 2) {
                updateBill();
            } else if (ip == 3) {
                System.out.print("Enter Bill No.: ");
                String bNo = s.nextInt() + "";
                if (billList.containsKey(bNo)) {
                    System.out.println(bNo + " deleted successfully...!\n");
                    billList.remove(bNo);
                }
            } else if (ip == 4) {
                break;
            } else {
                System.out.println("Wrong Input, Try Again...!\n");
            }
        }
    }

    private static Bill newBill(Cashier c) {
        Bill bill = new Bill(billNos + "", c.getName());
        ++billNos;

        addProductToBill(bill);
        return bill;
    }

    private static void addProductToBill(Bill bill) {
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("Enter 'DONE' to quit.");
            System.out.print("Enter Product's ID: ");
            String pID = s.nextLine();
            if (pID.equals("DONE")) {
                break;
            }
            System.out.println();
            if (!inventory.containsProduct(pID)) {
                System.out.println("Product doesn't exist, Try Again...!\n");
                continue;
            }
            int temp = inventory.getQuantity(pID);

            if (temp == 0) {
                System.out.println("Product is Out of Stock...!\n");// Doubt product re entry
            } else {
                System.out.print("Enter Quantity: ");
                int q = s.nextInt();
                System.out.println();

                while (temp < q) {
                    System.out.println("Requested quantity not available, only " + temp + " left, Try Again...!\n");
                    System.out.print("Enter Quantity: ");
                    q = s.nextInt();
                    System.out.println();
                }
                bill.addProduct(inventory.getProduct(pID), q);
                inventory.updateStock(pID, temp - q);
            }
        }
    }

    private static void updateBill() {
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("1.Add New Product\n2.Update Quantity\n3.Delete Product\n4.Exit\n");
            System.out.print("------>");
            int ip = s.nextInt();
            System.out.println();

            if (ip == 1) {
                System.out.print("Enter Bill No.: ");
                String bNo = s.nextInt() + "";
                Bill bill = billList.get(bNo);
                addProductToBill(bill);
            } else if (ip == 2) {
                System.out.print("Enter Bill No.: ");
                String bNo = s.nextInt() + "";
                System.out.println();

                if (!billList.containsKey(bNo)) {
                    System.out.println("Bill No. doesn't exist..!, Try Again...!");
                    break;
                }

                Bill bill = billList.get(bNo);
                System.out.print("Enter Product's ID: ");
                String pID = s.nextInt() + "";

                if (!bill.containsProduct(pID)) {
                    System.out.println("Product (" + pID + ") doesn't exist in bill (" + bNo + ")...!");
                    System.out.println();
                    continue;
                }

                int temp = inventory.getQuantity(pID);
                int oldQ = bill.getQuantity(pID);

                if (temp == 0) {
                    System.out.println("Product is Out of Stock...!\n");// Doubt product re entry
                } else {
                    System.out.print("Enter Quantity: ");
                    int q = s.nextInt();
                    System.out.println();

                    while (temp < q) {
                        System.out.println("Requested quantity not available, only " + temp + " left, Try Again...!\n");
                        System.out.print("Enter Quantity: ");
                        q = s.nextInt();
                        System.out.println();
                    }
                    bill.addProduct(inventory.getProduct(pID), q);
                    inventory.updateStock(pID, temp - (q - oldQ));
                }
            } else if (ip == 3) {
                System.out.print("Enter Bill No.: ");
                String bNo = s.nextInt() + "";
                System.out.println();

                if (!billList.containsKey(bNo)) {
                    System.out.println("Bill No. doesn't exist..!, Try Again...!");
                    break;
                }

                Bill bill = billList.get(bNo);
                System.out.print("Enter Product's ID: ");
                String pID = s.nextInt() + "";

                if (!bill.containsProduct(pID)) {
                    System.out.println("Product (" + pID + ") doesn't exist in bill (" + bNo + ")...!");
                    System.out.println();
                    continue;
                }
                int temp = inventory.getQuantity(pID);
                int q = bill.getQuantity(pID);
                bill.deleteProduct(pID);
                inventory.updateStock(pID, temp + q);
            } else if (ip == 4) {
                break;
            } else {
                System.out.println("Wrong Input, Try Again...!\n");
            }
        }
    }
}
