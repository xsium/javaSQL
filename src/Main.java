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
        String cat="        へ     ♡  ╱|、\n     ૮ - ՛ )    (`- 7\n      / ⁻ ៸|      |、⁻〵\n   乀(ˍ,ل ل      じしˍ,)ノ";
        System.out.println("Welcome to our secret library");
        System.out.println(cat);

        Requete.loadDB(cdaLibrary);

        while (on) {
            try {
                System.out.println("Awaiting your command, type help to get the list of commands :");
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
                    case "cat":
                        System.out.println(cat);
                        break;
                    default:
                        System.out.println("invalid command");
                }
            } catch (InputMismatchException e) {
                throw new InputMismatchException("please, respect the books");
            }
        }
        System.out.println("Thanks for visiting our library!");
    }

}