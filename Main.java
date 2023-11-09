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

    public static void startOwner() {
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

    public static void addCashier() {
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

    public static void generateReport() {

    }

    public static void startCashier(Cashier c) {
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("1.Stock Entry\n2.Bill Entry\n3.Logout\n");
            System.out.print("------>");
            int ip = s.nextInt();
            if (ip == 1) {
                stockEntry();
            } else if (ip == 2) {
                billEntry(c);
            } else if (ip == 3) {
                break;
            } else {
                System.out.println("Wrong Input, Try Again...\n!");
            }
        }
    }

    public static void stockEntry() {
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("1.Add Stock\n2.Update Stock\n3.Delete Stock\n4.Display Inventory\n5.Exit\n");
            System.out.print("------>");
            int ip = s.nextInt();
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

    public static void addStock() {
        Scanner s = new Scanner(System.in);
        System.out.print("No. of products to be added? ");
        System.out.println();
        int num = s.nextInt();
        while (num > 0) {
            System.out.print("Enter Product's Name: ");
            String pName = s.nextLine();
            String pID = prodIDs + "";
            ++prodIDs;
            System.out.print("Enter Product's Tax: ");
            int pTax = s.nextInt();
            System.out.print("Enter Product's Price: ");
            double pPrice = s.nextDouble();// doubt
            System.out.print("Enter Initial Quantity: ");
            int q = s.nextInt();
            inventory.addProduct(pName, pID, pTax, pPrice, q);
            --num;
            System.out.println(pName + " (" + pID + ") Added Successfully...!\n");
        }
    }

    public static void updateStock() {
        Scanner s = new Scanner(System.in);
        System.out.print("No. of products to be updated? ");
        int num = s.nextInt();
        System.out.println();
        while (num > 0) {
            System.out.print("Enter Product's ID: ");
            String pID = s.nextLine();
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
            --num;
            System.out.println("Updated Successfully, New Stock: \n" + inventory.displayStock(pID));
        }
    }

    public static void deleteStock() {
        Scanner s = new Scanner(System.in);
        System.out.print("No. of products to be deleted? ");
        int num = s.nextInt();
        System.out.println();
        while (num > 0) {
            System.out.print("Enter Product's ID: ");
            String pID = s.nextLine();
            System.out.println();
            if (!inventory.containsProduct(pID)) {
                System.out.println("Product doesn't exists, Try Again...!\n");
                continue;
            }
            inventory.deleteProduct(pID);
            --num;
            System.out.println(inventory.getProduct(pID).getName() + " Deleted Successfully...!\n");
        }
    }

    public static void billEntry(Cashier c) {
        // Scanner s = new Scanner(System.in);
        // while (true) {
        // System.out.println("1.New Bill\n2.Update Bill\n3.Delete Bill\n4.Exit");
        // System.out.print("------>");
        // int ip = s.nextInt();
        // if (ip == 1) {
        // Bill bill = addBill();
        // c.addBill(bill.getBillNo(), bill);
        // } else if (ip == 2) {
        // updateBill(c);
        // } else if (ip == 3) {
        // System.out.print("Enter Bill No.: ");
        // int bNo = s.nextInt();
        // c.deleteBill(bNo + "");
        // } else if (ip == 4) {
        // break;
        // } else {
        // System.out.println("Wrong Input, Try Again...!");
        // }
        // }
    }

    // public static Bill addBill() {
    // Scanner s = new Scanner(System.in);
    // Bill bill = new Bill(billNos + "");
    // ++billNos;
    // System.out.print("No. of products? ");
    // System.out.println();
    // int num = s.nextInt();
    // while (num > 0) {

    // // System.out.print("Enter Product's ID: ");
    // // String pID = s.nextLine();
    // // if (inventory.containsProduct(pID)) {
    // // System.out.println("Enter Quantity: ");
    // // int q = s.nextInt();
    // // int temp = inventory.getQuantity(pID);
    // // if (temp >= q) {
    // // bill.addProduct(inventory.getProduct(pID), q);
    // // inventory.updateStock(pID, temp - q);
    // // } else {
    // // System.out.println("Requested quantity not available, only " + temp + "
    // left.
    // // Try Again...!");
    // // num += 1;
    // // }
    // // } else {
    // // System.out.println("Product doesn't exist, Try Again...!");
    // // num += 1;
    // // }
    // // --num;
    // // System.out.println();

    // System.out.print("Enter Product's ID: ");
    // String pID = s.nextLine();
    // if (!inventory.containsProduct(pID)) {
    // System.out.println("Product doesn't exist, Try Again...!");
    // continue;
    // }
    // System.out.println("Enter Quantity: ");
    // int q = s.nextInt();
    // int temp = inventory.getQuantity(pID);
    // if (temp < q) {
    // System.out.println("Requested quantity not available, only " + temp + " left.
    // Try Again...!");
    // continue;
    // } else {
    // bill.addProduct(inventory.getProduct(pID), q);
    // inventory.updateStock(pID, temp - q);
    // --num;
    // }
    // System.out.println();
    // }
    // return bill;
    // }

    public static void updateBill(Cashier c) {
        // Scanner s = new Scanner(System.in);
        // while (true) {
        // System.out.println("1.Add Product\n2.Update Quantity\n3.Exit");
        // System.out.print("------>");
        // int ip = s.nextInt();
        // if (ip == 1) {
        // System.out.print("Enter Bill No.: ");
        // String bNo = s.nextInt() + "";
        // if (!c.containsBill(bNo)) {
        // System.out.println("Bill No. doesn't exist, Try Again...!");
        // continue;
        // }
        // System.out.print("Enter Product ID: ");
        // String pID = s.nextInt() + "";
        // if (!inventory.containsProduct(pID)) {
        // System.out.println("Product doesn't exist, Try Again...!");
        // continue;
        // }
        // System.out.print("Enter Quantity: ");
        // int q = s.nextInt();

        // c.updateBill(bNo, inventory.getProduct(pID), q);
        // } else if (ip == 2) {

        // } else if (ip == 3) {
        // break;
        // } else {
        // System.out.println("Wrong Input, Try Again...!");
        // }
        // }

    }

}
