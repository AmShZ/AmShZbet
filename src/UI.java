import java.io.*;
import java.util.*;

public class UI {
    static Scanner scanner = new Scanner(System.in);
    static void start() {
        System.out.println("");
        System.out.println("Press 1 to login");
        System.out.println("Press 2 to login as an administrator");
        System.out.println("Press Q to exit");
        System.out.println("");
        System.out.print("Enter your input: ");
        String inp = scanner.nextLine();
        if (inp.equals("1")) {
            Main.start();
            start();
            return;
        }
        else if (inp.equals("2")) {
            Admin.start();
            start();
            return;
        }
        else if (inp.equals("Q")){
            return;
        }
        else {
            System.out.println("Invalid input!");
            start();
        }
    }
    public static void main(String[] args) {
        Data.recoverData();
        start();
    }
}