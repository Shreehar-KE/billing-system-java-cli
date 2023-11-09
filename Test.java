import java.util.Scanner;

public class Test {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        Inventory test = new Inventory();
        test.addProduct("pencil", "1000", 3, 50, 100);
        test.addProduct("pen", "1001", 2, 500, 200);
        test.addProduct("eraser", "1002", 1, 20, 300);
        test.displayInventory();
        System.out.println(test.getProduct("1000"));
        test.updateStock("1000", 200);
        test.updateStock("10011", 200);
        test.deleteProduct("1002");
        test.deleteProduct("100011");
        test.displayInventory();
        s.close();
    }
}
