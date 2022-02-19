package br.com.compass.pb.avaliacao2.questao09.application;

import br.com.compass.pb.avaliacao2.questao09.model.Product;

import java.sql.Date;
import java.util.*;

public class IOUtil {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";


    private IOUtil() {}


    public static void printMenu() {
        System.out.println(ANSI_GREEN + "============== Shop ==============");
        System.out.println("Choose an option:");
        System.out.println("\t1) ADD a new sale");
        System.out.println("\t2) UPDATE a sale");
        System.out.println("\t3) DELETE a sale");
        System.out.println("\t4) Search for sales");
        System.out.println("\t5) LIST all sales");
        System.out.println("\t0) " + ANSI_RED + "EXIT");
        System.out.println(ANSI_GREEN + "==================================" + ANSI_RESET);
    }


    public static int readPositiveInteger(Scanner sc, String msg) {
        Integer value = null;

        while (value == null) {
            System.out.print(msg);
            try {
                value = sc.nextInt();
                if (value < 0) {
                    IOUtil.printErrorMsg("Cannot be < 0");
                    value = null;
                }
            }
            catch (InputMismatchException e) {
                printErrorMsg("Not a valid input! Try again");
            }
            sc.nextLine();
        }
        return value;
    }


    public static Product readProduct(Scanner sc) {
        System.out.print("Product name: ");
        String name = sc.nextLine();
        System.out.print("Description: ");
        String description = sc.nextLine();
        float price = readFloat(sc, "Price: ");
        float discount = readFloat(sc, "Discount (%): ") / 100;
        Date date = readDate(sc);

        return new Product(name, description, price, discount, date);
    }


    public static float readFloat(Scanner sc, String msg) {
        Float value = null;
        while (value == null) {
            System.out.print(msg);
            try {
                value = sc.nextFloat();

                if (value < 0) {
                    IOUtil.printErrorMsg("Value cannot be < 0");
                    value = null;
                }
            }
            catch (InputMismatchException e) {
                printErrorMsg("Not a valid input! Try again");
            }
            sc.nextLine();
        }
        return value;
    }


    private static Date readDate(Scanner sc) {
        Date date = null;
        while (date == null) {
            System.out.print("Start date (YYYY-MM-DD): ");
            try {
                date = Date.valueOf(sc.nextLine());
            }
            catch (IllegalArgumentException e) {
                printErrorMsg("Not a valid input! Try again");
            }
        }
        return date;
    }

    public static List<Product> readProductsFile(Scanner fileScanner, int nProducts) {
        List<Product> productList = new LinkedList<>();

        while (fileScanner.hasNextLine() && nProducts > 0) {
            Scanner lsc = new Scanner(fileScanner.nextLine());
            lsc.useLocale(Locale.US);
            lsc.useDelimiter(",");

            String name = lsc.next();
            String description = lsc.next();
            float price = lsc.nextFloat();
            float discount = lsc.nextFloat();
            Date date = Date.valueOf(lsc.next());

            productList.add(new Product(name, description, price, discount, date));
            nProducts--;
            lsc.close();
        }
        return  productList;
    }

    public static void printErrorMsg(String msg) {
        System.out.println(ANSI_RED + msg + ANSI_RESET);
    }

}
