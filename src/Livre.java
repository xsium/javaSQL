import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Livre {
    private String titre;
    private String description;
    private String datePublication;
    private ArrayList<String> genre;

    Livre(){
        this.titre="";
        this.description="";
        this.datePublication="";
        this.genre= new ArrayList<String>();
    }

    Livre(String titre, String description, String datePublication, ArrayList<String> genre){
        this.titre=titre;
        this.description=description;
        this.datePublication=datePublication;
        this.genre= genre;
    }

    public String getTitre() {
        return this.titre;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDatePublication() {
        return this.datePublication;
    }

    public ArrayList<String> getGenre() {
        return this.genre;
    }
    public String getGenreAsString() {
        String listGenre= "";
        for(int i=0; i<this.getGenre().size();i++){
            listGenre= listGenre +this.getGenre().get(i)+";";
        }
        return listGenre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }
    public void setGenreFromString(String genreString) {
        if (genreString != null && !genreString.isEmpty()) {
            String[] genres = genreString.split(";");
            ArrayList<String> genreList = new ArrayList<String>(Arrays.asList(genres));
            this.setGenre(genreList);
        }
    }

    public void displayLivreTitre(){
        System.out.println("Title: "+ this.titre);
    }
    public void displayLivre(){
        System.out.println("-------------------------------------------------");
        System.out.println("Title: "+ this.titre);
        System.out.println("Description: "+this.description);
        System.out.println("Date of publication: "+this.datePublication);
        System.out.println("Genres:");
        for(int i=0; i<this.genre.size();i++){
            System.out.println(i+1+"- "+this.genre.get(i));
        }
        System.out.println("-------------------------------------------------");
    }
    public static void addLivre(ArrayList<Livre> Library, Scanner sc){
        System.out.println("Enter the title:");
        String title= sc.nextLine();
        System.out.println("Entrer the description:");
        String desc= sc.nextLine();
        System.out.println("Enter the publication date :");
        String date= sc.nextLine();

        ArrayList<String> genres= new ArrayList<String>();
        boolean genreLoop=true;
        while(genreLoop){
            System.out.println("Enter the book's genre , use 'ok' to validate your entries,'reset' to clear all previous genres, 'cancel' to redo the last entry");
            String genre= sc.nextLine();
            switch (genre) {
                case "ok":
                    genreLoop=false;
                    break;

                case "reset":
                    genres.clear();
                    break;

                case "cancel":
                    genres.removeLast();
                    break;

                default:
                    genres.add(genre);
            }
        }
        Livre newBook=new Livre(title, desc, date, genres);
        Library.add(newBook);
        Requete.addLivreDB(newBook,Library);
    }
    public static void removeLivre(ArrayList<Livre> library, Scanner sc){
        boolean deleted=false;
        System.out.println("Enter the title of the book to remove:");
        String title= sc.nextLine();
        for(int i=0; i<library.size(); i++){
            if(library.get(i).getTitre().equals(title)){
                Requete.deleteLivreDBbyTitle(library.get(i),library);
                deleted=true;
            }
        }
        if(deleted){
            System.out.println(title+" has been terminated!");
        }
    }
    public static void findAll(ArrayList<Livre> library){
        for(int i=0; i<library.size();i++){
            library.get(i).displayLivreTitre();
        }
    }
    public static void findAndDisplay(ArrayList<Livre> library, Scanner sc){
        System.out.println("what's the title of the book you want to display?");
        boolean found=false;
        String title= sc.nextLine();
        for(int i=0; i<library.size();i++){
            if(library.get(i).getTitre().equals(title)){
                library.get(i).displayLivre();
                found=true;
            }
        }
        if(!found){
            System.out.println("Sorry, the book you asked for isn't available in our library.");
        }
    }
    public static void help(){
        System.out.println("-------------------------------------------------");
        System.out.println("available commands:");
        System.out.println("-------------------------------------------------");
        System.out.println("add : allow the user to manually add a book to the library by following the instructions.");
        System.out.println("remove : allow the user to manually remove a book from the library by giving the correct title when prompted to do so.");
        System.out.println("findAll : allow the user to display the title of all the books in the library.");
        System.out.println("display : allow the user to display all of the informations we have about a particular book. The user will be asked to enter the correct title.");
        System.out.println("quit : close the library application.");
        System.out.println("-------------------------------------------------");
    }
}