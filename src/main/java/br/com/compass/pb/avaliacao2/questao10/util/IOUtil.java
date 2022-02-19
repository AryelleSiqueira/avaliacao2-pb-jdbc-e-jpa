package br.com.compass.pb.avaliacao2.questao10.util;

import br.com.compass.pb.avaliacao2.questao09.model.Product;

import java.sql.Date;
import java.util.*;

public class IOUtil {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";


    private IOUtil() {}


    public static int readPositiveInteger(Scanner sc, String msg) {
        Integer value = null;

        while (value == null) {
            System.out.print(msg);
            try {
                value = sc.nextInt();
                if (value < 0) {
                    value = null;
                    printErrorMsg("Not a valid input! Try again");
                }
            }
            catch (InputMismatchException e) {
                printErrorMsg("Not a valid input! Try again");
            }
            sc.nextLine();
        }
        return value;
    }


    public static void printErrorMsg(String msg) {
        System.out.println(ANSI_RED + msg + ANSI_RESET);
    }

}
