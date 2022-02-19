package br.com.compass.pb.avaliacao2.questao09.application;

import br.com.compass.pb.avaliacao2.questao09.connection.ConnectionFailedException;
import br.com.compass.pb.avaliacao2.questao09.controller.ProductController;
import br.com.compass.pb.avaliacao2.questao09.service.NoProductsFoundException;
import br.com.compass.pb.avaliacao2.questao09.service.ProductAlreadyExistsException;
import br.com.compass.pb.avaliacao2.questao09.model.NegativeIDException;
import br.com.compass.pb.avaliacao2.questao09.model.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private Menu() {}


    public static void menu(ProductController pc) {
        Scanner fileSc = null;
        try {
            fileSc = new Scanner(new File("src/main/resources/products.csv"));
            fileSc.nextLine();
        }
        catch (FileNotFoundException e) {
            IOUtil.printErrorMsg("Could not open file");
        }

        Scanner sc = new Scanner(System.in);

        while (true) {
            IOUtil.printMenu();
            int chosenOption = IOUtil.readPositiveInteger(sc, "> ");

            switch (chosenOption) {
                case 1:
                    register(pc, sc, fileSc);
                    break;
                case 2:
                    update(pc, sc);
                    break;
                case 3:
                    delete(pc, sc);
                    break;
                case 4:
                    search(pc, sc);
                    break;
                case 5:
                    listAll(pc);
                    break;
                case 0:
                    sc.close();
                    if (fileSc != null) {
                        fileSc.close();
                    }
                    return;
                default:
                    IOUtil.printErrorMsg("No such option!");
            }
        }
    }


    public static void register(ProductController pc, Scanner sc, Scanner fileSc) {
       try {
            pc.save(IOUtil.readProduct(sc));

            if (fileSc != null) {
                List<Product> productList = IOUtil.readProductsFile(fileSc, 3);
                productList.forEach(pc::save);
            }

            System.out.println("Product registered!");
        }
        catch (ProductAlreadyExistsException | ConnectionFailedException e) {
            IOUtil.printErrorMsg(e.getMessage());
        }
    }


    public static void update(ProductController pc, Scanner sc) {
        try {
            int id = IOUtil.readPositiveInteger(sc, "Enter sale's ID: ");
            Product product = IOUtil.readProduct(sc);
            product.setId(id);

            pc.update(product);
            System.out.println("Product #" + id +" updated!");
        }
        catch (NumberFormatException e) {
            IOUtil.printErrorMsg("Not a valid input! Try again");
        }
        catch (NegativeIDException | ConnectionFailedException e) {
            IOUtil.printErrorMsg(e.getMessage() + "Try again");
        }
    }


    public static void search(ProductController pc, Scanner sc) {
        System.out.print("Search for: ");
        String line = sc.nextLine();

        try {
            List<Product> productList = pc.search(line);
            productList.forEach(System.out::println);
        }
        catch (ConnectionFailedException | NoProductsFoundException e) {
            IOUtil.printErrorMsg(e.getMessage());
        }
    }


    public static void delete(ProductController pc, Scanner sc) {
        try {
            int id = IOUtil.readPositiveInteger(sc, "Enter sale's ID: ");
            pc.delete(id);
            System.out.println("Product #" + id +" deleted!");
        }
        catch (InputMismatchException e) {
            IOUtil.printErrorMsg("Not a valid input! Try again");
        }
        catch (ConnectionFailedException | NoProductsFoundException e) {
            IOUtil.printErrorMsg(e.getMessage());
        }
    }


    public static void listAll(ProductController pc) {
        try {
            List<Product> productList = pc.listAll();
            productList.forEach(System.out::println);
        }
        catch (ConnectionFailedException | NoProductsFoundException e) {
            IOUtil.printErrorMsg(e.getMessage());
        }
    }

}
