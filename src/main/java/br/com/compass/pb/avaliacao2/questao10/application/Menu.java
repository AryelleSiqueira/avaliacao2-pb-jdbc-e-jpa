package br.com.compass.pb.avaliacao2.questao10.application;

import br.com.compass.pb.avaliacao2.questao10.model.EmployeeMood;
import br.com.compass.pb.avaliacao2.questao10.model.Mood;
import br.com.compass.pb.avaliacao2.questao10.service.EmployeeMoodService;
import br.com.compass.pb.avaliacao2.questao10.service.NoRegisterFoundException;
import br.com.compass.pb.avaliacao2.questao10.util.IOUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class Menu {

    private Menu() {}


    public static void menu(EmployeeMoodService service) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println(IOUtil.ANSI_GREEN + "==================================");
            System.out.println("\t(1) Type a message");
            System.out.println("\t(2) List registered moods");
            System.out.println("\t(3) " + IOUtil.ANSI_RED + "EXIT");
            System.out.println(IOUtil.ANSI_GREEN + "==================================" + IOUtil.ANSI_RESET);
            int option = IOUtil.readPositiveInteger(sc, "> ");

            switch (option) {
                case 1:
                    service.save(readEmployeeEntry(sc));
                    break;
                case 2:
                    try {
                        service.listAll().forEach(System.out::println);
                    }
                    catch (NoRegisterFoundException e) {
                        IOUtil.printErrorMsg(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    IOUtil.printErrorMsg("No such option!");
            }
        }
    }


    public static EmployeeMood readEmployeeEntry(Scanner sc) {
        System.out.print("What's your name? ");
        String name = sc.nextLine();

        System.out.printf("Please, tell us what's on your mind, %s:%n", name);
        String msg = sc.nextLine();

        return new EmployeeMood(name, getMoodFromMessage(msg));
    }


    public static Mood getMoodFromMessage(String msg) {
        int nHappyFaces = StringUtils.countMatches(msg, ":-)");
        int nSadFaces = StringUtils.countMatches(msg, ":-(");

        if (nHappyFaces > nSadFaces)  {
            return Mood.CHEERFUL;
        }
        if (nHappyFaces < nSadFaces) {
            return Mood.UPSET;
        }
        return Mood.NEUTRAL;
    }
}
