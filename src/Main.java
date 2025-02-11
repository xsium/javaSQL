import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            bibliotheque(scanner);
        }
        catch(InputMismatchException e){
            System.out.println("Invalid Format.");
        }
    }
    public static void bibliotheque(Scanner scanner) {
        ArrayList<Livre> cdaLibrary = new ArrayList<Livre>();
        boolean on = true;
        System.out.println("Welcome to our secret library");
        Requete.loadDB(cdaLibrary);

        while (on) {
            try {
                System.out.println("Awaiting your command, type help to get a list :");
                String query = scanner.nextLine();

                switch (query) {
                    case "add":
                        Livre.addLivre(cdaLibrary, scanner);
                        break;

                    case "remove":
                        Livre.removeLivre(cdaLibrary, scanner);
                        break;

                    case "findAll":
                        Livre.findAll(cdaLibrary);
                        break;
                    case "display":
                        Livre.findAndDisplay(cdaLibrary, scanner);
                        break;
                    case "help":
                        Livre.help();
                        break;
                    case "quit":
                        on=false;
                        break;
                    default:
                        System.out.println("command invalid");
                }
            } catch (InputMismatchException e) {
                throw new InputMismatchException("please, respect the books");
            }
        }
        System.out.println("Thanks for visiting our library!");
    }

}