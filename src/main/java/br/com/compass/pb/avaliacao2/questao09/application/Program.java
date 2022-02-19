package br.com.compass.pb.avaliacao2.questao09.application;

import br.com.compass.pb.avaliacao2.questao09.connection.ConnectionFailedException;
import br.com.compass.pb.avaliacao2.questao09.controller.ProductController;

public class Program {

    public static void main(String[] args) {

        try (ProductController pc = new ProductController()) {
            Menu.menu(pc);
            System.out.println("Closing...");
        }
        catch (ConnectionFailedException e) {
            System.err.println(e.getMessage());
        }
    }
}
